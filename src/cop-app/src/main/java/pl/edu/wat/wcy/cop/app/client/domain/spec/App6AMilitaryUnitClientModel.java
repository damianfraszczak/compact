/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.domain.spec;

import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.domain.MapSymbolWithBase64ClientModel;
import pl.edu.wat.wcy.cop.app.shared.domain.App6AMilitaryUnitDto;
// Represents app 6 a military unit client model.

public class App6AMilitaryUnitClientModel extends MapSymbolWithBase64ClientModel<App6AMilitaryUnitDto> {

    /**
     * @param object
     * @param feature
     * @param base64Image
     */
    public App6AMilitaryUnitClientModel(App6AMilitaryUnitDto object, Feature feature, String base64Image) {
        super(object, feature, base64Image);
    }

}
