package com.tinqin.libraryv2.book.api.operations.getbookopenlibfirst;

import com.tinqin.libraryv2.book.api.models.ApiBookOpenLibModel;
import com.tinqin.libraryv2.book.api.models.base.ApiOutput;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ApiGetBookOpenLibFirstOutput extends ApiOutput {
    private String bookId;
    private ApiBookOpenLibModel bookOpenLib;

}
