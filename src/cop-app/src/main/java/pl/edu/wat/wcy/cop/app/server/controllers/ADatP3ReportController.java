/**
 *
 */
package pl.edu.wat.wcy.cop.app.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.wcy.cop.app.shared.GwtEndpoints;
import pl.edu.wat.wcy.cop.app.shared.domain.ADatP3ReportDto;
import pl.edu.wat.wcy.cop.domain.scenario.ADatP3Report;
import pl.edu.wat.wcy.cop.services.ADatP3ReportService;


@RestController
@RequestMapping(GwtEndpoints.GWT_ADATP3_REST_SERVICE)
// Handles a dat 3 report requests.
public class ADatP3ReportController extends AbstractController<ADatP3Report, ADatP3ReportDto, ADatP3ReportService> {

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.server.controllers.AbstractController#
     * getDtoClass()
     */
    @Override
    protected Class<ADatP3ReportDto> getDtoClass() {
        return ADatP3ReportDto.class;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.server.controllers.AbstractController#
     * getEntityClass()
     */
    @Override
    protected Class<ADatP3Report> getEntityClass() {
        return ADatP3Report.class;
    }

}
