package com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels;

import lombok.*;

@Getter
@Setter

public class GoogleVolumeDtl {
    private String title;
    private String subtitle;
    private String[] authors;
    private String language;
    private String description;
    private Integer pageCount;
    private String[] categories;
    private ImageLink imageLinks  ;
    private IndustryIdentifier []industryIdentifiers;

    public GoogleVolumeDtl() {
        this.authors = new String[0];
        this.categories = new String[0];
        this.industryIdentifiers = new IndustryIdentifier[0];
        this.imageLinks = new ImageLink();
    }

    @Getter
    @Setter
    public static class IndustryIdentifier {
        private String type;
        private String identifier;
    }
    @Getter
    @Setter
    public static class ImageLink {
        private String thumbnail;
        private String smallThumbnail;
    }


}
