/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain;
// Carries map symbol data.


public class MapSymbolDto {
    private String cNDescription = "";
    private String cSDescription = "";
    private String lCDescription = "";
    private String lNDescription = "";
    private String lSDescription = "";
    private String rCDescription = "";
    private String rNDescription = "";
    private String rSDescription = "";
    private String symbolName;

    private int iconHeight = 50;
    private int iconWidth = 50;
    private int iconOpacity = 100;
    private boolean showDescription;

    public String getcNDescription() {
        return cNDescription;
    }

    public void setcNDescription(String cNDescription) {
        this.cNDescription = cNDescription;
    }

    public String getcSDescription() {
        return cSDescription;
    }

    public void setcSDescription(String cSDescription) {
        this.cSDescription = cSDescription;
    }

    public String getlCDescription() {
        return lCDescription;
    }

    public void setlCDescription(String lCDescription) {
        this.lCDescription = lCDescription;
    }

    public String getlNDescription() {
        return lNDescription;
    }

    public void setlNDescription(String lNDescription) {
        this.lNDescription = lNDescription;
    }

    public String getlSDescription() {
        return lSDescription;
    }

    public void setlSDescription(String lSDescription) {
        this.lSDescription = lSDescription;
    }

    public String getrCDescription() {
        return rCDescription;
    }

    public void setrCDescription(String rCDescription) {
        this.rCDescription = rCDescription;
    }

    public String getrNDescription() {
        return rNDescription;
    }

    public void setrNDescription(String rNDescription) {
        this.rNDescription = rNDescription;
    }

    public String getrSDescription() {
        return rSDescription;
    }

    public void setrSDescription(String rSDescription) {
        this.rSDescription = rSDescription;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public int getIconHeight() {
        return iconHeight;
    }

    public void setIconHeight(int iconHeight) {
        this.iconHeight = iconHeight;
    }

    public int getIconWidth() {
        return iconWidth;
    }

    public void setIconWidth(int iconWidth) {
        this.iconWidth = iconWidth;
    }

    public int getIconOpacity() {
        return iconOpacity;
    }

    public void setIconOpacity(int iconOpacity) {
        this.iconOpacity = iconOpacity;
    }

    public boolean isShowDescription() {
        return showDescription;
    }

    public void setShowDescription(boolean showDescription) {
        this.showDescription = showDescription;
    }

}
