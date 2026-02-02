/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.widget.core.client.button.TextButton;
import pl.edu.wat.wcy.cop.app.client.utils.images.AppImages;
// Represents user information widget.


public class UserInformationWidget implements IsWidget {
    private TextButton userLabel;

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget() {
        if (userLabel == null) {
            userLabel = new TextButton("Mariusz Chmielewski", AppImages.INSTANCE.user());
            userLabel.setEnabled(false);
            userLabel.removeStyleName(ThemeStyles.get().style().disabled());
        }
        return userLabel;
    }

}
