import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CensusDAO;
import com.bridgelabz.censusanalyser.model.IndiaCensusCSV;

import java.util.Map;

public class IndiacensusAdapter extends CensusAdapter {

    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        try {
            Map<String, CensusDAO> censusStateMap = super.loadCensusData(IndiaCensusCSV.class, csvFilePath[0]);
            return censusStateMap;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }
}