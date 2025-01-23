package com.tinqin.libraryv2.book.domain.clients.externalServices;

import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.VolumeInfo;

import java.util.List;
import java.util.Optional;

public interface SearchBook <R extends VolumeInfo>{
    List<R> searchBook(String bookName, String author);

    List<R> searchBook(String bookName, String author,Integer page);

    Optional<R> getFirstBook(String bookName, String author);

    List<R> searchBooksByAuthor(String author,Integer page);
}
