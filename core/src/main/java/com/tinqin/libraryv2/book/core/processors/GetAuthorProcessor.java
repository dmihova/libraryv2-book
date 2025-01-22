package com.tinqin.libraryv2.book.core.processors;

import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorAuthorModel;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthor.GetAuthor;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthor.ProcessorGetAuthorInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthor.ProcessorGetAuthorOutput;
import com.tinqin.libraryv2.book.core.errorhandler.base.ErrorHandler;
import com.tinqin.libraryv2.book.core.errorhandler.exceptions.NotFoundException;
import com.tinqin.libraryv2.book.persistence.models.Author;
import com.tinqin.libraryv2.book.persistence.repositories.AuthorRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.tinqin.libraryv2.book.core.Messages.AUTHOR_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class GetAuthorProcessor implements GetAuthor {
    private final AuthorRepository authorRepository;
    private final ConversionService conversionService;
    private final ErrorHandler errorHandler;

    @Override
    public Either<OperationError, ProcessorGetAuthorOutput> process(ProcessorGetAuthorInput input) {
        return fetchAuthor(input)
                .map(author -> ProcessorGetAuthorOutput
                        .builder()
                        .author(conversionService.convert(author, ProcessorAuthorModel.class))
                        .build())
                .toEither()
                .mapLeft(errorHandler::handle);
    }

    private Try<Author> fetchAuthor(ProcessorGetAuthorInput input) {
        return Try.of(() -> authorRepository.findById(UUID.fromString(input.getAuthorId()))
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND)));
    }
}
