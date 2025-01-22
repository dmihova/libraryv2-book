package com.tinqin.libraryv2.book.apiadapter.operations.querybooks;

import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookModel;
import com.tinqin.libraryv2.book.apiadapter.operations.base.ProcessorOutput;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Builder
@Getter
public class ProcessorQueryBooksOutput implements ProcessorOutput {
    private List<ProcessorBookModel> books;

}
