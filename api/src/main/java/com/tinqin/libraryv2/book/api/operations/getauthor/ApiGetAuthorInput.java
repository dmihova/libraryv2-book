package com.tinqin.libraryv2.book.api.operations.getauthor;

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
public class ApiGetAuthorInput extends ApiInput {

    @UUID
    @NotBlank(message =AUTHOR_ID_CANNOT_BE_NULL)
    private String authorId;
}
