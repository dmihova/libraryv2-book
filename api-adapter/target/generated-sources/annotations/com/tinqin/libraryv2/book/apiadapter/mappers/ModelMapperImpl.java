package com.tinqin.libraryv2.book.apiadapter.mappers;

import com.tinqin.libraryv2.book.api.models.ApiAuthorOutput;
import com.tinqin.libraryv2.book.api.models.ApiBookOutput;
import com.tinqin.libraryv2.book.api.models.ApiError;
import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorAuthorModel;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-20T17:57:46+0200",
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
    public ApiBookOutput toBook(ProcessorBookModel output) {
        if ( output == null ) {
            return null;
        }

        ApiBookOutput.ApiBookOutputBuilder apiBookOutput = ApiBookOutput.builder();

        apiBookOutput.title( output.getTitle() );
        apiBookOutput.bookId( output.getBookId() );
        apiBookOutput.description( output.getDescription() );
        apiBookOutput.publishYear( output.getPublishYear() );
        apiBookOutput.pages( output.getPages() );
        apiBookOutput.authors( processorAuthorModelListToApiAuthorOutputList( output.getAuthors() ) );

        return apiBookOutput.build();
    }

    @Override
    public ApiAuthorOutput toAuthor(ProcessorAuthorModel output) {
        if ( output == null ) {
            return null;
        }

        ApiAuthorOutput.ApiAuthorOutputBuilder apiAuthorOutput = ApiAuthorOutput.builder();

        apiAuthorOutput.authorId( output.getAuthorId() );
        apiAuthorOutput.firstName( output.getFirstName() );
        apiAuthorOutput.lastName( output.getLastName() );

        return apiAuthorOutput.build();
    }

    protected List<ApiAuthorOutput> processorAuthorModelListToApiAuthorOutputList(List<ProcessorAuthorModel> list) {
        if ( list == null ) {
            return null;
        }

        List<ApiAuthorOutput> list1 = new ArrayList<ApiAuthorOutput>( list.size() );
        for ( ProcessorAuthorModel processorAuthorModel : list ) {
            list1.add( toAuthor( processorAuthorModel ) );
        }

        return list1;
    }
}
