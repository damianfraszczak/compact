/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.services.server;

import com.google.inject.Singleton;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.GwtClientEndpoints;
import pl.edu.wat.wcy.cop.app.shared.domain.CrisisEventDto;
import pl.edu.wat.wcy.cop.app.shared.response.OkResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
// Defines the contract for crisis event service provider.
public interface CrisisEventServiceProvider extends RestService {
    @POST
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_CRISIS_EVENT_REST_SERVICE)
    void create(CrisisEventDto crisisEventDto, MethodCallback<OkResponse<CrisisEventDto>> callback);

    @PUT
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_CRISIS_EVENT_REST_SERVICE)
    void update(CrisisEventDto crisisEventDto, MethodCallback<OkResponse<CrisisEventDto>> callback);

    @DELETE
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_CRISIS_EVENT_REST_SERVICE)
    void delete(CrisisEventDto crisisEventDto, MethodCallback<OkResponse<String>> callback);

    @DELETE
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_CRISIS_EVENT_REST_SERVICE)
    void delete(@QueryParam("id") Long id, MethodCallback<OkResponse<String>> callback);
}
