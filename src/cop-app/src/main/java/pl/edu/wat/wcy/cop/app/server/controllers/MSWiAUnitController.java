/**
 *
 */
package pl.edu.wat.wcy.cop.app.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.wcy.cop.app.shared.GwtEndpoints;
import pl.edu.wat.wcy.cop.app.shared.domain.MSWiAUnitDto;
import pl.edu.wat.wcy.cop.domain.scenario.MSWiAUnit;
import pl.edu.wat.wcy.cop.services.MSWiAUnitService;


@RestController
@RequestMapping(GwtEndpoints.GWT_MSWIA_REST_SERVICE)
// Handles ms wi a unit requests.
public class MSWiAUnitController extends AbstractController<MSWiAUnit, MSWiAUnitDto, MSWiAUnitService> {

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.server.controllers.AbstractController#
     * getDtoClass()
     */
    @Override
    protected Class<MSWiAUnitDto> getDtoClass() {
        // TODO Auto-generated method stub
        return MSWiAUnitDto.class;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.server.controllers.AbstractController#
     * getEntityClass()
     */
    @Override
    protected Class<MSWiAUnit> getEntityClass() {
        // TODO Auto-generated method stub
        return MSWiAUnit.class;
    }

}
