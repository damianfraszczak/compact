/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.services.server;

import com.google.gwt.core.shared.GWT;
import com.google.inject.Singleton;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import pl.edu.wat.wcy.cop.app.client.services.LocalCache;
import pl.edu.wat.wcy.cop.app.client.utils.CopLogger;
import pl.edu.wat.wcy.cop.app.client.utils.LoggingMethodCallback;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.GwtClientEndpoints;
import pl.edu.wat.wcy.cop.app.shared.response.ErrorResponse;
import pl.edu.wat.wcy.cop.app.shared.response.OkResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;


@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
// Defines the contract for symbol service provider.
public interface SymbolServiceProvider extends RestService {
    SymbolServiceProvider INSTANCE = GWT.create(SymbolServiceProvider.class);

    default void getBase64ImageFromCache(String code, MethodCallback<OkResponse<String>> callback) {
        CopLogger.getInstance().debug(this, "KOd " + code);

        String value = LocalCache.getInstance().get(code);
        if (value != null) {
            callback.onSuccess(null, new OkResponse<String>(value));
        } else {
            getBase64ImagesForCodes(Arrays.asList(code), new LoggingMethodCallback<OkResponse<List<String>>>() {

                @Override
                protected void success(Method method, OkResponse<List<String>> response) {
                    LocalCache.getInstance().put(code, response.getContent().get(0));
                    callback.onSuccess(method, new OkResponse<String>(LocalCache.getInstance().get(code)));
                }
            });
        }
    }

    @GET
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_SYMBOL_SERVICE)
    void getBase64ImageForCode(@QueryParam("code") String code, MethodCallback<OkResponse<String>> callback);

    @GET
    @Path(AppConstants.PROD_APP_PREFIX + GwtClientEndpoints.GWT_SYMBOL_SERVICE)
    void getBase64ImagesForCodes(@QueryParam("codes") List<String> codes,
                                 MethodCallback<OkResponse<List<String>>> callback);

}
