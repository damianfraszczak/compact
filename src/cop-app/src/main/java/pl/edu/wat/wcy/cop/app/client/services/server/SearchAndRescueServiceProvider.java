package pl.edu.wat.wcy.cop.app.client.services.server;

import com.google.inject.Singleton;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.GwtClientEndpoints;
import pl.edu.wat.wcy.cop.app.shared.domain.ScenarioDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueSegmentDto;
import pl.edu.wat.wcy.cop.app.shared.response.OkResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
// Defines the contract for search and rescue service provider.
public interface SearchAndRescueServiceProvider extends RestService {
    @POST
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_SAR_REST_SERVICE)
    void create(SearchAndRescueDto poi, MethodCallback<OkResponse<SearchAndRescueDto>> callback);

    @PUT
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_SAR_REST_SERVICE)
    void update(SearchAndRescueDto poi, MethodCallback<OkResponse<SearchAndRescueDto>> callback);

    @DELETE
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_SAR_REST_SERVICE)
    void delete(SearchAndRescueDto poi, MethodCallback<OkResponse<String>> callback);

    @DELETE
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_SAR_REST_SERVICE)
    void delete(@QueryParam("id") Long id, MethodCallback<OkResponse<String>> callback);

    @GET
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_SAR_REST_SERVICE + "/segments")
    void getSegmentsForScenarioIdAndModifiedDate(@QueryParam("scenarioId") Long scenarioId,
                     @QueryParam("modifiedDateTimestamp") long modifiedDateTimestamp,
                     MethodCallback<OkResponse<List<SearchAndRescueSegmentDto>>> callback);
}
