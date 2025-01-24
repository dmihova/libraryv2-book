package com.tinqin.libraryv2.book.apiadapter.operations.querybooksalgolib;

import com.tinqin.libraryv2.book.apiadapter.operations.base.ProcessorInput;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class ProcessorQueryBooksAlgoLibInput implements ProcessorInput {
    private String title;
    private String author;
    private String authorLike;

}
