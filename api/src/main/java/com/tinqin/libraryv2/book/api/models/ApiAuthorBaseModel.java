package com.tinqin.libraryv2.book.api.models;

import com.tinqin.libraryv2.book.api.models.base.ApiOutput;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ApiAuthorBaseModel extends ApiOutput {
        private String authorId;
        private String firstName;
        private String lastName;

}
