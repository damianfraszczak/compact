/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol.extra;

import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ol.OLMapManager;
import pl.edu.wat.wcy.cop.app.client.utils.MessagesUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.WidgetsGroupLikeRibbonWithButtonsGroup;
import pl.edu.wat.wcy.cop.app.client.utils.images.IconsUtils;
import pl.edu.wat.wcy.cop.app.shared.ol.OLCordsFormattingType;
// Represents ol coords formating mode ribbon component.


public class OLCoordsFormatingModeRibbonComponent extends WidgetsGroupLikeRibbonWithButtonsGroup {
    @Inject
    private Messages messages;
    @Inject
    private OLMapManager olMapManager;

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.utils.gui.WidgetsGroupLikeRibbon#
     * updateComponentState()
     */
    @Override
    public void updateComponentState() {
        super.updateComponentState();
        setSelectedButtonByKey(olMapManager.getSelectedCoordsFormat());
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.utils.gui.
     * WidgetsGroupLikeRibbonWithToogleButtons#getSelectHandlerImpl()
     */
    @Override
    protected SelectHandler getSelectHandlerImpl() {
        return new SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                ToggleButton button = (ToggleButton) event.getSource();
                olMapManager.setSelectedCoordsFormat(button.getData(WIDGETS_GROUP_LIKE_RIBBON_DATA_KEY));
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
        int index = 0;
        for (OLCordsFormattingType coordsFormat : OLCordsFormattingType.values()) {
            createToogleButton(MessagesUtils.coordsFormatToString(coordsFormat),
                    IconsUtils.coordsFormatToIcon(coordsFormat), coordsFormat, index / 2, index % 2);
            index++;
        }
        updateComponentState();
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.utils.gui.WidgetsGroupLikeRibbon#
     * getButtonGroupHeading()
     */
    @Override
    protected String getButtonGroupHeading() {
        return messages.coords_control();
    }

}
