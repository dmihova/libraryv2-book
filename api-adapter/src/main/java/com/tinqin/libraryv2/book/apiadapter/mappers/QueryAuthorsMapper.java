package com.tinqin.libraryv2.book.apiadapter.mappers;

import com.tinqin.libraryv2.book.api.operations.queryauthors.ApiQueryAuthorsInput;
import com.tinqin.libraryv2.book.api.operations.queryauthors.ApiQueryAuthorsOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.queryauthors.ProcessorQueryAuthorsInput;
import com.tinqin.libraryv2.book.apiadapter.operations.queryauthors.ProcessorQueryAuthorsOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ModelMapper.class)
public interface QueryAuthorsMapper {

    QueryAuthorsMapper INSTANCE = Mappers.getMapper(QueryAuthorsMapper.class);

    ProcessorQueryAuthorsInput toOperation(ApiQueryAuthorsInput input);

    ApiQueryAuthorsOutput toApiResult(ProcessorQueryAuthorsOutput output);


}
