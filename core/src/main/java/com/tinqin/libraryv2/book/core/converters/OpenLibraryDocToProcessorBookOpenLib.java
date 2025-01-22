package com.tinqin.libraryv2.book.core.converters;

import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookOpenLibModel;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.OpenLibraryDoc;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OpenLibraryDocToProcessorBookOpenLib implements Converter<OpenLibraryDoc, ProcessorBookOpenLibModel> {
    @Override
    public ProcessorBookOpenLibModel convert(OpenLibraryDoc source) {
        return ProcessorBookOpenLibModel
                .builder()
                .title(source.getTitle() )
                .authorNames(source.getAuthor_name())
                .authorKeys(source.getAuthor_key())
                .isbns(source.getIsbn())
                .pages(source.getNumber_of_pages_median())
                .publishYear(source.getFirst_publish_year())
                .subjectKeys(source.getSubject_key())
                .subjects(source.getSubject())
                .build();
    }
}
