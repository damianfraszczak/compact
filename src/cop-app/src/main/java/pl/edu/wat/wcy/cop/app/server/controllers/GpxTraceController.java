package pl.edu.wat.wcy.cop.app.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.wat.wcy.cop.app.shared.GwtEndpoints;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.GpxTraceDto;
import pl.edu.wat.wcy.cop.domain.scenario.sar.GpxTrace;
import pl.edu.wat.wcy.cop.services.GpxTraceService;

@RestController
@RequestMapping(GwtEndpoints.GWT_GPX_REST_SERVICE)
// Handles gpx trace requests.
public class GpxTraceController extends AbstractController<GpxTrace, GpxTraceDto, GpxTraceService> {

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.server.controllers.AbstractController#
     * getDtoClass()
     */
    @Override
    protected Class<GpxTraceDto> getDtoClass() {
        return GpxTraceDto.class;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.server.controllers.AbstractController#
     * getEntityClass()
     */
    @Override
    protected Class<GpxTrace> getEntityClass() {
        return GpxTrace.class;
    }
}