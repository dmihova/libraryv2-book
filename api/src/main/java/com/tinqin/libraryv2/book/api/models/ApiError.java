package com.tinqin.libraryv2.book.api.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ApiError {
    private HttpStatus status;
    private String errorCode;
    private String message;

}
