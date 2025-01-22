package com.tinqin.libraryv2.book.core.queries.filters;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class QueryBookFilter   {

    private String title;
    private String titleLike;

    private UUID authorId;
    private String authorFirstName;
    private String authorLastName;
    private String authorFirstNameLike;
    private String authorLastNameLike;

    private BigDecimal priceMin;
    private BigDecimal priceMax;
    private BigDecimal pricePerRentalMin;
    private BigDecimal pricePerRentalMax;
    private Integer stockMin ;
    private Integer stockMax ;
    private Boolean isDeleted;

    private Integer pageMin ;
    private Integer pageMax ;

    private LocalDate createDateMin ;
    private LocalDate createDateMax ;



}
