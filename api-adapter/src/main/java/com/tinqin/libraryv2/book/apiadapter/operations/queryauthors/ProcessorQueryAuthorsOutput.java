package com.tinqin.libraryv2.book.apiadapter.operations.queryauthors;

import com.tinqin.libraryv2.book.apiadapter.models.ProcessorAuthorBaseModel;
import com.tinqin.libraryv2.book.apiadapter.operations.base.ProcessorOutput;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Builder
@Getter
public class ProcessorQueryAuthorsOutput implements ProcessorOutput {
    private List<ProcessorAuthorBaseModel> authors;

}
