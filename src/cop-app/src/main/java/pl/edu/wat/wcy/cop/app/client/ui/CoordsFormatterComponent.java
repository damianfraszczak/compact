/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.widget.core.client.button.TextButton;
import pl.edu.wat.wcy.cop.app.client.ol.OLCoordsFormatter.CoordsChangedListener;
import pl.edu.wat.wcy.cop.app.client.ol.OLMapUtils;
import pl.edu.wat.wcy.cop.app.client.utils.images.GisImages;
// Represents coords formatter component.


public class CoordsFormatterComponent implements IsWidget {
    private TextButton coordsLabel;

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget() {
        if (coordsLabel == null) {
            coordsLabel = new TextButton("", GisImages.INSTANCE.coords());
            coordsLabel.setEnabled(false);
            coordsLabel.removeStyleName(ThemeStyles.get().style().disabled());
            OLMapUtils.COORDS_FORMATTER.setListener(new CoordsChangedListener() {
                @Override
                public void onCoordsChanged(String formattedCoords) {
                    coordsLabel.setText(formattedCoords);
                }
            });
        }
        return coordsLabel;
    }

}
