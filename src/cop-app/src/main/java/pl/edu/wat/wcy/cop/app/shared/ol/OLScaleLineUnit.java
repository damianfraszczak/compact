/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.ol;
// Enumerates ol scale line unit.


public enum OLScaleLineUnit {
    DEGREES("degrees"), IMPERIAL_INCH("imperial"), US_INCH("us"), NAUTICAL_MILE("nautical"), METRIC("metric");

    private String code;

    /**
     * @param code
     */
    OLScaleLineUnit(String code) {
        this.code = code;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

}
