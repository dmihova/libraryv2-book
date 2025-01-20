package com.tinqin.libraryv2.book.domain.clients.externalServices.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenLibrarySearchResponse {
    private Integer numFound;
    private Integer start;
    private Integer   offset;
    private  OpenLibraryDoc [] docs;
}
