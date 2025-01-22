package com.tinqin.libraryv2.book.apiadapter.operations.getbookopenlibfirst;

import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookOpenLibModel;
import com.tinqin.libraryv2.book.apiadapter.operations.base.ProcessorOutput;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Builder
@Getter
@Setter
public class ProcessorGetBookOpenLibFirstOutput implements ProcessorOutput {
    private UUID bookId;
    private ProcessorBookOpenLibModel bookOpenLib;

}
