package pl.edu.wat.wcy.cop.services;

import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.cop.domain.scenario.sar.SearchAndRescueSegment;
import pl.edu.wat.wcy.cop.services.repositories.impl.SearchAndRescueSegmentRepository;

import java.util.Date;
import java.util.List;

@Service
// Provides search and rescue segment operations.
public class SearchAndRescueSegmentService extends AbstractServiceImpl<SearchAndRescueSegment, SearchAndRescueSegmentRepository> {


    public List<SearchAndRescueSegment> getByScenarioIdAndModifiedByAfter(long scenarioId, Date modifiedDate) {
        return repository.findByScenarioIdAndModifiedDateAfter(scenarioId, modifiedDate);
    }

    @Override
    public SearchAndRescueSegment update(SearchAndRescueSegment object) {
        return super.update(object);
    }

    @Override
    public SearchAndRescueSegment add(SearchAndRescueSegment object) {
        return super.add(object);
    }
}
