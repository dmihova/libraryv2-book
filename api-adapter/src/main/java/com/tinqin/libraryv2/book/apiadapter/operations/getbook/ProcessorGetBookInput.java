package com.tinqin.libraryv2.book.apiadapter.operations.getbook;

import com.tinqin.libraryv2.book.apiadapter.operations.base.ProcessorInput;
import lombok.*;


@Builder
@Getter
@AllArgsConstructor
@Setter
public class ProcessorGetBookInput implements ProcessorInput {

    String bookId;
}
