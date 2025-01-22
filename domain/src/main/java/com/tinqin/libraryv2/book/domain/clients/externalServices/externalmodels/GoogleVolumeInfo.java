package com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
    public class GoogleVolumeInfo extends VolumeInfo {
    private String title;
    private String subtitle;
    private String [] authors;
    private String language;
    private ISBN [] isbns;
    private Integer pageCount;
    private String[] categories;
    private ImageLink[] imageLinks;

    private static class ISBN {
        private String isbnType;
        private String isbn;
    }

    private class ImageLink {
        private String thumbnail;
       }



}
