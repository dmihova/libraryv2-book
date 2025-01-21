package com.tinqin.libraryv2.book.core.converters;


import com.tinqin.libraryv2.book.apiadapter.models.ProcessorAuthorModel;
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
                .pricePerRental(book.getPricePerRental())
                .stock(book.getStock())
                .createdOn(book.getCreatedOn())
                .updatedOn(book.getUpdatedOn())
                .isDeleted(book.getIsDeleted())
                .authors(
                        book.getAuthors()
                                .stream()
                                .map(author -> ProcessorAuthorModel.builder()
                                        .authorId(author.getId().toString())
                                        .firstName(author.getFirstName())
                                        .lastName(author.getLastName())
                                        .build())
                                .toList()
                )

                .build();
    }
}
