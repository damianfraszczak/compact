/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol;

import com.google.gwt.resources.client.ImageResource;
import ol.layer.Base;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElement;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement;
// Represents layer tree element.


public class LayerTreeElement extends DefaultTreeElement<OLBaseWrapper, String>
        implements TreeElement<OLBaseWrapper, String> {

    /**
     * @param object
     */
    public LayerTreeElement(OLBaseWrapper object, ImageResource image) {
        super(object, image);
        setChecked(object.getLayer().getVisible());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElement#setChecked(
     * boolean)
     */
    @Override
    public void setChecked(boolean checked) {
        // TODO Auto-generated method stub
        super.setChecked(checked);
        Base layer = getObject().getLayer();
        if (layer.getVisible() != checked) {
            layer.setVisible(checked);
        }

    }

}
