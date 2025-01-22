package com.tinqin.libraryv2.book.api.operations.querybooks;

import com.tinqin.libraryv2.book.api.models.ApiBookModel;
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
public class ApiQueryBooksOutput extends ApiOutput {

    private List<ApiBookModel> books;




}
