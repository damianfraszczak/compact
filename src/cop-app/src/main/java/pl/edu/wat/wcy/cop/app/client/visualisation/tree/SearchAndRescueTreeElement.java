package pl.edu.wat.wcy.cop.app.client.visualisation.tree;

import pl.edu.wat.wcy.cop.app.client.domain.spec.SearchAndRescueClientModel;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElementWithFeatures;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement;
// Represents search and rescue tree element.

public class SearchAndRescueTreeElement extends DefaultTreeElementWithFeatures<SearchAndRescueClientModel, String>
        implements TreeElement<SearchAndRescueClientModel, String> {

    /**
     * @param object
     * @param image
     * @param features
     */
    public SearchAndRescueTreeElement(SearchAndRescueClientModel object) {
        super(object, null, object.getFeatures());
    }
}
