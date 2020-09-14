import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CensusDAO;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class CensusAnalyserTest {
    private final String INDIA_CENSUS_CSV_FILE_PATH = "D:\\parag\\Fellowship\\"
            + "Indian-Census-Analyser-\\src\\test\\resources\\IndiaStateCensusData.csv";
    private final String INDIA_CENSUS_CSV_FILE_PATH_FOR_STATE_CODE = "D:\\parag\\Fellowship\\"
            + "Indian-Census-Analyser-\\src\\test\\resources\\IndiaStateCode.csv";
    private final String US_CENSUS_CSV_PATH = "D:\\parag\\Fellowship\\"
            + "Indian-Census-Analyser-\\src\\test\\resources\\USCensusData.csv";
    Map<String, CensusDAO> map;


    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusStats.INDIAN_CENSUS_STATS);
            censusAnalyser.setAdapter(new IndiacensusAdapter());
            map = censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            System.out.println(map.size());
            Assert.assertEquals(29, map.size());
        } catch (Exception e) {

        }
    }

    @Test
    public void givenIndianStateCodeCensusCSVFileShouldReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusStats.INDIAN_STATE_CODE_STATS);
            censusAnalyser.setAdapter(new StateCodeAdapter());
            map = censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH_FOR_STATE_CODE);
            System.out.println(map.size());
            Assert.assertEquals(36, map.size());
        } catch (Exception e) {

        }
    }

    @Test
    public void givenUSCensusCSVFile_ReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusStats.US_CENSUS_STATS);
            censusAnalyser.setAdapter(new UScensusAdapter());
            map = censusAnalyser.loadCensusData(US_CENSUS_CSV_PATH);
            System.out.println(map.size());
            Assert.assertEquals(51, map.size());
        } catch (Exception e) {

        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        final String WRONG_CSV_FILE_PATH = "D:\\fellowship\\Day8-Census-Analyser\\" +
                "src\\test\\resources";
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusStats.INDIAN_CENSUS_STATS);
        try {
            censusAnalyser.setAdapter(new IndiacensusAdapter());
            map = censusAnalyser.loadCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFileType_ShouldThrowException() {
        final String WRONG_CSV_FILE_TYPE = "D:\\fellowship\\Day8-Census-Analyser\\" +
                "src\\test\\resources\\IndiaStateCensusData.txt";
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusStats.INDIAN_CENSUS_STATS);
        try {
            censusAnalyser.setAdapter(new IndiacensusAdapter());
            map = censusAnalyser.loadCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() {
        final String WRONG_CSV_DELIMITER_TYPE = "D:\\fellowship\\Day8-Census-Analyser\\" +
                "src\\test\\resources\\IndiaStateCensusData.csv";
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusStats.INDIAN_CENSUS_STATS);
        try {
            censusAnalyser.setAdapter(new IndiacensusAdapter());
            map = censusAnalyser.loadCensusData(WRONG_CSV_DELIMITER_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongHeader_ShouldThrowException() {
        final String WRONG_CSV_HEADER = "D:\\fellowship\\Day8-Census-Analyser\\" +
                "src\\test\\resources\\CorrectCsvFileWithWrongCsvHeader.csv";
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusStats.INDIAN_CENSUS_STATS);
        try {
            censusAnalyser.setAdapter(new IndiacensusAdapter());
            map = censusAnalyser.loadCensusData(WRONG_CSV_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithWrongFile_ShouldThrowException() {
        final String WRONG_CSV_FILE_PATH = "D:\\fellowship\\Day8-Census-Analyser\\" +
                "src\\test\\resources\\CorrectCsvFileWithWrongFile.csv";
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusStats.INDIAN_STATE_CODE_STATS);
        try {
            censusAnalyser.setAdapter(new StateCodeAdapter());
            map = censusAnalyser.loadCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithWrongFileType_ShouldThrowException() {
        final String WRONG_CSV_FILE_TYPE = "D:\\fellowship\\Day8-Census-Analyser\\" +
                "src\\test\\resources\\CorrectCsvFileWithWrongFile.txt";
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusStats.INDIAN_STATE_CODE_STATS);
        try {
            censusAnalyser.setAdapter(new StateCodeAdapter());
            map = censusAnalyser.loadCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithWrongDelimiter_ShouldThrowException() {
        final String WRONG_CSV_DELIMITER_TYPE = "D:\\fellowship\\Day8-Census-Analyser\\" +
                "src\\test\\resources\\IncorrectDelimiterOfStateCodeFile.csv";
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusStats.INDIAN_STATE_CODE_STATS);
        try {
            censusAnalyser.setAdapter(new StateCodeAdapter());
            map = censusAnalyser.loadCensusData(WRONG_CSV_DELIMITER_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithWrongHeader_ShouldThrowException() {
        final String WRONG_CSV_HEADER = "D:\\fellowship\\Day8-Census-Analyser\\" +
                "src\\test\\resources\\StateCodeWithWrongHeader.csv";
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusStats.INDIAN_STATE_CODE_STATS);
        try {
            censusAnalyser.setAdapter(new StateCodeAdapter());
            map = censusAnalyser.loadCensusData(WRONG_CSV_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortResult() {
        try {
            IndiacensusAdapter indiacensusAdapter = new IndiacensusAdapter();
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusStats.INDIAN_CENSUS_STATS);
            censusAnalyser.setAdapter(new IndiacensusAdapter());
            map = indiacensusAdapter.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedResult = censusAnalyser.getFieldWiseSortedCensusData(FieldTOSort.BY_STATE, map);
            CensusDAO[] censuscvs = new Gson().fromJson(sortedResult, CensusDAO[].class);
            Assert.assertEquals("Andhra Pradesh", censuscvs[0].state);
        } catch (CensusAnalyserException e) {

        }
    }

    @Test
    public void givenIndianStateCodeData_WhenSortedOnStateCode_ShouldReturnSortResult() {
        try {
            IndiacensusAdapter indiacensusAdapter = new IndiacensusAdapter();
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusStats.INDIAN_STATE_CODE_STATS);
            censusAnalyser.setAdapter(new IndiacensusAdapter());
            map = indiacensusAdapter.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH_FOR_STATE_CODE);
            String sortedResult = censusAnalyser.getFieldWiseSortedCensusData(FieldTOSort.BY_STATE_CODE, map);
             CensusDAO[] censuscvs = new Gson().fromJson(sortedResult, CensusDAO[].class);
            Assert.assertEquals("Andhra Pradesh", censuscvs[0].stateName);
        } catch (CensusAnalyserException e) {

        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnSortResult() {
        try {
            IndiacensusAdapter indiacensusAdapter = new IndiacensusAdapter();
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusStats.INDIAN_CENSUS_STATS);
            censusAnalyser.setAdapter(new IndiacensusAdapter());
            map = indiacensusAdapter.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedResult = censusAnalyser.getFieldWiseSortedCensusData(FieldTOSort.BY_POPULATION, map);
            CensusDAO[] censuscvs = new Gson().fromJson(sortedResult, CensusDAO[].class);
            Assert.assertEquals("Uttar Pradesh", censuscvs[0].state);
        } catch (CensusAnalyserException e) {

        }
    }

    @Test
    public void givenUSCensusData_SortedByPopulationWise_ShouldReturn_SortedValues() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusStats.US_CENSUS_STATS);
            censusAnalyser.setAdapter(new UScensusAdapter());
            map = censusAnalyser.loadCensusData(US_CENSUS_CSV_PATH);
            String sortedResult = censusAnalyser.getFieldWiseSortedCensusData(FieldTOSort.BY_POPULATION, map);
            CensusDAO[] censuscvs = new Gson().fromJson(sortedResult, CensusDAO[].class);
            Assert.assertEquals("NC", censuscvs[0].stateID);
        } catch (CensusAnalyserException e) {

        }
    }

    @Test
    public void givenUSCensusData_SortedByPopulationDensity_ShouldReturn_SortedValues() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusStats.US_CENSUS_STATS);
            censusAnalyser.setAdapter(new UScensusAdapter());
            map = censusAnalyser.loadCensusData(US_CENSUS_CSV_PATH);
            String sortedResult = censusAnalyser.getFieldWiseSortedCensusData(FieldTOSort.BY_POPULATION_DENSITY, map);
            CensusDAO[] censuscvs = new Gson().fromJson(sortedResult, CensusDAO[].class);
            Assert.assertEquals("DC", censuscvs[0].stateID);
        } catch (CensusAnalyserException e) {

        }
    }

    @Test
    public void givenUSCensusData_SortedByArea_ShouldReturn_SortedValues() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusStats.US_CENSUS_STATS);
            censusAnalyser.setAdapter(new UScensusAdapter());
            map = censusAnalyser.loadCensusData(US_CENSUS_CSV_PATH);
            String sortedResult = censusAnalyser.getFieldWiseSortedCensusData(FieldTOSort.BY_STATE_AREA, map);
            CensusDAO[] censuscvs = new Gson().fromJson(sortedResult, CensusDAO[].class);
            Assert.assertEquals("NC", censuscvs[0].stateID);
        } catch (CensusAnalyserException e) {

        }
    }

    @Test
    public void givenUSCensusData_SortedByWaterArea_ShouldReturn_SortedValues() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusStats.US_CENSUS_STATS);
            censusAnalyser.setAdapter(new UScensusAdapter());
            map = censusAnalyser.loadCensusData(US_CENSUS_CSV_PATH);
            String sortedResult = censusAnalyser.getFieldWiseSortedCensusData(FieldTOSort.BY_WATER_AREA, map);
            CensusDAO[] censuscvs = new Gson().fromJson(sortedResult, CensusDAO[].class);
            Assert.assertEquals("AK", censuscvs[0].stateID);
        } catch (CensusAnalyserException e) {

        }
    }

}
