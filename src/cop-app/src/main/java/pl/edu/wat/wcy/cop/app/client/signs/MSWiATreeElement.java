/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.signs;

import com.google.gwt.resources.client.ImageResource;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElement;
import pl.edu.wat.wcy.cop.app.shared.MSWiASymbol;
// Represents ms wi a tree element.


public class MSWiATreeElement extends DefaultTreeElement<MSWiASymbol, String> {

    /**
     * @param object
     * @param image
     */
    public MSWiATreeElement(MSWiASymbol object, ImageResource image) {
        super(object, image);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElement#getObjectDesct
     * ()
     */
    @Override
    public String getObjectDesct() {
        return getObject().getCode() + " " + getObject().getDescription();
    }

}
