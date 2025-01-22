package com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpenLibraryDoc extends VolumeInfo {
    private String[] author_name;
    private String[] author_key;
    private String  first_publish_year;
    private String[] isbn;
    private String[] language;
    private String number_of_pages_median;
    private String  title;
    private String[] subject;
    private String[] subject_key;
    private String[] id_librarything;


}
