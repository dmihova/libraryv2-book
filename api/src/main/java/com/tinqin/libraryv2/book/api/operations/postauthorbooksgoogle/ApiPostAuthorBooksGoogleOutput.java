package com.tinqin.libraryv2.book.api.operations.postauthorbooksgoogle;

import com.tinqin.libraryv2.book.api.models.ApiBookBaseModel;
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
public class ApiPostAuthorBooksGoogleOutput extends ApiOutput {
    private String authorId;
    private Integer newBooksCount;
    private List<ApiBookBaseModel> books;

}
