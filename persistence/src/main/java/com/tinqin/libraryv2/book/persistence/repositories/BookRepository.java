package com.tinqin.libraryv2.book.persistence.repositories;


import com.tinqin.libraryv2.book.persistence.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {


    List<Book> findAll();


    Optional<Book> findBookById(UUID id);
}
