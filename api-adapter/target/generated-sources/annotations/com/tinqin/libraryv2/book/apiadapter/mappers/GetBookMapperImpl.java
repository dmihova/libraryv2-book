package com.tinqin.libraryv2.book.apiadapter.mappers;

import com.tinqin.libraryv2.book.api.operations.getBook.ApiGetBookInput;
import com.tinqin.libraryv2.book.api.operations.getBook.ApiGetBookOutput;
import com.tinqin.libraryv2.book.apiadapter.operations.getbook.ProcessorGetBookInput;
import com.tinqin.libraryv2.book.apiadapter.operations.getbook.ProcessorGetBookOutput;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-21T12:05:08+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class GetBookMapperImpl implements GetBookMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProcessorGetBookInput toOperation(ApiGetBookInput input) {
        if ( input == null ) {
            return null;
        }

        ProcessorGetBookInput.ProcessorGetBookInputBuilder processorGetBookInput = ProcessorGetBookInput.builder();

        processorGetBookInput.bookId( input.getBookId() );

        return processorGetBookInput.build();
    }

    @Override
    public ApiGetBookOutput toApiResult(ProcessorGetBookOutput output) {
        if ( output == null ) {
            return null;
        }

        ApiGetBookOutput.ApiGetBookOutputBuilder apiGetBookOutput = ApiGetBookOutput.builder();

        apiGetBookOutput.book( modelMapper.toBook( output.getBook() ) );

        return apiGetBookOutput.build();
    }
}
