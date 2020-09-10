import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.model.IndiaStateCodeCSV;
import com.bridgelabz.censusanalyser.model.UScensusCSV;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {
    private final String INDIA_CENSUS_CSV_FILE_PATH = "D:\\parag\\Fellowship\\"
            + "Indian-Census-Analyser-\\src\\test\\resources\\IndiaStateCensusData.csv";
    private final String INDIA_CENSUS_CSV_FILE_PATH_FOR_STATE_CODE = "D:\\parag\\Fellowship\\"
            + "Indian-Census-Analyser-\\src\\test\\resources\\IndiaStateCode.csv";
    private final String US_CENSUS_CSV_PATH = "D:\\parag\\Fellowship\\"
            + "Indian-Census-Analyser-\\src\\test\\resources\\USCensusData.csv";
    private Object IndiaCensusCSV;

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }


    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            final String WRONG_CSV_FILE_PATH = "D:\\fellowship\\Day8-Census-Analyser\\" +
                    "src\\test\\resources";
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);

        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            final String WRONG_CSV_FILE_TYPE = "D:\\fellowship\\Day8-Census-Analyser\\" +
                    "src\\test\\resources\\IndiaStateCensusData.txt";
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() {
        try {
            final String WRONG_CSV_DELIMITER_TYPE = "D:\\fellowship\\Day8-Census-Analyser\\" +
                    "src\\test\\resources\\IncorrectDelimiterWithCorrectCsvFile.csv";
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_DELIMITER_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongHeader_ShouldThrowException() {
        try {
            final String WRONG_CSV_HEADER = "D:\\fellowship\\Day8-Census-Analyser\\" +
                    "src\\test\\resources\\CorrectCsvFileWithWrongCsvHeader.csv";
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCensusCSVFileShouldReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateCodeCsv(INDIA_CENSUS_CSV_FILE_PATH_FOR_STATE_CODE);
            Assert.assertEquals(6, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithWrongFile_ShouldThrowException() {
        try {
            final String WRONG_CSV_FILE_PATH = "D:\\fellowship\\Day8-Census-Analyser\\" +
                    "src\\test\\resources";
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateCodeCsv(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            final String WRONG_CSV_FILE_TYPE = "D:\\fellowship\\Day8-Census-Analyser\\" +
                    "src\\test\\resources\\StateCodeFileWithWrongType.txt";
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateCodeCsv(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithWrongDelimiter_ShouldThrowException() {
        try {
            final String WRONG_CSV_DELIMITER_TYPE = "D:\\fellowship\\Day8-Census-Analyser\\" +
                    "src\\test\\resources\\IncorrectDelimiterOfStateCodeFile.csv";
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateCodeCsv(WRONG_CSV_DELIMITER_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithWrongHeader_ShouldThrowException() {
        try {
            final String WRONG_CSV_HEADER = "D:\\fellowship\\Day8-Census-Analyser\\" +
                    "src\\test\\resources\\StateCodeWithWrongHeader.csv";
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateCodeCsv(WRONG_CSV_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String SortedCensusData = censusAnalyser.getStateSortedCensusData();
            IndiaCensusCSV censusCsv[] = new Gson().fromJson(SortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCsv[0].state);

        } catch (CensusAnalyserException e) {

        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnStateCode_ShouldReturnSortResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaStateCodeCsv(INDIA_CENSUS_CSV_FILE_PATH_FOR_STATE_CODE);
            String SortedCensusData = censusAnalyser.getStateCodeSortedCensusData();
            IndiaStateCodeCSV censusCsv[] = new Gson().fromJson(SortedCensusData, IndiaStateCodeCSV[].class);
            System.out.println(censusCsv[0]);
            Assert.assertEquals("Andhra Pradesh", censusCsv[0].stateName);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnSortResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String SortedCensusData = censusAnalyser.getStatePopulationSortedCensusData();
            IndiaCensusCSV censusCsv[] = new Gson().fromJson(SortedCensusData, IndiaCensusCSV[].class);
            System.out.println(censusCsv[0]);
            Assert.assertEquals("Uttar Pradesh", censusCsv[0].state);

        } catch (CensusAnalyserException e) {

        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String SortedCensusData = censusAnalyser.getStatePopulationDensitySortedCensusData();
            IndiaCensusCSV censusCsv[] = new Gson().fromJson(SortedCensusData, IndiaCensusCSV[].class);
            System.out.println(censusCsv[0]);
            Assert.assertEquals("Bihar", censusCsv[0].state);
        } catch (CensusAnalyserException e) {

        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnStateArea_ShouldReturnSortResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String SortedCensusData = censusAnalyser.getStateAreaSortedCensusData();
            IndiaCensusCSV censusCsv[] = new Gson().fromJson(SortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Rajasthan", censusCsv[0].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUSCensusCSVFile_ReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadUSCensusData(US_CENSUS_CSV_PATH);
            Assert.assertEquals(51, numOfRecords);
        } catch (CensusAnalyserException e) {
            System.out.println(e.
                    getMessage());
        }
    }

    @Test
    public void givenUSCensusData_SortedByPopulationWise_ShouldReturn_SortedValues() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadUSCensusData(US_CENSUS_CSV_PATH);
            String sortedCensusData = censusAnalyser.getUSPopulationSortedCensusData();
            UScensusCSV censusCSV[] = new Gson().fromJson(sortedCensusData, UScensusCSV[].class);
            Assert.assertEquals("NC", censusCSV[0].stateID);
        } catch (CensusAnalyserException e) {
            System.out.println("ERROR");
        }
    }

    @Test
    public void givenUSCensusData_SortedByPopulationDensity_ShouldReturn_SortedValues() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadUSCensusData(US_CENSUS_CSV_PATH);
            String sortedCensusData = censusAnalyser.getPopulationDensityWiseSortedCensusDataForUS();
            UScensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, UScensusCSV[].class);
            Assert.assertEquals("AK", censusCSV[0].stateID);

        } catch (CensusAnalyserException e) {
            System.out.println("ERROR");
        }
    }

    @Test
    public void givenUSCensusData_SortedByArea_ShouldReturn_SortedValues() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadUSCensusData(US_CENSUS_CSV_PATH);
            String sortedCensusData = censusAnalyser.getAreaWiseSortedCensusDataForUS();
            UScensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, UScensusCSV[].class);
            Assert.assertEquals("AK", censusCSV[0].stateID);
        } catch (CensusAnalyserException e) {
            System.out.println("ERROR");
        }
    }

    @Test
    public void givenUSCensusData_SortedByWaterArea_ShouldReturn_SortedValues() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadUSCensusData(US_CENSUS_CSV_PATH);
            String sortedCensusData = censusAnalyser.getWaterAreaWiseSortedCensusDataForUS();
            UScensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, UScensusCSV[].class);
            Assert.assertEquals("AK", censusCSV[0].stateID);

        } catch (CensusAnalyserException e) {
            System.out.println("ERROR");
        }
    }
}