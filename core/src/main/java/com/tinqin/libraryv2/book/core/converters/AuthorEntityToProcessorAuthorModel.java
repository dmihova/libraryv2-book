package com.tinqin.libraryv2.book.core.converters;

import com.tinqin.libraryv2.book.apiadapter.models.ProcessorAuthorModel;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookBaseModel;
import com.tinqin.libraryv2.book.persistence.models.Author;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthorEntityToProcessorAuthorModel implements Converter<Author, ProcessorAuthorModel> {
    @Override
    public ProcessorAuthorModel convert(Author author) {

        return ProcessorAuthorModel
                .builder()
                .authorId(author.getId().toString())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .books(
                        author.getBooks()
                                .stream()
                                .map(book -> ProcessorBookBaseModel.builder()
                                        .bookId(book.getId().toString())
                                        .title(book.getTitle())
                                        .subtitle(book.getSubtitle()!=null?book.getSubtitle():"")
                                        .build())
                                .toList()
                )
                .build();
    }
}
