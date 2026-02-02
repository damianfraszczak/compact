/**
 *
 */
package pl.edu.wat.wcy.cop.app.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.container.Viewport;
import pl.edu.wat.wcy.cop.app.client.mvp.ShellView;
import pl.edu.wat.wcy.cop.app.client.mvp.impl.ConfigurationPlace;
import pl.edu.wat.wcy.cop.app.client.mvp.CopPlaceHistoryMapper;
// Handles app requests.

public class AppController {
    @Inject
    private ShellView shellView;
    @Inject
    private PlaceController placeController;
    @Inject
    private ActivityMapper activityMapper;
    @Inject
    private EventBus eventBus;
    @Inject
    private AppInitiator appInitiator;

    @SuppressWarnings("deprecation")
    public void start(HasWidgets hasWidgets) {
        appInitiator.init();
        ActivityManager mainActivityManager = new ActivityManager(activityMapper, eventBus);
        mainActivityManager.setDisplay(shellView.getDisplay());
        CopPlaceHistoryMapper historyMapper = GWT.create(CopPlaceHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, new ConfigurationPlace());
        historyHandler.handleCurrentHistory();
        Viewport viewport = new Viewport();
        viewport.add(shellView);
        hasWidgets.add(viewport);

    }

}
