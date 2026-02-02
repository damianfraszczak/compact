package pl.edu.wat.wcy.cop.services.repositories.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.cop.domain.scenario.sar.SearchAndRescueSegment;
import pl.edu.wat.wcy.cop.services.repositories.BaseRepository;

import java.util.Date;
import java.util.List;

@Repository
// Defines the contract for search and rescue segment repository.
public interface SearchAndRescueSegmentRepository extends BaseRepository<SearchAndRescueSegment, Long> {

    //@Query("SELECT s from SearchAndRescueSegment s where s.uuid =:uuid")
    SearchAndRescueSegment findByUuid(String uuid);

    List<SearchAndRescueSegment> findByScenarioIdAndModifiedDateAfter(long scenarioId, Date modifiedDate);
}
