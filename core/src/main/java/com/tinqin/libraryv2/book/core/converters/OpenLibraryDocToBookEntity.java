package com.tinqin.libraryv2.book.core.converters;

import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.OpenLibraryDoc;
import com.tinqin.libraryv2.book.persistence.models.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OpenLibraryDocToBookEntity implements Converter<OpenLibraryDoc, Book> {
    @Override
    public Book convert(OpenLibraryDoc source) {
        return Book
                .builder()
                .title(source.getTitle() )
                .publishYear(source.getFirst_publish_year())
                .build();
    }
}
