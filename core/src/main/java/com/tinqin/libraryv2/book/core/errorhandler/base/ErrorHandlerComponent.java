package com.tinqin.libraryv2.book.core.errorhandler.base;


import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;

public interface ErrorHandlerComponent {

    OperationError handle(Throwable throwable);

    ErrorHandlerComponent getNext();

    void setNext(ErrorHandlerComponent next);
}
