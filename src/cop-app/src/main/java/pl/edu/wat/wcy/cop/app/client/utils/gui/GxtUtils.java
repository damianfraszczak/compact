/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.gui;

import com.sencha.gxt.core.client.dom.XDOM;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
// Provides gxt utilities.


public class GxtUtils {

    public static final int MARGIN_SIZE = 5;
    public static final int MIN_WIDTH = Math.max(400, getScreenWidth() / 3) + 50;
    public static final int MIN_HEIGHT = Math.max(400, getScreenHeight() / 3);
    public static final int FORM_FIELD_SIZE = 60;
    private static final int DEFAULT_PADDING_SIZE = 5;
    public static final Padding DEFAULT_PADDING = new Padding(DEFAULT_PADDING_SIZE);

    public static int getScreenWidth() {
        return XDOM.getViewportWidth();
    }

    public static int getScreenHeight() {
        return XDOM.getViewportHeight();
    }

    public static Margins getDefaultMarginForTheme() {
        // trioton
        return new Margins(MARGIN_SIZE);
    }

    public static void initThemeBasedPropsForContainer(SimpleContainer container) {
        // dla tritona
        if (true) {
            container.setBorders(true);
        }
    }

    /**
     * @param window
     */
    public static void maximize(Dialog window) {
        window.setPixelSize(getScreenWidth(), getScreenHeight());
    }
}
