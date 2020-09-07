package com.bridgelabz.indiancensusanalyser.opencsvbuilder;

public class CSVBuilderFactory {
    public static ICSVBuilder createCSVBuilder() {
        return new OpenCSVBuilder();
    }
}