package com.tinqin.libraryv2.book.apiadapter.operations.queryauthors;

import com.tinqin.libraryv2.book.apiadapter.operations.base.ProcessorInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@AllArgsConstructor
@Setter
public class ProcessorQueryAuthorsInput implements ProcessorInput {

    private String firstName;
    private String lastName;
    private String firstNameLike;
    private String lastNameLike;
    private Integer page;
}
