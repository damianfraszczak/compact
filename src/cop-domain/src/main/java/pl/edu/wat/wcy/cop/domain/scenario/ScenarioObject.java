/**
 * 
 */
package pl.edu.wat.wcy.cop.domain.scenario;

import pl.edu.wat.wcy.cop.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
// Base type for scenario elements.
public class ScenarioObject extends BaseEntity {
	@Column(name = "scenario_id")
	private Long scenarioId;

	public Long getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(Long scenarioId) {
		this.scenarioId = scenarioId;
	}

}
