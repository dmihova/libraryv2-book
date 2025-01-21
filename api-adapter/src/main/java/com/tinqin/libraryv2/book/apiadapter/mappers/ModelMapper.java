package com.tinqin.libraryv2.book.apiadapter.mappers;


import com.tinqin.libraryv2.book.api.models.ApiAuthorOutput;
import com.tinqin.libraryv2.book.api.models.ApiBookOutput;
import com.tinqin.libraryv2.book.api.models.ApiError;
import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorAuthorModel;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

    ApiError toApiError(OperationError operationError);

    @Mapping(source = "description", target = "description", qualifiedByName = "descriptionToDescription")
    ApiBookOutput toBook(ProcessorBookModel output);

    @Named("descriptionToDescription")
    static String descriptionToDescription(String  description) {
        return description +" test";
    }



    ApiAuthorOutput toAuthor(ProcessorAuthorModel output);
}
