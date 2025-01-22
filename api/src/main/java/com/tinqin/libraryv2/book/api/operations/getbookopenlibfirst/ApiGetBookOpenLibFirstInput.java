package com.tinqin.libraryv2.book.api.operations.getbookopenlibfirst;

import com.tinqin.libraryv2.book.api.models.base.ApiInput;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

import static com.tinqin.libraryv2.book.api.ValidationMessages.BOOK_ID_CANNOT_BE_NULL;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ApiGetBookOpenLibFirstInput extends ApiInput {

    @UUID
    @NotBlank(message =BOOK_ID_CANNOT_BE_NULL)
    private String bookId;
}
