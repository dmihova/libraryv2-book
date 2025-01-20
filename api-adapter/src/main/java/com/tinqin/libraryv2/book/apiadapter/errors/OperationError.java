package com.tinqin.libraryv2.book.apiadapter.errors;



import org.springframework.http.HttpStatus;

public interface OperationError {

    HttpStatus getStatus();

    String getErrorCode();

    String getMessage();

}
