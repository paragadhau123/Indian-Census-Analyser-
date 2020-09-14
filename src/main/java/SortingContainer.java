import com.bridgelabz.censusanalyser.model.CensusDAO;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortingContainer {
    static Map<FieldTOSort, Comparator> HashMap = new HashMap<>();

    public Comparator getParameter(FieldTOSort fields) {
        Comparator<CensusDAO> populationcomparator = Comparator.comparing(compare -> compare.population);
        Comparator<CensusDAO> populationdensitycomparator = Comparator.comparing(compare -> compare.populationDensity);
        Comparator<CensusDAO> statecomparator = Comparator.comparing(compare -> compare.state);
        Comparator<CensusDAO> statecodecomparator = Comparator.comparing(compare -> compare.stateCode);
        Comparator<CensusDAO> stateareacomparator = Comparator.comparing(compare -> compare.area);
        Comparator<CensusDAO> waterareacomparator = Comparator.comparing(compare -> compare.waterArea);

        HashMap.put(FieldTOSort.BY_POPULATION, populationcomparator.reversed());
        HashMap.put(FieldTOSort.BY_POPULATION_DENSITY, populationdensitycomparator.reversed());
        HashMap.put(FieldTOSort.BY_STATE, statecomparator);
        HashMap.put(FieldTOSort.BY_STATE_CODE, statecodecomparator);
        HashMap.put(FieldTOSort.BY_STATE_AREA, stateareacomparator.reversed());
        HashMap.put(FieldTOSort.BY_WATER_AREA, waterareacomparator.reversed());

        Comparator<CensusDAO> comparatorToSort = HashMap.get(fields);

        return comparatorToSort;
    }


    public Comparator<CensusDAO> getData(FieldTOSort fields) {

        return getParameter(fields);
    }
}
