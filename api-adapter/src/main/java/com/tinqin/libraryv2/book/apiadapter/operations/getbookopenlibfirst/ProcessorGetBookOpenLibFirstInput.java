package com.tinqin.libraryv2.book.apiadapter.operations.getbookopenlibfirst;

import com.tinqin.libraryv2.book.apiadapter.operations.base.ProcessorInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@AllArgsConstructor
@Setter
public class ProcessorGetBookOpenLibFirstInput implements ProcessorInput {

    String bookId;
}
