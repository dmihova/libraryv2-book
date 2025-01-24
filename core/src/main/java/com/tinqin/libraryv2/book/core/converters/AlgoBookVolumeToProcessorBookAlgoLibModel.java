package com.tinqin.libraryv2.book.core.converters;

import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookAlgoLibModel;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.AlgoBookVolume;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.OpenLibraryDoc;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlgoBookVolumeToProcessorBookAlgoLibModel implements Converter<AlgoBookVolume, ProcessorBookAlgoLibModel> {
    @Override
    public ProcessorBookAlgoLibModel convert(AlgoBookVolume source) {
        AlgoBookVolume.ISBN[] isbnIdentifiers = source.getISBNidentifiers();
        List<String> isbns = new ArrayList<String>();
        if (isbnIdentifiers != null && isbnIdentifiers.length > 0 ) {
            for (AlgoBookVolume.ISBN isbn : isbnIdentifiers) {
                isbns.add(isbn.toString());
            }
        }
        return ProcessorBookAlgoLibModel
                .builder()
                .title(source.getTitle() )
                .pages(source.getPages() )
                .authors(source.getAuthors() )
                .categories(source.getCategories() )
                .description(source.getLocalizedDescription())
                .imgUrl(source.getImgUrl())
                .isbns(isbns.toArray(new String[0]))
                .publishYear(source.getPublished())
                .build();
    }
}
