package com.tinqin.libraryv2.book.core.processors;

import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookOpenLibModel;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooksopenlib.ProcessorQueryBooksOpenLibInput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooksopenlib.ProcessorQueryBooksOpenLibOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooksopenlib.QueryBooksOpenLib;
import com.tinqin.libraryv2.book.core.errorhandler.base.ErrorHandler;
import com.tinqin.libraryv2.book.core.errorhandler.exceptions.OpenLibException;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.OpenLibraryDoc;
import com.tinqin.libraryv2.book.domain.clients.externalServices.services.OpenLibrarySearchBook;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QueryBooksOpenLibProcessor implements QueryBooksOpenLib {
    private final ConversionService conversionService;
    private final ErrorHandler errorHandler;
    private final OpenLibrarySearchBook openLibrarySearchBook;

    @Override
    public Either<OperationError, ProcessorQueryBooksOpenLibOutput> process(ProcessorQueryBooksOpenLibInput input) {
        return fetchBook(input)
                .toEither()
                .mapLeft(errorHandler::handle);
    }

    private Try<List<OpenLibraryDoc>> fetchBooks(ProcessorQueryBooksOpenLibInput input) {
        return Try.of(() ->
                openLibrarySearchBook
                         .searchBook(input.getTitle(), input.getAuthor(), input.getPage())
        );

    }


    private Try<ProcessorQueryBooksOpenLibOutput> fetchBook(ProcessorQueryBooksOpenLibInput input) {
        return Try.of(() -> {

            List<OpenLibraryDoc> openLibraryDocs = openLibrarySearchBook
                    .searchBook(input.getTitle(), input.getAuthor(), input.getPage());

            return ProcessorQueryBooksOpenLibOutput
                    .builder()
                    .booksOpenLib(
                            openLibraryDocs
                                    .stream()
                                    .map(openLibraryDoc -> conversionService.convert(openLibraryDoc, ProcessorBookOpenLibModel.class))
                                    .toList()
                    )
                    .build();
        });
    }
}
