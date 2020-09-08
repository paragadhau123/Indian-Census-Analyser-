package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.List;

public class OpenCSVBuilder<E> implements ICSVBuilder {
    public List<E> getCSVFileList(Reader reader, Class csvClass) throws CSVBuilderException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true).withSeparator(',');
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean.parse();
        } catch (IllegalStateException e) {
            throw new CSVBuilderException(e.getMessage(), CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}
