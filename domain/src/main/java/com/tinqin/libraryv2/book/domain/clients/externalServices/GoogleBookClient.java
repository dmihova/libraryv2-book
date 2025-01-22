package com.tinqin.libraryv2.book.domain.clients.externalServices;


import com.tinqin.libraryv2.book.domain.clients.FeignConfig;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.GoogleBookSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "googleBookClient", url = "${google.books.url}",
        configuration = FeignConfig.class)
public interface GoogleBookClient {
    @GetMapping(
            path = {"/volumes"},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<GoogleBookSearchResponse> search(@RequestParam(name="q") String q,
                                                    @RequestParam(name="startIndex") Integer startIndex,
                                                    @RequestParam(name="maxResults") Integer maxResults,
                                                    @RequestParam(name="printType") String printType
                                                     );
}


