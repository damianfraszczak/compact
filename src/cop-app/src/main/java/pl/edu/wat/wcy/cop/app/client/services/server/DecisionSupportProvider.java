/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.services.server;

import com.google.inject.Singleton;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.GwtClientEndpoints;
import pl.edu.wat.wcy.cop.app.shared.request.FloodAreaRequest;
import pl.edu.wat.wcy.cop.app.shared.request.LosAreaRequest;
import pl.edu.wat.wcy.cop.app.shared.request.TerrainProfileRequest;
import pl.edu.wat.wcy.cop.app.shared.response.OkResponse;
import pl.edu.wat.wcy.cop.app.shared.response.capability.FloodAreaCapability;
import pl.edu.wat.wcy.cop.app.shared.response.capability.LosAreaCapability;
import pl.edu.wat.wcy.cop.app.shared.response.capability.TerrainProfileCapability;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
// Defines the contract for decision support provider.
public interface DecisionSupportProvider extends RestService {
    @POST
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_FLOOD_AREA)
    void getFloodAreaPoints(FloodAreaRequest request, MethodCallback<OkResponse<FloodAreaCapability>> callback);

    @POST
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_LOS_AREA)
    void getLosAreaPoints(LosAreaRequest request, MethodCallback<OkResponse<LosAreaCapability>> callback);


    @POST
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_TERRAIN_PROFILE)
    void getTerrainProfile(TerrainProfileRequest request,
                           MethodCallback<OkResponse<TerrainProfileCapability>> callback);

    @DELETE
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_APP6A_REST_SERVICE)
    void delete(@QueryParam("id") Long id, MethodCallback<OkResponse<String>> callback);

}
