package com.tinqin.libraryv2.book.apiadapter;


import com.tinqin.libraryv2.book.api.models.ApiError;
import com.tinqin.libraryv2.book.api.operations.getBook.ApiGetBookInput;
import com.tinqin.libraryv2.book.api.operations.getBook.ApiGetBookOutput;
import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.mappers.GetBookMapper;
import com.tinqin.libraryv2.book.apiadapter.mappers.ModelMapper;
import com.tinqin.libraryv2.book.apiadapter.operations.getbook.GetBook;
import com.tinqin.libraryv2.book.apiadapter.operations.getbook.ProcessorGetBookInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getbook.ProcessorGetBookOutput;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiAdapter {
    //Mappers
    private final ModelMapper modelMapper;
    private final GetBookMapper getBookMapper;

    //Processors
    private final GetBook getBook;


    public Either<ApiError, ApiGetBookOutput> getBook(ApiGetBookInput apiInput) {
        ProcessorGetBookInput operationInput = getBookMapper.toOperation(apiInput);

        Either<OperationError, ProcessorGetBookOutput> processed = getBook.process(operationInput);

        return processed.isRight()
                ? Either.right(getBookMapper.toApiResult(processed.get()))
                : Either.left(modelMapper.toApiError(processed.getLeft()));
    }
}
