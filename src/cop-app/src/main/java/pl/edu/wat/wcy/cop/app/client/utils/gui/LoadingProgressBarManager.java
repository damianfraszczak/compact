/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.gui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.utils.images.AppImages;


@Singleton
// Manages loading progress bar.
public class LoadingProgressBarManager {
    private static final int Z_INDEX = 101;
    private final PopupPanel popupPanel = new PopupPanel();
    private final FlowPanel container = new FlowPanel();

    private boolean visible;
    private int count;

    @Inject
    public LoadingProgressBarManager(Messages messages, AppImages asset) {
        final Image ajaxImage = new Image(asset.loadingIcon());
        final Grid grid = new Grid(1, 3);
        grid.setWidget(0, 0, ajaxImage);
        grid.setText(0, 2, messages.loadingMessage_message());
        this.container.add(grid);
        popupPanel.add(this.container);
    }

    public void showProgressBar() {
        if (!visible) {
            visible = true;
            setProgressBarPositionToFront();
            popupPanel.setGlassEnabled(true);
            popupPanel.center();
            popupPanel.show();
        }
        count++;
    }

    public void hideProgressBar() {
        count--;
        if (count == 0 && visible) {
            popupPanel.setGlassEnabled(false);
            popupPanel.hide();
            visible = false;
        }

    }
    public void forceHideProgressBar() {
        count = 1;
        hideProgressBar();

    }
    /**
     *
     */
    private void setProgressBarPositionToFront() {
        DOM.setIntStyleAttribute(popupPanel.getElement(), "zIndex", Z_INDEX);

    }
}
