/**
 * 
 */
package pl.edu.wat.wcy.cop.domain.scenario.enums.crisis;

// Lists crisis event types.
public enum CrisisEventType {
	RAD("RAD"), NUC("NUC"), BIO("BIO"), CHEM("CHEM");

	private final String name;

	CrisisEventType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
