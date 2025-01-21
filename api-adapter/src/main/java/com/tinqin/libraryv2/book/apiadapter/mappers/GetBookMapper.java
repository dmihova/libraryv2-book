package com.tinqin.libraryv2.book.apiadapter.mappers;

import com.tinqin.libraryv2.book.api.operations.getBook.ApiGetBookInput;
import com.tinqin.libraryv2.book.api.operations.getBook.ApiGetBookOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.getbook.ProcessorGetBookInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getbook.ProcessorGetBookOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ModelMapper.class)
public interface GetBookMapper {

    GetBookMapper INSTANCE = Mappers.getMapper(GetBookMapper.class);

    ProcessorGetBookInput toOperation(ApiGetBookInput input);

    ApiGetBookOutput toApiResult(ProcessorGetBookOutput output);


}
