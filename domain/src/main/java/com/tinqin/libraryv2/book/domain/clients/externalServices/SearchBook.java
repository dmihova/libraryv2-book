package com.tinqin.libraryv2.book.domain.clients.externalServices;

import com.tinqin.libraryv2.book.domain.clients.externalServices.dtos.VolumeInfo;

import java.util.Optional;

public interface SearchBook <R extends VolumeInfo>{
    public Optional<R> searchBook(String bookName, String author);
}
