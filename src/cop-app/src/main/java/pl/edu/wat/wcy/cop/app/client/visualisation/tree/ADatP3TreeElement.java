/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.tree;

import com.google.gwt.resources.client.ImageResource;
import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.domain.spec.ADatP3ReportClientModel;
import pl.edu.wat.wcy.cop.app.client.domain.spec.ADatP3ReportClientModel.AdatP3FeatureType;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElementWithFeatures;
// Represents a dat 3 tree element.


public class ADatP3TreeElement extends DefaultTreeElementWithFeatures<ADatP3ReportClientModel, String> {

    private AdatP3FeatureType featureType;

    /**
     * @param object
     * @param image
     */
    public ADatP3TreeElement(ADatP3ReportClientModel object, AdatP3FeatureType featureType, ImageResource image) {
        super(object, image, object.getFeatures(featureType));
        this.featureType = featureType;
    }

    /**
     * @param object
     * @param features
     * @param image
     */
    public ADatP3TreeElement(ADatP3ReportClientModel object, Feature[] features, ImageResource image) {
        super(object, image, features);
    }

    /**
     * @return the featureType
     */
    public AdatP3FeatureType getFeatureType() {
        return featureType;
    }

    /**
     * @param featureType
     *            the featureType to set
     */
    public void setFeatureType(AdatP3FeatureType featureType) {
        this.featureType = featureType;
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
        // TODO Auto-generated method stub
        return getObject().getaDatP3().getCbrnType().getName() + (getObject().getaDatP3().getDelta() != null ? " " + getObject().getaDatP3().getDelta() : "");
    }

}
