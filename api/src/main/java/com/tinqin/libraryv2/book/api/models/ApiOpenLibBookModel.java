package com.tinqin.libraryv2.book.api.models;

import com.tinqin.libraryv2.book.api.models.base.ApiOutput;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ApiOpenLibBookModel extends ApiOutput {

    String title;
    private String publishYear;
    private String pages;

    String [] authorNames;
    String [] authorKeys;
    String [] isbns;
    String [] subjects;
    String [] subjectKeys;



}
