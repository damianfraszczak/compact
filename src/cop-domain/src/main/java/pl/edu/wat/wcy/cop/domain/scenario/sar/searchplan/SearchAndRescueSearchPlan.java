package pl.edu.wat.wcy.cop.domain.scenario.sar.searchplan;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
// Stores a search-and-rescue plan.
public class SearchAndRescueSearchPlan implements Serializable {
    private int[][][][] x;
    private List<SectorSearchPlan> sectorSearchPlans;
    private double time;
}
