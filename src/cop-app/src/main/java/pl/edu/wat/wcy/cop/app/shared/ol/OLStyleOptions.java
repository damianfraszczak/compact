package pl.edu.wat.wcy.cop.app.shared.ol;
// Represents ol style options.

public class OLStyleOptions {
    private String releaseAreaColor;
    private String hazardArea1Color;
    private String hazardArea2Color;
    private String hazardArea3Color;
    private String opacity;
    private boolean hasFill;

    public OLStyleOptions() {
        hasFill = true;
        opacity = "0.7";
        hazardArea3Color = "FE9000";
        hazardArea2Color = "FEC300";
        hazardArea1Color = "FEFE00";
        releaseAreaColor = "FE0000";
    }

    public OLStyleOptions(String opacity) {
        this.opacity = opacity;
    }

    public String getReleaseAreaColor() {
        return releaseAreaColor;
    }

    public void setReleaseAreaColor(String releaseAreaColor) {
        this.releaseAreaColor = releaseAreaColor;
    }

    public String getHazardArea1Color() {
        return hazardArea1Color;
    }

    public void setHazardArea1Color(String hazardArea1Color) {
        this.hazardArea1Color = hazardArea1Color;
    }

    public String getHazardArea2Color() {
        return hazardArea2Color;
    }

    public void setHazardArea2Color(String hazardArea2Color) {
        this.hazardArea2Color = hazardArea2Color;
    }

    public String getHazardArea3Color() {
        return hazardArea3Color;
    }

    public void setHazardArea3Color(String hazardArea3Color) {
        this.hazardArea3Color = hazardArea3Color;
    }

    public String getOpacity() {
        return opacity;
    }

    public void setOpacity(String opacity) {
        this.opacity = opacity;
    }

    public boolean isHasFill() {
        return hasFill;
    }

    public void setHasFill(boolean hasFill) {
        this.hasFill = hasFill;
    }
}
