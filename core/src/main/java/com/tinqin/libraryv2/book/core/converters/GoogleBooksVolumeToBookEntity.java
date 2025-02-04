package com.tinqin.libraryv2.book.core.converters;

import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.GoogleVolume;
import com.tinqin.libraryv2.book.domain.clients.externalServices.externalmodels.GoogleVolumeDtl;
import com.tinqin.libraryv2.book.persistence.models.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class GoogleBooksVolumeToBookEntity implements Converter<GoogleVolume, Book> {
    @Override
    public Book convert(GoogleVolume source) {
        GoogleVolumeDtl dtl = source.getVolumeInfo();
        if (dtl == null) {
            return Book
                    .builder()
                    .build();
        }

        return Book
                .builder()
                .title(dtl.getTitle())
                .subtitle(dtl.getSubtitle()==null?"":dtl.getSubtitle())
                .description(dtl.getDescription())
                .isbn(getIsbn(dtl))
                .publishYear( getYear(dtl.getPublishedDate()))
                .pages(dtl.getPageCount())
                .build();
    }

    private String getIsbn(GoogleVolumeDtl dtl) {
        GoogleVolumeDtl.IndustryIdentifier[] industryIdentifiers = dtl.getIndustryIdentifiers();
        if (industryIdentifiers==null || industryIdentifiers.length==0) {
            return "";
        }
        Optional<GoogleVolumeDtl.IndustryIdentifier> isbn13 = Arrays.stream(industryIdentifiers)
                .filter(ii -> ii.getType().equalsIgnoreCase("ISBN_13"))
                .findFirst();
        if (isbn13.isPresent()) { return isbn13.get().getIdentifier();}
       return industryIdentifiers[0].getIdentifier();

    }

    private String getYear(String publishedDate) {
        if (publishedDate == null) {return "";}
        int size = Math.min(4,publishedDate.length());
        return publishedDate.substring(0, size);
    }
}
