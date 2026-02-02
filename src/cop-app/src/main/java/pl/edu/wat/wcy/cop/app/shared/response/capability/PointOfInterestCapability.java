/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.response.capability;
// Represents point of interest capability.


public class PointOfInterestCapability extends ScenarioPointObjectCapability {
    private String name = ResponseUtils.STRING_NAME;
    private String description = ResponseUtils.STRING_NAME;

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

}
