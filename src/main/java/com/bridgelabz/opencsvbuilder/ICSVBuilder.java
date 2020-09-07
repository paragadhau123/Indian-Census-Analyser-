package com.bridgelabz.opencsvbuilder;

import com.bridgelabz.indiancensusanalyser.opencsvbuilder.CSVBuilderException;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder<E> {
    Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException;
}