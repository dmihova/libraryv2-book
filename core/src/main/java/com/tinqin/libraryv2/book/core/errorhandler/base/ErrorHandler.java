package com.tinqin.libraryv2.book.core.errorhandler.base;


import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;

public interface ErrorHandler {

    OperationError handle(Throwable throwable);
}
