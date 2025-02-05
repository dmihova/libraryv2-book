package com.tinqin.libraryv2.book.apiadapter.models;

import lombok.Builder;
import lombok.Getter;


import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class ProcessorBookModel {
    private String title;
    private String subtitle;
    private String bookId;
    private String description;
    private String publishYear;
    private Integer pages;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    private List<ProcessorAuthorBaseModel> authors;
    private List<String> categories;
    private String series;


}
