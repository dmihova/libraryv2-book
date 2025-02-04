package com.tinqin.libraryv2.book.api.operations.getauthor;

import com.tinqin.libraryv2.book.api.models.ApiAuthorModel;
import com.tinqin.libraryv2.book.api.models.base.ApiOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ApiGetAuthorOutput extends ApiOutput {

    private ApiAuthorModel author;


}
