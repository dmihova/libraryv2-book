package com.tinqin.libraryv2.book.domain.clients.externalServices.services;

import com.tinqin.libraryv2.book.domain.clients.externalServices.GoogleBookClient;
import com.tinqin.libraryv2.book.domain.clients.externalServices.SearchBook;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.GoogleBookSearchResponse;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.GoogleVolumeInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Component
public class GoogleSearchBook implements SearchBook<GoogleVolumeInfo>  {
    private final GoogleBookClient googleBookClient;
    @Override
    public List<GoogleVolumeInfo> searchBook(String bookName, String author) {

        String search ="intitle:\"%s\" + inauthor:\"%s\"";
        String fullSearch = String.format(search,bookName,author);
        ResponseEntity<GoogleBookSearchResponse> res =
                googleBookClient.search(fullSearch,
                        0,20, "books");

        return List.of( );
    }

    @Override
    public List<GoogleVolumeInfo> searchBook(String bookName, String author, Integer page) {
        return List.of();
    }

    @Override
    public Optional<GoogleVolumeInfo> getFirstBook(String bookName, String author) {

        return Optional.empty();
    }

    @Override
    public List<GoogleVolumeInfo> searchBooksByAuthor(String author, Integer page) {
        return List.of();
    }

}
