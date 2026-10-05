/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.events;

import pl.edu.wat.wcy.cop.app.client.domain.spec.ScenarioClientModel;
// Represents a scenario loaded event.


public class ScenarioLoadedEvent extends BaseEvent {

    private ScenarioClientModel scenario;

    /**
     * @param source
     * @param scenario
     */
    public ScenarioLoadedEvent(Object source, ScenarioClientModel scenario) {
        super(source);
        this.scenario = scenario;
    }

    /**
     * @return the scenario
     */
    public ScenarioClientModel getScenario() {
        return scenario;
    }

}
