package com.tinqin.libraryv2.book.persistence.models;


import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Table
@Entity
@Getter

public class AuthorStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private UUID authorUID;
    public AuthorStatus() {

    }
    public AuthorStatus(UUID authorUID) {
        this.authorUID = authorUID;
    }


}
