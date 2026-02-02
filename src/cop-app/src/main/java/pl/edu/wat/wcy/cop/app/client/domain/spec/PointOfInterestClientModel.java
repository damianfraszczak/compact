/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.domain.spec;

import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.domain.MapSymbolWithImageSourceClientModel;
import pl.edu.wat.wcy.cop.app.shared.domain.PointOfInterestDto;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
// Represents point of interest client model.

public class PointOfInterestClientModel extends MapSymbolWithImageSourceClientModel<PointOfInterestDto> {

    public PointOfInterestClientModel(PointOfInterestDto object, Feature feature, String base64Image,
                                      Feature... zones) {
        super(object, addArrays(feature, zones), base64Image);
    }

    public PointOfInterestClientModel(PointOfInterestDto object, Feature feature, String base64Image,
                                      Collection<Feature> zones) {
        super(object, addArrays(feature, zones.toArray(new Feature[zones.size()])), base64Image);
    }

    /**
     * @param feature
     * @param zones
     * @return
     */
    private static Feature[] addArrays(Feature feature, Feature[] zones) {
        List<Feature> features = new LinkedList<>();
        features.add(feature);
        features.addAll(Arrays.asList(zones));
        return features.toArray(new Feature[features.size()]);
    }

}
