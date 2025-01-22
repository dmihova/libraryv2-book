package com.tinqin.libraryv2.book.apiadapter.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class ProcessorAuthorBaseModel {
    private String authorId;
    private String firstName;
    private String lastName;

    private List<ProcessorBookBaseModel> books;
}
