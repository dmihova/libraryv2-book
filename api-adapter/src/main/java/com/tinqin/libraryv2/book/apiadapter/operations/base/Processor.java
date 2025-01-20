package com.tinqin.libraryv2.book.apiadapter.operations.base;



import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import io.vavr.control.Either;

public interface Processor <R extends ProcessorOutput, I extends ProcessorInput>{
    Either<OperationError, R> process(I input);
}
