/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol;

import com.google.gwt.core.client.Callback;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import ol.Collection;
import ol.layer.Base;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElement;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElementObject;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement;
import pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject;

import java.util.HashMap;
import java.util.List;
// Represents grouping layers management tree.

public class GroupingLayersManagementTree extends LayerManagementPanel {
    private TreeElement<UniqueObject<String>, String> rootLayers;
    private TreeElement<UniqueObject<String>, String> operationalLayers;
    private TreeElement<UniqueObject<String>, String> gisLayers;
    private TreeElement<UniqueObject<String>, String> analiticalLayers;
    private TreeElement<UniqueObject<String>, String> vectorLayers;

    /**
     * @param eventBus
     */
    @Inject
    public GroupingLayersManagementTree(EventBus eventBus) {
        super(eventBus);
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
        gisLayers = new DefaultTreeElement<UniqueObject<String>, String>(
                new DefaultTreeElementObject(MESSAGES.appmanagementtabs_layerstab_tree_gislayersroot()),
                gisImages.layers_geo());
        operationalLayers = new DefaultTreeElement<UniqueObject<String>, String>(
                new DefaultTreeElementObject(MESSAGES.appmanagementtabs_layerstab_tree_operationallayersroot()),
                gisImages.layers_operational());
        analiticalLayers = new DefaultTreeElement<UniqueObject<String>, String>(
                new DefaultTreeElementObject(MESSAGES.appmanagementtabs_layerstab_tree_analiticallayersroot()),
                gisImages.layers_analytical());
        vectorLayers = new DefaultTreeElement<UniqueObject<String>, String>(
                new DefaultTreeElementObject(MESSAGES.layer_vector()), gisImages.layers_vector());
        rootLayers.addChild(gisLayers);
        rootLayers.addChild(analiticalLayers);
        rootLayers.addChild(operationalLayers);
        rootLayers.addChild(vectorLayers);
        Collection<Base> layers = olMapManager.getLayersAsCollection();
        List<Base> layersAsList = OLLayerUtils.sortByLayerIndex(layers, false);
        for (Base layer : layersAsList) {
            if (olMapManager.isLayerVisibleInTree(layer))
                getParent(layer).addChild(getNode(layer));
        }
        callback.onSuccess(rootLayers);

    }

    /**
     * @param layer
     * @return
     */
    @Override
    protected TreeElement getParent(Base layer) {
        switch (olMapManager.getLayerType(layer)) {
            case ANALYTICAL:
                return analiticalLayers;
            case GIS:
                return gisLayers;
            case OPERATIONAL:
                return operationalLayers;
            case VECTOR:
                return vectorLayers;
            default:
                break;
        }
        return rootLayers;
    }
}
