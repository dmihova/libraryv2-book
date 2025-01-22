package com.tinqin.libraryv2.book.apiadapter.operations.getbook;

import com.tinqin.libraryv2.book.apiadapter.operations.base.ProcessorInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.UUID;


@Builder
@Getter
@AllArgsConstructor
@Setter
public class ProcessorGetBookInput implements ProcessorInput {
    @UUID
    @NotBlank
    String bookId;
}
