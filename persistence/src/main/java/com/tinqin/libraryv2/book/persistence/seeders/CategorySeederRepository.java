package com.tinqin.libraryv2.book.persistence.seeders;


import com.tinqin.libraryv2.book.persistence.models.Category;
import com.tinqin.libraryv2.book.persistence.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@Order(1)

public class CategorySeederRepository implements ApplicationRunner {
     private final CategoryRepository categoryRepository;

    @Override
     public void run(ApplicationArguments args) throws Exception {
        if (categoryRepository.count() > 0) {
            return;
        }
        String fileWithPath = "rest/src/main/resources/seederdata/books_2.csv";
        Set<String> categoriesSet = Files.readAllLines(Path.of(fileWithPath))
                .stream()
                .filter(textLine -> !textLine.isBlank())
                .filter(textLine -> !textLine.equals("genre"))
                .map(textLine -> textLine.split(";")[0])
                .map(categoriesText -> categoriesText.split("&"))
                .flatMap(categoriesText -> Arrays.stream(categoriesText).map(String::trim))
                .map(String::toLowerCase)
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));

        List<Category> newCategories = categoriesSet
                .stream()
                .map(categoryName -> Category
                        .builder()
                        .name(categoryName)
                        .description(categoryName)
                        .build())
                .toList();
        categoryRepository.saveAll(newCategories);

    }

}



