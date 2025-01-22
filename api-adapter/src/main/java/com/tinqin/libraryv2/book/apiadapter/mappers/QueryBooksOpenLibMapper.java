package com.tinqin.libraryv2.book.apiadapter.mappers;

import com.tinqin.libraryv2.book.api.operations.querybooksopenlib.ApiQueryBooksOpenLibInput;
import com.tinqin.libraryv2.book.api.operations.querybooksopenlib.ApiQueryBooksOpenLibOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooksopenlib.ProcessorQueryBooksOpenLibInput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooksopenlib.ProcessorQueryBooksOpenLibOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ModelMapper.class)
public interface QueryBooksOpenLibMapper {

    QueryBooksOpenLibMapper INSTANCE = Mappers.getMapper(QueryBooksOpenLibMapper.class);

    ProcessorQueryBooksOpenLibInput toOperation(ApiQueryBooksOpenLibInput input);

    ApiQueryBooksOpenLibOutput toApiResult(ProcessorQueryBooksOpenLibOutput output);


}
