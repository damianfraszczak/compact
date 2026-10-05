/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.services.server;

import com.google.inject.Singleton;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.GwtClientEndpoints;
import pl.edu.wat.wcy.cop.app.shared.domain.App6AMilitaryUnitDto;
import pl.edu.wat.wcy.cop.app.shared.response.OkResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
// Defines the contract for app 6 a service provider.
public interface App6AServiceProvider extends RestService {

    @POST
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_APP6A_REST_SERVICE)
    void create(App6AMilitaryUnitDto app6aMilitaryUnit,
                MethodCallback<OkResponse<App6AMilitaryUnitDto>> callback);

    @PUT
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_APP6A_REST_SERVICE)
    void update(App6AMilitaryUnitDto app6aMilitaryUnit,
                MethodCallback<OkResponse<App6AMilitaryUnitDto>> callback);

    @DELETE
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_APP6A_REST_SERVICE)
    void delete(App6AMilitaryUnitDto app6aMilitaryUnit, MethodCallback<OkResponse<String>> callback);

    @DELETE
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_APP6A_REST_SERVICE)
    void delete(@QueryParam("id") Long id, MethodCallback<OkResponse<String>> callback);
}
