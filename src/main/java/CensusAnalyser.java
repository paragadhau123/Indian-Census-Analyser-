import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.model.IndiaCensusDAO;
import com.bridgelabz.censusanalyser.model.IndiaStateCodeCSV;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class CensusAnalyser {
    List<IndiaCensusDAO> censusList;
    List<IndiaCensusDAO> stateCodeList;
    public CensusAnalyser() {
        this.censusList = new ArrayList<>();
    }
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
            while (censusCSVIterator.hasNext()) {
                this.censusList.add(new IndiaCensusDAO(censusCSVIterator.next()));
            }
            return censusList.size();
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
    }
    public int loadStateCode(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            stateCodeList = csvBuilder.getCSVFileList(reader, IndiaStateCodeCSV.class);
            return stateCodeList.size();
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.RUN_TIME_EXCEPTION);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.STATE_CODE_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    e.type.name());
        }
    }
    //Sorting census data in ascending order by state//
    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusDAO> censusCSVComparator = Comparator.comparing(census -> census.state);
        this.sort(censusCSVComparator);
        String sortedStateCensus = new Gson().toJson(censusList);
        return sortedStateCensus;

    }
    //Sorting census data in ascending order by population//
    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusDAO> censusCSVComparator = Comparator.comparing(census -> census.population);
        this.sort(censusCSVComparator.reversed());
        String sortedPopulationCensus = new Gson().toJson(censusList);
        return sortedPopulationCensus;
    }
    //Sorting census data in descending order by population//
    public String getReversedPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusDAO> censusCSVComparator = Comparator.comparing(census -> census.population);
        this.sort(censusCSVComparator);
        String reversedPopulationCensus = new Gson().toJson(censusList);
        return reversedPopulationCensus;
    }
    //Sorting census data in descending order by area//
    public String getReversedAreaWiseSortedCensusData() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusDAO> censusCSVComparator = Comparator.comparing(census -> census.area);
        this.sort(censusCSVComparator.reversed());
        String reversedAreaCensus = new Gson().toJson(censusList);
        return reversedAreaCensus;
    }
    public String getReversedDensityWiseSortedCensusData() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusDAO> censusCSVComparator = Comparator.comparing(census -> census.densityPerSqKm);
        this.sort(censusCSVComparator.reversed());
        String reversedDensityCensus = new Gson().toJson(censusList);
        return reversedDensityCensus;
    }
    private void sort(Comparator<IndiaCensusDAO> censusCSVComparator) {
        IntStream.range(0, censusList.size() - 1).flatMap(i -> IntStream.range(0, censusList.size() - i - 1)).forEach(j -> {
            IndiaCensusDAO census1 = censusList.get(j);
            IndiaCensusDAO census2 = censusList.get(j + 1);
            if (censusCSVComparator.compare(census1, census2) > 0) {
                censusList.set(j, census2);
                censusList.set(j + 1, census1);
            }
        });
    }
}
