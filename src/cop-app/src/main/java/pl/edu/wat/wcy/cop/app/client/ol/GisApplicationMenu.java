/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.menu.Menu;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.services.server.DecisionSupportProvider;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.images.GisImages;
// Represents gis application menu.

public class GisApplicationMenu implements IsWidget {
    @Inject
    private Messages messages;
    @Inject
    private OLMapManager olMapManager;
    @Inject
    private DecisionSupportProvider decisionSupportProvider;
    @Inject
    private GisImages gisImages;
    private TextButton gisMenuButton;

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget() {
        if (gisMenuButton == null) {
            gisMenuButton = new TextButton(messages.gismenu_tile(), gisImages.layers());
            Menu gisMenu = new Menu();
            gisMenuButton.setMenu(gisMenu);

            gisMenu.add(GxtComponentsUtils.createMenuMenuItem(messages.layeradd_title(),
                    OLGuiUtils.createAddLayerMenu(olMapManager, gisImages, messages), gisImages.layer_add()));
            gisMenu.add(GxtComponentsUtils.createMenuMenuItem(messages.gismenu_measuremt(),
                    OLGuiUtils.createMeasurmentsMenu(olMapManager, gisImages, messages), gisImages.measure()));
            gisMenu.add(GxtComponentsUtils.createMenuMenuItem(messages.gismenu_scaleline(),
                    OLGuiUtils.createScaleLineTypeMenu(olMapManager, gisImages, messages), gisImages.scale_line()));
            gisMenu.add(GxtComponentsUtils.createMenuMenuItem(messages.gismenu_coords(),
                    OLGuiUtils.createCoordsFormatterMenu(olMapManager, gisImages, messages), gisImages.coords()));
            // gisMenu.add(GxtComponentsUtils.createMenuMenuItem(messages.gismenu_measuremt(),
            // OLGuiUtils.createControlsMenu(olMapManager, gisImages, messages),
            // gisImages.measurement()));
            gisMenu.add(GxtComponentsUtils.createMenuMenuItem(messages.gismenu_decisionsupport(),
                    OLGuiDecisionSupportUtils.createDecisionSupportMenu(olMapManager, decisionSupportProvider, messages)));
        }
        return gisMenuButton;
    }

}
