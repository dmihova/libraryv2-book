package com.tinqin.libraryv2.book.api.operations.querybooksalgolib;

import com.tinqin.libraryv2.book.api.models.ApiBookAlgoLibModel;
import com.tinqin.libraryv2.book.api.models.base.ApiOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ApiQueryBooksAlgoLibOutput extends ApiOutput {

    private List<ApiBookAlgoLibModel> booksAlgoLib;




}
