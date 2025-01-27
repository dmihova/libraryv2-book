package com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels;


import lombok.*;

@Getter
@Setter
public class OpenLibrarySearchResponse {
    private Integer numFound;
    private Integer start;
    private Integer offset;

    private  OpenLibraryVolume[] docs;

    public OpenLibrarySearchResponse() {
        this.docs = new OpenLibraryVolume[0];
    }
}
