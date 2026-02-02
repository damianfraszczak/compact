/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import ol.OLUtil;
import ol.layer.Base;
import pl.edu.wat.wcy.cop.app.client.events.*;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreePanel;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.images.AppImages;
import pl.edu.wat.wcy.cop.app.client.utils.images.GisImages;
import pl.edu.wat.wcy.cop.app.client.utils.CopLogger;
import pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayerFormat;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayerType;

import java.util.Map;


@SuppressWarnings("unchecked")
// Renders layer management panel UI.
public abstract class LayerManagementPanel extends TreePanel<UniqueObject<String>, String> {
    private final LayerManagementPanelEventBinder eventBinder = GWT.create(LayerManagementPanelEventBinder.class);
    @Inject
    protected OLMapManager olMapManager;
    @Inject
    protected GisImages gisImages;
    @Inject
    protected AppImages appImages;
    protected EventBus eventBus;
    protected Map<Base, LayerTreeElement> layerNodes;
    private Menu editLayerStyleMenu;
    private Menu addLayerMenu;

    /**
     * @param eventBus
     */
    public LayerManagementPanel(EventBus eventBus) {
        super();
        this.eventBus = eventBus;
        eventBinder.bindEventHandlers(this, eventBus);
    }

    @EventHandler
    void onMapLoadedEvent(MapLoadedEvent event) {
        reloadData();
    }

    @EventHandler
    void onNewLayerAddedEvent(ObjectAddedEvent<Base> event) {
        if (checkIfIntrestEvent(event)) {
            CopLogger.getInstance().debug(this,
                    "Otrzymalem event dodanej nowej warstwy " + OLUtil.getName(event.getObject()));
            addLayer(event.getObject());
        }

    }

    @EventHandler
    void onLayerUpdatedEvent(ObjectUpdatedEvent<Base> event) {
        if (checkIfIntrestEvent(event)) {
            CopLogger.getInstance().debug(this,
                    "Otrzymalem event aktualziacji warstwy " + OLUtil.getName(event.getObject()));
            updateLayer(event.getObject());
        }
    }

    @EventHandler
    void onLayerRemovedEvent(ObjectRemovedEvent<Base> event) {
        if (checkIfIntrestEvent(event)) {
            CopLogger.getInstance().debug(this,
                    "Otrzymalem event usuniecia warstwy " + OLUtil.getName(event.getObject()));
            removeLayer(event.getObject());
        }
    }

    protected void addLayer(Base layer) {
        TreeUtils.addNodeToParent(tree, getParent(layer), getNode(layer));
    }

    /**
     * @param layer
     */
    protected void updateLayer(Base layer) {
        TreeUtils.updateNode(tree, getNode(layer));
    }

    /**
     * @param layer
     */
    protected void removeLayer(Base layer) {
        TreeUtils.removeNode(tree, getNode(layer));
    }

    /**
     * @param layer
     * @return
     */
    protected TreeElement<? extends UniqueObject<String>, String> getNode(Base layer) {
        LayerTreeElement layerNode = layerNodes.get(layer);
        if (layerNode == null) {
            layerNode = new LayerTreeElement(new OLBaseWrapper(layer), OLGuiUtils.getImageForLayer(gisImages, layer));
        }
        return layerNode;
    }

    /**
     * @param layer
     * @return
     */
    protected abstract TreeElement getParent(Base layer);

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.tree.TreePanel#buildToolbar()
     */

    @Override
    protected void addItemsToToolbar(ToolBar toolbar, Widget... components) {
        toolbar.add(GxtComponentsUtils.createButtonMenu(MESSAGES.layeradd_title(), getAddLayerMenu(),
                gisImages.layer_add()));
        // TODO naprawienie exportu mapy
//        toolbar.add(GxtComponentsUtils.createTextButton(MESSAGES.data_export(),
//                new SelectEvent.SelectHandler() {
//
//                    @Override
//                    public void onSelect(SelectEvent event) {
//                        OLNativeMethods.exportMap(olMapManager.getMap());
//                    }
//                }, gisImages.export_map()));
        super.addItemsToToolbar(toolbar, components);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.tree.TreePanel#getContextMenuForObject
     * (pl.edu.wat.wcy.cop.apRp.client.ui.tree.TreeElement)
     */
    @Override
    protected Menu getContextMenuForObject(TreeElement<UniqueObject<String>, String> selectedElement) {
        Menu menu = new Menu();
        menu.add(GxtComponentsUtils.createMenuMenuItem(MESSAGES.layeradd_title(), getAddLayerMenu(),
                gisImages.layer_add()));
        menu.add(GxtComponentsUtils.createMenuItem(MESSAGES.layeradd_simresult_title(),
                olMapManager.getSelectionHandlerForAddConfigLayer(OLLayerType.VECTOR, OLLayerFormat.KML),
                gisImages.layer_kml()));
        if (selectedElement.getObject() instanceof OLBaseWrapper) {
            OLBaseWrapper baseWrapper = (OLBaseWrapper) selectedElement.getObject();

            menu.add(GxtComponentsUtils.createMenuMenuItem(MESSAGES.layer_edit(), getEditLayerMenu(baseWrapper),
                    gisImages.layer_edit()));
//            edycji stylow mapy wylaczona
//            menu.add(GxtComponentsUtils.createMenuMenuItem(MESSAGES.layer_edit_style(),
//                    getEditLayerStyleMenu(baseWrapper), gisImages.layer_edit()));

            menu.add(GxtComponentsUtils.createMenuItem(MESSAGES.layer_selectForSpyMouseMode(),
                    new SelectionHandler<Item>() {

                        @Override
                        public void onSelection(SelectionEvent<Item> event) {
                            selectForSpyMode(baseWrapper.getLayer());
                        }

                        private native void selectForSpyMode(Base layer) /*-{
                            if ($wnd.changeSpySelectedLayer) {
                                $wnd.changeSpySelectedLayer(layer);
                            } else {
                                $wnd.spySelectedLayer = layer;
                            }
                        }-*/;
                    }, appImages.spyMousemode()));
            menu.add(GxtComponentsUtils.createMenuItem("Uzyj warstwy jako overview",
                    new SelectionHandler<Item>() {

                        @Override
                        public void onSelection(SelectionEvent<Item> event) {
                           olMapManager.setOverview(baseWrapper.getLayer());
                        }


                    }, appImages.spyMousemode()));

        }
        return menu;
    }

    /**
     * @param baseWrapper
     * @return
     */
    private Menu getEditLayerMenu(OLBaseWrapper baseWrapper) {
        return OLGuiUtils.createEditLayerMenu(olMapManager, baseWrapper.getLayer(), gisImages, MESSAGES);
    }

    /**
     * @param baseWrapper
     * @return
     */
    private Menu getEditLayerStyleMenu(OLBaseWrapper baseWrapper) {
        if (editLayerStyleMenu == null) {
            editLayerStyleMenu = OLGuiUtils.createEditLayerStyleMenu(olMapManager, baseWrapper.getLayer(), gisImages,
                    MESSAGES);
        }
        return editLayerStyleMenu;
    }

    private Menu getAddLayerMenu() {
        if (addLayerMenu == null) {
            addLayerMenu = OLGuiUtils.createAddLayerMenu(olMapManager, gisImages, MESSAGES);
        }
        return addLayerMenu;
    }

    /**
     * @param event
     * @return
     */
    private boolean checkIfIntrestEvent(ObjectEvent<?> event) {
        return event.getObject() instanceof Base;
    }

    interface LayerManagementPanelEventBinder extends EventBinder<LayerManagementPanel> {
    }

}
