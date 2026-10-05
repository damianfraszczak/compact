/**
 * 
 */
package pl.edu.wat.wcy.cop.domain.scenario;

import javax.persistence.Embeddable;


@Embeddable
// Stores symbology information for scenario objects.
public class MapSymbol {

	private String cNDescription;
	private String cSDescription;
	private String lCDescription;
	private String lNDescription;
	private String lSDescription;
	private String rCDescription;
	private String rNDescription;
	private String rSDescription;
	private String symbolName;

	private Integer iconHeight;
	private Integer iconWidthh;
	private Integer iconOpacity;
	private Boolean showDescription;

	public MapSymbol() {
		super();
		iconHeight = 50;
		iconWidthh = 50;
		iconOpacity = 1;
		showDescription = true;
	}

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
		if (iconHeight == null) {
			iconHeight = new Integer(0);
		}
		return iconHeight;
	}

	public void setIconHeight(int iconHeight) {
		this.iconHeight = iconHeight;
	}

	public int getIconWidthh() {
		if (iconWidthh == null) {
			iconWidthh = new Integer(0);
		}
		return iconWidthh;
	}

	public void setIconWidthh(int iconWidthh) {
		this.iconWidthh = iconWidthh;
	}

	public int getIconOpacity() {
		if (iconOpacity == null) {
			iconOpacity = new Integer(0);
		}
		return iconOpacity;
	}

	public void setIconOpacity(int iconOpacity) {
		this.iconOpacity = iconOpacity;
	}

	public boolean isShowDescription() {
		if (showDescription == null) {
			showDescription = true;
		}
		return showDescription;
	}

	public void setShowDescription(boolean showDescription) {
		this.showDescription = showDescription;
	}

}
