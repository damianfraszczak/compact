/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.utils.gui.NotificationUtils;

import java.util.logging.Level;


@Singleton
// Represents error formatter.
public class ErrorFormatter {

    @Inject
    private Messages messages;

    public void showError(Object source, Throwable exception, String inputMessage) {
        String message = inputMessage + exception + " - " + exception.getMessage() + "Stack trace: "
                + stackTraceToString(exception);
        CopLogger.getInstance().log(source, Level.SEVERE, message);
        NotificationUtils.showToast("", message);
    }

    private String stackTraceToString(Throwable e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
