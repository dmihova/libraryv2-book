package com.tinqin.libraryv2.book.api.operations.queryauthors;

import com.tinqin.libraryv2.book.api.models.base.ApiInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ApiQueryAuthorsInput extends ApiInput {

    private String firstName;
    private String lastName;
    private String firstNameLike;
    private String lastNameLike;
    @Builder.Default
    private Integer page=0;





}
