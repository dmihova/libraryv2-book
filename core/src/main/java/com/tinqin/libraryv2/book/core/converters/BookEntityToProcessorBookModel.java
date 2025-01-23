package com.tinqin.libraryv2.book.core.converters;


import com.tinqin.libraryv2.book.apiadapter.models.ProcessorAuthorBaseModel;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookModel;
import com.tinqin.libraryv2.book.persistence.models.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookEntityToProcessorBookModel implements Converter<Book, ProcessorBookModel> {
    @Override
    public ProcessorBookModel convert(Book book) {

        return ProcessorBookModel
                .builder()
                .bookId(book.getId().toString())
                .title(book.getTitle())
                .pages(book.getPages())
                .description(book.getDescription())
                .publishYear(book.getPublishYear())
                .price(book.getPrice())
                .createdOn(book.getCreatedOn())
                .updatedOn(book.getUpdatedOn())
                 .authors(
                        book.getAuthors()
                                .stream()
                                .map(author -> ProcessorAuthorBaseModel.builder()
                                        .authorId(author.getId().toString())
                                        .firstName(author.getFirstName())
                                        .lastName(author.getLastName())
                                        .build())
                                .toList()
                )

                .build();
    }
}
