package com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoogleBookSearchResponse {

    long totalItems;
    List<GoogleVolume> items;
}
