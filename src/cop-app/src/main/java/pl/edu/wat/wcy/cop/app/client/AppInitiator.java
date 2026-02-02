/**
 *
 */
package pl.edu.wat.wcy.cop.app.client;

import com.google.inject.Inject;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;
import pl.edu.wat.wcy.cop.app.client.services.server.*;
import pl.edu.wat.wcy.cop.app.client.signs.MSWiASymbolsProvider;
// Represents app initiator.

public class AppInitiator {
    @Inject
    private VisualizationDataProvider visualizationDataProvider;
    @Inject
    private ADatP3ServiceProvider adatP3ServiceProvider;
    @Inject
    private App6AServiceProvider app6a6AServiceProvider;
    @Inject
    private CrisisEventServiceProvider crisisEventServiceProvider;
    @Inject
    private MSWiAUnitServiceProvider mswiaUnitServiceProvider;
    @Inject
    private PointOfInterestServiceProvider poiServiceProviderentServiceProvider;
    @Inject
    private SearchAndRescueServiceProvider sarServiceProviderentServiceProvider;
    @Inject
    private GpxTraceServiceProvider gpxServiceProviderentServiceProvider;
    @Inject
    private SymbolServiceProvider symbolServiceProvider;
    @Inject
    private DecisionSupportProvider decisionSupportProvider;

    public void init() {
        initRestServices();
    }

    /**
     *
     */
    private void initRestServices() {
        Resource resource = new Resource("");
        ((RestServiceProxy) visualizationDataProvider).setResource(resource);
        ((RestServiceProxy) symbolServiceProvider).setResource(resource);
        ((RestServiceProxy) decisionSupportProvider).setResource(resource);

        ((RestServiceProxy) adatP3ServiceProvider).setResource(resource);
        ((RestServiceProxy) app6a6AServiceProvider).setResource(resource);
        ((RestServiceProxy) crisisEventServiceProvider).setResource(resource);
        ((RestServiceProxy) mswiaUnitServiceProvider).setResource(resource);
        ((RestServiceProxy) poiServiceProviderentServiceProvider).setResource(resource);
        ((RestServiceProxy) sarServiceProviderentServiceProvider).setResource(resource);
        ((RestServiceProxy) gpxServiceProviderentServiceProvider).setResource(resource);
        MSWiASymbolsProvider.getMainSymbol();
    }
}
