package com.tinqin.libraryv2.book.apiadapter.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProcessorBookOpenLibModel {
    String title;
    private String publishYear;
    private String pages;

    String [] authorNames;
    String [] authorKeys;
    String [] isbns;
    String [] subjects;
    String [] subjectKeys;

}
