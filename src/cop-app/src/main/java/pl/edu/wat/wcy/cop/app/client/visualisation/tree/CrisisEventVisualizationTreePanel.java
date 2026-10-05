/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.tree;

import pl.edu.wat.wcy.cop.app.client.domain.spec.CrisisEventClientModel;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElement;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElementObject;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement;
import pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject;
// Renders crisis event visualization tree panel UI.


public class CrisisEventVisualizationTreePanel extends VisualisationTreePanel<CrisisEventClientModel> {

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.visualisation.VisualisationTreePanel#
     * getNode(java.lang.Object)
     */
    @Override
    protected TreeElement<? extends UniqueObject<String>, String> getNode(CrisisEventClientModel object) {
        return new CrisisEventTreeElement(object);
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
                new DefaultTreeElementObject(MESSAGES.appmanagementtabs_events_tree_root()),
                GIS_IMAGES.layers_operational());
    }

}
