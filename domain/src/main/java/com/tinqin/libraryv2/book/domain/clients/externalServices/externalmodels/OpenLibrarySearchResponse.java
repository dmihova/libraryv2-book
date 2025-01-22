package com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OpenLibrarySearchResponse {
    private Integer numFound;
    private Integer start;
    private Integer   offset;

    @Builder.Default
    private  OpenLibraryDoc [] docs = new OpenLibraryDoc[0];
}
