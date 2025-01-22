package com.tinqin.libraryv2.book.apiadapter.mappers;

import com.tinqin.libraryv2.book.api.models.ApiAuthorBaseModel;
import com.tinqin.libraryv2.book.api.models.ApiAuthorModel;
import com.tinqin.libraryv2.book.api.models.ApiBookBaseModel;
import com.tinqin.libraryv2.book.api.models.ApiBookModel;
import com.tinqin.libraryv2.book.api.models.ApiBookOpenLibModel;
import com.tinqin.libraryv2.book.api.models.ApiError;
import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorAuthorBaseModel;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorAuthorModel;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookBaseModel;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookModel;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookOpenLibModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-22T16:24:05+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class ModelMapperImpl implements ModelMapper {

    @Override
    public ApiError toApiError(OperationError operationError) {
        if ( operationError == null ) {
            return null;
        }

        ApiError.ApiErrorBuilder apiError = ApiError.builder();

        apiError.status( operationError.getStatus() );
        apiError.errorCode( operationError.getErrorCode() );
        apiError.message( operationError.getMessage() );

        return apiError.build();
    }

    @Override
    public ApiBookModel toBook(ProcessorBookModel output) {
        if ( output == null ) {
            return null;
        }

        ApiBookModel.ApiBookModelBuilder apiBookModel = ApiBookModel.builder();

        apiBookModel.description( ModelMapper.descriptionToDescription( output.getDescription() ) );
        apiBookModel.title( output.getTitle() );
        apiBookModel.bookId( output.getBookId() );
        apiBookModel.publishYear( output.getPublishYear() );
        apiBookModel.pages( output.getPages() );
        apiBookModel.price( output.getPrice() );
        apiBookModel.pricePerRental( output.getPricePerRental() );
        apiBookModel.stock( output.getStock() );
        apiBookModel.createdOn( output.getCreatedOn() );
        apiBookModel.updatedOn( output.getUpdatedOn() );
        apiBookModel.isDeleted( output.getIsDeleted() );
        apiBookModel.authors( processorAuthorBaseModelListToApiAuthorBaseModelList( output.getAuthors() ) );

        return apiBookModel.build();
    }

    @Override
    public ApiBookOpenLibModel toBookOpenLib(ProcessorBookOpenLibModel output) {
        if ( output == null ) {
            return null;
        }

        ApiBookOpenLibModel.ApiBookOpenLibModelBuilder apiBookOpenLibModel = ApiBookOpenLibModel.builder();

        apiBookOpenLibModel.title( output.getTitle() );
        apiBookOpenLibModel.publishYear( output.getPublishYear() );
        apiBookOpenLibModel.pages( output.getPages() );
        String[] authorNames = output.getAuthorNames();
        if ( authorNames != null ) {
            apiBookOpenLibModel.authorNames( Arrays.copyOf( authorNames, authorNames.length ) );
        }
        String[] authorKeys = output.getAuthorKeys();
        if ( authorKeys != null ) {
            apiBookOpenLibModel.authorKeys( Arrays.copyOf( authorKeys, authorKeys.length ) );
        }
        String[] isbns = output.getIsbns();
        if ( isbns != null ) {
            apiBookOpenLibModel.isbns( Arrays.copyOf( isbns, isbns.length ) );
        }
        String[] subjects = output.getSubjects();
        if ( subjects != null ) {
            apiBookOpenLibModel.subjects( Arrays.copyOf( subjects, subjects.length ) );
        }
        String[] subjectKeys = output.getSubjectKeys();
        if ( subjectKeys != null ) {
            apiBookOpenLibModel.subjectKeys( Arrays.copyOf( subjectKeys, subjectKeys.length ) );
        }

        return apiBookOpenLibModel.build();
    }

    @Override
    public List<ApiBookOpenLibModel> toBooksOpenLib(List<ProcessorBookOpenLibModel> output) {
        if ( output == null ) {
            return null;
        }

        List<ApiBookOpenLibModel> list = new ArrayList<ApiBookOpenLibModel>( output.size() );
        for ( ProcessorBookOpenLibModel processorBookOpenLibModel : output ) {
            list.add( toBookOpenLib( processorBookOpenLibModel ) );
        }

        return list;
    }

    @Override
    public ApiAuthorBaseModel toAuthor(ProcessorAuthorBaseModel output) {
        if ( output == null ) {
            return null;
        }

        ApiAuthorBaseModel.ApiAuthorBaseModelBuilder apiAuthorBaseModel = ApiAuthorBaseModel.builder();

        apiAuthorBaseModel.authorId( output.getAuthorId() );
        apiAuthorBaseModel.firstName( output.getFirstName() );
        apiAuthorBaseModel.lastName( output.getLastName() );

        return apiAuthorBaseModel.build();
    }

    @Override
    public ApiAuthorModel toAuthor(ProcessorAuthorModel output) {
        if ( output == null ) {
            return null;
        }

        ApiAuthorModel.ApiAuthorModelBuilder apiAuthorModel = ApiAuthorModel.builder();

        apiAuthorModel.authorId( output.getAuthorId() );
        apiAuthorModel.firstName( output.getFirstName() );
        apiAuthorModel.lastName( output.getLastName() );
        apiAuthorModel.books( toBooks( output.getBooks() ) );

        return apiAuthorModel.build();
    }

    @Override
    public List<ApiBookBaseModel> toBooks(List<ProcessorBookBaseModel> output) {
        if ( output == null ) {
            return null;
        }

        List<ApiBookBaseModel> list = new ArrayList<ApiBookBaseModel>( output.size() );
        for ( ProcessorBookBaseModel processorBookBaseModel : output ) {
            list.add( toBook( processorBookBaseModel ) );
        }

        return list;
    }

    @Override
    public ApiBookBaseModel toBook(ProcessorBookBaseModel output) {
        if ( output == null ) {
            return null;
        }

        ApiBookBaseModel.ApiBookBaseModelBuilder apiBookBaseModel = ApiBookBaseModel.builder();

        apiBookBaseModel.title( output.getTitle() );
        apiBookBaseModel.bookId( output.getBookId() );

        return apiBookBaseModel.build();
    }

    protected List<ApiAuthorBaseModel> processorAuthorBaseModelListToApiAuthorBaseModelList(List<ProcessorAuthorBaseModel> list) {
        if ( list == null ) {
            return null;
        }

        List<ApiAuthorBaseModel> list1 = new ArrayList<ApiAuthorBaseModel>( list.size() );
        for ( ProcessorAuthorBaseModel processorAuthorBaseModel : list ) {
            list1.add( toAuthor( processorAuthorBaseModel ) );
        }

        return list1;
    }
}
