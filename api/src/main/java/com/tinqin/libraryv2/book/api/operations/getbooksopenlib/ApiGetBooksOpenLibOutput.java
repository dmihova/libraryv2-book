package com.tinqin.libraryv2.book.api.operations.getbooksopenlib;

import com.tinqin.libraryv2.book.api.models.ApiBookOpenLibModel;
import com.tinqin.libraryv2.book.api.models.base.ApiOutput;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ApiGetBooksOpenLibOutput extends ApiOutput {
    private String bookId;
    private List<ApiBookOpenLibModel> booksOpenLib;

}
