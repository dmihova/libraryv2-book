package com.tinqin.libraryv2.book.apiadapter.mappers;

import com.tinqin.libraryv2.book.api.operations.getbooksopenlib.ApiGetBooksOpenLibInput;
import com.tinqin.libraryv2.book.api.operations.getbooksopenlib.ApiGetBooksOpenLibOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.getbooksopenlib.ProcessorGetBooksOpenLibInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getbooksopenlib.ProcessorGetBooksOpenLibOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ModelMapper.class)
public interface GetBooksOpenLibMapper {
    GetBooksOpenLibMapper INSTANCE = Mappers.getMapper(GetBooksOpenLibMapper.class);

    ProcessorGetBooksOpenLibInput toOperation(ApiGetBooksOpenLibInput input);

    ApiGetBooksOpenLibOutput toApiResult(ProcessorGetBooksOpenLibOutput output);
}
