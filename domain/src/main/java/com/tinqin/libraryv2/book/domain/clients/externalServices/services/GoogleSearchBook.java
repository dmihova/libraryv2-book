package com.tinqin.libraryv2.book.domain.clients.externalServices.services;

import com.tinqin.libraryv2.book.domain.clients.externalServices.GoogleBookClient;
import com.tinqin.libraryv2.book.domain.clients.externalServices.SearchBook;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Component
public class GoogleSearchBook implements SearchBook<GoogleVolume> {
    private final GoogleBookClient googleBookClient;

    @Override
    public List<GoogleVolume> searchBook(String bookName, String author) {
        return searchBook(bookName, author, 0, 40);

    }

    @Override
    public List<GoogleVolume> searchBook(String bookName, String author, Integer page) {
        return searchBook(bookName, author, page, 40);
    }

    @Override
    public Optional<GoogleVolume> getFirstBook(String bookName, String author) {
        List<GoogleVolume> books = searchBook(bookName, author, 0, 1);
        if (books.isEmpty()) return Optional.empty();
        return Optional.of(books.get(0));
    }

    @Override
    public List<GoogleVolume> searchBooksByAuthor(String author, Integer page) {
        String search = "inauthor:\"%s\"";
        String fullSearch = String.format(search, author);
        ResponseEntity<GoogleBookSearchResponse> res =
                googleBookClient.search(fullSearch, page, 40, "books","en");
        return formatResult(res);
    }


    public List<GoogleVolume> searchBook(String bookName, String author, Integer page, Integer maxResult) {
        String search = "intitle:\"%s\" + inauthor:\"%s\"";
        String fullSearch = String.format(search, bookName, author);
        ResponseEntity<GoogleBookSearchResponse> res =
                googleBookClient.search(fullSearch,
                        page, maxResult, "books","en");

        return formatResult(res);
    }

    private List<GoogleVolume> formatResult(ResponseEntity<GoogleBookSearchResponse> response) {
        if (response != null && response.getBody() != null && response.getBody().getItems() != null) {
            return response.getBody().getItems();
        }
        return List.of();
    }

}
