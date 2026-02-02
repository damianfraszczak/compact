package pl.edu.wat.wcy.cop.app.client.domain.spec;

import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.domain.MapSymbolClientModel;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueSegmentDto;

import java.util.Collection;
// Represents search and rescue segment client model.

public class SearchAndRescueSegmentClientModel extends MapSymbolClientModel<SearchAndRescueSegmentDto> {
    public SearchAndRescueSegmentClientModel(SearchAndRescueSegmentDto object,
                                             Feature... zones) {
        super(object, zones);
    }

    public SearchAndRescueSegmentClientModel(SearchAndRescueSegmentDto object,
                                             Collection<Feature> zones) {
        super(object, zones.toArray(new Feature[zones.size()]));
    }

   
}
