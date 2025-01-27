package com.tinqin.libraryv2.book.domain.clients.externalServices.services;

import com.tinqin.libraryv2.book.domain.clients.externalServices.OpenLibraryBookClient;
import com.tinqin.libraryv2.book.domain.clients.externalServices.SearchBook;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.*;
 import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RequiredArgsConstructor
@Component
public class OpenLibrarySearchBook implements SearchBook<OpenLibraryVolume> {
    private final OpenLibraryBookClient openLibraryBookClient;

    @Override
    public List<OpenLibraryVolume> searchBook(String title, String author) {
        ResponseEntity<OpenLibrarySearchResponse> response =
                openLibraryBookClient.search(
                        author==null?"":author,
                        title==null?"":title,
                        20, 1, "eng");
        return Arrays.stream(formatResult(response)).toList();
    }

    @Override
    public List<OpenLibraryVolume> searchBook(String title, String author, Integer page) {
        ResponseEntity<OpenLibrarySearchResponse> response =
                openLibraryBookClient.search(author, title, 20, page, "eng");
        return Arrays.stream(formatResult(response)).toList();

    }

    @Override
    public Optional<OpenLibraryVolume> getFirstBook(String title, String author) {
        ResponseEntity<OpenLibrarySearchResponse> response =
                openLibraryBookClient.search(author, title,
                        1, 1, "eng");
        OpenLibraryVolume[] docs = formatResult(response);
        if (docs.length > 0) {
            return Optional.of(docs[0]);
        }
        return Optional.empty();
    }

    @Override
    public List<OpenLibraryVolume> searchBooksByAuthor(String author, Integer page) {
        ResponseEntity<OpenLibrarySearchResponse> response =
                openLibraryBookClient.searchForAuthor(author,100, page, "eng");
        return Arrays.stream(formatResult(response)).toList();

    }


    private OpenLibraryVolume[] formatResult(ResponseEntity<OpenLibrarySearchResponse> response) {
           return Objects.requireNonNullElse(
                Objects.requireNonNull(response.getBody()).getDocs(),
                new OpenLibraryVolume[0]);

    }



}
