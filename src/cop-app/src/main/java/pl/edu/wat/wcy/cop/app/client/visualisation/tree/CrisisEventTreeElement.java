/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.tree;

import pl.edu.wat.wcy.cop.app.client.domain.spec.CrisisEventClientModel;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElementWithFeatures;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement;
// Represents crisis event tree element.


public class CrisisEventTreeElement extends DefaultTreeElementWithFeatures<CrisisEventClientModel, String>
        implements TreeElement<CrisisEventClientModel, String> {

    /**
     * @param object
     */
    public CrisisEventTreeElement(CrisisEventClientModel object) {
        super(object, null, object.getFeatures());
    }

}
