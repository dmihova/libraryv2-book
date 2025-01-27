package com.tinqin.libraryv2.book.api.operations.getauthorbooksalgolib;

import com.tinqin.libraryv2.book.api.models.ApiAlgoLibBookModel;
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
public class ApiGetAuthorBooksAlgoLibOutput extends ApiOutput {
    private String authorId;
    private List<ApiAlgoLibBookModel> openLibBooks;

}
