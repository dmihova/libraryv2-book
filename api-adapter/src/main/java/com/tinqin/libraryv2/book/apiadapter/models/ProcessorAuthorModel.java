package com.tinqin.libraryv2.book.apiadapter.models;


import lombok.Builder;
import lombok.Getter;
import java.util.List;


@Builder
@Getter
public class ProcessorAuthorModel {
    private String authorId;
    private String firstName;
    private String lastName;

    private List<ProcessorBookBaseModel> books;
}
