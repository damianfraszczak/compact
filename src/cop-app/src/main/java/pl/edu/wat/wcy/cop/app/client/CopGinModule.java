/**
 *
 */
package pl.edu.wat.wcy.cop.app.client;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import pl.edu.wat.wcy.cop.app.client.mvp.ShellView;
import pl.edu.wat.wcy.cop.app.client.mvp.impl.*;
import pl.edu.wat.wcy.cop.app.client.mvp.CesarActivityMapper;
import pl.edu.wat.wcy.cop.app.client.ol.MapWidget;
import pl.edu.wat.wcy.cop.app.client.ol.OLMapManager;
import pl.edu.wat.wcy.cop.app.client.services.server.SymbolServiceProvider;
import pl.edu.wat.wcy.cop.app.client.utils.images.AppImages;
import pl.edu.wat.wcy.cop.app.client.utils.images.GisImages;
import pl.edu.wat.wcy.cop.app.client.utils.resources.copResources;
import pl.edu.wat.wcy.cop.app.client.utils.CopLogger;
// Represents cop gin module.

public class CopGinModule extends AbstractGinModule {

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.inject.client.AbstractGinModule#configure()
     */
    @Override
    protected void configure() {
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(OLMapManager.class).in(Singleton.class);
        bind(MapWidget.class).in(Singleton.class);
        bind(ActivityMapper.class).to(CesarActivityMapper.class).asEagerSingleton();
        bind(ShellView.class).to(ShellViewImpl.class).asEagerSingleton();
        bind(VisualizationView.class).to(VisualizationViewImpl.class);
        bind(ConfigurationView.class).to(ConfigurationViewImpl.class);
    }

    @SuppressWarnings("deprecation")
    @Provides
    @Singleton
    public PlaceController providePlaceController(EventBus eventBus) {
        return new PlaceController(eventBus);
    }

    @Provides
    @Singleton
    public CopLogger providecopLogger() {
        return CopLogger.getInstance();
    }

    @Provides
    @Singleton
    public AppImages provideAppImages() {
        return AppImages.INSTANCE;
    }

    @Provides
    @Singleton
    public GisImages provideGisImages() {
        return GisImages.INSTANCE;
    }

    @Provides
    @Singleton
    public copResources providecopResources() {
        return copResources.INSTANCE;
    }

    @Provides
    @Singleton
    public SymbolServiceProvider provideSymbolServiceProvider() {
        return SymbolServiceProvider.INSTANCE;
    }

}
