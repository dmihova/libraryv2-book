package com.tinqin.libraryv2.book.core.processors;

import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookBaseModel;
import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookOpenLibModel;
import com.tinqin.libraryv2.book.apiadapter.operations.getauthorbooksopenlib.ProcessorGetAuthorBooksOpenLibInput;
import com.tinqin.libraryv2.book.apiadapter.operations.postauthorbooksopenlib.PostAuthorBooksOpenLib;
import com.tinqin.libraryv2.book.apiadapter.operations.postauthorbooksopenlib.ProcessorPostAuthorBooksOpenLibInput;
import com.tinqin.libraryv2.book.apiadapter.operations.postauthorbooksopenlib.ProcessorPostAuthorBooksOpenLibOutput;
import com.tinqin.libraryv2.book.core.errorhandler.base.ErrorHandler;
import com.tinqin.libraryv2.book.core.errorhandler.exceptions.NotFoundException;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.OpenLibraryDoc;
import com.tinqin.libraryv2.book.domain.clients.externalServices.services.OpenLibrarySearchBook;
import com.tinqin.libraryv2.book.persistence.models.Author;
import com.tinqin.libraryv2.book.persistence.models.Book;
import com.tinqin.libraryv2.book.persistence.repositories.AuthorRepository;
import com.tinqin.libraryv2.book.persistence.repositories.BookRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.tinqin.libraryv2.book.core.Messages.AUTHOR_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class PostAuthorBooksOpenLibProcessor implements PostAuthorBooksOpenLib {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ConversionService conversionService;
    private final ErrorHandler errorHandler;
    private final OpenLibrarySearchBook openLibrarySearchBook;


    @Override
    public Either<OperationError, ProcessorPostAuthorBooksOpenLibOutput> process(ProcessorPostAuthorBooksOpenLibInput input) {
        return Try.of(() -> {
                            Author authorInitial = authorRepository
                                    .findById(UUID.fromString(input.getAuthorId()))
                                    .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND));
                            List<OpenLibraryDoc> openLibraryDocs = openLibrarySearchBook
                                    .searchBooksByAuthor(authorInitial.getFullName(), 1);
                            Integer newBooksCount = addMissingBooks(authorInitial,openLibraryDocs);



                            Author authorUpdated = authorRepository
                                    .findById(UUID.fromString(input.getAuthorId()))
                                    .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND));
                            return ProcessorPostAuthorBooksOpenLibOutput
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

    private Integer addMissingBooks(Author author , List<OpenLibraryDoc> openLibraryDocs) {
        if (openLibraryDocs.isEmpty()) {return 0;}
        Set<String> existingBooks = author
                .getBooks()
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toSet());

        List<Book> newBooks = new ArrayList<>();
        for (OpenLibraryDoc openLibraryDoc : openLibraryDocs) {
            String bookTitle = openLibraryDoc.getTitle();
            if ( openLibraryDoc.getAuthor_name().length>5){
                //skip anthologies
                continue;
            }
            if (!existingBooks.contains(bookTitle)) {
                Book newBook = conversionService.convert(openLibraryDoc, Book.class);
                newBook.setAuthors(List.of(author));
                newBooks.add(newBook);
                existingBooks.remove(bookTitle);
            }
        }
        bookRepository.saveAll(newBooks);
        return newBooks.size();


    }


}
