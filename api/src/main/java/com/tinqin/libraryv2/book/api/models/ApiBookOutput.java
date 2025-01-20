package com.tinqin.libraryv2.book.api.models;

import com.tinqin.libraryv2.book.api.operations.base.ApiOutput;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ApiBookOutput extends ApiOutput {

    private String title;
    private String bookId;
    private String description;
    private String publishYear;
    private Integer pages;
    private List<ApiAuthorOutput> authors;



}
