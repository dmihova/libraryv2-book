package com.tinqin.libraryv2.book.api.models;

import com.tinqin.libraryv2.book.api.operations.base.ApiOutput;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ApiAuthorOutput extends ApiOutput {
        private String authorId;
        private String firstName;
        private String lastName;

}
