package com.bridgelabz.indiancensusanalyser.exception;

public class CensusAnalyserException extends Exception {
    public ExceptionType type;

    public enum ExceptionType {
        CENSUS_FILE_PROBLEM, WRONG_FILE_TYPE, RUN_TIME_EXCEPTION,STATE_CODE_FILE_PROBLEM,UNABLE_TO_PARSE


    }


    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

}
