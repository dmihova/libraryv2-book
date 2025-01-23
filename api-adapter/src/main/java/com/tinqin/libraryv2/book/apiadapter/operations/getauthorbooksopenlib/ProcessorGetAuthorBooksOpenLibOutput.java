package com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksopenlib;

import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookOpenLibModel;
import com.tinqin.libraryv2.book.apiadapter.operations.base.ProcessorOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@Setter
public class ProcessorGetAuthorBooksOpenLibOutput implements ProcessorOutput {
    private UUID authorId;
    private List<ProcessorBookOpenLibModel> booksOpenLib;

}
