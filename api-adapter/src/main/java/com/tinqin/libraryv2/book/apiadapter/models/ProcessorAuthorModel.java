package com.tinqin.libraryv2.book.apiadapter.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ProcessorAuthorModel {
    private String authorId;
    private String firstName;
    private String lastName;
}
