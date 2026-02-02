/**
 *
 */
package pl.edu.wat.wcy.cop.app.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.wcy.cop.app.shared.GwtEndpoints;
import pl.edu.wat.wcy.cop.app.shared.domain.CrisisEventDto;
import pl.edu.wat.wcy.cop.domain.scenario.CrisisEvent;
import pl.edu.wat.wcy.cop.services.CrisisEventService;


@RestController
@RequestMapping(GwtEndpoints.GWT_CRISIS_EVENT_REST_SERVICE)
// Handles crisis event requests.
public class CrisisEventController extends AbstractController<CrisisEvent, CrisisEventDto, CrisisEventService> {

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.server.controllers.AbstractController#
     * getDtoClass()
     */
    @Override
    protected Class<CrisisEventDto> getDtoClass() {
        // TODO Auto-generated method stub
        return CrisisEventDto.class;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.server.controllers.AbstractController#
     * getEntityClass()
     */
    @Override
    protected Class<CrisisEvent> getEntityClass() {
        // TODO Auto-generated method stub
        return CrisisEvent.class;
    }

}
