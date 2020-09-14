import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CensusDAO;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class CensusAnalyser {
    Map<String, CensusDAO> Map;
    private CensusStats statistics;
    SortingContainer container;
    public CensusAdapter adapter;

    public CensusAnalyser(CensusStats statistics) {
        container = new SortingContainer();
        this.statistics = statistics;
    }

    public void setAdapter(CensusAdapter adapter) {
        this.adapter = adapter;
    }

    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException {

        Map = this.adapter.loadCensusData(csvFilePath);

        return Map;
    }

    public String getFieldWiseSortedCensusData(FieldTOSort fieldName, Map<String, CensusDAO> Map) throws CensusAnalyserException {
        Comparator<CensusDAO> comparator = null;
        SortingContainer container = new SortingContainer();
        if (Map == null || Map.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        comparator = container.getData(fieldName);
        ArrayList arrayList = Map.values().stream()
                .sorted(comparator)
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedStateCensus = new Gson().toJson(arrayList);
        return sortedStateCensus;
    }
}