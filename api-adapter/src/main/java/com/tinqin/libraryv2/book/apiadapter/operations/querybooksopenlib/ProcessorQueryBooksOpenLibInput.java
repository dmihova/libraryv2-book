package com.tinqin.libraryv2.book.apiadapter.operations.querybooksopenlib;

import com.tinqin.libraryv2.book.apiadapter.operations.base.ProcessorInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Builder
@Getter
@AllArgsConstructor
@Setter
public class ProcessorQueryBooksOpenLibInput implements ProcessorInput {
    private String title;
    private String author;
    @Builder.Default
    private Integer page=1 ;
}
