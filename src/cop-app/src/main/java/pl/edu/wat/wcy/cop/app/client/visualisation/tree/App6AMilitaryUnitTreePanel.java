/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.tree;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.widget.core.client.menu.Menu;
import pl.edu.wat.wcy.cop.app.client.domain.spec.App6AMilitaryUnitClientModel;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElement;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElementObject;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreePanel;
import pl.edu.wat.wcy.cop.app.client.utils.images.GisImages;
import pl.edu.wat.wcy.cop.app.shared.domain.ScenarioPointObjectDto;
import pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject;

import java.util.List;
// Renders app 6 a military unit tree panel UI.


public class App6AMilitaryUnitTreePanel extends TreePanel<UniqueObject<String>, String> {
    private static GisImages GIS_IMAGES = GisImages.INSTANCE;
    private List<App6AMilitaryUnitClientModel> objects;
    private DefaultTreeElement<UniqueObject<String>, String> root;

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.tree.TreePanel#loadTreeDataFromRoot(
     * com.google.gwt.core.client.Callback)
     */
    @Override
    protected void loadTreeDataFromRoot(Callback<TreeElement<UniqueObject<String>, String>, Exception> callback) {
        root = new DefaultTreeElement<UniqueObject<String>, String>(
                new DefaultTreeElementObject(MESSAGES.appmanagementtabs_app6atab_tree_root()),
                GIS_IMAGES.layers_operational());
        if (objects != null) {
            for (App6AMilitaryUnitClientModel object : objects) {
                root.addChild((TreeElement<UniqueObject<String>, String>) getNode(object));
            }
        }
        callback.onSuccess(root);

    }

    /**
     * @return the objects
     */
    public List<App6AMilitaryUnitClientModel> getObjects() {
        return objects;
    }

    /**
     * @param objects
     *            the objects to set
     */
    public void setObjects(List<App6AMilitaryUnitClientModel> objects) {
        this.objects = objects;
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            @Override
            public void execute() {
                reloadData();
            }
        });
    }

    /**
     * @param layer
     * @return
     */
    private TreeElement<? extends UniqueObject<String>, String> getNode(App6AMilitaryUnitClientModel object) {
        return new App6AMilitaryUnitTreeElement(object);
    }/*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.tree.TreePanel#
     * getContextMenuForObject
     * (pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement)
     */

    @Override
    protected Menu getContextMenuForObject(TreeElement<UniqueObject<String>, String> selectedElement) {
        return VisualisationTreePanel.extendMenuForShowOnMapMenuItem(super.getContextMenuForObject(selectedElement),
                (ScenarioPointObjectDto) selectedElement);
    }
}
