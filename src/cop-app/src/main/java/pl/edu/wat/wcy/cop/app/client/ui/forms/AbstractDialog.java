/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtUtils;
// Represents abstract dialog.

public abstract class AbstractDialog {
    protected static Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    protected Dialog window;
    protected SelectHandler okHandler;

    /**
     *
     * @param model
     * @param okHandler
     */
    public AbstractDialog(SelectHandler okHandler) {
        this(MESSAGES.defaultdialog_title(), okHandler);
    }

    public AbstractDialog(String title, SelectHandler okHandler) {
        this.okHandler = okHandler;
        prepareUI(title);
        layoutMainComponent();
    }

    public void show() {
        beforeShow();
        window.show();
        window.addShowHandler(new ShowHandler() {

            @Override
            public void onShow(ShowEvent event) {
                afterShow();

            }
        });
    }

    /**
     *
     */
    protected void afterShow() {
        // TODO Auto-generated method stub

    }

    /**
     * @return the okHandler
     */
    public SelectHandler getOkHandler() {
        return okHandler;
    }

    /**
     * @param okHandler
     *            the okHandler to set
     */
    public void setOkHandler(SelectHandler okHandler) {
        this.okHandler = okHandler;
    }

    /**
     *
     */
    protected void layoutMainComponent() {
        initMainComponent();
        window.setWidget(getMainComponent());
    }

    /**
     *
     */
    protected void initMainComponent() {
        // TODO Auto-generated method stub

    }

    /**
     *
     */
    protected void beforeShow() {
        // TODO Auto-generated method stub

    }

    /**
     * @param title
     *
     */
    protected void prepareUI(String title) {
        window = new Dialog();
        window.setModal(true);
        window.setBlinkModal(true);
        window.setPixelSize(GxtUtils.MIN_WIDTH, GxtUtils.MIN_HEIGHT);
        window.setBodyBorder(false);
        window.setResizable(true);
        window.setHideOnButtonClick(true);
        window.setShadow(true);
        window.setPredefinedButtons();
        window.setMaximizable(true);
        if (title != null) {
            window.setTitle(title);
            window.setHeading(title);
        }
        createButtons();
    }

    /**
     *
     */
    protected void createButtons() {
        window.addButton(new TextButton(MESSAGES.defaultdialog_cancel(), new SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                window.hide();
            }
        }));
        window.addButton(new TextButton(MESSAGES.defaultdialog_ok(), new SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                window.hide();
                okHandler.onSelect(event);
            }
        }));
    }

    /**
     * @return
     */
    protected abstract IsWidget getMainComponent();
}
