import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.main.CensusAnalyser;
import com.bridgelabz.censusanalyser.model.IndiaCensusCSV;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {
    /*Test Cases For Reading indiaStateCensusData.csv*/
    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        final String INDIA_CENSUS_CSV_FILE_PATH = "D:\\parag\\Fellowship\\Indian-Census-Analyser-\\" +
                "src\\test\\resources\\IndiaStateCensusData.csv";

        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        final String WRONG_CSV_FILE_PATH = "D:\\parag\\Fellowship\\Indian-Census-Analyser-\\" +
                "src\\main\\resources\\IndiaStateCensusData.csv";
        try {
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
        final String WRONG_FILE_TYPE = "D:\\parag\\Fellowship\\Indian-Census-Analyser-\\" +
                "src\\test\\resources\\IndiaStateCensusData.txt";
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithIncorrectDelimiter_ShouldThrowException() {
        final String WRONG_DELIMETER = "D:\\parag\\Fellowship\\Indian-Census-Analyser-\\"
                + "src\\test\\resources\\StateDataWrongDelimetre.csv";
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_DELIMETER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.RUN_TIME_EXCEPTION, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WithWrongHeader_ShouldThrowException() {
        final String WRONG_HEADER = "D:\\parag\\Fellowship\\Indian-Census-Analyser-\\"
                + "src\\test\\resources\\IndiaStateCensusDataWrongHeader.csv";
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_HEADER);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("Error capturing CSV header!", e.getMessage());
        }
    }

    /*Test Cases For Reading IndiaStateCode.csv*/
    @Test
    public void givenIndianStateCodeFile_ShouldReturn_CorrectRecords() {
        final String STATE_CODE_CSV_FILE_PATH = "D:\\parag\\Fellowship\\Indian-Census-Analyser-\\"
                + "src\\test\\resources\\IndiaStateCode.csv";
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadStateCode(STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaStateCode_WithWrongFile_ShouldThrowException() {
        final String STATE_CODE_CSV_FILE_PATH = "D:\\parag\\Fellowship\\Indian-Census-Analyser-\\"
                + "src\\main\\resources\\IndiaStateCode.csv";
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadStateCode(STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.STATE_CODE_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WithWrongFileType_ShouldThrowException() {
        final String STATE_CODE_CSV_FILE_PATH = "D:\\parag\\Fellowship\\Indian-Census-Analyser-\\"
                + "src\\test\\resources\\IndiaStateCode.txt";

        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadStateCode(STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.STATE_CODE_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCSVFile_WithIncorrectDelimiter_ShouldThrowException() {
        final String WRONG_DELIMETER_STATE_CODE_CSV_FILE = "D:\\parag\\Fellowship\\Indian-Census-Analyser-\\"
                + "src\\test\\resources\\StateCodeWrongDelimetre.csv";
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadStateCode(WRONG_DELIMETER_STATE_CODE_CSV_FILE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.RUN_TIME_EXCEPTION, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCSVFile_WithWrongHeader_ShouldThrowException() {
        final String WRONG_HEADER_STATE_CODE_CSV_FILE = "D:\\parag\\Fellowship\\Indian-Census-Analyser-\\"
                + "src\\test\\resources\\IndiaStateCodeWrongHeader.csv";
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadStateCode(WRONG_HEADER_STATE_CODE_CSV_FILE);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("Error capturing CSV header!", e.getMessage());
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
        final String INDIA_CENSUS_CSV_FILE_PATH = "D:\\parag\\Fellowship\\Indian-Census-Analyser-\\" +
                "src\\test\\resources\\IndiaStateCensusData.csv";
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnStatepopulation_ShouldReturnSortedResult() {
        final String INDIA_CENSUS_CSV_FILE_PATH = "D:\\parag\\Fellowship\\Indian-Census-Analyser-\\" +
                "src\\test\\resources\\IndiaStateCensusData.csv";
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("607688", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

}