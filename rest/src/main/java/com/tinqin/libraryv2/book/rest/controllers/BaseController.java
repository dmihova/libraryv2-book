package com.tinqin.libraryv2.book.rest.controllers;


import com.tinqin.libraryv2.book.api.models.ApiError;
import com.tinqin.libraryv2.book.api.models.base.ApiOutput;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class BaseController {
    public BaseController() {

    }

    protected <O extends ApiOutput> ResponseEntity<?> mapToResponseEntity(
            Either<ApiError, O> either, HttpStatus httpStatus) {

        return either.isRight()
                ? new ResponseEntity((ApiOutput) either.get(), httpStatus)
                : new ResponseEntity(((ApiError) either.getLeft()), httpStatus);
    }

}
