package com.bridgelabz.censusanalyser.exception;

public class CensusAnalyserException extends Exception {
    public ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CensusAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }

    public CensusAnalyserException(String message, String name) {
        super(message);
        this.type = ExceptionType.valueOf(name);
    }

    public enum ExceptionType {
        CENSUS_FILE_PROBLEM, WRONG_FILE_TYPE, RUN_TIME_EXCEPTION, STATE_CODE_FILE_PROBLEM, NO_CENSUS_DATA,
        JSON_WRITE_ERROR,INDEX_OUT_OF_BOUND,US_FILE_PROBLEM
    }
}

