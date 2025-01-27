package com.tinqin.libraryv2.book.domain.clients.externalServices.services;

import com.tinqin.libraryv2.book.domain.clients.externalServices.AlgoBookApiClient;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.AlgoBookResponse;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.AlgoBookVolume;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AlgoSearchBook {

    private final AlgoBookApiClient algoBookApiClient;

    public AlgoBookVolume searchISBN(String isbn) {
        ResponseEntity<AlgoBookVolume> response = algoBookApiClient.searchISBN(isbn);
        return response.getBody();
    }
    public List<AlgoBookVolume> searchByAuthor(String author) {
        ResponseEntity<List<AlgoBookVolume>> response = algoBookApiClient.searchAuthor(author);
        return response.getBody();
    }
    public List<AlgoBookVolume> searchTitle(String title) {
        ResponseEntity<List<AlgoBookVolume>> response = algoBookApiClient.searchTitle(title);
        return response.getBody();
    }
}
