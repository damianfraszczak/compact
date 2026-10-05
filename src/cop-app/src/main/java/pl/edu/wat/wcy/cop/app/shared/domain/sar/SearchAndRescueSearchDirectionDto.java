package pl.edu.wat.wcy.cop.app.shared.domain.sar;

import pl.edu.wat.wcy.cop.app.shared.domain.FeatureStyleDto;
import pl.edu.wat.wcy.cop.app.shared.domain.ScenarioPointObjectDto;
// Carries search and rescue search direction data.

public class SearchAndRescueSearchDirectionDto extends ScenarioPointObjectDto {
    FeatureStyleDto style = new FeatureStyleDto();

    public FeatureStyleDto getStyle() {
        return style;
    }

    public void setStyle(FeatureStyleDto style) {
        this.style = style;
    }
}
