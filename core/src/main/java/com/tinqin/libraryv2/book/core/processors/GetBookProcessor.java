package com.tinqin.libraryv2.book.core.processors;

import com.tinqin.libraryv2.book.apiadapter.errors.BeError;
import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookModel;
import com.tinqin.libraryv2.book.apiadapter.operations.getbook.GetBook;
import com.tinqin.libraryv2.book.apiadapter.operations.getbook.ProcessorGetBookInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getbook.ProcessorGetBookOutput;
import com.tinqin.libraryv2.book.core.errorhandler.base.ErrorHandler;
import com.tinqin.libraryv2.book.core.errorhandler.exceptions.NotFoundException;
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
public class GetBookProcessor implements GetBook  {
    private final BookRepository bookRepository;
    private final ConversionService conversionService;
    private final ErrorHandler errorHandler;

    @Override
    public Either<OperationError, ProcessorGetBookOutput> process(ProcessorGetBookInput input) {
        return fetchBook(input)
                .map(book ->  ProcessorGetBookOutput
                                 .builder()
                                 .book(conversionService.convert(book,ProcessorBookModel.class))
                                 .build())
                .toEither()
                .mapLeft(errorHandler::handle );
    }

    private Try<Book> fetchBook(ProcessorGetBookInput input) {
        return Try.of(() -> bookRepository.findById(UUID.fromString(input.getBookId()))
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND)));
    }
}
