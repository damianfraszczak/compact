/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol.extra;

import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ol.OLGuiUtils;
import pl.edu.wat.wcy.cop.app.client.ol.OLMapManager;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.WidgetsGroupLikeRibbonWithButtons;
import pl.edu.wat.wcy.cop.app.client.utils.images.GisImages;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayerFormat;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayerType;
// Represents ol add layer ribbon component.


public class OLAddLayerRibbonComponent extends WidgetsGroupLikeRibbonWithButtons {
    @Inject
    private OLMapManager olMapManager;
    @Inject
    private Messages messages;
    @Inject
    private GisImages gisImages;

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.utils.gui.
     * WidgetsGroupLikeRibbonWithButtons#getSelectHandlerImpl()
     */
    @Override
    protected SelectHandler getSelectHandlerImpl() {
        return new SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {

            }
        };
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.utils.gui.WidgetsGroupLikeRibbon#
     * addRibbonElementsUsingConfigureWidgetPlacement()
     */
    @Override
    protected void addRibbonElementsUsingConfigureWidgetPlacement() {
        createTextMenuButton(messages.layeradd_tile_title(), gisImages.layers(), 0, 0, null,
                OLGuiUtils.getAddTileLayerMenu(olMapManager, messages, gisImages));
        createTextMenuButton(messages.layeradd_weather_title(), gisImages.layer_weather(), 1, 0, null,
                OLGuiUtils.getAddWeatherLayerMenu(olMapManager, messages, gisImages));
        createTextMenuButton(messages.layeradd_bing_title(), gisImages.layer_bing(), 2, 0, null,
                OLGuiUtils.getAddBingLayerMenu(olMapManager, messages, gisImages));
        createTextMenuButton(messages.layer_custom(), gisImages.layer_add(), 0, 1, null,
                OLGuiUtils.getAddConfigLayerMenu(olMapManager, messages, gisImages));
        TextButton button = GxtComponentsUtils.createTextButton(messages.layeradd_simresult_title(),
                olMapManager.getSelectionHandlerForAddConfigLayer(OLLayerType.VECTOR, OLLayerFormat.KML),
                gisImages.layer_kml());
        configureWidgetPlacement(button, 1, 1, "");

    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.utils.gui.WidgetsGroupLikeRibbon#
     * getButtonGroupHeading()
     */
    @Override
    protected String getButtonGroupHeading() {
        return messages.layer_add();
    }

}
