package pl.edu.wat.wcy.cop.app.shared.domain.sar.searchplan;

import java.util.LinkedList;
import java.util.List;
// Carries sector search plan data.

public class SectorSearchPlanDto {
    private List<FormationCrewDto> formationCrews;



    public List<FormationCrewDto> getFormationCrews() {
        if(formationCrews == null){
            formationCrews = new LinkedList<>();
        }
        return formationCrews;
    }

    public void setFormationCrews(List<FormationCrewDto> formationCrews) {
        this.formationCrews = formationCrews;
    }



}
