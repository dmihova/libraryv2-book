package com.tinqin.libraryv2.book.persistence.repositories;


import com.tinqin.libraryv2.book.persistence.models.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {

    Optional<Author> findByLastNameAndFirstName(String lastName, String firstName);

    List<Author> findAll(Specification<Author> specification, Pageable paging);
}
