package pl.edu.wat.wcy.cop.domain.scenario.sar;

import pl.edu.wat.wcy.cop.domain.scenario.FeatureStyle;
import pl.edu.wat.wcy.cop.domain.scenario.ScenarioPointObject;

import javax.persistence.Entity;

@Entity
// Stores the search direction details for SAR.
public class SearchAndRescueSearchDirection extends ScenarioPointObject {
    private FeatureStyle style;

    public FeatureStyle getStyle() {
        return style;
    }

    public void setStyle(FeatureStyle style) {
        this.style = style;
    }
}
