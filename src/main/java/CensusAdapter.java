import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CensusDAO;
import com.bridgelabz.censusanalyser.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.model.IndiaStateCodeCSV;
import com.bridgelabz.censusanalyser.model.UScensusCSV;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class CensusAdapter {


    public abstract Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException;

    protected <E> Map<String, CensusDAO> loadCensusData(Class<E> censusCSVClass, String csvFilePath) throws CensusAnalyserException {
        Map<String, CensusDAO> Map = new HashMap<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, censusCSVClass);
            Iterable<E> censusCSV = () -> censusCSVIterator;
            String className = censusCSVClass.getSimpleName();
            if (className.equals("IndiaCensusCSV")) {
                StreamSupport.stream(censusCSV.spliterator(), false)
                        .map(IndiaCensusCSV.class::cast)
                        .forEach(csvCensus -> Map.put(csvCensus.state, new CensusDAO(csvCensus)));
            }
            if (className.equals("UScensusCSV")) {
                StreamSupport.stream(censusCSV.spliterator(), false)
                        .map(UScensusCSV.class::cast)
                        .forEach(csvCensus -> Map.put(csvCensus.State, new CensusDAO(csvCensus)));
            }
            if (className.equals("IndiaStateCode")) {
                StreamSupport.stream(censusCSV.spliterator(), false)
                        .map(IndiaStateCodeCSV.class::cast)
                        .forEach(csvCensus -> Map.put(csvCensus.stateName, new CensusDAO(csvCensus)));
            }
            return Map;
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
}
