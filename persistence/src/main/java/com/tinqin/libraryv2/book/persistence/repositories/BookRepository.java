package com.tinqin.libraryv2.book.persistence.repositories;


import com.tinqin.libraryv2.book.persistence.models.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    Optional<Book> findBookById(UUID id);

    List<Book> findAll(Specification<Book> specification, Pageable paging);


    List<Book> findBookByTitle(String title);
}
