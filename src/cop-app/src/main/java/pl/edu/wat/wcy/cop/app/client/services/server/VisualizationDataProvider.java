/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.services.server;

import com.google.inject.Singleton;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.GwtClientEndpoints;
import pl.edu.wat.wcy.cop.app.shared.domain.ScenarioDto;
import pl.edu.wat.wcy.cop.app.shared.response.OkResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
// Defines the contract for visualization data provider.
public interface VisualizationDataProvider extends RestService {
    @GET
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_VISUALZIATION_SERVICE)
    void getScenario(@QueryParam("id") Long id, MethodCallback<OkResponse<ScenarioDto>> callback);

    @DELETE
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_VISUALZIATION_SERVICE)
    void clearScenario(@QueryParam("id") Long id, MethodCallback<OkResponse<ScenarioDto>> callback);

    @POST
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_VISUALZIATION_SERVICE)
    void createScenario(ScenarioDto scenario, MethodCallback<OkResponse<Long>> callback);

    @GET
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_VISUALZIATION_SERVICE_GET_LIST)
    void getScenarioList(MethodCallback<OkResponse<List<Long>>> callback);
    @GET
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_USER_SERVICE)
    void getUser(MethodCallback<OkResponse<String>> callback);

    @GET
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_CLIENT_IP_SERVICE)
    void getClientIp(MethodCallback<OkResponse<String>> callback);
}
