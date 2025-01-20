package com.tinqin.libraryv2.book.domain.clients.externalServices.services;

import com.tinqin.libraryv2.book.domain.clients.externalServices.GoogleBookClient;
import com.tinqin.libraryv2.book.domain.clients.externalServices.OpenLibraryBookClient;
import com.tinqin.libraryv2.book.domain.clients.externalServices.SearchBook;
import com.tinqin.libraryv2.book.domain.clients.externalServices.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@RequiredArgsConstructor
@Component
public class OpenLibrarySearchBook  implements SearchBook <OpenLibraryDoc>{
    private final OpenLibraryBookClient openLibraryBookClient;
    @Override
    public Optional<OpenLibraryDoc> searchBook(String bookName, String author) {

        String search ="intitle:\"%s\" + inauthor:\"%s\"";
        String fullSearch = String.format(search,bookName,author);
        ResponseEntity<OpenLibrarySearchResponse> res =
                openLibraryBookClient.search(author,bookName,
                      20,1, "eng");

         return Optional.empty();
    }

}
