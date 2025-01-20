package com.tinqin.libraryv2.book.core.errorhandler.components;



import com.tinqin.libraryv2.book.apiadapter.errors.BeError;
import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.core.errorhandler.exceptions.BusinessException;
import com.tinqin.libraryv2.book.core.errorhandler.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class BusinessErrorHandlerComponent extends BaseErrorHandlerComponent {

    @Override
    public OperationError handle(Throwable throwable) {

        if (throwable instanceof BusinessException exception) {
            return BeError
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorCode("BE-001")
                    .message(exception.getMessage())
                    .build();
        }
        if (throwable instanceof NotFoundException exception) {
            return BeError
                    .builder()
                    .status(HttpStatus.NOT_FOUND)
                    .errorCode("BE-002")
                    .message(exception.getMessage())
                    .build();
        }
        return getNext().handle(throwable);
    }
}
