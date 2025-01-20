package com.tinqin.libraryv2.book.domain.clients.externalServices.services;

import com.tinqin.libraryv2.book.domain.clients.externalServices.GoogleBookClient;
import com.tinqin.libraryv2.book.domain.clients.externalServices.SearchBook;
import com.tinqin.libraryv2.book.domain.clients.externalServices.dtos.GoogleBookSearchResponse;
import com.tinqin.libraryv2.book.domain.clients.externalServices.dtos.GoogleVolumeInfo;
import com.tinqin.libraryv2.book.domain.clients.externalServices.dtos.VolumeInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;


@RequiredArgsConstructor
@Component
public class GoogleSearchBook implements SearchBook<GoogleVolumeInfo>  {
    private final GoogleBookClient googleBookClient;
    @Override
    public Optional<GoogleVolumeInfo> searchBook(String bookName, String author) {

        String search ="intitle:\"%s\" + inauthor:\"%s\"";
        String fullSearch = String.format(search,bookName,author);
        ResponseEntity<GoogleBookSearchResponse> res =
                googleBookClient.search(fullSearch,
                        0,20, "books");


        return Optional.empty();
    }

}
