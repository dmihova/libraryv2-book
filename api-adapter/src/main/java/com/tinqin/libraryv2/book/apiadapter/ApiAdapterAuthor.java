package com.tinqin.libraryv2.book.apiadapter;

import com.tinqin.libraryv2.book.api.models.ApiError;
import com.tinqin.libraryv2.book.api.operations.getauthor.ApiGetAuthorInput;
import com.tinqin.libraryv2.book.api.operations.getauthor.ApiGetAuthorOutput;
import com.tinqin.libraryv2.book.api.operations.getauthorbooksopenlib.ApiGetAuthorBooksOpenLibInput;
import com.tinqin.libraryv2.book.api.operations.getauthorbooksopenlib.ApiGetAuthorBooksOpenLibOutput;
import com.tinqin.libraryv2.book.api.operations.postauthorbooksopenlib.ApiPostAuthorBooksOpenLibInput;
import com.tinqin.libraryv2.book.api.operations.postauthorbooksopenlib.ApiPostAuthorBooksOpenLibOutput;
import com.tinqin.libraryv2.book.api.operations.queryauthors.ApiQueryAuthorsInput;
import com.tinqin.libraryv2.book.api.operations.queryauthors.ApiQueryAuthorsOutput;
import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.mappers.*;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthor.GetAuthor;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthor.ProcessorGetAuthorInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthor.ProcessorGetAuthorOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksopenlib.GetAuthorBooksOpenLib;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksopenlib.ProcessorGetAuthorBooksOpenLibInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksopenlib.ProcessorGetAuthorBooksOpenLibOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.postauthorbooksopenlib.PostAuthorBooksOpenLib;
import com.tinqin.libraryv2.book.apiadapter.operations.postauthorbooksopenlib.ProcessorPostAuthorBooksOpenLibInput;
import com.tinqin.libraryv2.book.apiadapter.operations.postauthorbooksopenlib.ProcessorPostAuthorBooksOpenLibOutput;
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
    private final GetAuthorBooksOpenLibMapper getAuthorBooksOpenLibMapper;
    private final PostAuthorBooksOpenLibMapper postAuthorBooksOpenLibMapper;

    //Processors
    private final QueryAuthors queryAuthors;
    private final GetAuthor getAuthor;
    private final GetAuthorBooksOpenLib getAuthorBooksOpenLib;
    private final PostAuthorBooksOpenLib postAuthorBooksOpenLib;


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

    public Either<ApiError, ApiGetAuthorBooksOpenLibOutput> getAuthorBooksOpenLib(ApiGetAuthorBooksOpenLibInput apiInput) {
        ProcessorGetAuthorBooksOpenLibInput operationInput = getAuthorBooksOpenLibMapper.toOperation(apiInput);

        Either<OperationError, ProcessorGetAuthorBooksOpenLibOutput> processed = getAuthorBooksOpenLib.process(operationInput);

        return processed.isRight()
                ? Either.right(getAuthorBooksOpenLibMapper.toApiResult(processed.get()))
                : Either.left(modelMapper.toApiError(processed.getLeft()));

    }
    public Either<ApiError, ApiPostAuthorBooksOpenLibOutput> postAuthorBooksOpenLib(ApiPostAuthorBooksOpenLibInput apiInput) {
        ProcessorPostAuthorBooksOpenLibInput operationInput = postAuthorBooksOpenLibMapper.toOperation(apiInput);

        Either<OperationError, ProcessorPostAuthorBooksOpenLibOutput> processed = postAuthorBooksOpenLib.process(operationInput);

        return processed.isRight()
                ? Either.right(postAuthorBooksOpenLibMapper.toApiResult(processed.get()))
                : Either.left(modelMapper.toApiError(processed.getLeft()));

    }

}
