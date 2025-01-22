package com.tinqin.libraryv2.book.apiadapter.mappers;

import com.tinqin.libraryv2.book.api.models.ApiAuthorModel;
import com.tinqin.libraryv2.book.api.operations.getauthor.ApiGetAuthorInput;
import com.tinqin.libraryv2.book.api.operations.getauthor.ApiGetAuthorOutput;
import com.tinqin.libraryv2.book.api.operations.getbook.ApiGetBookInput;
import com.tinqin.libraryv2.book.api.operations.getbook.ApiGetBookOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthor.ProcessorGetAuthorInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthor.ProcessorGetAuthorOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.getbook.ProcessorGetBookInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getbook.ProcessorGetBookOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ModelMapper.class)
public interface GetAuthorMapper {

    GetAuthorMapper INSTANCE = Mappers.getMapper(GetAuthorMapper.class);

    ProcessorGetAuthorInput toOperation(ApiGetAuthorInput input);

    ApiGetAuthorOutput toApiResult(ProcessorGetAuthorOutput output);


}
