package com.tinqin.libraryv2.book.apiadapter.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProcessorBookGoogleBooksModel {
    private String externalId;
    private String title;
    private String description;
    private String[] authors;
    private Integer pageCount;
    private String[] categories;
    private String imgUrl;
    private String[] isbns;


}
