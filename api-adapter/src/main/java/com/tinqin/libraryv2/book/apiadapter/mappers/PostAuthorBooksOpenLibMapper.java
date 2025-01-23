package com.tinqin.libraryv2.book.apiadapter.mappers;

import com.tinqin.libraryv2.book.api.operations.postauthorbooksopenlib.ApiPostAuthorBooksOpenLibInput;
import com.tinqin.libraryv2.book.api.operations.postauthorbooksopenlib.ApiPostAuthorBooksOpenLibOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.postauthorbooksopenlib.ProcessorPostAuthorBooksOpenLibInput;
import com.tinqin.libraryv2.book.apiadapter.operations.postauthorbooksopenlib.ProcessorPostAuthorBooksOpenLibOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ModelMapper.class)
public interface PostAuthorBooksOpenLibMapper {
    PostAuthorBooksOpenLibMapper INSTANCE = Mappers.getMapper(PostAuthorBooksOpenLibMapper.class);

    ProcessorPostAuthorBooksOpenLibInput toOperation(ApiPostAuthorBooksOpenLibInput input);

    ApiPostAuthorBooksOpenLibOutput toApiResult(ProcessorPostAuthorBooksOpenLibOutput output);
}
