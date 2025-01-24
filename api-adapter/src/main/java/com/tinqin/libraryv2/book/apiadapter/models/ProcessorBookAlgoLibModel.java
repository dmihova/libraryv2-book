package com.tinqin.libraryv2.book.apiadapter.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProcessorBookAlgoLibModel {
    String title;
    private String[] authors;
    private String description;
    private String pages;
    private String publishYear;
    private String imgUrl;
    private String[] categories;
    String [] isbns;


}
