package com.tinqin.libraryv2.book.domain.clients.externalServices;


import com.tinqin.libraryv2.book.domain.clients.FeignConfig;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.AlgoBookResponse;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.AlgoBookVolume;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "algoBookApiClient", url = "${algobook.url}",
        configuration = FeignConfig.class)
public interface AlgoBookApiClient {
    @GetMapping(
            path = {"/isbn/{isbn}"},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<AlgoBookVolume> searchISBN(@PathVariable(name="isbn") String isbn);



    @GetMapping(
            path = {"/title/{title}"},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<List<AlgoBookVolume>> searchTitle(@PathVariable(name="title") String title);

    @GetMapping(
            path = {"/author/{author}"},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<List<AlgoBookVolume>> searchAuthor(@PathVariable(name="author") String author);

}


