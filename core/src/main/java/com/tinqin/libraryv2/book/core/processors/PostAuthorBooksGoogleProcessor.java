package com.tinqin.libraryv2.book.core.processors;

import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookBaseModel;
import com.tinqin.libraryv2.book.apiadapter.operations.postauthorbooksgoogle.PostAuthorBooksGoogle;
import com.tinqin.libraryv2.book.apiadapter.operations.postauthorbooksgoogle.ProcessorPostAuthorBooksGoogleInput;
import com.tinqin.libraryv2.book.apiadapter.operations.postauthorbooksgoogle.ProcessorPostAuthorBooksGoogleOutput;
import com.tinqin.libraryv2.book.core.errorhandler.base.ErrorHandler;
import com.tinqin.libraryv2.book.core.errorhandler.exceptions.NotFoundException;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.GoogleVolume;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.GoogleVolumeDtl;
import com.tinqin.libraryv2.book.domain.clients.externalServices.services.GoogleSearchBook;
import com.tinqin.libraryv2.book.persistence.models.Author;
import com.tinqin.libraryv2.book.persistence.models.Book;
import com.tinqin.libraryv2.book.persistence.repositories.AuthorRepository;
import com.tinqin.libraryv2.book.persistence.repositories.BookRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.tinqin.libraryv2.book.core.Messages.AUTHOR_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class PostAuthorBooksGoogleProcessor implements PostAuthorBooksGoogle {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ConversionService conversionService;
    private final ErrorHandler errorHandler;
    private final GoogleSearchBook googleSearchBook;

    @Override
    public Either<OperationError, ProcessorPostAuthorBooksGoogleOutput> process(ProcessorPostAuthorBooksGoogleInput input) {
        return Try.of(() -> {
                            Author authorInitial = authorRepository
                                    .findById(UUID.fromString(input.getAuthorId()))
                                    .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND));

                            Integer newBooksCount = addMissingBooks(authorInitial);

                            Author authorUpdated = authorRepository
                                    .findById(UUID.fromString(input.getAuthorId()))
                                    .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND));
                            return ProcessorPostAuthorBooksGoogleOutput
                                    .builder()
                                    .authorId(authorUpdated.getId())
                                    .newBooksCount(newBooksCount)
                                    .books(authorUpdated
                                            .getBooks()
                                            .stream()
                                            .map(bookEntity -> conversionService.convert(bookEntity, ProcessorBookBaseModel.class))
                                            .toList())
                                    .build();
                        }
                )
                .toEither()
                .mapLeft(errorHandler::handle);
    }

    private Integer addMissingBooks(Author author) {
        int newBooksCount = 0;
        int maxPage = 10;
        int page = 0;


        List<Book> existingBookList = author
                .getBooks()
                .stream()
                .toList();
        Map<String, Book> existingBooksSet = new HashMap<>();
        for (Book existingBook : existingBookList) {
            existingBooksSet.putIfAbsent(existingBook.getTitle(), existingBook);
        }
        Set<String> processedBooks = new HashSet<>();

        List<GoogleVolume> volumes = googleSearchBook.searchBooksByAuthor(author.getFullName(), page);
        while (page < maxPage && !volumes.isEmpty()) {
            for (GoogleVolume volume : volumes) {
                GoogleVolumeDtl volumeInfo = volume.getVolumeInfo();
                if (volumeInfo == null) {
                    continue;
                }
                String bookTitle = volume.getVolumeInfo().getTitle();


                if (skipVolume(volumeInfo, processedBooks, bookTitle)) {
                    continue;
                }

                processedBooks.add(bookTitle);
                Book tempBook = conversionService.convert(volume, Book.class);
                if (tempBook == null) {
                    continue;
                }
                if (!existingBooksSet.containsKey(bookTitle)) {
                    tempBook.setAuthors(List.of(author));
                    bookRepository.save(tempBook);
                    newBooksCount++;
                } else {
                    boolean bookUpdated = false;
                    Book existingBook = existingBooksSet.get(bookTitle);
                    if (existingBook.getDescription() == null || existingBook.getDescription().isEmpty()) {
                        existingBook.setDescription(tempBook.getDescription());
                        bookUpdated = true;
                    }
                    if (existingBook.getIsbn() == null || existingBook.getIsbn().isEmpty()) {
                        existingBook.setIsbn(tempBook.getIsbn());
                        bookUpdated = true;
                    }

                    if (existingBook.getPublishYear() == null ||
                            existingBook.getPublishYear().isEmpty() ||
                            existingBook.getPublishYear().equals("0")) {
                        if (tempBook.getPublishYear() != null &&
                                !tempBook.getPublishYear().isEmpty()) {
                            existingBook.setPublishYear(tempBook.getPublishYear());
                            bookUpdated = true;
                        }
                    }
                    if (existingBook.getSubtitle() == null ||existingBook.getSubtitle().isEmpty()){

                        if (tempBook.getSubtitle() != null &&
                                !tempBook.getSubtitle().isEmpty()) {
                            existingBook.setSubtitle(tempBook.getSubtitle());
                            bookUpdated = true;
                        }
                    }

                    if (bookUpdated) {
                        bookRepository.save(existingBook);
                    }

                }
            }
            page++;
            if (volumes.size() < 40) {
                break;
            }
            volumes = googleSearchBook.searchBooksByAuthor(author.getFullName(), page);
        }
        return newBooksCount;
    }

    private static boolean skipVolume(GoogleVolumeDtl volumeInfo, Set<String> processedBooks, String bookTitle) {
        //only english
        String language = volumeInfo.getLanguage().toLowerCase();
        if (!language.equals("en")) {
            return true;
        }
        if (processedBooks.contains(bookTitle)) {
            return true;
        }

        if (bookTitle.toLowerCase().contains("boxed set")){
            return true;
        }

        boolean isFiction = false;
        if (volumeInfo.getCategories() != null) {
            for (String categoryName : volumeInfo.getCategories()) {
                if (categoryName.toLowerCase().contains("fiction")) {
                    isFiction = true;
                    break;
                }
            }
        }
        if (!isFiction) {
            return true;
        }


/*                boolean isUniPaper = false;
                if (volumeInfo.getAuthors() != null) {
                    if (volumeInfo.getAuthors().length > 3) {
                        continue;
                    }
                    for (String authorName : volumeInfo.getAuthors()) {
                        if (authorName.contains("University") || authorName.contains("Department")) {
                            isUniPaper = true;
                            break;
                        }
                    }
                }

                if (isUniPaper) {
                    continue;
                }*/



        return false;
    }
}
