/**
 *
 */
package pl.edu.wat.wcy.cop.symbolservice;

/**
 *
 * @author Damian Frąszczak
 * @since 14 lut 2017
 */
// Signals image not found for code errors.
public class ImageNotFoundForCodeException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -1191029811575150509L;
    private SymbolType symbolType;
    private String code;

    /**
     * @param symbolType
     * @param code
     */
    public ImageNotFoundForCodeException(SymbolType symbolType, String code) {
        this.symbolType = symbolType;
        this.code = code;
    }

    public SymbolType getSymbolType() {
        return symbolType;
    }

    public void setSymbolType(SymbolType symbolType) {
        this.symbolType = symbolType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ImageNotFoundForCodeException{" +
                "symbolType=" + symbolType +
                ", code='" + code + '\'' +
                '}';
    }
}
