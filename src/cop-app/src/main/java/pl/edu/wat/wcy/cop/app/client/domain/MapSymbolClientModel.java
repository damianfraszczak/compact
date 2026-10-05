/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.domain;

import ol.Feature;
import ol.style.Style;
import pl.edu.wat.wcy.cop.app.client.ol.OLFeatureBuilder;
import pl.edu.wat.wcy.cop.app.client.ol.extra.FeatureInfoProvider;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject;

import java.util.LinkedList;
import java.util.List;
// Represents map symbol client model.

public abstract class MapSymbolClientModel<T extends UniqueObject<String>>
        implements UniqueObject<String>, FeatureInfoProvider, WithDomainObjectElement<T> {

    private T object;
    private Feature[] features;
    private List<Style> initialStyles = new LinkedList<>();
    private boolean checked = true;

    public MapSymbolClientModel(T object, Feature... features) {
        super();
        this.object = object;
        this.setFeatures(features);
    }

    @Override
    public String getTooltipText() {
        return getDescription();
    }

    @Override
    public String getKey() {
        return getObject().getKey();
    }

    @Override
    public String getDescription() {
        return getObject().getDescription();
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public Feature[] getFeatures() {
        return features;
    }

    public void setFeatures(Feature... features) {
        this.features = features;
        setFeaturesTooltipProvider();
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        if (features != null) {
            if (!checked) {
                for (int i = 0; i < features.length; i++) {
                    initialStyles.add(features[i].getStyle());
                    features[i].setStyle(OLFeatureBuilder.createInvisibleStyle());
                }
            } else {
                for (int i = 0; i < features.length; i++) {
                    features[i].setStyle(initialStyles.get(i));
                }
            }
        }

    }

    /**
     *
     */
    private void setFeaturesTooltipProvider() {
        if (features != null) {
            for (Feature feature : features) {
                if (feature != null) {
                    if(feature.get(FeatureInfoProvider.PROVIDER_KEY) == null){
                        feature.set(FeatureInfoProvider.PROVIDER_KEY, this);
                    }

                    feature.set(AppConstants.FEATURE_OBJECT_OWNER, this);
                }

            }
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getObject().toString();
    }

    public Feature getEditableFeature() {
        return this.getFeatures()[this.getFeatures().length - 1];
    }
}
