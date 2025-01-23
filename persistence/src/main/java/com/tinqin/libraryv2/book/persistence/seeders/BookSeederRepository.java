package com.tinqin.libraryv2.book.persistence.seeders;


import com.tinqin.libraryv2.book.persistence.models.Author;
import com.tinqin.libraryv2.book.persistence.models.Book;
import com.tinqin.libraryv2.book.persistence.repositories.AuthorRepository;
import com.tinqin.libraryv2.book.persistence.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Component
@RequiredArgsConstructor
@Order(1)

public class BookSeederRepository implements ApplicationRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (bookRepository.count() > 0) {
            return;
        }
        String fileWithPath = "rest/src/main/resources/seederdata/books_2.csv";
        String currentRelativePath = Paths.get("").toAbsolutePath().toString();
        Random rand = new Random();

        List<Book> newBooks = Files.readAllLines(Path.of(fileWithPath))
                .stream()
                .filter(textLine -> !textLine.isBlank())
                .map(textLine -> textLine.split(";"))
                .filter(bookArray -> bookArray.length >= 5)
                .filter(bookArray -> !bookArray[5].trim().equals("Title"))
                .map(bookArray -> Book
                        .builder()
                        .title(bookArray[5].trim())
                        .authors(getDBAuthorList(bookArray[1].trim(), bookArray[2].trim()))
                        .pages(rand.nextInt(50, 1000))
                        .publishYear("")
                        .build())
                .filter(bookEntity -> {
                    List<Author> authorList = bookEntity.getAuthors();
                    return !authorList.isEmpty();
                })
                .toList();

        bookRepository.saveAll(newBooks);

    }

    private List<Author> getDBAuthorList(String authorNames1, String authorNames2) {
        Author author = getDBAuthor(authorNames1);
        if (author == null) {
            return new ArrayList<>();
        }
        List<Author> authorList = new ArrayList<>(List.of(author));
        if (!authorNames2.isEmpty()) {
            Author author2 = getDBAuthor(authorNames2);
            authorList.add(author2);
        }
        return authorList;
    }

    private Author getDBAuthor(String authorName) {
        if (authorName.isBlank()) {
            return null;
        }
        String[] authorNames = authorName.split("\\s+");
        String lastName = authorNames[authorNames.length - 1];
        if (lastName.isBlank()) {
            return null;
        }
        String firstName = "N/A";
        if (authorNames.length > 1) {
            StringBuilder sb = new StringBuilder(authorNames[0]);
            for (int i = 1; i < authorNames.length - 1; i++) {
                sb.append(" ").append(authorNames[i]);
            }
            firstName = sb.toString();
        }

        Optional<Author> optionalAuthor = authorRepository
                .findByLastNameAndFirstName(lastName, firstName);
        if (optionalAuthor.isPresent()) {
            return optionalAuthor.get();
        }
        Author newAuthor = Author
                .builder()
                .lastName(lastName)
                .firstName(firstName)
                .build();

        return authorRepository.save(newAuthor);
    }


}



