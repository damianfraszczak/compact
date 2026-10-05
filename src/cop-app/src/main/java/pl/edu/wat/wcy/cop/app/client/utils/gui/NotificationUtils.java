/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.gui;

import com.sencha.gxt.widget.core.client.info.DefaultInfoConfig;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.info.InfoConfig.InfoPosition;
// Provides notification utilities.


public class NotificationUtils {

    public static final int DEFAULT_DISPLAY_TIME = 2500;
    public static final InfoPosition DEFAULT_POSITION = InfoPosition.BOTTOM_LEFT;

    public static void showToast(String title, String message) {
        showToast(title, message, DEFAULT_POSITION, DEFAULT_DISPLAY_TIME);
    }

    public static void showToast(String title, String message, InfoPosition position, int displayTime) {
        DefaultInfoConfig config = new DefaultInfoConfig(title, message);
        config.setPosition(position);
        config.setDisplay(displayTime);
        Info.display(config);
    }
}
