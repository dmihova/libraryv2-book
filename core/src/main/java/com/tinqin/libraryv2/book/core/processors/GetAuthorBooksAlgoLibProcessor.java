package com.tinqin.libraryv2.book.core.processors;

import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookAlgoLibModel;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksalgolib.GetAuthorBooksAlgoLib;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksalgolib.ProcessorGetAuthorBooksAlgoLibInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksalgolib.ProcessorGetAuthorBooksAlgoLibOutput;
import com.tinqin.libraryv2.book.core.errorhandler.base.ErrorHandler;
import com.tinqin.libraryv2.book.core.errorhandler.exceptions.NotFoundException;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.AlgoBookVolume;
import com.tinqin.libraryv2.book.domain.clients.externalServices.services.AlgoSearchBook;
import com.tinqin.libraryv2.book.persistence.models.Author;
import com.tinqin.libraryv2.book.persistence.repositories.AuthorRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.tinqin.libraryv2.book.core.Messages.AUTHOR_NOT_FOUND;

@Component
@RequiredArgsConstructor

public class GetAuthorBooksAlgoLibProcessor implements GetAuthorBooksAlgoLib {
    private final AuthorRepository authorRepository;
    private final ConversionService conversionService;
    private final ErrorHandler errorHandler;
    private final AlgoSearchBook algoSearchBook;

    @Override
    public Either<OperationError, ProcessorGetAuthorBooksAlgoLibOutput> process(ProcessorGetAuthorBooksAlgoLibInput input) {
        return fetchAuthor(input)
                .flatMap(this::getBooks)
                .toEither()
                .mapLeft(errorHandler::handle);
    }

    private Try<Author> fetchAuthor(ProcessorGetAuthorBooksAlgoLibInput input) {
        return Try.of(() -> authorRepository.findById(UUID.fromString(input.getAuthorId()))
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND)));
    }

    private Try<ProcessorGetAuthorBooksAlgoLibOutput> getBooks(Author authorEntity) {
        return Try.of(() -> {
            List<AlgoBookVolume> books = algoSearchBook
                    .searchByAuthor(authorEntity.getFullName());
            return ProcessorGetAuthorBooksAlgoLibOutput
                    .builder()
                    .authorId(authorEntity.getId())
                    .openLibBooks(
                            books
                                    .stream()
                                    .map(openLibraryDoc -> conversionService.convert(books, ProcessorBookAlgoLibModel.class))
                                    .toList()
                    )
                    .build();
        });
    }

}
