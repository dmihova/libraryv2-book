package com.tinqin.libraryv2.book.apiadapter.mappers;

import com.tinqin.libraryv2.book.api.operations.getauthorbooksalgolib.ApiGetAuthorBooksAlgoLibInput;
import com.tinqin.libraryv2.book.api.operations.getauthorbooksalgolib.ApiGetAuthorBooksAlgoLibOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksalgolib.ProcessorGetAuthorBooksAlgoLibInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksalgolib.ProcessorGetAuthorBooksAlgoLibOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ModelMapper.class)
public interface GetAuthorBooksAlgoLibMapper {

    GetAuthorBooksAlgoLibMapper INSTANCE = Mappers.getMapper(GetAuthorBooksAlgoLibMapper.class);

    ProcessorGetAuthorBooksAlgoLibInput toOperation(ApiGetAuthorBooksAlgoLibInput input);

    ApiGetAuthorBooksAlgoLibOutput toApiResult(ProcessorGetAuthorBooksAlgoLibOutput output);


}
