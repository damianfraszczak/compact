/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.response.capability;
// Represents ms wi a unit capability.


public class MSWiAUnitCapability extends ScenarioPointObjectCapability {
    private String code = ResponseUtils.STRING_NAME;
    private String name = ResponseUtils.STRING_NAME;

    private String description = ResponseUtils.STRING_NAME;

    private String chapterDeliminator = ResponseUtils.STRING_NAME;
    private String formation = ResponseUtils.INTEGER_NAME;
    private String type = ResponseUtils.INTEGER_NAME;

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the chapterDeliminator
     */
    public String getChapterDeliminator() {
        return chapterDeliminator;
    }

    /**
     * @param chapterDeliminator
     *            the chapterDeliminator to set
     */
    public void setChapterDeliminator(String chapterDeliminator) {
        this.chapterDeliminator = chapterDeliminator;
    }

    /**
     * @return the formation
     */
    public String getFormation() {
        return formation;
    }

    /**
     * @param formation
     *            the formation to set
     */
    public void setFormation(String formation) {
        this.formation = formation;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

}
