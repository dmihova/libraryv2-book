package com.tinqin.libraryv2.book.apiadapter.models;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class ProcessorBookModel {
    private String title;
    private String bookId;
    private String description;
    private String publishYear;
    private Integer pages;
    private BigDecimal price;
    private BigDecimal pricePerRental;
    private Integer stock;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Boolean isDeleted;

    private List<ProcessorAuthorModel> authors;

}
