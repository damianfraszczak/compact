/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol;

import com.google.gwt.core.client.Callback;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.tree.Tree.CheckState;
import ol.Collection;
import ol.layer.Base;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElement;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElementObject;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject;

import java.util.HashMap;
import java.util.List;
// Represents ordered layers management tree.


public class OrderedLayersManagementTree extends LayerManagementPanel {
    private TreeElement<UniqueObject<String>, String> rootLayers;

    /**
     * @param eventBus
     */
    @Inject
    public OrderedLayersManagementTree(EventBus eventBus) {
        super(eventBus);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ol.LayerManagementPanel#getParent(ol.
     * layer.Base)
     */
    @Override
    protected TreeElement getParent(Base layer) {
        return rootLayers;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.tree.TreePanel#loadTreeDataFromRoot(
     * com.google.gwt.core.client.Callback)
     */

    @Override
    protected void loadTreeDataFromRoot(Callback<TreeElement<UniqueObject<String>, String>, Exception> callback) {
        layerNodes = new HashMap<>();
        rootLayers = new DefaultTreeElement<UniqueObject<String>, String>(
                new DefaultTreeElementObject(MESSAGES.appmanagementtabs_layerstab_tree_root()), gisImages.layers());
        Collection<Base> layers = olMapManager.getLayersAsCollection();
        List<Base> layersAsList = OLLayerUtils.sortByLayerIndex(layers, false);
        for (Base layer : layersAsList) {
            if (olMapManager.isLayerVisibleInTree(layer))
                getParent(layer).addChild(getNode(layer));
        }
        callback.onSuccess(rootLayers);

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.tree.TreePanel#getContextMenuForObject
     * (pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement)
     */
    @Override
    protected Menu getContextMenuForObject(TreeElement<UniqueObject<String>, String> selectedElement) {
        Menu menu = super.getContextMenuForObject(selectedElement);

        if (selectedElement.getObject() instanceof OLBaseWrapper) {
            OLBaseWrapper baseWrapper = (OLBaseWrapper) selectedElement.getObject();
//			menu.add(GxtComponentsUtils.createMenuItem(MESSAGES.layer_top(), new SelectionHandler<Item>() {
//
//				@Override
//				public void onSelection(SelectionEvent<Item> event) {
//					if (olMapManager.layerTop(baseWrapper.getLayer())) {
//						changePosition(getParent(baseWrapper.getLayer()), selectedElement, 0);
//					}
//
//				}
//			}, appImages.up()));
            menu.add(GxtComponentsUtils.createMenuItem(MESSAGES.layer_up(), new SelectionHandler<Item>() {

                @Override
                public void onSelection(SelectionEvent<Item> event) {
                    if (olMapManager.layerUp(baseWrapper.getLayer())) {
                        movePosition(getParent(baseWrapper.getLayer()), selectedElement, -1);
                    }
                }
            }, appImages.up()));
            menu.add(GxtComponentsUtils.createMenuItem(MESSAGES.layer_down(), new SelectionHandler<Item>() {

                @Override
                public void onSelection(SelectionEvent<Item> event) {
                    if (olMapManager.layerDown(baseWrapper.getLayer())) {
                        movePosition(getParent(baseWrapper.getLayer()), selectedElement, 1);
                    }

                }
            }, appImages.down()));
//			menu.add(GxtComponentsUtils.createMenuItem(MESSAGES.layer_bottom(), new SelectionHandler<Item>() {
//
//				@Override
//				public void onSelection(SelectionEvent<Item> event) {
//					if (olMapManager.layerBottom(baseWrapper.getLayer())) {
//						movePosition(getParent(baseWrapper.getLayer()), selectedElement,
//								getParent(baseWrapper.getLayer()).getChildren().size() - 1);
//					}
//
//				}
//			}, appImages.down()));

        }
        return menu;
    }

    private void changePosition(TreeElement parent, TreeElement child, int newPosition) {
        int currentIndex = tree.getStore().indexOf(child);
        if (newPosition > -1 && newPosition < tree.getStore().getChildCount(parent)) {
            if (newPosition > currentIndex) {
                newPosition--;
            }
            CheckState checked = tree.getChecked(child);
            tree.getStore().remove(child);
            tree.getStore().insert(parent, newPosition, child);
            tree.setChecked(child, checked);
        }

    }

    private void movePosition(TreeElement parent, TreeElement child, int offset) {
        changePosition(parent, child, tree.getStore().indexOf(child) + offset);
    }
}
