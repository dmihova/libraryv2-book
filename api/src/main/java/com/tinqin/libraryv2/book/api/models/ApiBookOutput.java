package com.tinqin.libraryv2.book.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tinqin.libraryv2.book.api.operations.base.ApiOutput;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ApiBookOutput extends ApiOutput {

    private String title;
    private String bookId;
    private String description;
    private String publishYear;
    private Integer pages;
    private BigDecimal price;
    private BigDecimal pricePerRental;
    private Integer stock;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdOn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime updatedOn;

    private Boolean isDeleted;
    private List<ApiAuthorOutput> authors;



}
