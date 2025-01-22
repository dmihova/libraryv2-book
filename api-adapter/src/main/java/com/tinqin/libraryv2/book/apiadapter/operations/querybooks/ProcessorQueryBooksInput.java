package com.tinqin.libraryv2.book.apiadapter.operations.querybooks;

import com.tinqin.libraryv2.book.apiadapter.operations.base.ProcessorInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;


@Builder
@Getter
@AllArgsConstructor
@Setter
public class ProcessorQueryBooksInput implements ProcessorInput {

    private String title;
    private String authorId;
    private BigDecimal priceMin;
    private BigDecimal priceMax;
    private BigDecimal pricePerRentalMin;
    private BigDecimal pricePerRentalMax;
    private Integer stockMin ;
    private Integer stockMax ;
    private Boolean isDeleted;
    private Integer page ;
}
