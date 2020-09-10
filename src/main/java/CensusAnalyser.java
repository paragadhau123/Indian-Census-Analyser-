import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CensusDAO;
import com.bridgelabz.censusanalyser.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.model.IndiaStateCodeCSV;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    List<CensusDAO> censusList;
    List<CensusDAO> stateCodeList;
    Map<String, CensusDAO> stateCensusMap;
    Map<String, CensusDAO> stateCodeMap;

    public CensusAnalyser() {
        this.censusList = new ArrayList();
        this.stateCodeList = new ArrayList<>();
        this.stateCensusMap = new HashMap<>();
        this.stateCodeMap = new HashMap<>();
    }

    public int loadCsvData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
            Iterable<IndiaCensusCSV> indiaCensusCSV = () -> csvFileIterator;
            StreamSupport.stream(indiaCensusCSV.spliterator(), false)
                    .forEach(csvCensus -> this.stateCensusMap.put(csvCensus.state, new CensusDAO(csvCensus)));
            return stateCensusMap.size();
        } catch (Exception e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    public int loadIndiaStateCodeCsv(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaStateCodeCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCodeCSV.class);
            Iterable<IndiaStateCodeCSV> indiaStateCodeCSV = () -> csvFileIterator;
            StreamSupport.stream(indiaStateCodeCSV.spliterator(), false)
                    .forEach(csvCensus -> this.stateCodeMap.put(csvCensus.stateName, new CensusDAO(csvCensus)));
            return stateCodeMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> statesCodeIterable = () -> iterator;
        int numberOfEntries = (int) StreamSupport.stream(statesCodeIterable.spliterator(), false).count();
        return numberOfEntries;
    }

    public String getStateSortedCensusData() throws CensusAnalyserException {
        if (stateCensusMap == null || stateCensusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.state);
        List<CensusDAO> censusDAOList = new ArrayList<>(stateCensusMap.values());
        this.sort(censusDAOList, censusComparator);
        String sortedData = new Gson().toJson(censusDAOList);
        return sortedData;
    }

    public String getStateCodeSortedCensusData() throws CensusAnalyserException {
        if (stateCensusMap == null || stateCensusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.stateCode);
        List<CensusDAO> censusDAOList = new ArrayList<>(stateCensusMap.values());
        this.sort(censusDAOList, censusComparator);
        String sortedData = new Gson().toJson(censusDAOList);
        return sortedData;
    }

    public String getStatePopulationSortedCensusData() throws CensusAnalyserException {
        if (stateCensusMap == null || stateCensusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.population);
        List<CensusDAO> censusDAOList = new ArrayList<>(stateCensusMap.values());
        this.sort(censusDAOList, censusComparator);
        String sortedData = new Gson().toJson(censusDAOList);
        return sortedData;
    }

    public String getStatePopulationDensitySortedCensusData() throws CensusAnalyserException {
        if (stateCensusMap == null || stateCensusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        List<CensusDAO> censusDAOList = new ArrayList<>(stateCensusMap.values());
        this.sort(censusDAOList, censusComparator);
        String sortedData = new Gson().toJson(censusDAOList);
        return sortedData;
    }

    public String getStateAreaSortedCensusData() throws CensusAnalyserException {
        if (stateCensusMap == null || stateCensusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.area);
        List<CensusDAO> censusDAOList = new ArrayList<>(stateCensusMap.values());
        this.sort(censusDAOList, censusComparator);
        String sortedData = new Gson().toJson(censusDAOList);
        return sortedData;
    }

    private void sort(List<CensusDAO> censusDAOList, Comparator<CensusDAO> censusCSVComparator) {
        for (int i = 0; i < censusDAOList.size(); i++) {
            for (int j = 0; j < censusDAOList.size() - i - 1; j++) {
                CensusDAO censusCSV = censusDAOList.get(j);
                CensusDAO censusCSV1 = censusDAOList.get(j + 1);
                if (censusCSVComparator.compare(censusCSV, censusCSV1) > 0) {
                    censusDAOList.set(j, censusCSV1);
                    censusDAOList.set(j + 1, censusCSV);
                }
            }
        }
    }
}