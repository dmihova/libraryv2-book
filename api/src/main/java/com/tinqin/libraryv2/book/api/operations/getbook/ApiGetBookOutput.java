package com.tinqin.libraryv2.book.api.operations.getbook;

import com.tinqin.libraryv2.book.api.models.ApiBookModel;
import com.tinqin.libraryv2.book.api.models.base.ApiOutput;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ApiGetBookOutput extends ApiOutput {

    private ApiBookModel book;




}
