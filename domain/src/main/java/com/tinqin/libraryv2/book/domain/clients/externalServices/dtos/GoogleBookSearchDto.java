package com.tinqin.libraryv2.book.domain.clients.externalServices.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleBookSearchDto {
    private String id;
    private String selfLink;
    private GoogleVolumeInfo googleVolumeInfo;
}



