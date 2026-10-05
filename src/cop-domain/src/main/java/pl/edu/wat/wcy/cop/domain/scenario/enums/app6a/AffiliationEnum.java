/**
 * 
 */
package pl.edu.wat.wcy.cop.domain.scenario.enums.app6a;


// Lists APP-6A affiliation options.
public enum AffiliationEnum {
	UNKNOWN("u"), FRIEND("f"), NEUTRAL("n"), HOSTILE("h");
	private String code;

	AffiliationEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
