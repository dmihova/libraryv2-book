package com.tinqin.libraryv2.book.core.queries.specifications;


import com.tinqin.libraryv2.book.core.queries.filters.QueryAuthorFilter;
import com.tinqin.libraryv2.book.persistence.models.Author;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AuthorSpecification {

    public static Specification<Author> getSpecification(QueryAuthorFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getAuthorFirstNameLike() != null && !filter.getAuthorFirstNameLike().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("firstName"), filter.getAuthorFirstNameLike() + "%"));
            }

            if (filter.getAuthorFirstName() != null && !filter.getAuthorFirstName().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("firstName"), filter.getAuthorFirstName()));
            }

            if (filter.getAuthorLastNameLike() != null && !filter.getAuthorLastNameLike().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("lastName"), filter.getAuthorLastNameLike() + "%"));
            }
            if (filter.getAuthorLastName() != null && !filter.getAuthorLastName().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("lastName"), filter.getAuthorLastName()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));


        };
    }
}
