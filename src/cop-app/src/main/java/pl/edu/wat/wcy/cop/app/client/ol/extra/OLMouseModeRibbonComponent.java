/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol.extra;

import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ol.OLGuiDecisionSupportUtils;
import pl.edu.wat.wcy.cop.app.client.ol.OLMapManager;
import pl.edu.wat.wcy.cop.app.client.ol.OLMeasureManager.OLMeasureMode;
import pl.edu.wat.wcy.cop.app.client.ol.OLMeasureManager.OLMeasureType;
import pl.edu.wat.wcy.cop.app.client.ol.OLNativeMethods;
import pl.edu.wat.wcy.cop.app.client.services.server.DecisionSupportProvider;
import pl.edu.wat.wcy.cop.app.client.utils.gui.WidgetsGroupLikeRibbonWithButtonsGroup;
import pl.edu.wat.wcy.cop.app.client.utils.images.GisImages;
// Represents ol mouse mode ribbon component.


public class OLMouseModeRibbonComponent extends WidgetsGroupLikeRibbonWithButtonsGroup {

    @Inject
    private OLMapManager olMapManager;
    @Inject
    private DecisionSupportProvider decisionSupportProvider;
    @Inject
    private Messages messages;
    @Inject
    private GisImages gisImages;

    @Override
    protected SelectHandler getSelectHandlerImpl() {
        return new SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                Component button = (Component) event.getSource();
                MouseMode selectedMouseMode = button.getData(WIDGETS_GROUP_LIKE_RIBBON_DATA_KEY);
                olMapManager.clearMouseMode();
                switch (selectedMouseMode) {
                    case AREA_MEASURE:
                        olMapManager.startMeasure(OLMeasureType.POLYGON, null, null, OLMeasureMode.PERSIST);
                        break;
                    case CIRCLE_MEASURE:
                        olMapManager.startMeasure(OLMeasureType.CIRCLE, null, null, OLMeasureMode.PERSIST);
                        break;
                    case DISTANCE_MEASURE:
                        olMapManager.startMeasure(OLMeasureType.DISTANCE, null, null,
                                OLMeasureMode.PERSIST);
                        break;
                    case FLOOD_AREA_MEASURE:
                        OLGuiDecisionSupportUtils.getSelectionHandlerForFloodArea(olMapManager, decisionSupportProvider)
                                .onSelection(null);
                        break;
                    case TERRAIN_PROFILE_MEASURE:
                        OLGuiDecisionSupportUtils
                                .getSelectionHandlerForTerrainProfile(olMapManager, decisionSupportProvider)
                                .onSelection(null);
                        break;
                    case SPY:
                        olMapManager.startSpyMouseMode();
                        break;
                    case LOS_AREA_MEASURE:
                        OLGuiDecisionSupportUtils.getSelectionHandlerForLosArea(olMapManager, decisionSupportProvider)
                                .onSelection(null);
                        break;
                    case FIT_MAP:
                        OLNativeMethods.fitToFeatureOnMap(olMapManager.getMap(),
                                olMapManager.getAllFeatures());
                        break;
                    case INDICATING:
                    default:
                        break;
                }

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
        createToogleButton(messages.mousemode_indicating(), gisImages.measure(), MouseMode.INDICATING, 0, 0);
        createToogleButton(messages.mousemode_measure_area(), gisImages.measure_area(), MouseMode.AREA_MEASURE, 1, 0);
        createToogleButton(messages.mousemode_measure_circle(), gisImages.measure_circle(), MouseMode.CIRCLE_MEASURE, 2,
                0);
        createToogleButton(messages.mousemode_measure_distance(), gisImages.measure_distance(),
                MouseMode.DISTANCE_MEASURE, 0, 1);
        createToogleButton(messages.mousemode_decisionsupport_floodarea(), gisImages.measure(),
                MouseMode.FLOOD_AREA_MEASURE, 1, 1);
        createToogleButton(messages.mousemode_decisionsupport_terrainProfile(), gisImages.measure(),
                MouseMode.TERRAIN_PROFILE_MEASURE, 2, 1);
        createToogleButton(messages.mousemode_spy(), gisImages.spyMouseMode(), MouseMode.SPY, 0, 2);
        createToogleButton(messages.mousemode_decisionsupport_losarea(), gisImages.measure(),
                MouseMode.LOS_AREA_MEASURE, 1, 2);
        createTextButton(messages.gismenu_fit(), gisImages.center(),
                MouseMode.FIT_MAP, 1, 2);
        setSelectedButtonByKey(MouseMode.INDICATING);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.utils.gui.WidgetsGroupLikeRibbon#
     * getButtonGroupHeading()
     */
    @Override
    protected String getButtonGroupHeading() {
        return messages.mousemode();
    }

    public enum MouseMode {
        INDICATING, CIRCLE_MEASURE, AREA_MEASURE, DISTANCE_MEASURE, FLOOD_AREA_MEASURE, TERRAIN_PROFILE_MEASURE, SPY, LOS_AREA_MEASURE, FIT_MAP
    }

}
