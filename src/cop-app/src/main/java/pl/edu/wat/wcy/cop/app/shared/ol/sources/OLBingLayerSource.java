/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.ol.sources;
// Enumerates ol bing layer source.


public enum OLBingLayerSource {
    AERIAL("Aerial"),
    AERIAL_WITH_LABELS("AerialWithLabels"),
    ROAD("Road"),
    COLLINS_BART("collinsBart"),
    ORDNANCESURVEY(
            "ordnanceSurvey");

    private String type;

    OLBingLayerSource(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
