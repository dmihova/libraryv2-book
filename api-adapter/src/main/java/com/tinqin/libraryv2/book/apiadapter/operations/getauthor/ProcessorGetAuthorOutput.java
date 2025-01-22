package com.tinqin.libraryv2.book.apiadapter.operations.getauthor;

import com.tinqin.libraryv2.book.apiadapter.models.ProcessorAuthorModel;
import com.tinqin.libraryv2.book.apiadapter.operations.base.ProcessorOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@AllArgsConstructor
@Setter
public class ProcessorGetAuthorOutput  implements ProcessorOutput {
    private ProcessorAuthorModel author;

}
