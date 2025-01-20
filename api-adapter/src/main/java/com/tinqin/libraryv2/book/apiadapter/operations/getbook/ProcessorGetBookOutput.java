package com.tinqin.libraryv2.book.apiadapter.operations.getbook;

import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookModel;
import com.tinqin.libraryv2.book.apiadapter.operations.base.ProcessorOutput;
import lombok.*;


@Builder
@Getter
public class ProcessorGetBookOutput implements ProcessorOutput {
    private ProcessorBookModel book;

}
