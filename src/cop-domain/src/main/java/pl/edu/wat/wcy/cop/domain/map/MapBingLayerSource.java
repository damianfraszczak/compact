/**
 * 
 */
package pl.edu.wat.wcy.cop.domain.map;

// Lists Bing map layer sources.
public enum MapBingLayerSource {
	AERIAL("Aerial"),
	AERIAL_WITH_LABELS("AerialWithLabels"),
	ROAD("Road"),
	COLLINS_BART("collinsBart"),
	ORDNANCESURVEY(
			"ordnanceSurvey");

	private String type;

	MapBingLayerSource(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
