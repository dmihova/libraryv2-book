package com.tinqin.libraryv2.book.persistence.repositories;

import com.tinqin.libraryv2.book.persistence.models.AuthorStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorStatusRepository extends JpaRepository<AuthorStatus, UUID> {
}