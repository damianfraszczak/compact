/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.mvp.impl;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.mvp.ShellView;
import pl.edu.wat.wcy.cop.app.client.services.server.VisualizationDataProvider;
import pl.edu.wat.wcy.cop.app.shared.response.OkResponse;
// Represents shell view impl.

public class ShellViewImpl implements ShellView, IsWidget, ResizeHandler {
    private static final int WIDTH_THRESHOLD = 743;

    private BorderLayoutContainer container;
    private ContentPanel display = new ContentPanel();
    @Inject
    private VisualizationDataProvider visualizationDataProvider;
    @Inject
    private Messages messages;

    public ShellViewImpl() {
        Window.addResizeHandler(this);
    }

    @Override
    public Widget asWidget() {
        if (container == null) {
            container = new BorderLayoutContainer();
            display.setHeaderVisible(true);
            display.setHeading(this.messages.app_title());
            container.setCenterWidget(display, new MarginData(0));
            Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                @Override
                public void execute() {
                    handleResize(Window.getClientWidth(), Window.getClientHeight());
                }
            });

        }
        return container;
    }

    public AcceptsOneWidget getDisplay() {
        return new AcceptsOneWidget() {
            @Override
            public void setWidget(IsWidget w) {
                display.setWidget(w);
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {
                        display.forceLayout();
                    }
                });
            }
        };
    }

    @Override
    public void onResize(ResizeEvent resizeEvent) {
        if (GXT.isTablet()) {
            handleResize(resizeEvent.getWidth(), resizeEvent.getHeight());
        } else {
            handleResize(resizeEvent.getWidth(), WIDTH_THRESHOLD);
        }
    }

    private void handleResize(int width, int threshold) {
        if (width > threshold) {
            container.hide(LayoutRegion.NORTH);
            container.show(LayoutRegion.WEST);
        } else {
            container.hide(LayoutRegion.WEST);
            container.show(LayoutRegion.NORTH);
        }
    }

}
