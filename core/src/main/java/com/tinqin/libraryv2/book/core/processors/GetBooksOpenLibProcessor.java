package com.tinqin.libraryv2.book.core.processors;

import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookOpenLibModel;
import com.tinqin.libraryv2.book.apiadapter.operations.getbooksopenlib.GetBooksOpenLib;
import com.tinqin.libraryv2.book.apiadapter.operations.getbooksopenlib.ProcessorGetBooksOpenLibInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getbooksopenlib.ProcessorGetBooksOpenLibOutput;
import com.tinqin.libraryv2.book.core.errorhandler.base.ErrorHandler;
import com.tinqin.libraryv2.book.core.errorhandler.exceptions.NotFoundException;
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

import java.util.List;
import java.util.UUID;

import static com.tinqin.libraryv2.book.core.Messages.BOOK_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class GetBooksOpenLibProcessor implements GetBooksOpenLib {
    private final BookRepository bookRepository;
    private final ConversionService conversionService;
    private final ErrorHandler errorHandler;
    private final OpenLibrarySearchBook openLibrarySearchBook;

    @Override
    public Either<OperationError, ProcessorGetBooksOpenLibOutput> process(ProcessorGetBooksOpenLibInput input) {
        return fetchBook(input)
                .flatMap(this::getBookOpenLib)
                .toEither()
                .mapLeft(errorHandler::handle);
    }

    private Try<ProcessorGetBooksOpenLibOutput> getBookOpenLib(Book bookEntity) {
        return Try.of(() -> {
            String author = bookEntity
                    .getAuthors()
                    .stream()
                    .map(Author::getFullName)
                    .findFirst().orElse("");
            List<OpenLibraryVolume> openLibraryDocs = openLibrarySearchBook
                    .searchBook(bookEntity.getTitle(), author);

            return ProcessorGetBooksOpenLibOutput
                    .builder()
                    .bookId(bookEntity.getId())
                    .openLibBooks(
                            openLibraryDocs
                                    .stream()
                                    .map(openLibraryDoc -> conversionService.convert(openLibraryDoc, ProcessorBookOpenLibModel.class))
                                    .toList()
                    )
                    .build();
        });
    }

    private Try<Book> fetchBook(ProcessorGetBooksOpenLibInput input) {
        return Try.of(() -> bookRepository.findById(UUID.fromString(input.getBookId()))
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND)));
    }
}
