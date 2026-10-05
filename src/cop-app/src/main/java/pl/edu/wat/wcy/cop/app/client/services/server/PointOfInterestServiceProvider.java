/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.services.server;

import com.google.inject.Singleton;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.GwtClientEndpoints;
import pl.edu.wat.wcy.cop.app.shared.domain.PointOfInterestDto;
import pl.edu.wat.wcy.cop.app.shared.response.OkResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
// Defines the contract for point of interest service provider.
public interface PointOfInterestServiceProvider extends RestService {
    @POST
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_POI_REST_SERVICE)
    void create(PointOfInterestDto poi, MethodCallback<OkResponse<PointOfInterestDto>> callback);

    @PUT
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_POI_REST_SERVICE)
    void update(PointOfInterestDto poi, MethodCallback<OkResponse<PointOfInterestDto>> callback);

    @DELETE
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_POI_REST_SERVICE)
    void delete(PointOfInterestDto poi, MethodCallback<OkResponse<String>> callback);

    @DELETE
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_POI_REST_SERVICE)
    void delete(@QueryParam("id") Long id, MethodCallback<OkResponse<String>> callback);
}
