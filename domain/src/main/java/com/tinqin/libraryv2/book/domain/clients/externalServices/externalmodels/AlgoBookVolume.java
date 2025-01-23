package com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlgoBookVolume extends VolumeInfo {
    private String title;
    private String[] authors;
    private String localizedDescription;
    private String pages;
    private String imgUrl;
    private String published;
    private String[] categories;
    private ISBN[] iSBNidentifiers;

    public static class ISBN {
        private String type;
        private String identifier;
    }

}
