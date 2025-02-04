package com.tinqin.libraryv2.book.core.converters;


import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookBaseModel;
import com.tinqin.libraryv2.book.persistence.models.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookEntityToProcessorBookBaseModel implements Converter<Book, ProcessorBookBaseModel> {
    @Override
    public ProcessorBookBaseModel convert(Book book) {
        return ProcessorBookBaseModel
                .builder()
                .bookId(book.getId().toString())
                .title(book.getTitle())
                .subtitle(book.getSubtitle()==null?"":book.getSubtitle())
                .build();
    }
}
