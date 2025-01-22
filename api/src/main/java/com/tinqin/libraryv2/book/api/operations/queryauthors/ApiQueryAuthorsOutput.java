package com.tinqin.libraryv2.book.api.operations.queryauthors;

import com.tinqin.libraryv2.book.api.models.ApiAuthorBaseModel;
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
public class ApiQueryAuthorsOutput extends ApiOutput {

    private List<ApiAuthorBaseModel> authors;




}
