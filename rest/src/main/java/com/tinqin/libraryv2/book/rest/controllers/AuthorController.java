package com.tinqin.libraryv2.book.rest.controllers;

import com.tinqin.libraryv2.book.api.ApiRoutes;
import com.tinqin.libraryv2.book.api.models.ApiError;
import com.tinqin.libraryv2.book.api.operations.getauthor.ApiGetAuthorInput;
import com.tinqin.libraryv2.book.api.operations.getauthor.ApiGetAuthorOutput;
import com.tinqin.libraryv2.book.api.operations.getbook.ApiGetBookInput;
import com.tinqin.libraryv2.book.api.operations.getbook.ApiGetBookOutput;
import com.tinqin.libraryv2.book.api.operations.queryauthors.ApiQueryAuthorsInput;
import com.tinqin.libraryv2.book.api.operations.queryauthors.ApiQueryAuthorsOutput;
import com.tinqin.libraryv2.book.apiadapter.ApiAdapterAuthor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthorController extends BaseController {

    private final ApiAdapterAuthor apiAdapterAuthor;


    @GetMapping(ApiRoutes.API_AUTHORS)
    public ResponseEntity<?> queryAuthors(
            @Valid @RequestParam(name = "firstName", required = false ) String firstName,
            @Valid @RequestParam(name = "lastName", required = false) String lastName ,
            @Valid @RequestParam(name = "firstNameLike", required = false ) String firstNameLike,
            @Valid @RequestParam(name = "lastNameLike", required = false) String lastNameLike ,
            @Valid @RequestParam(name = "page", required = false, defaultValue = "0") Integer page

    ) {
        ApiQueryAuthorsInput apiInput = ApiQueryAuthorsInput
                .builder()
                .firstNameLike(firstNameLike)
                .lastNameLike(lastNameLike)
                .firstName(firstName)
                .lastName(lastName)
                .page(page)
                .build();
        Either<ApiError, ApiQueryAuthorsOutput> result = apiAdapterAuthor.queryAuthors(apiInput);
        return mapToResponseEntity(result, HttpStatus.OK);
    }



    @Operation(summary = "Get author by UUID",
            description = "Get author details by UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found")})

    @GetMapping(ApiRoutes.API_AUTHORS_ID)
    public ResponseEntity<?> getBook(@PathVariable("authorId") String authorId) {
        ApiGetAuthorInput apiInput = ApiGetAuthorInput
                .builder()
                .authorId(authorId)
                .build();
        Either<ApiError, ApiGetAuthorOutput> result = apiAdapterAuthor.getAuthor(apiInput);
        return mapToResponseEntity(result, HttpStatus.OK);
    }
}
