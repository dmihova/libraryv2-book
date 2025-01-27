package com.tinqin.libraryv2.book.core.processors;

import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookOpenLibModel;
import com.tinqin.libraryv2.book.apiadapter.operations.getbookopenlibfirst.GetBookOpenLibFirst;
import com.tinqin.libraryv2.book.apiadapter.operations.getbookopenlibfirst.ProcessorGetBookOpenLibFirstInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getbookopenlibfirst.ProcessorGetBookOpenLibFirstOutput;
import com.tinqin.libraryv2.book.core.errorhandler.base.ErrorHandler;
import com.tinqin.libraryv2.book.core.errorhandler.exceptions.NotFoundException;
import com.tinqin.libraryv2.book.core.errorhandler.exceptions.OpenLibException;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.OpenLibraryVolume;
import com.tinqin.libraryv2.book.domain.clients.externalServices.services.OpenLibrarySearchBook;
import com.tinqin.libraryv2.book.persistence.models.Author;
import com.tinqin.libraryv2.book.persistence.models.Book;
import com.tinqin.libraryv2.book.persistence.repositories.BookRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.tinqin.libraryv2.book.core.Messages.BOOK_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class GetBookOpenLibFirstProcessor implements GetBookOpenLibFirst {
    private final BookRepository bookRepository;
    private final ConversionService conversionService;
    private final ErrorHandler errorHandler;
    private final OpenLibrarySearchBook openLibrarySearchBook;

    @Override
    public Either<OperationError, ProcessorGetBookOpenLibFirstOutput> process(ProcessorGetBookOpenLibFirstInput input) {
        return fetchBook(input)
                .flatMap(this::getBookOpenLib)
                .toEither()
                .mapLeft(errorHandler::handle );
    }

    private Try<ProcessorGetBookOpenLibFirstOutput> getBookOpenLib(Book bookEntity) {
        return Try.of(() -> {
            String author = bookEntity
                    .getAuthors()
                    .stream()
                    .map(Author::getFullName)
                    .findFirst().orElse("");
            OpenLibraryVolume openLibraryDoc = openLibrarySearchBook
                    .getFirstBook( bookEntity.getTitle(),author)
                    .orElseThrow(() -> new OpenLibException(BOOK_NOT_FOUND));

              return ProcessorGetBookOpenLibFirstOutput
                      .builder()
                      .bookId(bookEntity.getId())
                      .openLibBook( conversionService.convert(openLibraryDoc, ProcessorBookOpenLibModel.class))
                      .build();
             });



    }

    private Try<Book> fetchBook(ProcessorGetBookOpenLibFirstInput input) {
        return Try.of(() -> bookRepository.findById(UUID.fromString(input.getBookId()))
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND)));
    }
}
