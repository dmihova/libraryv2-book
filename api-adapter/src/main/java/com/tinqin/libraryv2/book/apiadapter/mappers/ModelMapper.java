package com.tinqin.libraryv2.book.apiadapter.mappers;


import com.tinqin.libraryv2.book.api.models.*;
import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.models.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

    ApiError toApiError(OperationError operationError);

    @Mapping(source = "description", target = "description", qualifiedByName = "descriptionToDescription")
    ApiBookModel toBook(ProcessorBookModel output);

    @Named("descriptionToDescription")
    static String descriptionToDescription(String description) {
        if (description == null || description.isEmpty()) {
            return "N/A";
        }
        return description;
    }

    ApiAuthorBaseModel toAuthor(ProcessorAuthorBaseModel output);
    ApiAuthorModel toAuthor(ProcessorAuthorModel output);

    List<ApiBookBaseModel> toBooks(List<ProcessorBookBaseModel> output);
    ApiBookBaseModel toBook(ProcessorBookBaseModel output);


    ApiOpenLibBookModel toOpenLibBook(ProcessorBookOpenLibModel output);
    List<ApiOpenLibBookModel> toOpenLibBooks(List<ProcessorBookOpenLibModel> output);

    ApiAlgoLibBookModel toAlgoLibBook(ProcessorBookAlgoLibModel output);
    List<ApiAlgoLibBookModel> toAlgoLibBooks(List<ProcessorBookAlgoLibModel> output);

    ApiGoogleBooksBookModel toGoogleBooksBook(ProcessorBookGoogleBooksModel output);
    List<ApiGoogleBooksBookModel> toGoogleBooksBooks(List<ApiGoogleBooksBookModel> output);
}
