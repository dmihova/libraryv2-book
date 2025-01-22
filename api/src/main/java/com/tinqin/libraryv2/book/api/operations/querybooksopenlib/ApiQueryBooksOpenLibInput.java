package com.tinqin.libraryv2.book.api.operations.querybooksopenlib;

import com.tinqin.libraryv2.book.api.models.base.ApiInput;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ApiQueryBooksOpenLibInput extends ApiInput {

    @Length(min = 1)
    private String title;
    @Length(min = 1)
    private String author;


    @Builder.Default
    @Min(value = 1)
    private Integer page =1;
}
