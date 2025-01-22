package com.tinqin.libraryv2.book.core.processors;

import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorAuthorBaseModel;
import com.tinqin.libraryv2.book.apiadapter.operations.queryauthors.ProcessorQueryAuthorsInput;
import com.tinqin.libraryv2.book.apiadapter.operations.queryauthors.ProcessorQueryAuthorsOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.queryauthors.QueryAuthors;
import com.tinqin.libraryv2.book.core.errorhandler.base.ErrorHandler;
import com.tinqin.libraryv2.book.core.queries.filters.QueryAuthorFilter;
import com.tinqin.libraryv2.book.core.queries.specifications.AuthorSpecification;
import com.tinqin.libraryv2.book.persistence.models.Author;
import com.tinqin.libraryv2.book.persistence.repositories.AuthorRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryAuthorsProcessor implements QueryAuthors {
    private final AuthorRepository authorRepository;
    private final ConversionService conversionService;
    private final ErrorHandler errorHandler;

    @Override
    public Either<OperationError, ProcessorQueryAuthorsOutput> process(ProcessorQueryAuthorsInput input) {
        return fetchAuthors(input)
                .map(entityAuthors -> ProcessorQueryAuthorsOutput
                        .builder()
                        .authors(
                                entityAuthors
                                        .stream()
                                        .map(entityAuthor -> conversionService.convert(entityAuthor, ProcessorAuthorBaseModel.class))
                                        .toList()
                        )
                        .build())
                .toEither()
                .mapLeft(errorHandler::handle);
    }

    private Try<List<Author>> fetchAuthors(ProcessorQueryAuthorsInput input) {
        return Try.of(() -> {
            QueryAuthorFilter filter = conversionService.convert(input, QueryAuthorFilter.class);
            Specification<Author> specification = AuthorSpecification.getSpecification(filter);
            Pageable paging = PageRequest.of(input.getPage(),
                    20,
                    Sort.by("lastName", "firstName").ascending());

            return authorRepository.findAll(specification, paging);
        });
    }


}
