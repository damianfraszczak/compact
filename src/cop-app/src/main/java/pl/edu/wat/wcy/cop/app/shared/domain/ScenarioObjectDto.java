/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain;
// Carries scenario object data.


public class ScenarioObjectDto extends DefaultObject implements ScenarioObject{

    private Long scenarioId;

    /**
     *
     */
    public ScenarioObjectDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param uuid
     */
    public ScenarioObjectDto(String uuid) {
        super(uuid);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param uuid
     * @param scenarioId
     */
    public ScenarioObjectDto(String uuid, Long scenarioId) {
        super(uuid);
        this.scenarioId = scenarioId;
    }

    /**
     * @return the scenarioId
     */
    public Long getScenarioId() {
        return scenarioId;
    }

    /**
     * @param scenarioId
     *            the scenarioId to set
     */
    public void setScenarioId(Long scenarioId) {
        this.scenarioId = scenarioId;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
