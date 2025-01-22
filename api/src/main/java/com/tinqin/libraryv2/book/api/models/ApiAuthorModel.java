package com.tinqin.libraryv2.book.api.models;

import com.tinqin.libraryv2.book.api.models.base.ApiOutput;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ApiAuthorModel extends ApiOutput {
        private String authorId;
        private String firstName;
        private String lastName;
        private List<ApiBookBaseModel> books;
}
