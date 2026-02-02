/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.tree;

import pl.edu.wat.wcy.cop.app.client.domain.spec.MSWiAUnitClientModel;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElementWithFeatures;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement;
import pl.edu.wat.wcy.cop.app.client.utils.images.IconsUtils;
// Represents ms wi a unit tree element.


public class MSWiAUnitTreeElement extends DefaultTreeElementWithFeatures<MSWiAUnitClientModel, String>
        implements TreeElement<MSWiAUnitClientModel, String> {

    /**
     * @param object
     * @param image
     * @param features
     */
    public MSWiAUnitTreeElement(MSWiAUnitClientModel object) {
        super(object, IconsUtils.getTreeIconFromBase64(object.getBase64Image()), object.getFeatures());
    }

}
