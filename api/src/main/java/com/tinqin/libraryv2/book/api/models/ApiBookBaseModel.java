package com.tinqin.libraryv2.book.api.models;

import com.tinqin.libraryv2.book.api.models.base.ApiOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ApiBookBaseModel extends ApiOutput {

    private String title;
    private String bookId;

}
