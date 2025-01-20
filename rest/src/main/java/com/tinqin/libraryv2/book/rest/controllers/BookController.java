package com.tinqin.libraryv2.book.rest.controllers;

import com.tinqin.libraryv2.book.api.ApiRoutes;
import com.tinqin.libraryv2.book.api.models.ApiError;
import com.tinqin.libraryv2.book.api.operations.getBook.ApiGetBookInput;
import com.tinqin.libraryv2.book.api.operations.getBook.ApiGetBookOutput;
import com.tinqin.libraryv2.book.apiadapter.ApiAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController extends BaseController {

   private final ApiAdapter apiAdapter;


    @Operation(summary = "Get book by UUID",
            description = "Get book details by UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found")})

    @GetMapping(ApiRoutes.API_BOOKS_ID)
    public ResponseEntity<?> getBook(@PathVariable("bookId") String bookId) {
        ApiGetBookInput bookInput = ApiGetBookInput
                .builder()
                .bookId(bookId)
                .build();
        Either<ApiError, ApiGetBookOutput> result = apiAdapter.getBook(bookInput);
        return  mapToResponseEntity(result,HttpStatus.OK);
    }
}
