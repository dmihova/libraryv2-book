package com.tinqin.libraryv2.book.domain.clients.externalServices.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenLibraryDoc extends VolumeInfo {
        String [] author_name;
        String [] author_key;
        String [] first_publish_year;
        String [] isbn;
        String [] language;
        String  number_of_pages_median;
        String [] title;
        String [] subject;
        String [] subject_key;
        String [] id_librarything;




}
