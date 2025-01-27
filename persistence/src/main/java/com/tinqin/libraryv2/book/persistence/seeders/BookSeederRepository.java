package com.tinqin.libraryv2.book.persistence.seeders;


import com.tinqin.libraryv2.book.persistence.models.Author;
import com.tinqin.libraryv2.book.persistence.models.Book;
import com.tinqin.libraryv2.book.persistence.models.Category;
import com.tinqin.libraryv2.book.persistence.models.AuthorSeries;
import com.tinqin.libraryv2.book.persistence.repositories.AuthorRepository;
import com.tinqin.libraryv2.book.persistence.repositories.BookRepository;
import com.tinqin.libraryv2.book.persistence.repositories.CategoryRepository;
import com.tinqin.libraryv2.book.persistence.repositories.AuthorSeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@Order(2)

public class BookSeederRepository implements ApplicationRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorSeriesRepository authorSeriesRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (bookRepository.count() > 0) {
            return;
        }
        String fileWithPath = "rest/src/main/resources/seederdata/books_2.csv";
        String currentRelativePath = Paths.get("").toAbsolutePath().toString();
        Random rand = new Random();

        Map<String, Category> categoryMap = categoryRepository.findAll()
                .stream()
                .collect(Collectors.toMap(
                        Category::getName, category -> category, (a, b) -> b));

        List<Book> newBooks = Files.readAllLines(Path.of(fileWithPath))
                .stream()
                .filter(textLine -> !textLine.isBlank())
                .map(textLine -> textLine.split(";"))
                .filter(oneBookArray -> oneBookArray.length >= 5)
                .filter(oneBookArray -> !oneBookArray[5].trim().equals("Title"))
                .map(oneBookArray -> {
                    List<Author> bookAuthors =getDBAuthorList(oneBookArray[1].trim(), oneBookArray[2].trim());
                    String year="";
                    if (oneBookArray.length >6) {
                        year=oneBookArray[6].trim();
                    }
                    return Book
                            .builder()
                            .title(oneBookArray[5].trim())
                            .authors(bookAuthors)
                            .categories(getCategories(categoryMap, oneBookArray[0].trim()))
                            .authorSeries(getSeries(bookAuthors,oneBookArray[3].trim()))
                            .pages(rand.nextInt(50, 1000))
                            .publishYear(year)
                            .build();
                   }
                )
                .filter(bookEntity -> {
                    List<Author> authorList = bookEntity.getAuthors();
                    return !authorList.isEmpty();
                })
                .toList();
        bookRepository.saveAll(newBooks);
    }

    private AuthorSeries getSeries(List<Author> bookAuthors, String seriesName) {
        if (seriesName == null || seriesName.isEmpty()) {return null;}
        if (bookAuthors.isEmpty()) {return null;}
        Author author = bookAuthors.getFirst();

        Optional<AuthorSeries> optionalSeries = authorSeriesRepository.findByNameAndAuthor(seriesName,author);
        if (optionalSeries.isPresent()) {
            return optionalSeries.get();
        }
        AuthorSeries newAuthorSeries =   AuthorSeries
                .builder()
                .name(seriesName)
                .author(author)
                .description("")
                .build();
        return authorSeriesRepository.save(newAuthorSeries);


    }

    private List<Category> getCategories(Map<String, Category> categoryMap, String inString) {
        return Arrays.stream(inString.split("&"))
                .map(String::trim)
                .map(String::toLowerCase)
                .distinct()
                .map(categoryMap::get)
                .collect(Collectors.toList());
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



