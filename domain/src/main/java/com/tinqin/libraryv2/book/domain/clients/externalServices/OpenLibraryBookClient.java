package com.tinqin.libraryv2.book.domain.clients.externalServices;


import com.tinqin.libraryv2.book.domain.clients.FeignConfig;
import com.tinqin.libraryv2.book.domain.clients.externalServices.dtos.OpenLibrarySearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openOrgBookClient", url = "${openlibrary.url}",
        configuration = FeignConfig.class)
public interface OpenLibraryBookClient {
    @GetMapping(
            path = {"/search.json"},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<OpenLibrarySearchResponse> search(@RequestParam(name="author") String author,
                                                     @RequestParam(name="title") String title,
                                                     @RequestParam(name="limit") Integer limit,
                                                     @RequestParam(name="page") Integer page,
                                                     @RequestParam(name="language")String language
                                                     );
}


