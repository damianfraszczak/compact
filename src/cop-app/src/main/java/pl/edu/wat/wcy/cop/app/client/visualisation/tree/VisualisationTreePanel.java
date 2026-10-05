/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.tree;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ol.OLMapUtils;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreePanel;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.images.GisImages;
import pl.edu.wat.wcy.cop.app.shared.domain.ScenarioPointObjectDto;
import pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject;

import java.util.List;
// Renders visualisation tree panel UI.


public abstract class VisualisationTreePanel<T> extends TreePanel<UniqueObject<String>, String> {

    protected static GisImages GIS_IMAGES = GisImages.INSTANCE;
    protected static Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    private TreeElement<UniqueObject<String>, String> root;
    private List<T> objects;

    /**
     * @param originalMenu
     * @param object
     * @return
     */
    public static Menu extendMenuForShowOnMapMenuItem(Menu originalMenu, ScenarioPointObjectDto object) {
        originalMenu.add(GxtComponentsUtils.createMenuItem(
                CopGinInjector.INSTANCE.getMessages().scenarioobject_showonmap(), new SelectionHandler<Item>() {

                    @Override
                    public void onSelection(SelectionEvent<Item> event) {
                        OLMapUtils.centerMap(object.getPoint().getLat(), object.getPoint().getLon());
                    }
                }, GisImages.INSTANCE.center()));
        return originalMenu;
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
        root = createRoot();
        if (objects != null) {
            for (T object : objects) {
                root.addChild((TreeElement<UniqueObject<String>, String>) getNode(object));
            }
        }
        callback.onSuccess(root);

    }

    /**
     * @param object
     * @return
     */
    protected abstract TreeElement<? extends UniqueObject<String>, String> getNode(T object);

    /**
     * @return
     */
    protected abstract TreeElement<UniqueObject<String>, String> createRoot();

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {

            @Override
            public void execute() {
                reloadData();

            }
        });
    }
}
