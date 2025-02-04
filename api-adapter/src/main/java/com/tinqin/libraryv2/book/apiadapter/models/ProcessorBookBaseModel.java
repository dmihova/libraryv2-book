package com.tinqin.libraryv2.book.apiadapter.models;

import lombok.Builder;
import lombok.Getter;



@Builder
@Getter
public class ProcessorBookBaseModel {
    private String title;
    private String subtitle;
    private String bookId;

}
