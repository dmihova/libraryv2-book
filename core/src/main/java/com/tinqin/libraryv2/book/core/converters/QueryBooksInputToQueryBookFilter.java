package com.tinqin.libraryv2.book.core.converters;



import com.tinqin.libraryv2.book.apiadapter.operations.querybooks.ProcessorQueryBooksInput;
import com.tinqin.libraryv2.book.core.queries.filters.QueryBookFilter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class QueryBooksInputToQueryBookFilter implements Converter<ProcessorQueryBooksInput, QueryBookFilter> {
    @Override
    public QueryBookFilter convert(ProcessorQueryBooksInput source) {
        return QueryBookFilter
                .builder()
                .authorId( source.getAuthorId()!=null ? UUID.fromString( source.getAuthorId()):null)
                .titleLike(source.getTitleLike())
                .title( source.getTitle() )
                .authorFirstNameLike(source.getAuthorFirstNameLike())
                .authorLastNameLike(source.getAuthorLastNameLike())
                .authorFirstName(source.getAuthorFirstName())
                .authorLastName(source.getAuthorLastName())
                .build();
    }
}
