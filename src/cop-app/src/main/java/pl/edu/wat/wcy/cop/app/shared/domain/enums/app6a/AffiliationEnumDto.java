/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a;
// Enumerates affiliation enum dto.


public enum AffiliationEnumDto {
    UNKNOWN("u"), FRIEND("f"), NEUTRAL("n"), HOSTILE("h");
    private String code;

    AffiliationEnumDto(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
