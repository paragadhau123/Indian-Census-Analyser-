package com.bridgelabz.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class UScensusCSV {
    @CsvBindByName(column = "State Id", required = true)
    public String stateID;

    @CsvBindByName(column = "State", required = true)
    public String State;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "Housing units", required = true)
    public int housingUnits;

    @CsvBindByName(column = "Total area", required = true)
    public float totalArea;

    @CsvBindByName(column = "Water area", required = true)
    public float waterArea;

    @CsvBindByName(column = "Land area", required = true)
    public float landArea;

    @CsvBindByName(column = "Population Density", required = true)
    public float populationDensity;

    @CsvBindByName(column = "Housing Density", required = true)
    public float housingDensity;

    @Override
    public String toString() {
        return "USCensusCSV{" +
                "state ID='" + stateID + '\'' +
                ", state='" + State + '\'' +
                ", population=" + population +
                ", housingUnits=" + housingUnits +
                ", totalArea=" + totalArea +
                ", waterArea=" + waterArea +
                ", landArea=" + landArea +
                ", populationDensity=" + populationDensity +
                ", housingDensity=" + housingDensity +
                '}';
    }
}
