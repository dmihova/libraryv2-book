package com.tinqin.libraryv2.book.api.operations.getBook;

import com.tinqin.libraryv2.book.api.models.ApiBook;
import com.tinqin.libraryv2.book.api.models.ApiBookOutput;
import com.tinqin.libraryv2.book.api.operations.base.ApiOutput;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ApiGetBookOutput extends ApiOutput {

    private ApiBookOutput book;




}
