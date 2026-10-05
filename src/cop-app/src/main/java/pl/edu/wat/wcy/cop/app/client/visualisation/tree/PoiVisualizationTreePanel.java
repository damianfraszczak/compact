/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.tree;

import com.sencha.gxt.widget.core.client.menu.Menu;
import pl.edu.wat.wcy.cop.app.client.domain.spec.PointOfInterestClientModel;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElement;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElementObject;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement;
import pl.edu.wat.wcy.cop.app.shared.domain.ScenarioPointObjectDto;
import pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject;
// Renders poi visualization tree panel UI.


public class PoiVisualizationTreePanel extends VisualisationTreePanel<PointOfInterestClientModel> {

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.visualisation.VisualisationTreePanel#
     * getNode(java.lang.Object)
     */
    @Override
    protected TreeElement<? extends UniqueObject<String>, String> getNode(PointOfInterestClientModel object) {
        return new PoiTreeElement(object);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.visualisation.VisualisationTreePanel#
     * createRoot()
     */
    @Override
    protected TreeElement<UniqueObject<String>, String> createRoot() {
        return new DefaultTreeElement<UniqueObject<String>, String>(
                new DefaultTreeElementObject(MESSAGES.appmanagementtabs_poitab_tree_root()),
                GIS_IMAGES.layers_operational());
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
        return VisualisationTreePanel.extendMenuForShowOnMapMenuItem(super.getContextMenuForObject(selectedElement),
                (ScenarioPointObjectDto) selectedElement);
    }
}
