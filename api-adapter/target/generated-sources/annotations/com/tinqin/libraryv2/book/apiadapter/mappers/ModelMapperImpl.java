package com.tinqin.libraryv2.book.apiadapter.mappers;

import com.tinqin.libraryv2.book.api.models.ApiAlgoLibBookModel;
import com.tinqin.libraryv2.book.api.models.ApiAuthorBaseModel;
import com.tinqin.libraryv2.book.api.models.ApiAuthorModel;
import com.tinqin.libraryv2.book.api.models.ApiBookBaseModel;
import com.tinqin.libraryv2.book.api.models.ApiBookModel;
import com.tinqin.libraryv2.book.api.models.ApiError;
import com.tinqin.libraryv2.book.api.models.ApiGoogleBooksBookModel;
import com.tinqin.libraryv2.book.api.models.ApiOpenLibBookModel;
import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorAuthorBaseModel;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorAuthorModel;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookAlgoLibModel;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookBaseModel;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookGoogleBooksModel;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookModel;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookOpenLibModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-27T12:31:34+0200",
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
        apiBookModel.createdOn( output.getCreatedOn() );
        apiBookModel.updatedOn( output.getUpdatedOn() );
        apiBookModel.authors( processorAuthorBaseModelListToApiAuthorBaseModelList( output.getAuthors() ) );
        List<String> list1 = output.getCategories();
        if ( list1 != null ) {
            apiBookModel.categories( new ArrayList<String>( list1 ) );
        }

        return apiBookModel.build();
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

    @Override
    public ApiOpenLibBookModel toOpenLibBook(ProcessorBookOpenLibModel output) {
        if ( output == null ) {
            return null;
        }

        ApiOpenLibBookModel.ApiOpenLibBookModelBuilder apiOpenLibBookModel = ApiOpenLibBookModel.builder();

        apiOpenLibBookModel.title( output.getTitle() );
        apiOpenLibBookModel.publishYear( output.getPublishYear() );
        apiOpenLibBookModel.pages( output.getPages() );
        String[] authorNames = output.getAuthorNames();
        if ( authorNames != null ) {
            apiOpenLibBookModel.authorNames( Arrays.copyOf( authorNames, authorNames.length ) );
        }
        String[] authorKeys = output.getAuthorKeys();
        if ( authorKeys != null ) {
            apiOpenLibBookModel.authorKeys( Arrays.copyOf( authorKeys, authorKeys.length ) );
        }
        String[] isbns = output.getIsbns();
        if ( isbns != null ) {
            apiOpenLibBookModel.isbns( Arrays.copyOf( isbns, isbns.length ) );
        }
        String[] subjects = output.getSubjects();
        if ( subjects != null ) {
            apiOpenLibBookModel.subjects( Arrays.copyOf( subjects, subjects.length ) );
        }
        String[] subjectKeys = output.getSubjectKeys();
        if ( subjectKeys != null ) {
            apiOpenLibBookModel.subjectKeys( Arrays.copyOf( subjectKeys, subjectKeys.length ) );
        }

        return apiOpenLibBookModel.build();
    }

    @Override
    public List<ApiOpenLibBookModel> toOpenLibBooks(List<ProcessorBookOpenLibModel> output) {
        if ( output == null ) {
            return null;
        }

        List<ApiOpenLibBookModel> list = new ArrayList<ApiOpenLibBookModel>( output.size() );
        for ( ProcessorBookOpenLibModel processorBookOpenLibModel : output ) {
            list.add( toOpenLibBook( processorBookOpenLibModel ) );
        }

        return list;
    }

    @Override
    public ApiAlgoLibBookModel toAlgoLibBook(ProcessorBookAlgoLibModel output) {
        if ( output == null ) {
            return null;
        }

        ApiAlgoLibBookModel.ApiAlgoLibBookModelBuilder apiAlgoLibBookModel = ApiAlgoLibBookModel.builder();

        apiAlgoLibBookModel.title( output.getTitle() );
        String[] authors = output.getAuthors();
        if ( authors != null ) {
            apiAlgoLibBookModel.authors( Arrays.copyOf( authors, authors.length ) );
        }
        apiAlgoLibBookModel.description( output.getDescription() );
        apiAlgoLibBookModel.pages( output.getPages() );
        apiAlgoLibBookModel.publishYear( output.getPublishYear() );
        apiAlgoLibBookModel.imgUrl( output.getImgUrl() );
        String[] categories = output.getCategories();
        if ( categories != null ) {
            apiAlgoLibBookModel.categories( Arrays.copyOf( categories, categories.length ) );
        }
        String[] isbns = output.getIsbns();
        if ( isbns != null ) {
            apiAlgoLibBookModel.isbns( Arrays.copyOf( isbns, isbns.length ) );
        }

        return apiAlgoLibBookModel.build();
    }

    @Override
    public List<ApiAlgoLibBookModel> toAlgoLibBooks(List<ProcessorBookAlgoLibModel> output) {
        if ( output == null ) {
            return null;
        }

        List<ApiAlgoLibBookModel> list = new ArrayList<ApiAlgoLibBookModel>( output.size() );
        for ( ProcessorBookAlgoLibModel processorBookAlgoLibModel : output ) {
            list.add( toAlgoLibBook( processorBookAlgoLibModel ) );
        }

        return list;
    }

    @Override
    public ApiGoogleBooksBookModel toGoogleBooksBook(ProcessorBookGoogleBooksModel output) {
        if ( output == null ) {
            return null;
        }

        ApiGoogleBooksBookModel.ApiGoogleBooksBookModelBuilder apiGoogleBooksBookModel = ApiGoogleBooksBookModel.builder();

        apiGoogleBooksBookModel.externalId( output.getExternalId() );
        apiGoogleBooksBookModel.title( output.getTitle() );
        apiGoogleBooksBookModel.description( output.getDescription() );
        String[] authors = output.getAuthors();
        if ( authors != null ) {
            apiGoogleBooksBookModel.authors( Arrays.copyOf( authors, authors.length ) );
        }
        apiGoogleBooksBookModel.pageCount( output.getPageCount() );
        String[] categories = output.getCategories();
        if ( categories != null ) {
            apiGoogleBooksBookModel.categories( Arrays.copyOf( categories, categories.length ) );
        }
        apiGoogleBooksBookModel.imgUrl( output.getImgUrl() );
        String[] isbns = output.getIsbns();
        if ( isbns != null ) {
            apiGoogleBooksBookModel.isbns( Arrays.copyOf( isbns, isbns.length ) );
        }

        return apiGoogleBooksBookModel.build();
    }

    @Override
    public List<ApiGoogleBooksBookModel> toGoogleBooksBooks(List<ApiGoogleBooksBookModel> output) {
        if ( output == null ) {
            return null;
        }

        List<ApiGoogleBooksBookModel> list = new ArrayList<ApiGoogleBooksBookModel>( output.size() );
        for ( ApiGoogleBooksBookModel apiGoogleBooksBookModel : output ) {
            list.add( apiGoogleBooksBookModel );
        }

        return list;
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
