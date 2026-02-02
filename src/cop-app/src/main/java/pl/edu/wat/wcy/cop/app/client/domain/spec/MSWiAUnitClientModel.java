/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.domain.spec;

import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.domain.MapSymbolWithBase64ClientModel;
import pl.edu.wat.wcy.cop.app.shared.domain.MSWiAUnitDto;
// Represents ms wi a unit client model.

public class MSWiAUnitClientModel extends MapSymbolWithBase64ClientModel<MSWiAUnitDto> {

    /**
     * @param object
     * @param feature
     * @param base64Image
     */
    public MSWiAUnitClientModel(MSWiAUnitDto object, Feature feature, String base64Image) {
        super(object, feature, base64Image);
    }

}
