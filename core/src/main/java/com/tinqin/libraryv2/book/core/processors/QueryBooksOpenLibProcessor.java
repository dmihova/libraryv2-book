package com.tinqin.libraryv2.book.core.processors;

import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookOpenLibModel;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooksopenlib.ProcessorQueryBooksOpenLibInput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooksopenlib.ProcessorQueryBooksOpenLibOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooksopenlib.QueryBooksOpenLib;
import com.tinqin.libraryv2.book.core.errorhandler.base.ErrorHandler;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.OpenLibraryVolume;
import com.tinqin.libraryv2.book.domain.clients.externalServices.services.OpenLibrarySearchBook;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private Try<List<OpenLibraryVolume>> fetchBooks(ProcessorQueryBooksOpenLibInput input) {
        return Try.of(() ->
                openLibrarySearchBook
                         .searchBook(input.getTitle(), input.getAuthor(), input.getPage())
        );

    }


    private Try<ProcessorQueryBooksOpenLibOutput> fetchBook(ProcessorQueryBooksOpenLibInput input) {
        return Try.of(() -> {

            List<OpenLibraryVolume> openLibraryDocs = openLibrarySearchBook
                    .searchBook(input.getTitle(), input.getAuthor(), input.getPage());

            return ProcessorQueryBooksOpenLibOutput
                    .builder()
                    .openLibBooks(
                            openLibraryDocs
                                    .stream()
                                    .map(openLibraryDoc -> conversionService.convert(openLibraryDoc, ProcessorBookOpenLibModel.class))
                                    .toList()
                    )
                    .build();
        });
    }
}
