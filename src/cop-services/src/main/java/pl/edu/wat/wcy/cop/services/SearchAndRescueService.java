package pl.edu.wat.wcy.cop.services;

import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.cop.domain.scenario.sar.SearchAndRescue;
import pl.edu.wat.wcy.cop.services.repositories.impl.SearchAndRescueRepository;

import java.text.NumberFormat;

@Service
// Provides search and rescue operations.
public class SearchAndRescueService extends AbstractServiceImpl<SearchAndRescue, SearchAndRescueRepository> {
    public static final NumberFormat DEFAULT_NUMBER_FORMAT = NumberFormat.getNumberInstance();

}
