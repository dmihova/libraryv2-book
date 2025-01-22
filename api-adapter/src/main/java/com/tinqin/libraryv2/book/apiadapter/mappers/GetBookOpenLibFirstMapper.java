package com.tinqin.libraryv2.book.apiadapter.mappers;

import com.tinqin.libraryv2.book.api.operations.getbookopenlibfirst.ApiGetBookOpenLibFirstInput;
import com.tinqin.libraryv2.book.api.operations.getbookopenlibfirst.ApiGetBookOpenLibFirstOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.getbookopenlibfirst.ProcessorGetBookOpenLibFirstInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getbookopenlibfirst.ProcessorGetBookOpenLibFirstOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ModelMapper.class)
public interface GetBookOpenLibFirstMapper {
    GetBookOpenLibFirstMapper INSTANCE = Mappers.getMapper(GetBookOpenLibFirstMapper.class);

    ProcessorGetBookOpenLibFirstInput toOperation(ApiGetBookOpenLibFirstInput input);

    ApiGetBookOpenLibFirstOutput toApiResult(ProcessorGetBookOpenLibFirstOutput output);
}
