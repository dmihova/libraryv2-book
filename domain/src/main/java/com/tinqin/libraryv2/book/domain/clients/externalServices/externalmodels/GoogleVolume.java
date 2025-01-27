package com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoogleVolume extends VolumeInfo {
    private String id;
    private String selfLink;
    private GoogleVolumeDtl volumeInfo;
}



