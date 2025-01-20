package com.tinqin.libraryv2.book.core.errorhandler.components;


import com.tinqin.libraryv2.book.apiadapter.errors.BeError;
import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class InternalErrorHandlerComponent extends BaseErrorHandlerComponent {
    @Override
    public OperationError handle(Throwable throwable) {
        return BeError
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorCode("IE-001")
                .message(throwable.getMessage())
                .build();
    }
}
