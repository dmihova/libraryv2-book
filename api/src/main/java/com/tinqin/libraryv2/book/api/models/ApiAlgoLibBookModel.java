package com.tinqin.libraryv2.book.api.models;


import com.tinqin.libraryv2.book.api.models.base.ApiOutput;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class ApiAlgoLibBookModel extends ApiOutput {
    String title;
    private String[] authors;
    private String description;
    private String pages;
    private String publishYear;
    private String imgUrl;
    private String[] categories;
    String [] isbns;
}
