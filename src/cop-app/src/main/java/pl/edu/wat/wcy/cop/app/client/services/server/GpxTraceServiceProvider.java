package pl.edu.wat.wcy.cop.app.client.services.server;

import com.google.inject.Singleton;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.GwtClientEndpoints;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.GpxTraceDto;
import pl.edu.wat.wcy.cop.app.shared.response.OkResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
// Defines the contract for gpx trace service provider.
public interface GpxTraceServiceProvider extends RestService {
    @POST
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_GPX_REST_SERVICE)
    void create(GpxTraceDto poi, MethodCallback<OkResponse<GpxTraceDto>> callback);

    @PUT
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_GPX_REST_SERVICE)
    void update(GpxTraceDto poi, MethodCallback<OkResponse<GpxTraceDto>> callback);

    @DELETE
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_GPX_REST_SERVICE)
    void delete(GpxTraceDto poi, MethodCallback<OkResponse<String>> callback);

    @DELETE
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_GPX_REST_SERVICE)
    void delete(@QueryParam("id") Long id, MethodCallback<OkResponse<String>> callback);
}
