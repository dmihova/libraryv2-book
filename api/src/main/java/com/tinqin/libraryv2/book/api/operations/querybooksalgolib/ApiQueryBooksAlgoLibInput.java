package com.tinqin.libraryv2.book.api.operations.querybooksalgolib;

import com.tinqin.libraryv2.book.api.models.base.ApiInput;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
public class ApiQueryBooksAlgoLibInput extends ApiInput {

    @Length(min = 1)
    private String title;
    @Length(min = 1)
    private String author;
    @Length(min = 1)
    private String authorLike;

}
