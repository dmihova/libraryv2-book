package com.tinqin.libraryv2.book.api.operations.querybooks;

import com.tinqin.libraryv2.book.api.models.base.ApiInput;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ApiQueryBooksInput extends ApiInput {

    private String title;
    private String titleLike;

    @UUID
    private String authorId;

    private String authorFirstName;
    private String authorLastName;
    private String authorFirstNameLike;
    private String authorLastNameLike;

    @Builder.Default
    @Min(value = 0)
    private Integer page  =0;
}
