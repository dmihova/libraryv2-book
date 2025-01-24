package com.tinqin.libraryv2.book.api.models;


import com.tinqin.libraryv2.book.api.models.base.ApiOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Builder
@Getter
public class ApiBookAlgoLibModel extends ApiOutput {
    String title;
    private String[] authors;
    private String description;
    private String pages;
    private String publishYear;
    private String imgUrl;
    private String[] categories;
    String [] isbns;
}
