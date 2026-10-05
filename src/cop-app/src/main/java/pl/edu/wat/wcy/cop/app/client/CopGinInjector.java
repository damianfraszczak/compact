/**
 *
 */
package pl.edu.wat.wcy.cop.app.client;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import pl.edu.wat.wcy.cop.app.client.ol.MapWidget;
import pl.edu.wat.wcy.cop.app.client.ol.OLMapManager;
import pl.edu.wat.wcy.cop.app.client.services.server.VisualizationDataProvider;
import pl.edu.wat.wcy.cop.app.client.utils.ErrorFormatter;
import pl.edu.wat.wcy.cop.app.client.utils.gui.LoadingProgressBarManager;
@GinModules(CopGinModule.class)
// Defines the contract for cop gin injector.
public interface CopGinInjector extends Ginjector {

    CopGinInjector INSTANCE = GWT.create(CopGinInjector.class);

    AppController appController();

    OLMapManager getMapManager();

    MapWidget getMapWidget();

    Messages getMessages();
    VisualizationDataProvider getVisualizationDataProvider();

    /**
     * @return
     */
    ErrorFormatter getErrorFormatter();

    /**
     * @return
     */
    LoadingProgressBarManager getLoadingProgressBarManager();

    ActivityMapper activityMapper();




}
