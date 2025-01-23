package com.tinqin.libraryv2.book.api.operations.getauthorbooksopenlib;

import com.tinqin.libraryv2.book.api.models.ApiBookOpenLibModel;
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
public class ApiGetAuthorBooksOpenLibOutput extends ApiOutput {
    private String authorId;
    private List<ApiBookOpenLibModel> booksOpenLib;

}
