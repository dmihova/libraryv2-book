package com.tinqin.libraryv2.book.core.queries.filters;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class QueryAuthorFilter {
    private String authorFirstName;
    private String authorLastName;
    private String authorFirstNameLike;
    private String authorLastNameLike;




}
