package com.tinqin.libraryv2.book.apiadapter.models;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ProcessorBookModel {
    private String title;
    private String bookId;
    private String description;
    private String publishYear;
    private Integer pages;
    private List<ProcessorAuthorModel> authors;

}
