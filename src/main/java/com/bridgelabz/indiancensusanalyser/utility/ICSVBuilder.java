package com.bridgelabz.indiancensusanalyser.utility;

import com.bridgelabz.indiancensusanalyser.exception.CensusAnalyserException;

import java.io.Reader;
import java.util.Iterator;
public interface ICSVBuilder<E> {
    Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException;
}