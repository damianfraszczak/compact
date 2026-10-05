/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.domain.spec;

import ol.Feature;
import ol.style.Style;
import pl.edu.wat.wcy.cop.app.client.domain.MapSymbolClientModel;
import pl.edu.wat.wcy.cop.app.client.ol.OLFeatureBuilder;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.ADatP3;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.CBRNType;
import pl.edu.wat.wcy.cop.app.shared.domain.ADatP3ReportDto;

import java.util.*;
// Represents a dat 3 report client model.

public class ADatP3ReportClientModel extends MapSymbolClientModel<ADatP3ReportDto> {

    private ADatP3 aDatP3;
    private Map<AdatP3FeatureType, Feature[]> reportFeatures;
    private Map<AdatP3FeatureType, Style[]> styleOfReportFeatures;
    private CBRNType cbrnType;
    private Date dateOfIncydent;
    private boolean checked = true;

    /**
     * @param object
     * @param features
     */
    public ADatP3ReportClientModel(ADatP3ReportDto object, Feature... features) {
        super(object, features);
        this.reportFeatures = new LinkedHashMap<AdatP3FeatureType, Feature[]>();
        this.styleOfReportFeatures = new LinkedHashMap<AdatP3FeatureType, Style[]>();
    }

    public ADatP3ReportClientModel(ADatP3ReportDto model, ADatP3 aDatP3, Feature... features) {
        super(model, features);
        this.aDatP3 = aDatP3;
        this.cbrnType = aDatP3.getCbrnType();
        this.dateOfIncydent = aDatP3.getDelta();
        this.reportFeatures = new LinkedHashMap<AdatP3FeatureType, Feature[]>();
        this.styleOfReportFeatures = new LinkedHashMap<AdatP3FeatureType, Style[]>();
    }

    public ADatP3 getaDatP3() {
        return aDatP3;
    }

    public void setaDatP3(ADatP3 aDatP3) {
        this.aDatP3 = aDatP3;
    }

    public Map<AdatP3FeatureType, Feature[]> getReportFeatures() {
        return reportFeatures;
    }

    public void setReportFeatures(Map<AdatP3FeatureType, Feature[]> reportFeatures) {
        this.reportFeatures = reportFeatures;
    }

    public void putFeature(AdatP3FeatureType type, Feature... features) {
        if (features != null) {
            reportFeatures.put(type, features);
            Style[] styles = new Style[features.length];
            for (int i = 0; i < features.length; i++) {
                styles[i] = features[i].getStyle();
            }
            styleOfReportFeatures.put(type, styles);
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.domain.MapSymbolClientModel#getFeatures()
     */
    @Override
    public Feature[] getFeatures() {
        List<Feature> result = new LinkedList<>();
        reportFeatures.values().forEach(x -> result.addAll(Arrays.asList(x)));
        return result.toArray(new Feature[result.size()]);
    }

    public Feature[] getFeatures(AdatP3FeatureType type) {
        return reportFeatures.get(type);
    }

    public CBRNType getCbrnType() {
        return cbrnType;
    }

    public void setCbrnType(CBRNType cbrnType) {
        this.cbrnType = cbrnType;
    }

    public Date getDateOfIncydent() {
        return dateOfIncydent;
    }

    public void setDateOfIncydent(Date dateOfIncydent) {
        this.dateOfIncydent = dateOfIncydent;
    }

    public Map<AdatP3FeatureType, Style[]> getStyleOfReportFeatures() {
        return styleOfReportFeatures;
    }

    public void setStyleOfReportFeatures(Map<AdatP3FeatureType, Style[]> styleOfReportFeatures) {
        this.styleOfReportFeatures = styleOfReportFeatures;
    }

    public Style[] getStyles(AdatP3FeatureType type) {
        return styleOfReportFeatures.get(type);
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        Feature[] features = this.getFeatures(AdatP3FeatureType.RELEASE);
        Style[] styles = this.getStyles(AdatP3FeatureType.RELEASE);
        if (checked) {
            for (int i = 0; i < features.length; i++) {
                features[i].setStyle(styles[i]);
            }
            features = this.getFeatures(AdatP3FeatureType.HAZARD);
            styles = this.getStyles(AdatP3FeatureType.HAZARD);
            for (int i = 0; i < features.length; i++) {
                features[i].setStyle(styles[i]);
            }
        } else {
            for (int i = 0; i < features.length; i++) {
                styles[i] = features[i].getStyle();
                features[i].setStyle(OLFeatureBuilder.createInvisibleStyle());
            }
            features = this.getFeatures(AdatP3FeatureType.HAZARD);
            styles = this.getStyles(AdatP3FeatureType.HAZARD);
            for (int i = 0; i < features.length; i++) {
                styles[i] = features[i].getStyle();
                features[i].setStyle(OLFeatureBuilder.createInvisibleStyle());
            }
        }

    }

    public enum AdatP3FeatureType {
        HAZARD, RELEASE
    }
}
