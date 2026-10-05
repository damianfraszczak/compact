package pl.edu.wat.wcy.cop.services.repositories.impl;

import pl.edu.wat.wcy.cop.domain.scenario.CircleEstimation;
import pl.edu.wat.wcy.cop.services.repositories.BaseRepository;
// Defines the contract for circle estimation repository.

public interface CircleEstimationRepository extends BaseRepository<CircleEstimation, Long> {
    Iterable<CircleEstimation> findAllByName(String name);
}
