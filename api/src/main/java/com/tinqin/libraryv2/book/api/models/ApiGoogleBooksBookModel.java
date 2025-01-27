package com.tinqin.libraryv2.book.api.models;


import com.tinqin.libraryv2.book.api.models.base.ApiOutput;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class ApiGoogleBooksBookModel extends ApiOutput {
    private String externalId;
    private String title;
    private String description;
    private String[] authors;
    private Integer pageCount;
    private String[] categories;
    private String imgUrl;
    private String[] isbns;

}
