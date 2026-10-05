/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain.enums.crisis;
// Enumerates crisis event type dto.


public enum CrisisEventTypeDto {
    RAD("RAD"), NUC("NUC"), BIO("BIO"), CHEM("CHEM");

    private final String name;

    CrisisEventTypeDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
