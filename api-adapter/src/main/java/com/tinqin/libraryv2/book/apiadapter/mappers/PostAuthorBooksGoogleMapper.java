package com.tinqin.libraryv2.book.apiadapter.mappers;

import com.tinqin.libraryv2.book.api.operations.postauthorbooksgoogle.ApiPostAuthorBooksGoogleInput;
import com.tinqin.libraryv2.book.api.operations.postauthorbooksgoogle.ApiPostAuthorBooksGoogleOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.postauthorbooksgoogle.ProcessorPostAuthorBooksGoogleInput;
import com.tinqin.libraryv2.book.apiadapter.operations.postauthorbooksgoogle.ProcessorPostAuthorBooksGoogleOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ModelMapper.class)
public interface PostAuthorBooksGoogleMapper {
    PostAuthorBooksGoogleMapper INSTANCE = Mappers.getMapper(PostAuthorBooksGoogleMapper.class);

    ProcessorPostAuthorBooksGoogleInput toOperation(ApiPostAuthorBooksGoogleInput input);

    ApiPostAuthorBooksGoogleOutput toApiResult(ProcessorPostAuthorBooksGoogleOutput output);
}
