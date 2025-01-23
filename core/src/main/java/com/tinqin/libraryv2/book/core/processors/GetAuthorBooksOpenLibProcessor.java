package com.tinqin.libraryv2.book.core.processors;

import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookOpenLibModel;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksopenlib.GetAuthorBooksOpenLib;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksopenlib.ProcessorGetAuthorBooksOpenLibInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksopenlib.ProcessorGetAuthorBooksOpenLibOutput;
import com.tinqin.libraryv2.book.core.errorhandler.base.ErrorHandler;
import com.tinqin.libraryv2.book.core.errorhandler.exceptions.NotFoundException;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.OpenLibraryDoc;
import com.tinqin.libraryv2.book.domain.clients.externalServices.services.OpenLibrarySearchBook;
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
public class GetAuthorBooksOpenLibProcessor implements GetAuthorBooksOpenLib {
    private final AuthorRepository authorRepository;
    private final ConversionService conversionService;
    private final ErrorHandler errorHandler;
    private final OpenLibrarySearchBook openLibrarySearchBook;

    @Override
    public Either<OperationError, ProcessorGetAuthorBooksOpenLibOutput> process(ProcessorGetAuthorBooksOpenLibInput input) {
        return fetchAuthor(input)
                .flatMap(author -> getBookOpenLib(author, input.getPage()))
                .toEither()
                .mapLeft(errorHandler::handle);
    }

    private Try<Author> fetchAuthor(ProcessorGetAuthorBooksOpenLibInput input) {
        return Try.of(() -> authorRepository.findById(UUID.fromString(input.getAuthorId()))
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND)));
    }


    private Try<ProcessorGetAuthorBooksOpenLibOutput> getBookOpenLib(Author authorEntity, Integer page) {
        return Try.of(() -> {
            List<OpenLibraryDoc> openLibraryDocs = openLibrarySearchBook
                    .searchBooksByAuthor(authorEntity.getFullName(), 1);
            return ProcessorGetAuthorBooksOpenLibOutput
                    .builder()
                    .authorId(authorEntity.getId())
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
