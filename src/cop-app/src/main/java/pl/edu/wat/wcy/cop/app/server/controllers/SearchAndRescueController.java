package pl.edu.wat.wcy.cop.app.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.wcy.cop.app.shared.GwtEndpoints;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueDto;
import pl.edu.wat.wcy.cop.domain.scenario.sar.SearchAndRescue;
import pl.edu.wat.wcy.cop.services.SearchAndRescueSegmentService;
import pl.edu.wat.wcy.cop.services.SearchAndRescueService;

@RestController
@RequestMapping(GwtEndpoints.GWT_SEARCH_AND_RESCUE_SERVICE)
// Handles search and rescue requests.
public class SearchAndRescueController extends AbstractController<SearchAndRescue, SearchAndRescueDto, SearchAndRescueService> {

    @Autowired
    private SearchAndRescueSegmentService segmentService;

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.server.controllers.AbstractController#
     * getDtoClass()
     */
    @Override
    protected Class<SearchAndRescueDto> getDtoClass() {
        return SearchAndRescueDto.class;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.server.controllers.AbstractController#
     * getEntityClass()
     */
    @Override
    protected Class<SearchAndRescue> getEntityClass() {
        return SearchAndRescue.class;
    }
}