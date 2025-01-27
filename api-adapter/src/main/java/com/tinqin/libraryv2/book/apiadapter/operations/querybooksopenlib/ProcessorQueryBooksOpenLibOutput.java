package com.tinqin.libraryv2.book.apiadapter.operations.querybooksopenlib;

import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookModel;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookOpenLibModel;
import com.tinqin.libraryv2.book.apiadapter.operations.base.ProcessorOutput;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Builder
@Getter
public class ProcessorQueryBooksOpenLibOutput implements ProcessorOutput {
    private List<ProcessorBookOpenLibModel> openLibBooks;
}
