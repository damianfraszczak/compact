/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol.extra;

import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.ol.OLMeasureManager.OLMeasureType;
// Represents ol map measure.


public class OLMapMeasure {

    private OLMeasureType measureType;
    private Feature feature;

    /**
     * @param measureType
     * @param feature
     */
    public OLMapMeasure(OLMeasureType measureType, Feature feature) {
        super();
        this.measureType = measureType;
        this.feature = feature;
    }

    /**
     * @return the measureType
     */
    public OLMeasureType getMeasureType() {
        return measureType;
    }

    /**
     * @param measureType
     *            the measureType to set
     */
    public void setMeasureType(OLMeasureType measureType) {
        this.measureType = measureType;
    }

    /**
     * @return the feature
     */
    public Feature getFeature() {
        return feature;
    }

    /**
     * @param feature
     *            the feature to set
     */
    public void setFeature(Feature feature) {
        this.feature = feature;
    }

}
