package com.tinqin.libraryv2.book.apiadapter.mappers;

import com.tinqin.libraryv2.book.api.operations.getauthor.ApiGetAuthorInput;
import com.tinqin.libraryv2.book.api.operations.getauthor.ApiGetAuthorOutput;
import com.tinqin.libraryv2.book.api.operations.getauthorbooksopenlib.ApiGetAuthorBooksOpenLibInput;
import com.tinqin.libraryv2.book.api.operations.getauthorbooksopenlib.ApiGetAuthorBooksOpenLibOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthor.ProcessorGetAuthorInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthor.ProcessorGetAuthorOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksopenlib.ProcessorGetAuthorBooksOpenLibInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksopenlib.ProcessorGetAuthorBooksOpenLibOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ModelMapper.class)
public interface GetAuthorBooksOpenLibMapper {

    GetAuthorBooksOpenLibMapper INSTANCE = Mappers.getMapper(GetAuthorBooksOpenLibMapper.class);

    ProcessorGetAuthorBooksOpenLibInput toOperation(ApiGetAuthorBooksOpenLibInput input);

    ApiGetAuthorBooksOpenLibOutput toApiResult(ProcessorGetAuthorBooksOpenLibOutput output);


}
