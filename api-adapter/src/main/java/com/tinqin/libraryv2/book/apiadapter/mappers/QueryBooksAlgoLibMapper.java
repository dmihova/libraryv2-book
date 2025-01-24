package com.tinqin.libraryv2.book.apiadapter.mappers;

import com.tinqin.libraryv2.book.api.operations.querybooksalgolib.ApiQueryBooksAlgoLibInput;
import com.tinqin.libraryv2.book.api.operations.querybooksalgolib.ApiQueryBooksAlgoLibOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooksalgolib.ProcessorQueryBooksAlgoLibInput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooksalgolib.ProcessorQueryBooksAlgoLibOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ModelMapper.class)
public interface QueryBooksAlgoLibMapper {

    QueryBooksAlgoLibMapper INSTANCE = Mappers.getMapper(QueryBooksAlgoLibMapper.class);

    ProcessorQueryBooksAlgoLibInput toOperation(ApiQueryBooksAlgoLibInput input);

    ApiQueryBooksAlgoLibOutput toApiResult(ProcessorQueryBooksAlgoLibOutput output);


}
