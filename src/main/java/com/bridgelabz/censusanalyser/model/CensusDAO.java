package com.bridgelabz.censusanalyser.model;

public class CensusDAO {
    // StateCodeCSV Fields
    public int srNo;
    public int tin;
    public String stateCode;
    public String stateName;

    // IndianCensusCSV Fileds
    public int densityPerSqKm;
    public int population;
    public int area;
    public String state;

    // USCensusCSV Fields
    public int housingUnits;
    public float populationDensity;
    public float waterArea;
    public float landArea;
    public float totalArea;
    public float housingDensity;
    public int usPopulation;
    public String usState;
    public String stateID;

    public CensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        area = indiaCensusCSV.areaInSqKm;
        population = indiaCensusCSV.population;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
    }

    public CensusDAO(IndiaStateCodeCSV indiaStateCodeCSV) {
        stateName = indiaStateCodeCSV.stateName;
        stateCode = indiaStateCodeCSV.stateCode;
        tin = indiaStateCodeCSV.tin;
        srNo = indiaStateCodeCSV.srNo;
    }

    public CensusDAO(UScensusCSV usCensusCSV) {

        stateID = usCensusCSV.stateID;
        usState = usCensusCSV.State;
        usPopulation = usCensusCSV.population;
        housingDensity = usCensusCSV.housingDensity;
        totalArea = usCensusCSV.totalArea;
        landArea = usCensusCSV.landArea;
        waterArea = usCensusCSV.waterArea;
        populationDensity = usCensusCSV.populationDensity;
        housingUnits = usCensusCSV.housingUnits;

    }
}
