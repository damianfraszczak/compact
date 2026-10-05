/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.tree;

import pl.edu.wat.wcy.cop.app.client.domain.spec.PointOfInterestClientModel;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElementWithFeatures;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement;
import pl.edu.wat.wcy.cop.app.client.utils.images.IconsUtils;
// Represents poi tree element.


public class PoiTreeElement extends DefaultTreeElementWithFeatures<PointOfInterestClientModel, String>
        implements TreeElement<PointOfInterestClientModel, String> {

    /**
     * @param object
     * @param image
     * @param features
     */
    public PoiTreeElement(PointOfInterestClientModel object) {
        super(object, IconsUtils.getTreeIconFromImageSource(object.getImageSource()), object.getFeatures());
    }

}
