/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.CheckMenuItem;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import ol.layer.Base;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ol.OLMapManager.copLayerType;
import pl.edu.wat.wcy.cop.app.client.ol.OLMeasureManager.OLMeasureMode;
import pl.edu.wat.wcy.cop.app.client.ol.OLMeasureManager.OLMeasureType;
import pl.edu.wat.wcy.cop.app.client.ol.forms.*;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm;
import pl.edu.wat.wcy.cop.app.client.utils.MessagesUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.images.GisImages;
import pl.edu.wat.wcy.cop.app.client.utils.images.IconsUtils;
import pl.edu.wat.wcy.cop.app.shared.ol.OLCordsFormattingType;
import pl.edu.wat.wcy.cop.app.shared.ol.OLScaleLineUnit;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayerFormat;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayerType;
import pl.edu.wat.wcy.cop.app.shared.ol.sources.*;
// Provides ol gui utilities.

public class OLGuiUtils {

    private static GisImages GIS_IMAGES = GisImages.INSTANCE;
    private static Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();

    /**
     *
     * @param olMapManager
     * @param gisImages
     * @param messages
     * @return
     */
    public static Menu createAddLayerMenu(OLMapManager olMapManager, GisImages gisImages, Messages messages) {
        Menu addLayerMenu = new Menu();
        // tilelayer

        addLayerMenu.add(createAddTileLayerMenu(olMapManager, gisImages, messages));

        // weather layer
        addLayerMenu.add(createAddWeatherLayerMenu(olMapManager, gisImages, messages));
        // bing
        addLayerMenu.add(createAddBingLayerMenu(olMapManager, gisImages, messages));
        // mapbox
         MenuItem addMapboxLayerMenuItem = new
         MenuItem(messages.layeradd_mapbox_title(), gisImages.layer_bing());
         addLayerMenu.add(addMapboxLayerMenuItem);
         addMapboxLayerMenuItem.setSubMenu(getAddMapboxLayerMenu(olMapManager,
         messages, gisImages));
        // configured

        addLayerMenu.add(createAddConfigLayerMenu(olMapManager, gisImages, messages));


        return addLayerMenu;
    }

    public static MenuItem createAddConfigLayerMenu(OLMapManager olMapManager, GisImages gisImages, Messages messages) {
        return createLayerMenuItem(messages.layer_custom(), gisImages.layer_add(),
                getAddConfigLayerMenu(olMapManager, messages, gisImages));
    }

    public static MenuItem createAddBingLayerMenu(OLMapManager olMapManager, GisImages gisImages, Messages messages) {
        return createLayerMenuItem(messages.layeradd_bing_title(), gisImages.layer_bing(),
                getAddBingLayerMenu(olMapManager, messages, gisImages));
    }

    public static MenuItem createAddWeatherLayerMenu(OLMapManager olMapManager, GisImages gisImages, Messages messages) {
        return createLayerMenuItem(messages.layeradd_weather_title(), gisImages.layer_weather(),
                getAddWeatherLayerMenu(olMapManager, messages, gisImages));
    }

    public static MenuItem createAddTileLayerMenu(OLMapManager olMapManager, GisImages gisImages, Messages messages) {
        return createLayerMenuItem(messages.layeradd_tile_title(), gisImages.layers(),
                getAddTileLayerMenu(olMapManager, messages, gisImages));
    }

    /**
     *
     * @param olMapManager
     * @param messages
     * @param gisImages
     * @return
     */
    public static Menu getAddTileLayerMenu(OLMapManager olMapManager, Messages messages, GisImages gisImages) {
        Menu addTileLayerMenu = new Menu();
        for (OLTileLayerSource tileLayerType : OLTileLayerSource.values()) {
            addTileLayerMenu.add(GxtComponentsUtils.createMenuItem(tileLayerType.name(),
                    olMapManager.getSelectionHandlerForAddTileLayer(tileLayerType), gisImages.layers()));
        }
        return addTileLayerMenu;
    }

    /**
     *
     * @param olMapManager
     * @param messages
     * @param gisImages
     * @return
     */
    public static Menu getAddWeatherLayerMenu(OLMapManager olMapManager, Messages messages, GisImages gisImages) {
        Menu addWeatherLayerMenu = new Menu();
        for (OLWeatherLayerSources source : OLWeatherLayerSources.values()) {
            addWeatherLayerMenu.add(GxtComponentsUtils.createMenuItem(MessagesUtils.weatherLayerSourceToString(source),
                    olMapManager.getSelectionHandlerForAddWeatherLayer(source),
                    IconsUtils.weatherLayerSourceToIcon(source)));
        }
        return addWeatherLayerMenu;
    }

    /**
     *
     * @param olMapManager
     * @param messages
     * @param gisImages
     * @return
     */
    public static Menu getAddBingLayerMenu(OLMapManager olMapManager, Messages messages, GisImages gisImages) {
        Menu addBingLayerMenu = new Menu();
        for (OLBingLayerSource source : OLBingLayerSource.values()) {
            addBingLayerMenu.add(GxtComponentsUtils.createMenuItem(MessagesUtils.bingLayerSourceToString(source),
                    olMapManager.getSelectionHandlerForAddBingLayer(source), gisImages.layer_bing()));
        }
        return addBingLayerMenu;
    }

    /**
     *
     * @param olMapManager
     * @param messages
     * @param gisImages
     * @return
     */
    private static Menu getAddMapboxLayerMenu(OLMapManager olMapManager, Messages messages, GisImages gisImages) {
        Menu addMapboxLayerMenu = new Menu();
        for (OLMapBoxLayerSource source : OLMapBoxLayerSource.values()) {
            addMapboxLayerMenu.add(GxtComponentsUtils.createMenuItem(MessagesUtils.mapboxLayerSourceToString(source),
                    olMapManager.getSelectionHandlerForAddMapboxLayer(source),
                    IconsUtils.mapboxLayerSourceToIcon(source)));
        }
        return addMapboxLayerMenu;
    }

    private static MenuItem createLayerMenuItem(String title, ImageResource icon, Menu submenu) {
        MenuItem menuItem = new MenuItem(title, icon);
        menuItem.setSubMenu(submenu);
        return menuItem;
    }

    /**
     * @param olMapManager
     * @param messages2
     * @param gisImages
     * @return
     */
    public static Menu getAddConfigLayerMenu(OLMapManager olMapManager, Messages messages, GisImages gisImages) {
        Menu addConfigLayerMenu = new Menu();
        addConfigLayerMenu.add(GxtComponentsUtils.createMenuItem(messages.layers_custom_wms(),
                olMapManager.getSelectionHandlerForAddConfigLayer(OLLayerType.WMS), gisImages.layer_wms()));
        addConfigLayerMenu.add(GxtComponentsUtils.createMenuMenuItem(messages.layers_custom_wms_predefinied(),
                createPredefinedWMSMenu(olMapManager, messages, gisImages), gisImages.layer_wms()));
        addConfigLayerMenu.add(GxtComponentsUtils.createMenuItem(messages.layers_custom_wmts(),
                olMapManager.getSelectionHandlerForAddConfigLayer(OLLayerType.WMTS), gisImages.layer_wfs()));

        MenuItem addVectorLayerMenuItem = new MenuItem(messages.layeradd_vector_title(), gisImages.layer_add());
        addConfigLayerMenu.add(addVectorLayerMenuItem);
        addVectorLayerMenuItem.setSubMenu(getAddVectorLayerMenu(olMapManager, OLLayerType.VECTOR, messages, gisImages));

        MenuItem addVectorTileLayerMenuItem = new MenuItem(messages.layeradd_vector_tile_title(),
                gisImages.layer_add());
        addConfigLayerMenu.add(addVectorTileLayerMenuItem);
        addVectorTileLayerMenuItem
                .setSubMenu(getAddVectorLayerMenu(olMapManager, OLLayerType.VECTOR_TILE, messages, gisImages));

        addConfigLayerMenu.add(GxtComponentsUtils.createMenuItem(messages.layer_heatmap(),
                olMapManager.getSelectionHandlerForAddConfigLayer(OLLayerType.HEATMAP), gisImages.layer_kml()));

        return addConfigLayerMenu;
    }

    /**
     * @param olMapManager
     * @param vector
     * @param messages
     * @param gisImages
     * @return
     */
    private static Menu getAddVectorLayerMenu(OLMapManager olMapManager, OLLayerType vector, Messages messages,
                                              GisImages gisImages) {
        Menu menu = new Menu();
        menu.add(GxtComponentsUtils.createMenuItem(messages.layeradd_vector_kml_title(),
                olMapManager.getSelectionHandlerForAddConfigLayer(vector, OLLayerFormat.KML), gisImages.layer_kml()));
        menu.add(GxtComponentsUtils.createMenuItem("GPX",
                olMapManager.getSelectionHandlerForAddConfigLayer(vector, OLLayerFormat.GPX), gisImages.layer_kml()));
        menu.add(GxtComponentsUtils.createMenuItem(messages.layeradd_vector_geojson_title(),
                olMapManager.getSelectionHandlerForAddConfigLayer(vector, OLLayerFormat.GEO_JSON),
                gisImages.layer_kml()));
        menu.add(GxtComponentsUtils.createMenuItem(messages.layeradd_vector_topojson_title(),
                olMapManager.getSelectionHandlerForAddConfigLayer(vector, OLLayerFormat.TOPO_JSON),
                gisImages.layer_kml()));
        menu.add(GxtComponentsUtils.createMenuItem(messages.layeradd_vector_mvt_title(),
                olMapManager.getSelectionHandlerForAddConfigLayer(vector, OLLayerFormat.MVT), gisImages.layer_kml()));
        return menu;
    }

    /**
     *
     * @param olMapManager
     * @param messages
     * @param gisImages
     * @return
     */
    private static Menu createPredefinedWMSMenu(OLMapManager olMapManager, Messages messages, GisImages gisImages) {
        Menu menu = new Menu();
        for (OLWmsGeoportalLayerSources source : OLWmsGeoportalLayerSources.values()) {
            menu.add(GxtComponentsUtils.createMenuItem(source.getLayerName(),
                    olMapManager.getSelectionHandlerForAddPreconfiguredWmsLayerSource(source), gisImages.layer_wms()));
        }
        return menu;
    }

    /**
     * @param olMapManager
     * @param layer
     * @param gisImages
     * @param messages
     * @return
     */
    public static Menu createEditLayerMenu(OLMapManager olMapManager, Base layer, GisImages gisImages,
                                           Messages messages) {
        Menu editLayerMenu = new Menu();
        editLayerMenu.add(GxtComponentsUtils.createMenuItem(messages.layer_edit(),
                olMapManager.getSelectionHandlerForEditLayer(layer), gisImages.layer_edit()));
        if (copLayerType.GIS.equals(olMapManager.getLayerType(layer))) {
            editLayerMenu.add(GxtComponentsUtils.createMenuItem(messages.layer_remove(),
                    olMapManager.getSelectionHandlerForRemoveLayer(layer), gisImages.layer_remove()));
        }

        return editLayerMenu;
    }

    /**
     * @param olMapManager
     * @param layer
     * @param gisImages
     * @param messages
     * @return
     */
    public static Menu createEditLayerStyleMenu(OLMapManager olMapManager, Base layer, GisImages gisImages,
                                                Messages messages) {
        Menu editLayerStyleMenu = new Menu();
        editLayerStyleMenu.add(GxtComponentsUtils.createMenuItem(messages.layer_edit_style(),
                olMapManager.getSelectionHandlerForEditLayerStyle(layer), gisImages.layer_edit()));

        return editLayerStyleMenu;
    }

    /**
     * @param gisImages
     * @param layer
     * @return
     */
    public static ImageResource getImageForLayer(GisImages gisImages, Base layer) {
        // TODO Auto-generated method stub
        return gisImages.layers();
    }

    /**
     * @param olMapManager
     * @param gisImages
     * @param messages
     * @return
     */
    public static Menu createMeasurmentsMenu(OLMapManager olMapManager, GisImages gisImages, Messages messages) {
        String groupName = "measurment";
        Menu menu = new Menu();
        CheckMenuItem disabled = GxtComponentsUtils.createCheckMenuItemWithGroup(messages.menu_disabled(), groupName,
                gisImages.disabled());
        disabled.setChecked(true);
        CheckMenuItem distance = GxtComponentsUtils.createCheckMenuItemWithGroup(messages.gismenu_measuremt_distance(),
                groupName, gisImages.measure_distance());
        CheckMenuItem area = GxtComponentsUtils.createCheckMenuItemWithGroup(messages.gismenu_measuremt_area(),
                groupName, gisImages.measure_area());
        CheckMenuItem circle = GxtComponentsUtils.createCheckMenuItemWithGroup(messages.gismenu_measuremt_circle(),
                groupName, gisImages.measure_circle());
        menu.add(disabled);
        menu.add(distance);
        menu.add(area);
        menu.add(circle);

        menu.addSelectionHandler(new SelectionHandler<Item>() {
            @Override
            public void onSelection(SelectionEvent<Item> event) {
                olMapManager.stopMeasure();

                if (event.getSelectedItem().equals(distance)) {
                    olMapManager.startMeasure(OLMeasureType.DISTANCE, null, null,
                            OLMeasureMode.PERSIST_AND_IMMEDIATELY);
                } else if (event.getSelectedItem().equals(area)) {
                    olMapManager.startMeasure(OLMeasureType.POLYGON, null, null, OLMeasureMode.PERSIST_AND_IMMEDIATELY);
                } else if (event.getSelectedItem().equals(circle)) {
                    olMapManager.startMeasure(OLMeasureType.CIRCLE, null, null, OLMeasureMode.PERSIST_AND_IMMEDIATELY);
                }

            }
        });
        return menu;
    }

    /**
     *
     * @param newLayer
     * @param okHandler
     * @return
     */
    public static AbstractForm getFormForLayer(OLLayer newLayer, SelectHandler okHandler) {

        switch (newLayer.getLayerType()) {
            case IMAGE:
            case TILE:
            case BING:
            case WEATHER:
                return new OLTileLayerForm(newLayer, okHandler);
            case WMS:
                return new WmsLayerForm(newLayer, okHandler);
            case WMTS:
                return new WmtsLayerForm(newLayer, okHandler);
            case XYZ:
                return new XyzLayerForm(newLayer, okHandler);
            case VECTOR_TILE:
                return new VectorTileLayerForm(newLayer, okHandler);
            case KML:
            case VECTOR:
                return new VectorLayerForm(newLayer, okHandler);
            case HEATMAP:
                return new HeatmapLayerForm(newLayer, okHandler);
            default:
                break;

        }
        return null;
    }

    /**
     * @param layer
     * @param okHandler
     * @return
     */
    public static OLLayerForm getFormForLayer(Base layer, SelectHandler okHandler) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param olMapManager
     * @param gisImages
     * @param messages
     * @return
     */
    public static Menu createScaleLineTypeMenu(OLMapManager olMapManager, GisImages gisImages, Messages messages) {
        String groupName = "scale_line";
        Menu menu = new Menu();
        for (OLScaleLineUnit scaleLineUnit : OLScaleLineUnit.values()) {
            CheckMenuItem scaleLineUnitItem = GxtComponentsUtils.createCheckMenuItemWithGroup(
                    MessagesUtils.scaleLineUnitToString(scaleLineUnit), groupName,
                    IconsUtils.scaleLineUnitToIcon(scaleLineUnit));
            scaleLineUnitItem.setChecked(olMapManager.getSelectedScaleLineUnit().equals(scaleLineUnit));
            scaleLineUnitItem.setData(groupName, scaleLineUnit);
            menu.add(scaleLineUnitItem);
            scaleLineUnitItem.addSelectionHandler(new SelectionHandler<Item>() {
                @Override
                public void onSelection(SelectionEvent<Item> event) {
                    olMapManager.setSelectedScaleLine(event.getSelectedItem().getData(groupName));
                }
            });
        }

        return menu;
    }

    /**
     * @param olMapManager
     * @param gisImages
     * @param messages
     * @return
     */
    public static Menu createCoordsFormatterMenu(OLMapManager olMapManager, GisImages gisImages, Messages messages) {
        String groupName = "coords";
        Menu menu = new Menu();
        for (OLCordsFormattingType coordsFormat : OLCordsFormattingType.values()) {
            CheckMenuItem coordsFormatItem = GxtComponentsUtils.createCheckMenuItemWithGroup(
                    MessagesUtils.coordsFormatToString(coordsFormat), groupName,
                    IconsUtils.coordsFormatToIcon(coordsFormat));
            coordsFormatItem.setChecked(olMapManager.getSelectedCoordsFormat().equals(coordsFormat));
            coordsFormatItem.setData(groupName, coordsFormat);
            menu.add(coordsFormatItem);
            coordsFormatItem.addSelectionHandler(new SelectionHandler<Item>() {

                @Override
                public void onSelection(SelectionEvent<Item> event) {
                    olMapManager.setSelectedCoordsFormat(event.getSelectedItem().getData(groupName));
                }
            });
        }

        return menu;
    }

    /**
     * @param olMapManager
     * @param gisImages
     * @param messages
     * @return
     */
    public static Menu createControlsMenu(OLMapManager olMapManager, GisImages gisImages, Messages messages) {
        return new Menu();
    }

}
