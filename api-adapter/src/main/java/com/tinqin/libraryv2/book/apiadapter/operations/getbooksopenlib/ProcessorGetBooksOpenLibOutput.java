package com.tinqin.libraryv2.book.apiadapter.operations.getbooksopenlib;

import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookOpenLibModel;
import com.tinqin.libraryv2.book.apiadapter.operations.base.ProcessorOutput;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Builder
@Getter
@Setter
public class ProcessorGetBooksOpenLibOutput implements ProcessorOutput {
    private UUID bookId;
    private List<ProcessorBookOpenLibModel> openLibBooks;

}
