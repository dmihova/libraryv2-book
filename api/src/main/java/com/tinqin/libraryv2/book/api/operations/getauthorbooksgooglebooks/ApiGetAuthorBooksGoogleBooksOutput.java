package com.tinqin.libraryv2.book.api.operations.getauthorbooksgooglebooks;

import com.tinqin.libraryv2.book.api.models.ApiGoogleBooksBookModel;
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
public class ApiGetAuthorBooksGoogleBooksOutput extends ApiOutput {
    private String authorId;
    private List<ApiGoogleBooksBookModel> googleBooksBooks;

}
