package com.tinqin.libraryv2.book.core.processors;

import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookGoogleBooksModel;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookOpenLibModel;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksgooglebooks.GetAuthorBooksGoogleBooks;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksgooglebooks.ProcessorGetAuthorBooksGoogleBooksInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksgooglebooks.ProcessorGetAuthorBooksGoogleBooksOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksopenlib.ProcessorGetAuthorBooksOpenLibOutput;
import com.tinqin.libraryv2.book.core.errorhandler.base.ErrorHandler;
import com.tinqin.libraryv2.book.core.errorhandler.exceptions.NotFoundException;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.GoogleVolume;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.OpenLibraryVolume;
import com.tinqin.libraryv2.book.domain.clients.externalServices.services.GoogleSearchBook;
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
public class GetAuthorBooksGoogleBooksProcessor implements GetAuthorBooksGoogleBooks {

    private final AuthorRepository authorRepository;
    private final ConversionService conversionService;
    private final ErrorHandler errorHandler;
    private final GoogleSearchBook googleSearchBook;

    @Override
    public Either<OperationError, ProcessorGetAuthorBooksGoogleBooksOutput> process(ProcessorGetAuthorBooksGoogleBooksInput input) {
        return fetchAuthor(input)
                .flatMap(author -> getBooks(author,input.getPage()))
                .toEither()
                .mapLeft(errorHandler::handle);
    }

    private Try<Author> fetchAuthor(ProcessorGetAuthorBooksGoogleBooksInput input) {
        return Try.of(() -> authorRepository.findById(UUID.fromString(input.getAuthorId()))
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND)));
    }
    private Try<ProcessorGetAuthorBooksGoogleBooksOutput> getBooks(Author authorEntity, Integer page) {
        return Try.of(() -> {
            List<GoogleVolume>  googleVolumes = googleSearchBook
                    .searchBooksByAuthor(authorEntity.getFullName(),page );
            return ProcessorGetAuthorBooksGoogleBooksOutput
                    .builder()
                    .authorId(authorEntity.getId())
                    .googleBooksBooks(
                            googleVolumes
                                    .stream()
                                    .map(googleVolume -> conversionService.convert(googleVolume, ProcessorBookGoogleBooksModel.class))
                                    .toList()
                    )
                    .build();
        });
    }
}
