/**
 *
 */
package pl.edu.wat.wcy.cop.app.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.wcy.cop.app.shared.GwtEndpoints;
import pl.edu.wat.wcy.cop.app.shared.domain.App6AMilitaryUnitDto;
import pl.edu.wat.wcy.cop.domain.scenario.APP6AMilitaryUnit;
import pl.edu.wat.wcy.cop.services.APP6AMilitaryUnitService;


@RestController
@RequestMapping(GwtEndpoints.GWT_APP6A_REST_SERVICE)
// Handles 6 a military unit requests.
public class APP6AMilitaryUnitController
        extends AbstractController<APP6AMilitaryUnit, App6AMilitaryUnitDto, APP6AMilitaryUnitService> {

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.server.controllers.AbstractController#
     * getDtoClass()
     */
    @Override
    protected Class<App6AMilitaryUnitDto> getDtoClass() {
        return App6AMilitaryUnitDto.class;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.server.controllers.AbstractController#
     * getEntityClass()
     */
    @Override
    protected Class<APP6AMilitaryUnit> getEntityClass() {
        return APP6AMilitaryUnit.class;
    }

}
