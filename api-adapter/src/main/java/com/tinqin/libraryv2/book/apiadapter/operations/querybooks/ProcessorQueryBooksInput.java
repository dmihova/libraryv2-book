package com.tinqin.libraryv2.book.apiadapter.operations.querybooks;

import com.tinqin.libraryv2.book.apiadapter.operations.base.ProcessorInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;


@Builder
@Getter
@AllArgsConstructor
public class ProcessorQueryBooksInput implements ProcessorInput {

    private String title;
    private String titleLike;

    private String authorId;
    private String authorFirstName;
    private String authorLastName;
    private String authorFirstNameLike;
    private String authorLastNameLike;

    private Integer page;
}
