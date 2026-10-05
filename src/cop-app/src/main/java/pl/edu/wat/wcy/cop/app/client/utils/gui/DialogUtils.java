/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.gui;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.box.MultiLinePromptMessageBox;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;

import static com.sencha.gxt.widget.core.client.box.MessageBox.ICONS;
// Provides dialog utilities.


public class DialogUtils {

    private static Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();



    /**
     *
     * @param title
     * @param message
     */
    public static void showAlertDialog(String title, String message) {
        AlertMessageBox messageBox = new AlertMessageBox(title, message);
        messageBox.show();
    }

    /**
     *
     * @param title
     * @param message
     */
    public static void showMultiLineInputDialog(String title, String message, AsyncCallback<String> callback) {
        MultiLinePromptMessageBox messageBox = new MultiLinePromptMessageBox(title, message);
        messageBox.setModal(true);
        messageBox.addDialogHideHandler(new DialogHideHandler() {

            @Override
            public void onDialogHide(DialogHideEvent event) {
                if (event.getHideButton() == PredefinedButton.OK) {
                    callback.onSuccess(messageBox.getValue());
                }

            }
        });
        messageBox.show();
    }
    public static void showInfoDialog(String title, String message) {
        AlertMessageBox messageBox = new AlertMessageBox(title, message);
        messageBox.setIcon(ICONS.info());
        messageBox.show();
    }
    /**
     *
     * @param title
     * @param message
     * @param okHandler
     */
    public static void showConfirmDialog(String title, String message, SelectHandler okHandler) {
        ConfirmMessageBox messageBox = new ConfirmMessageBox(title, message);
        messageBox.addDialogHideHandler(new DialogHideHandler() {

            @Override
            public void onDialogHide(DialogHideEvent event) {
                if (event.getHideButton() == PredefinedButton.YES) {
                    okHandler.onSelect(new SelectEvent());
                }

            }
        });
        messageBox.show();

    }

    /**
     *
     */
    public static void showErrorDialog_NotSelected() {
        showAlertDialog(MESSAGES.errordialog_notselected_title(), MESSAGES.errordialog_notselected_message());
    }


}
