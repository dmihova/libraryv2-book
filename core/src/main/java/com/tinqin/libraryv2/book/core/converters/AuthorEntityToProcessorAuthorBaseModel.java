package com.tinqin.libraryv2.book.core.converters;

import com.tinqin.libraryv2.book.apiadapter.models.ProcessorAuthorBaseModel;
import com.tinqin.libraryv2.book.persistence.models.Author;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthorEntityToProcessorAuthorBaseModel implements Converter<Author, ProcessorAuthorBaseModel> {
    @Override
    public ProcessorAuthorBaseModel convert(Author author) {

        return ProcessorAuthorBaseModel
                .builder()
                .authorId(author.getId().toString())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();
    }
}
