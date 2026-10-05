/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol.extra;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ol.OLMapManager;
import pl.edu.wat.wcy.cop.app.client.utils.MessagesUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.WidgetsGroupLikeRibbonWithButtons;
import pl.edu.wat.wcy.cop.app.client.utils.images.IconsUtils;
import pl.edu.wat.wcy.cop.app.shared.ol.OLControlType;
// Represents ol controls ribbon component.


public class OLControlsRibbonComponent extends WidgetsGroupLikeRibbonWithButtons {
    @Inject
    private Messages messages;
    @Inject
    private OLMapManager olMapManager;

    @Override
    public void updateComponentState() {
        for (Widget widget : getWidgets()) {
            ToggleButton button = (ToggleButton) widget;
            button.setValue(olMapManager.isControlActive(button.getData(WIDGETS_GROUP_LIKE_RIBBON_DATA_KEY)));
        }
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
        for (OLControlType controltype : OLControlType.values()) {
            createToogleButton(MessagesUtils.controlTypeToString(controltype),
                    IconsUtils.controlTypeToIcon(controltype), controltype, index / 2, index % 2);
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
        return messages.controltype_control();
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.utils.gui.
     * WidgetsGroupLikeRibbonWithTextButtons#getSelectHandlerImpl()
     */
    @Override
    protected SelectHandler getSelectHandlerImpl() {
        return new SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                ToggleButton button = (ToggleButton) event.getSource();
                olMapManager.changeControlActivity(button.getData(WIDGETS_GROUP_LIKE_RIBBON_DATA_KEY));
            }
        };
    }

}
