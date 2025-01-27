package com.tinqin.libraryv2.book.apiadapter.mappers;

import com.tinqin.libraryv2.book.api.operations.getauthorbooksgooglebooks.ApiGetAuthorBooksGoogleBooksInput;
import com.tinqin.libraryv2.book.api.operations.getauthorbooksgooglebooks.ApiGetAuthorBooksGoogleBooksOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksgooglebooks.ProcessorGetAuthorBooksGoogleBooksInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksgooglebooks.ProcessorGetAuthorBooksGoogleBooksOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ModelMapper.class)
public interface GetAuthorBooksGoogleBooksMapper {

    GetAuthorBooksGoogleBooksMapper INSTANCE = Mappers.getMapper(GetAuthorBooksGoogleBooksMapper.class);

    ProcessorGetAuthorBooksGoogleBooksInput toOperation(ApiGetAuthorBooksGoogleBooksInput input);


    ApiGetAuthorBooksGoogleBooksOutput toApiResult(ProcessorGetAuthorBooksGoogleBooksOutput output);


}
