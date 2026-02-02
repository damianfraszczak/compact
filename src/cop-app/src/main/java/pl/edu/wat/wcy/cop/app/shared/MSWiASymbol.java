/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared;

import pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "symbol")
@XmlAccessorType(XmlAccessType.FIELD)
// Represents ms wi a symbol.
public class MSWiASymbol implements UniqueObject<String> {
    @XmlElement
    private String description;
    @XmlElement
    private String code;
    @XmlElement(name = "symbol")
    private List<MSWiASymbol> symbols;

    /**
     *
     */
    public MSWiASymbol() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param description
     * @param code
     * @param symbols
     */
    public MSWiASymbol(String description, String code, ArrayList<MSWiASymbol> symbols) {
        super();
        this.description = description;
        this.code = code;
        this.symbols = symbols;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the symbols
     */
    public List<MSWiASymbol> getSymbols() {
        return symbols;
    }

    /**
     * @param symbols
     *            the symbols to set
     */
    public void setSymbols(List<MSWiASymbol> symbols) {
        this.symbols = symbols;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject#getKey()
     */
    @Override
    public String getKey() {
        return getCode();
    }

}
