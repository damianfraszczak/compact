/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Command;
// Represents gwt scheduler.

public class GwtScheduler {
    public static void delay(int delayMs, Command afterDelay) {
        Scheduler.get().scheduleFixedDelay(() -> {
            afterDelay.execute();
            return false;
        }, delayMs);
    }
}
