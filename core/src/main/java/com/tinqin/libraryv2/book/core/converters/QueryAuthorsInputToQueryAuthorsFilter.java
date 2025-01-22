package com.tinqin.libraryv2.book.core.converters;



import com.tinqin.libraryv2.book.apiadapter.operations.queryauthors.ProcessorQueryAuthorsInput;
import com.tinqin.libraryv2.book.core.queries.filters.QueryAuthorFilter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class QueryAuthorsInputToQueryAuthorsFilter implements Converter<ProcessorQueryAuthorsInput, QueryAuthorFilter> {
    @Override
    public QueryAuthorFilter convert(ProcessorQueryAuthorsInput source) {
        return QueryAuthorFilter
                .builder()
                .authorFirstName(source.getFirstName())
                .authorLastName(source.getLastName())
                .authorFirstNameLike(source.getFirstNameLike())
                .authorLastNameLike(source.getLastNameLike())
                 .build();
    }
}
