package com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleBookSearchResponse {

    long totalItems;
    List<GoogleBookSearchDto> items;
}
