package pl.edu.wat.wcy.cop.domain.scenario.sar.searchplan;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
// Stores parameters for a sector search plan.
public class SectorSearchPlan implements Serializable {
    private List<FormationCrew> formationCrews;



}
