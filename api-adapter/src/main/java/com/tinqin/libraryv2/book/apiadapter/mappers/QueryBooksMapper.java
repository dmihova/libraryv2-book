package com.tinqin.libraryv2.book.apiadapter.mappers;

import com.tinqin.libraryv2.book.api.operations.querybooks.ApiQueryBooksInput;
import com.tinqin.libraryv2.book.api.operations.querybooks.ApiQueryBooksOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooks.ProcessorQueryBooksInput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooks.ProcessorQueryBooksOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ModelMapper.class)
public interface QueryBooksMapper {

    QueryBooksMapper INSTANCE = Mappers.getMapper(QueryBooksMapper.class);

    ProcessorQueryBooksInput toOperation(ApiQueryBooksInput input);

    ApiQueryBooksOutput toApiResult(ProcessorQueryBooksOutput output);


}
