package com.tinqin.libraryv2.book.api.operations.getauthorbooksgooglebooks;

import com.tinqin.libraryv2.book.api.models.base.ApiInput;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

import static com.tinqin.libraryv2.book.api.ValidationMessages.AUTHOR_ID_CANNOT_BE_NULL;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ApiGetAuthorBooksGoogleBooksInput extends ApiInput {

    @UUID
    @NotBlank(message =AUTHOR_ID_CANNOT_BE_NULL)
    private String authorId;

    @Builder.Default
    private Integer page =0;

}
