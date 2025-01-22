package com.tinqin.libraryv2.book.core.processors;

import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookModel;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooks.ProcessorQueryBooksOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooks.ProcessorQueryBooksInput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooks.QueryBooks;
import com.tinqin.libraryv2.book.core.errorhandler.base.ErrorHandler;
import com.tinqin.libraryv2.book.core.queries.filters.QueryBookFilter;
import com.tinqin.libraryv2.book.core.queries.specifications.BookSpecification;
import com.tinqin.libraryv2.book.persistence.models.Book;
import com.tinqin.libraryv2.book.persistence.repositories.BookRepository;
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
public class QueryBooksProcessor implements QueryBooks {
    private final BookRepository bookRepository;
    private final ConversionService conversionService;
    private final ErrorHandler errorHandler;

    @Override
    public Either<OperationError, ProcessorQueryBooksOutput> process(ProcessorQueryBooksInput input) {
        return fetchBooks(input)
                .map(entityBooks -> ProcessorQueryBooksOutput
                        .builder()
                        .books(
                                entityBooks
                                        .stream()
                                        .map(entityBook -> conversionService.convert(entityBook, ProcessorBookModel.class))
                                        .toList()
                        )
                        .build())
                .toEither()
                .mapLeft(errorHandler::handle);
    }

    private Try<List<Book>> fetchBooks(ProcessorQueryBooksInput input) {
        return Try.of(() -> {
            QueryBookFilter filter = conversionService.convert(input, QueryBookFilter.class);
            Specification<Book> specification = BookSpecification.getSpecification(filter);
            Pageable paging = PageRequest.of(input.getPage(), 20, Sort.by("title"));
            return bookRepository.findAll(specification, paging);
        });
    }


}
