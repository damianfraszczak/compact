package pl.edu.wat.wcy.cop.app.client.domain.spec;

import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.domain.MapSymbolClientModel;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.client.utils.resources.copResources;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueCircleZoneDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueDto;

import java.util.Collection;
import java.util.List;
// Represents search and rescue client model.

public class SearchAndRescueClientModel extends MapSymbolClientModel<SearchAndRescueDto> {
    public SearchAndRescueClientModel(SearchAndRescueDto object,
                                      Feature... zones) {
        super(object, zones);
    }

    public SearchAndRescueClientModel(SearchAndRescueDto object,
                                      Collection<Feature> zones) {
        super(object,zones.toArray(new Feature[zones.size()]));
    }

    @Override
    public String getTooltipText() {
        String tooltipTemplate = copResources.INSTANCE.sarTooltipTempalte().getText();
        List<SearchAndRescueCircleZoneDto> zones = getObject().getCircleZones();
        return StringUtils.format(tooltipTemplate,
                zones.get(0).getName().split("-")[0],
                zones.get(0).getName().split("-")[1],
                zones.get(0).getDispersion() + "",
                zones.get(1).getName().split("-")[0],
                zones.get(1).getName().split("-")[1],
                zones.get(1).getDispersion() + "",
                zones.get(2).getName().split("-")[0],
                zones.get(2).getName().split("-")[1],
                zones.get(2).getDispersion() + "",
                zones.get(3).getName().split("-")[0],
                zones.get(3).getName().split("-")[1],
                zones.get(3).getDispersion() + ""

                );
    }
}
