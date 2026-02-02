/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain;
// Carries ms wi a unit data.


public class MSWiAUnitDto extends ScenarioPointObjectDto implements ISymbolOnTheMapWithCode<String> {

    private String code;
    private String name;

    private String description;

    private String chapterDeliminator;
    private int formation;
    private int type;

    /**
     *
     */
    public MSWiAUnitDto() {
        super();
    }

    /**
     * @param point
     */
    public MSWiAUnitDto(GeoPointDto point) {
        super(point);
    }

    /**
     * @param code
     * @param name
     * @param description
     * @param point
     */
    public MSWiAUnitDto(String code, String name, String description, GeoPointDto point) {
        this(code, name, description, point, new MapSymbolDto());
    }

    /**
     * @param code
     * @param name
     * @param description
     * @param point
     * @param mapSymbol
     */
    public MSWiAUnitDto(String code, String name, String description, GeoPointDto point, MapSymbolDto mapSymbol) {
        super(point, mapSymbol);
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChapterDeliminator() {
        return chapterDeliminator;
    }

    public void setChapterDeliminator(String chapterDeliminator) {
        this.chapterDeliminator = chapterDeliminator;
    }

    public int getFormation() {
        return formation;
    }

    public void setFormation(int formation) {
        this.formation = formation;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getCode() + " " + getDescription();
    }
}
