/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.tree;

import com.google.gwt.resources.client.ImageResource;
import ol.Feature;
import ol.style.Style;
import pl.edu.wat.wcy.cop.app.client.ol.OLFeatureBuilder;
import pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject;

import java.util.LinkedList;
import java.util.List;
// Represents default tree element with features.


public class DefaultTreeElementWithFeatures<O extends UniqueObject<K>, K> extends DefaultTreeElement<O, K> {

    private Feature[] features;
    private List<Style> initialStyles = new LinkedList<>();

    /**
     * @param object
     * @param image
     */
    public DefaultTreeElementWithFeatures(O object, ImageResource image, Feature... features) {
        super(object, image);
        this.features = features;
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
        if (features != null) {
            if (!checked) {
                initialStyles.clear();
                for (int i = 0; i < features.length; i++) {
                    Feature feature = features[i];
                    initialStyles.add(feature.getStyle());
                    feature.setStyle(OLFeatureBuilder.createInvisibleStyle());
                }
            } else if (!initialStyles.isEmpty()) {
                for (int i = 0; i < features.length; i++) {
                    Feature feature = features[i];
                    feature.setStyle(initialStyles.get(i));
                }
            }
        }

    }
}
