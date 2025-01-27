package com.tinqin.libraryv2.book.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    @Builder.Default
    private String description="";

    @Column(name = "publish_year")
    @Builder.Default
    private String publishYear="";

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "price", nullable = false)
    @Builder.Default
    private BigDecimal price =BigDecimal.ZERO;

    @Column(name = "created_on", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    @CreationTimestamp
    private LocalDateTime updatedOn;


    @ToString.Exclude
    @JsonIgnoreProperties("books")
    @ManyToMany(fetch = FetchType.LAZY )
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    @ToString.Exclude
    @JsonIgnoreProperties("books")
    @ManyToMany(fetch = FetchType.LAZY )
    @JoinTable(
            name = "book_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

}
