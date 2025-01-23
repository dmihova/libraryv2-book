package com.tinqin.libraryv2.book.rest.controllers;

import com.tinqin.libraryv2.book.api.ApiRoutes;
import com.tinqin.libraryv2.book.api.models.ApiError;
import com.tinqin.libraryv2.book.api.operations.getbook.ApiGetBookInput;
import com.tinqin.libraryv2.book.api.operations.getbook.ApiGetBookOutput;
import com.tinqin.libraryv2.book.api.operations.getbookopenlibfirst.ApiGetBookOpenLibFirstInput;
import com.tinqin.libraryv2.book.api.operations.getbookopenlibfirst.ApiGetBookOpenLibFirstOutput;
import com.tinqin.libraryv2.book.api.operations.getbooksopenlib.ApiGetBooksOpenLibInput;
import com.tinqin.libraryv2.book.api.operations.getbooksopenlib.ApiGetBooksOpenLibOutput;
import com.tinqin.libraryv2.book.api.operations.querybooks.ApiQueryBooksInput;
import com.tinqin.libraryv2.book.api.operations.querybooks.ApiQueryBooksOutput;
import com.tinqin.libraryv2.book.api.operations.querybooksopenlib.ApiQueryBooksOpenLibInput;
import com.tinqin.libraryv2.book.api.operations.querybooksopenlib.ApiQueryBooksOpenLibOutput;
import com.tinqin.libraryv2.book.apiadapter.ApiAdapterBook;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class BookController extends BaseController {

    private final ApiAdapterBook apiAdapterBook;


    @GetMapping(ApiRoutes.API_BOOKS)
    public ResponseEntity<?> queryBooks(
            @RequestParam(name = "title", required = false, defaultValue = "") String title,
            @RequestParam(name = "titleLike", required = false, defaultValue = "") String titleLike,
            @Valid @RequestParam(name = "authorId", required = false) String authorId,
            @Valid @RequestParam(name = "authorFirstName", required = false) String authorFirstName,
            @Valid @RequestParam(name = "authorFirstNameLike", required = false) String authorFirstNameLike,
            @Valid @RequestParam(name = "authorLastName", required = false) String authorLastName,
            @Valid @RequestParam(name = "authorLastNameLike", required = false) String authorLastNameLike,
            @Valid @RequestParam(name = "page", required = false, defaultValue = "0") Integer page
    ) {
        ApiQueryBooksInput bookInput = ApiQueryBooksInput
                .builder()
                .title(title)
                .titleLike(titleLike)
                .authorId(authorId)
                .authorFirstName(authorFirstName)
                .authorFirstNameLike(authorFirstNameLike)
                .authorLastName(authorLastName)
                .authorLastNameLike(authorLastNameLike)
                .page(page)
                .build();
        Either<ApiError, ApiQueryBooksOutput> result = apiAdapterBook.queryBooks(bookInput);
        return mapToResponseEntity(result, HttpStatus.OK);
    }
    @GetMapping(ApiRoutes.API_BOOKS_QUERY_OPEN_LIB)
    public ResponseEntity<?> queryBooksOpenLib(
            @Valid @RequestParam(name = "title", required = true ) String title,
            @Valid @RequestParam(name = "author", required = true) String author ,
            @Valid @RequestParam(name = "page", required = false, defaultValue = "1") Integer page

    ) {
        ApiQueryBooksOpenLibInput bookInput = ApiQueryBooksOpenLibInput
                .builder()
                .title(title)
                .author (author )
                .page(page)
                .build();
        Either<ApiError, ApiQueryBooksOpenLibOutput> result = apiAdapterBook.queryBooksOpenLib(bookInput);
        return mapToResponseEntity(result, HttpStatus.OK);
    }



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
        Either<ApiError, ApiGetBookOutput> result = apiAdapterBook.getBook(bookInput);
        return mapToResponseEntity(result, HttpStatus.OK);
    }


    @GetMapping(ApiRoutes.API_BOOKS_OPEN_LIB)
    public ResponseEntity<?> getBookOpenLibFirst(@PathVariable("bookId") String bookId) {
        ApiGetBookOpenLibFirstInput bookInput = ApiGetBookOpenLibFirstInput
                .builder()
                .bookId(bookId)
                .build();
        Either<ApiError, ApiGetBookOpenLibFirstOutput> result = apiAdapterBook.getBookOpenLibFirst(bookInput);
        return mapToResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping(ApiRoutes.API_BOOKS_OPEN_LIB_ALL)
    public ResponseEntity<?> getBooksOpenLib(@PathVariable("bookId") String bookId,
                                             @RequestParam(name = "page", required = false, defaultValue = "1") Integer page
    ) {
        ApiGetBooksOpenLibInput bookInput = ApiGetBooksOpenLibInput
                .builder()
                .bookId(bookId)
                .page(page)
                .build();
        Either<ApiError, ApiGetBooksOpenLibOutput> result = apiAdapterBook.getBooksOpenLib(bookInput);
        return mapToResponseEntity(result, HttpStatus.OK);

    }



}
