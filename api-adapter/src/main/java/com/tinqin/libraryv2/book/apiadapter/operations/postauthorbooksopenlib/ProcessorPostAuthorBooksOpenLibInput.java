package com.tinqin.libraryv2.book.apiadapter.operations.postauthorbooksopenlib;

import com.tinqin.libraryv2.book.apiadapter.operations.base.ProcessorInput;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

@Builder
@Getter
@AllArgsConstructor
@Setter
public class ProcessorPostAuthorBooksOpenLibInput implements ProcessorInput {
    @UUID
    @NotBlank
    String authorId;

}
