/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.tree;

import pl.edu.wat.wcy.cop.app.client.domain.spec.App6AMilitaryUnitClientModel;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElementWithFeatures;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement;
import pl.edu.wat.wcy.cop.app.client.utils.images.IconsUtils;
// Represents app 6 a military unit tree element.


public class App6AMilitaryUnitTreeElement extends DefaultTreeElementWithFeatures<App6AMilitaryUnitClientModel, String>
        implements TreeElement<App6AMilitaryUnitClientModel, String> {

    /**
     * @param object
     * @param image
     */
    public App6AMilitaryUnitTreeElement(App6AMilitaryUnitClientModel object) {
        super(object, IconsUtils.getTreeIconFromBase64(object.getBase64Image()), object.getFeatures());
    }

}
