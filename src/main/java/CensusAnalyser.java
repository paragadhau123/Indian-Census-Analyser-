import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CensusDAO;
import com.bridgelabz.censusanalyser.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.model.IndiaStateCodeCSV;
import com.bridgelabz.censusanalyser.model.UScensusCSV;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    Map<String, CensusDAO> Map;

    public CensusAnalyser() {
        this.Map = new HashMap<>();
    }

    public <E> int loadCensusData(String csvFilePath, Class<E> censusCSVClass) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, censusCSVClass);
            Iterable<E> censusCSV = () -> censusCSVIterator;
            String className = censusCSVClass.getSimpleName();
            if (className.equals("IndiaCensusCSV")) {
                StreamSupport.stream(censusCSV.spliterator(), false)
                        .map(IndiaCensusCSV.class::cast)
                        .forEach(csvCensus -> this.Map.put(csvCensus.state, new CensusDAO(csvCensus)));
                return Map.size();
            }
            if (className.equals("UScensusCSV")) {
                StreamSupport.stream(censusCSV.spliterator(), false)
                        .map(UScensusCSV.class::cast)
                        .forEach(csvCensus -> this.Map.put(csvCensus.State, new CensusDAO(csvCensus)));
                return Map.size();
            }
            if (className.equals("IndiaStateCode")) {
                StreamSupport.stream(censusCSV.spliterator(), false)
                        .map(IndiaStateCodeCSV.class::cast)
                        .forEach(csvCensus -> this.Map.put(csvCensus.stateName, new CensusDAO(csvCensus)));
                return Map.size();
            }
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.RUN_TIME_EXCEPTION);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    e.type.name());
        }
        return 0;
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        return this.loadCensusData(csvFilePath, IndiaCensusCSV.class);
    }

    public int loadUSCensusData(String csvFilePath) throws CensusAnalyserException {
        return this.loadCensusData(csvFilePath, UScensusCSV.class);
    }

    public int loadIndiaStateCodeCsv(String csvFilePath) throws CensusAnalyserException {
        return this.loadCensusData(csvFilePath, IndiaStateCodeCSV.class);
    }

    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> statesCodeIterable = () -> iterator;
        int numberOfEntries = (int) StreamSupport.stream(statesCodeIterable.spliterator(), false).count();
        return numberOfEntries;
    }

    public String getStateSortedCensusData() throws CensusAnalyserException {
        if (Map == null || Map.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.state);
        List<CensusDAO> censusDAOList = new ArrayList<>(Map.values());
        this.sort(censusDAOList, censusComparator);
        String sortedData = new Gson().toJson(censusDAOList);
        return sortedData;
    }

    public String getStateCodeSortedCensusData() throws CensusAnalyserException {
        if (Map == null || Map.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.stateCode);
        List<CensusDAO> censusDAOList = new ArrayList<>(Map.values());
        this.sort(censusDAOList, censusComparator);
        String sortedData = new Gson().toJson(censusDAOList);
        return sortedData;
    }

    public String getStatePopulationSortedCensusData() throws CensusAnalyserException {
        if (Map == null || Map.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.population);
        List<CensusDAO> censusDAOList = new ArrayList<>(Map.values());
        this.sort(censusDAOList, censusComparator.reversed());
        String sortedData = new Gson().toJson(censusDAOList);
        return sortedData;
    }

    public String getStatePopulationDensitySortedCensusData() throws CensusAnalyserException {
        if (Map == null || Map.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        List<CensusDAO> censusDAOList = new ArrayList<>(Map.values());
        this.sort(censusDAOList, censusComparator.reversed());
        String sortedData = new Gson().toJson(censusDAOList);
        return sortedData;
    }

    public String getStateAreaSortedCensusData() throws CensusAnalyserException {
        if (Map == null || Map.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.area);
        List<CensusDAO> censusDAOList = new ArrayList<>(Map.values());
        this.sort(censusDAOList, censusComparator.reversed());
        String sortedData = new Gson().toJson(censusDAOList);
        return sortedData;
    }

    public String getUSPopulationSortedCensusData() throws CensusAnalyserException {
        if (Map == null || Map.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.population);
        List<CensusDAO> censusDAOList = new ArrayList<>(Map.values());
        this.sort(censusDAOList, censusComparator.reversed());
        String sortedData = new Gson().toJson(censusDAOList);
        return sortedData;
    }

    /*Method to get US Population Density Wise Sorted Census Data*/
    public String getPopulationDensityWiseSortedCensusDataForUS() throws CensusAnalyserException {
        if (Map == null || Map.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusCSVComparator = Comparator.comparing(census -> census.populationDensity);
        List<CensusDAO> censusDAOList = new ArrayList<>(Map.values());
        this.sort(censusDAOList, censusCSVComparator);
        String sortedData = new Gson().toJson(censusDAOList);
        return sortedData;
    }

    /*Method to get US Area Wise Sorted Census Data*/
    public String getAreaWiseSortedCensusDataForUS() throws CensusAnalyserException {
        if (Map == null || Map.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusCSVComparator = Comparator.comparing(census -> census.totalArea);
        List<CensusDAO> censusDAOList = new ArrayList<>(Map.values());
        this.sort(censusDAOList, censusCSVComparator.reversed());
        String sortedData = new Gson().toJson(censusDAOList);
        return sortedData;
    }

    /*Method to get US Water Area Wise Sorted Census Data*/
    public String getWaterAreaWiseSortedCensusDataForUS() throws CensusAnalyserException {
        if (Map == null && Map.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusCSVComparator = Comparator.comparing(census -> census.waterArea);
        List<CensusDAO> censusDAOList = new ArrayList<>(Map.values());
        this.sort(censusDAOList, censusCSVComparator.reversed());
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