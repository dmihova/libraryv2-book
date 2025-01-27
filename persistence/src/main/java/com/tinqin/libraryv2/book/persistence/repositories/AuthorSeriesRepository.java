package com.tinqin.libraryv2.book.persistence.repositories;

import com.tinqin.libraryv2.book.persistence.models.Author;
import com.tinqin.libraryv2.book.persistence.models.AuthorSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorSeriesRepository extends JpaRepository<AuthorSeries, UUID> {
    Optional<AuthorSeries> findByName(String seriesName);

    Optional<AuthorSeries> findByNameAndAuthor(String name, Author author);
}