/**
 *
 */
package pl.edu.wat.wcy.cop.app.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.wcy.cop.app.shared.GwtEndpoints;
import pl.edu.wat.wcy.cop.app.shared.domain.PointOfInterestDto;
import pl.edu.wat.wcy.cop.domain.scenario.PointOfInterest;
import pl.edu.wat.wcy.cop.services.PointOfInterestService;


@RestController
@RequestMapping(GwtEndpoints.GWT_POI_REST_SERVICE)
// Handles point of interest requests.
public class PointOfInterestController
        extends AbstractController<PointOfInterest, PointOfInterestDto, PointOfInterestService> {

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.server.controllers.AbstractController#
     * getDtoClass()
     */
    @Override
    protected Class<PointOfInterestDto> getDtoClass() {
        return PointOfInterestDto.class;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.server.controllers.AbstractController#
     * getEntityClass()
     */
    @Override
    protected Class<PointOfInterest> getEntityClass() {
        return PointOfInterest.class;
    }

}
