package com.tinqin.libraryv2.book.api.models;

import com.tinqin.libraryv2.book.api.models.base.ApiOutput;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiAuthorModel extends ApiOutput {

        @Getter
        private String authorId;
        @Getter
        private String firstName;
        @Getter
        private String lastName;
        private int bookCount;
        @Getter
        private List<ApiBookBaseModel> books;

         public int getBookCount() {
                return books.size();
        }

}
