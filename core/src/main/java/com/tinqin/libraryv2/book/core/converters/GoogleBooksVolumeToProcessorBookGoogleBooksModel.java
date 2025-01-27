package com.tinqin.libraryv2.book.core.converters;

import com.tinqin.libraryv2.book.apiadapter.models.ProcessorBookGoogleBooksModel;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.GoogleVolume;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.GoogleVolumeDtl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class GoogleBooksVolumeToProcessorBookGoogleBooksModel implements Converter<GoogleVolume, ProcessorBookGoogleBooksModel> {
    @Override
    public ProcessorBookGoogleBooksModel convert(GoogleVolume source) {
        GoogleVolumeDtl dtl = source.getVolumeInfo();
        if (dtl == null) {
            return ProcessorBookGoogleBooksModel
                    .builder()
                    .externalId(source.getId())
                    .build();
        }

        return ProcessorBookGoogleBooksModel
                .builder()
                .externalId(source.getId())
                .title(dtl.getTitle())
                .description(dtl.getDescription())
                .authors(dtl.getAuthors())
                .isbns(Arrays.stream(dtl.getIndustryIdentifiers())
                        .map(GoogleVolumeDtl.IndustryIdentifier::getIdentifier)
                        .toArray(String[]::new)
                )
                .imgUrl(
                        dtl.getImageLinks().getThumbnail()
                )
                .categories(dtl.getCategories())
                .pageCount(dtl.getPageCount())
                .build();
    }
}
