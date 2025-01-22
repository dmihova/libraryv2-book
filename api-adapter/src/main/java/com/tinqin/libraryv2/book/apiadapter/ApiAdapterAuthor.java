package com.tinqin.libraryv2.book.apiadapter;

import com.tinqin.libraryv2.book.api.models.ApiError;
import com.tinqin.libraryv2.book.api.operations.getauthor.ApiGetAuthorInput;
import com.tinqin.libraryv2.book.api.operations.getauthor.ApiGetAuthorOutput;
import com.tinqin.libraryv2.book.api.operations.queryauthors.ApiQueryAuthorsInput;
import com.tinqin.libraryv2.book.api.operations.queryauthors.ApiQueryAuthorsOutput;
import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.mappers.GetAuthorMapper;
import com.tinqin.libraryv2.book.apiadapter.mappers.ModelMapper;
import com.tinqin.libraryv2.book.apiadapter.mappers.QueryAuthorsMapper;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthor.GetAuthor;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthor.ProcessorGetAuthorInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthor.ProcessorGetAuthorOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.queryauthors.ProcessorQueryAuthorsInput;
import com.tinqin.libraryv2.book.apiadapter.operations.queryauthors.ProcessorQueryAuthorsOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.queryauthors.QueryAuthors;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiAdapterAuthor {
    //Mappers
    private final ModelMapper modelMapper;
    private final QueryAuthorsMapper queryAuthorsMapper;
    private final GetAuthorMapper getAuthorMapper;

    //Processors
    private final QueryAuthors queryAuthors;
    private final GetAuthor getAuthor;



    public Either<ApiError, ApiQueryAuthorsOutput> queryAuthors(ApiQueryAuthorsInput apiInput) {
        ProcessorQueryAuthorsInput operationInput = queryAuthorsMapper.toOperation(apiInput);

        Either<OperationError, ProcessorQueryAuthorsOutput> processed = queryAuthors.process(operationInput);

        return processed.isRight()
                ? Either.right(queryAuthorsMapper.toApiResult(processed.get()))
                : Either.left(modelMapper.toApiError(processed.getLeft()));
    }


    public Either<ApiError, ApiGetAuthorOutput> getAuthor(ApiGetAuthorInput apiInput) {
        ProcessorGetAuthorInput operationInput = getAuthorMapper.toOperation(apiInput);

        Either<OperationError, ProcessorGetAuthorOutput> processed = getAuthor.process(operationInput);

        return processed.isRight()
                ? Either.right(getAuthorMapper.toApiResult(processed.get()))
                : Either.left(modelMapper.toApiError(processed.getLeft()));

    }
}
