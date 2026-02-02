/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.images;

import pl.edu.wat.wcy.cop.app.shared.domain.enums.crisis.CrisisEventTypeDto;
// Maps crisis event type to image data.


public class CrisisEventTypeToImageMapper {
    /**
     * Zwracany jest string base64
     *
     * @param type
     * @return
     */
    public static String getImageSourceForCrisisEventType(CrisisEventTypeDto type) {
        if (type == null) {
            return MarkerImages.INSTANCE.radiolocation_danger().getSafeUri().asString();
        }
        switch (type) {
            case BIO:
                return MarkerImages.INSTANCE.chemical_danger().getSafeUri().asString();
            case CHEM:
                return MarkerImages.INSTANCE.biological_danger().getSafeUri().asString();
            case NUC:
            case RAD:
                return MarkerImages.INSTANCE.radiolocation_danger().getSafeUri().asString();
            default:
                return MarkerImages.INSTANCE.radiolocation_danger().getSafeUri().asString();
        }
    }
}
