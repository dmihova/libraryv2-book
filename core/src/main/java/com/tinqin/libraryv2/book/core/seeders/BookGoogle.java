package com.tinqin.libraryv2.book.core.seeders;


import com.tinqin.libraryv2.book.apiadapter.errors.OperationError;
import com.tinqin.libraryv2.book.apiadapter.operations.postauthorbooksgoogle.ProcessorPostAuthorBooksGoogleInput;
import com.tinqin.libraryv2.book.apiadapter.operations.postauthorbooksgoogle.ProcessorPostAuthorBooksGoogleOutput;
import com.tinqin.libraryv2.book.core.processors.PostAuthorBooksGoogleProcessor;
import com.tinqin.libraryv2.book.persistence.models.*;
import com.tinqin.libraryv2.book.persistence.repositories.*;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
@RequiredArgsConstructor
@Order(3)

public class BookGoogle implements ApplicationRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AuthorStatusRepository authorStatusRepository;
    private final PostAuthorBooksGoogleProcessor postAuthorBooksGoogleProcessor;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        if (bookRepository.count() == 0) {
            return;
        }

        List<UUID> authorIds = authorRepository.findAll()
                .stream()
                .map(Author::getId)
                .toList();
        boolean processed = false;
        for(UUID authorId : authorIds) {
            Optional<AuthorStatus> authorStatusOptional = authorStatusRepository.findByAuthorUID(authorId);
            if (authorStatusOptional.isEmpty()) {
                ProcessorPostAuthorBooksGoogleInput input = ProcessorPostAuthorBooksGoogleInput.builder().authorId(authorId.toString()).build();
                Either<OperationError, ProcessorPostAuthorBooksGoogleOutput> res  = postAuthorBooksGoogleProcessor.process( input );
                if (res.isRight()){
                    AuthorStatus newAuthorStatus =  new AuthorStatus(authorId);
                    authorStatusRepository.save(newAuthorStatus);
                    processed = true;
                }

                break;
            }

        }
        if (!processed) {
            int a=0;
        }
    }



}



