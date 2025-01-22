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

    @UUID
    private String authorId;

    @DecimalMin(value ="0")
    private BigDecimal priceMin;
    @DecimalMin(value ="0")
    private BigDecimal priceMax;
    @DecimalMin(value ="0")
    private BigDecimal pricePerRentalMin;
    @DecimalMin(value ="0")
    private BigDecimal pricePerRentalMax;
    @DecimalMin(value ="0")
    private Integer stockMin ;
    @DecimalMin(value ="0")
    private Integer stockMax ;

    private Boolean isDeleted;

    @Builder.Default
    @Min(value = 0)
    private Integer page  =0;
}
