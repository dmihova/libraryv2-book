package com.tinqin.libraryv2.book.apiadapter;


import com.tinqin.libraryv2.book.api.models.ApiError;
import com.tinqin.libraryv2.book.api.operations.getbook.ApiGetBookInput;
import com.tinqin.libraryv2.book.api.operations.getbook.ApiGetBookOutput;
import com.tinqin.libraryv2.book.api.operations.getbookopenlibfirst.ApiGetBookOpenLibFirstInput;
import com.tinqin.libraryv2.book.api.operations.getbookopenlibfirst.ApiGetBookOpenLibFirstOutput;
import com.tinqin.libraryv2.book.api.operations.getbooksopenlib.ApiGetBooksOpenLibInput;
import com.tinqin.libraryv2.book.api.operations.getbooksopenlib.ApiGetBooksOpenLibOutput;
import com.tinqin.libraryv2.book.api.operations.querybooks.ApiQueryBooksInput;
import com.tinqin.libraryv2.book.api.operations.querybooks.ApiQueryBooksOutput;
import com.tinqin.libraryv2.book.api.operations.querybooksalgolib.ApiQueryBooksAlgoLibInput;
import com.tinqin.libraryv2.book.api.operations.querybooksalgolib.ApiQueryBooksAlgoLibOutput;
import com.tinqin.libraryv2.book.api.operations.querybooksopenlib.ApiQueryBooksOpenLibInput;
import com.tinqin.libraryv2.book.api.operations.querybooksopenlib.ApiQueryBooksOpenLibOutput;
import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.mappers.*;
import com.tinqin.libraryv2.book.apiadapter.operations.getbook.GetBook;
import com.tinqin.libraryv2.book.apiadapter.operations.getbook.ProcessorGetBookInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getbook.ProcessorGetBookOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.getbookopenlibfirst.GetBookOpenLibFirst;
import com.tinqin.libraryv2.book.apiadapter.operations.getbookopenlibfirst.ProcessorGetBookOpenLibFirstInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getbookopenlibfirst.ProcessorGetBookOpenLibFirstOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.getbooksopenlib.GetBooksOpenLib;
import com.tinqin.libraryv2.book.apiadapter.operations.getbooksopenlib.ProcessorGetBooksOpenLibInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getbooksopenlib.ProcessorGetBooksOpenLibOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooks.ProcessorQueryBooksInput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooks.ProcessorQueryBooksOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooks.QueryBooks;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooksalgolib.ProcessorQueryBooksAlgoLibInput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooksalgolib.ProcessorQueryBooksAlgoLibOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooksalgolib.QueryBooksAlgoLib;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooksopenlib.ProcessorQueryBooksOpenLibInput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooksopenlib.ProcessorQueryBooksOpenLibOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.querybooksopenlib.QueryBooksOpenLib;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiAdapterBook {
    //Mappers
    private final ModelMapper modelMapper;
    private final GetBookMapper getBookMapper;
    private final QueryBooksMapper queryBooksMapper;
    private final GetBookOpenLibFirstMapper getBookOpenLibFirstMapper;
    private final GetBooksOpenLibMapper getBooksOpenLibMapper;
    private final QueryBooksOpenLibMapper queryBooksOpenLibMapper;
    private final QueryBooksAlgoLibMapper queryBooksAlgoLibMapper;
    //Processors
    private final GetBook getBook;
    private final QueryBooks queryBooks;
    private final GetBookOpenLibFirst getBookOpenLibFirst;
    private final GetBooksOpenLib getBooksOpenLib;
    private final QueryBooksOpenLib queryBooksOpenLib;
    private final QueryBooksAlgoLib queryBooksAlgoLib;

    public Either<ApiError, ApiQueryBooksOutput> queryBooks(ApiQueryBooksInput  apiInput) {
        ProcessorQueryBooksInput operationInput = queryBooksMapper.toOperation(apiInput);

        Either<OperationError, ProcessorQueryBooksOutput> processed = queryBooks.process(operationInput);

        return processed.isRight()
                ? Either.right(queryBooksMapper.toApiResult(processed.get()))
                : Either.left(modelMapper.toApiError(processed.getLeft()));
    }


    public Either<ApiError, ApiGetBookOutput> getBook(ApiGetBookInput apiInput) {
        ProcessorGetBookInput operationInput = getBookMapper.toOperation(apiInput);

        Either<OperationError, ProcessorGetBookOutput> processed = getBook.process(operationInput);

        return processed.isRight()
                ? Either.right(getBookMapper.toApiResult(processed.get()))
                : Either.left(modelMapper.toApiError(processed.getLeft()));
    }


    public Either<ApiError, ApiGetBookOpenLibFirstOutput> getBookOpenLibFirst(ApiGetBookOpenLibFirstInput apiInput) {
        ProcessorGetBookOpenLibFirstInput operationInput = getBookOpenLibFirstMapper.toOperation(apiInput);

        Either<OperationError, ProcessorGetBookOpenLibFirstOutput> processed = getBookOpenLibFirst.process(operationInput);

        return processed.isRight()
                ? Either.right(getBookOpenLibFirstMapper.toApiResult(processed.get()))
                : Either.left(modelMapper.toApiError(processed.getLeft()));
    }

    public Either<ApiError, ApiGetBooksOpenLibOutput> getBooksOpenLib(ApiGetBooksOpenLibInput apiInput) {
        ProcessorGetBooksOpenLibInput operationInput = getBooksOpenLibMapper.toOperation(apiInput);

        Either<OperationError, ProcessorGetBooksOpenLibOutput> processed = getBooksOpenLib.process(operationInput);
        return processed.isRight()
                ? Either.right(getBooksOpenLibMapper.toApiResult(processed.get()))
                : Either.left(modelMapper.toApiError(processed.getLeft()));
    }


    public Either<ApiError, ApiQueryBooksOpenLibOutput> queryBooksOpenLib(ApiQueryBooksOpenLibInput apiInput) {
        ProcessorQueryBooksOpenLibInput operationInput = queryBooksOpenLibMapper.toOperation(apiInput);

        Either<OperationError, ProcessorQueryBooksOpenLibOutput> processed = queryBooksOpenLib.process(operationInput);

        return processed.isRight()
                ? Either.right(queryBooksOpenLibMapper.toApiResult(processed.get()))
                : Either.left(modelMapper.toApiError(processed.getLeft()));
    }

    public Either<ApiError, ApiQueryBooksAlgoLibOutput> queryBooksAlgoLib(ApiQueryBooksAlgoLibInput apiInput) {
        ProcessorQueryBooksAlgoLibInput operationInput = queryBooksAlgoLibMapper.toOperation(apiInput);

        Either<OperationError, ProcessorQueryBooksAlgoLibOutput> processed = queryBooksAlgoLib.process(operationInput);

        return processed.isRight()
                ? Either.right(queryBooksAlgoLibMapper.toApiResult(processed.get()))
                : Either.left(modelMapper.toApiError(processed.getLeft()));
    }
}
