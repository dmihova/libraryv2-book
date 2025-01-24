package com.tinqin.libraryv2.book.core.processors;

import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookAlgoLibModel;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooksalgolib.ProcessorQueryBooksAlgoLibInput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooksalgolib.ProcessorQueryBooksAlgoLibOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooksalgolib.QueryBooksAlgoLib;
import com.tinqin.libraryv2.book.core.errorhandler.base.ErrorHandler;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.AlgoBookVolume;
import com.tinqin.libraryv2.book.domain.clients.externalServices.services.AlgoSearchBook;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class QueryBooksAlgoLibProcessor implements QueryBooksAlgoLib {
    private final ConversionService conversionService;
    private final ErrorHandler errorHandler;
    private final AlgoSearchBook algoSearchBook;

    @Override
    public Either<OperationError, ProcessorQueryBooksAlgoLibOutput> process(ProcessorQueryBooksAlgoLibInput input) {
        return fetchBooks(input)
                .flatMap(this::formatToProcessOutput)
                .toEither()
                .mapLeft(errorHandler::handle);
    }


    private Try<List<AlgoBookVolume>> fetchBooks(ProcessorQueryBooksAlgoLibInput input) {
        return Try.of(() ->
                algoSearchBook.searchTitle(input.getTitle())
                        .stream()
                        .filter(algoBookVolume -> verifyAuthor(algoBookVolume, input))
                        .toList()
        );
    }

    private boolean verifyAuthor(AlgoBookVolume algoBookVolume, ProcessorQueryBooksAlgoLibInput input) {
        if ((input.getAuthor() == null || input.getAuthor().isBlank()) &
                (input.getAuthorLike() == null || input.getAuthorLike().isBlank())) {
            return true;
        }
        String[] authors = algoBookVolume.getAuthors();
        if (authors == null || authors.length == 0) {
            return false;
        }
        Set<String> authorSet = new HashSet<>(
                Arrays.stream(authors)
                        .map(String::toLowerCase)
                        .toList());


        if (input.getAuthor() != null && !input.getAuthor().isBlank()) {
             return authorSet.contains(input.getAuthor().toLowerCase());
        }

        if (input.getAuthorLike() != null && !input.getAuthorLike().isBlank()) {
            return authorSet
                    .stream()
                    .filter(algoAuthor -> algoAuthor.contains(input.getAuthorLike().toLowerCase()))
                    .findFirst()
                    .orElse(null) != null;
        }

        return true;
    }

    private Try<ProcessorQueryBooksAlgoLibOutput> formatToProcessOutput(List<AlgoBookVolume> books) {

        return Try.of(() -> ProcessorQueryBooksAlgoLibOutput
                .builder()
                .booksAlgoLib(
                        books
                                .stream()
                                .map(book -> conversionService.convert(book, ProcessorBookAlgoLibModel.class))
                                .toList()
                )
                .build()
        );

    }

}
