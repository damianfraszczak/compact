/**
 *
 */
package pl.edu.wat.wcy.cop.services.repositories.impl;

import org.springframework.data.jpa.repository.Query;
import pl.edu.wat.wcy.cop.domain.scenario.Scenario;
import pl.edu.wat.wcy.cop.services.repositories.BaseRepository;

import java.util.List;
// Defines the contract for scenario repository.


public interface ScenarioRepository extends BaseRepository<Scenario, Long> {

    @Query("SELECT s.id from Scenario s")
    List<Long> getScenarioIds();
}
