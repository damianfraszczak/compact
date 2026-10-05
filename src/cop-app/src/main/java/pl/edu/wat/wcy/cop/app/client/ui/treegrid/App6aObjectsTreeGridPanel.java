/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.treegrid;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import pl.edu.wat.wcy.cop.app.client.domain.spec.App6AMilitaryUnitClientModel;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeGridPanel;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeGridsConfigs;
import pl.edu.wat.wcy.cop.app.client.utils.images.GisImages;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.App6AMilitaryUnitTreeElement;
import pl.edu.wat.wcy.cop.app.shared.domain.App6AMilitaryUnitDto;
import pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject;

import java.util.List;
// Renders app 6 a objects tree grid panel UI.


public class App6aObjectsTreeGridPanel extends TreeGridPanel<UniqueObject<String>, String> {
    private static GisImages GIS_IMAGES = GisImages.INSTANCE;
    private List<App6AMilitaryUnitClientModel> objects;
    private TreeElement<UniqueObject<String>, String> root;

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.treegrid.TreeGridPanel#
     * loadTreeDataFromRoot(com.google.gwt.core.client.Callback)
     */
    @Override
    protected void loadTreeDataFromRoot(Callback<TreeElement<UniqueObject<String>, String>, Exception> callback) {
        root = (TreeElement<UniqueObject<String>, String>) getRoot();
        if (objects != null) {
            for (App6AMilitaryUnitClientModel object : objects) {
                root.addChild((TreeElement<UniqueObject<String>, String>) getNode(object));
            }
        }
        callback.onSuccess(root);

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.treegrid.TreeGridPanel#getTreeColumn()
     */
    @Override
    protected ColumnConfig getTreeColumn() {

        return TreeGridsConfigs.APP6A_OBJECTS_COLUMN_CONFIG.get(0);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.treegrid.TreeGridPanel#getColumnModel(
     * )
     */
    @Override
    protected ColumnModel getColumnModel() {
        return TreeGridsConfigs.APP6A_OBJECTS_COLUMN_MODEL;
    }

    /**
     * @param layer
     * @return
     */
    private TreeElement<? extends UniqueObject<String>, String> getRoot() {
        App6AMilitaryUnitDto dto = new App6AMilitaryUnitDto();
        dto.setNameAdditional(MESSAGES.appmanagementtabs_app6atab_tree_root());
        return new App6AMilitaryUnitTreeElement(new App6AMilitaryUnitClientModel(dto, null, null));
    }

    /**
     * @param layer
     * @return
     */
    private TreeElement<? extends UniqueObject<String>, String> getNode(App6AMilitaryUnitClientModel object) {
        return new App6AMilitaryUnitTreeElement(object);
    }

    /**
     * @param app6aMilitaryUnits
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
}
