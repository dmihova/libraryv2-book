package com.tinqin.libraryv2.book.apiadapter.operations.querybooksalgolib;

import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookAlgoLibModel;
import com.tinqin.libraryv2.book.apiadapter.operations.base.ProcessorOutput;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Builder
@Getter
public class ProcessorQueryBooksAlgoLibOutput implements ProcessorOutput {
    private List<ProcessorBookAlgoLibModel> booksAlgoLib;
}
