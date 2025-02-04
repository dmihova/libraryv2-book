package com.tinqin.libraryv2.book.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tinqin.libraryv2.book.api.models.base.ApiOutput;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ApiBookModel extends ApiOutput {

    private String title;
    private String subtitle;
    private String bookId;
    private String description;
    private String publishYear;
    private Integer pages;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdOn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime updatedOn;
    private List<ApiAuthorBaseModel> authors;
    private List<String> categories;
    private String series;



}
