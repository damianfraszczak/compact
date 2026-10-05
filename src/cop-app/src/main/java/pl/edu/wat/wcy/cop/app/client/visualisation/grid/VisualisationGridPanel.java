/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.grid;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ol.OLMapUtils;
import pl.edu.wat.wcy.cop.app.client.ol.OLNativeMethods;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.images.GisImages;

import java.util.List;
// Renders visualisation grid panel UI.


public abstract class VisualisationGridPanel<T> extends GridPanel<T> {

    protected static GisImages GIS_IMAGES = GisImages.INSTANCE;
    protected static Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();

    private List<T> objects;

    public static MenuItem createShowOnMapMenuItem() {
        return GxtComponentsUtils.createMenuItem(CopGinInjector.INSTANCE.getMessages().scenarioobject_showonmap(),
                new SelectionHandler<Item>() {

                    @Override
                    public void onSelection(SelectionEvent<Item> event) {

                    }
                }, GisImages.INSTANCE.center());
    }

    /**
     * @param originalMenu
     * @param object
     * @return
     */
    public static Menu extendMenuForShowOnMapMenuItem(Menu originalMenu, Feature... features) {
        originalMenu.add(GxtComponentsUtils.createMenuItem(
                CopGinInjector.INSTANCE.getMessages().scenarioobject_showonmap(), new SelectionHandler<Item>() {

                    @Override
                    public void onSelection(SelectionEvent<Item> event) {
                        // OLMapUtils.centerMap(object.getPoint().getLat(),
                        // object.getPoint().getLon());
                        OLNativeMethods.fitToFeatureOnMap(OLMapUtils.getMap(), features);
                    }
                }, GisImages.INSTANCE.center()));
        return originalMenu;
    }

    /**
     * @return the objects
     */
    public List<T> getObjects() {
        return objects;
    }

    /**
     *
     * @param objects
     */
    public void setObjects(List<T> objects) {
        this.objects = objects;
        getStore().getStore().clear();
        getStore().getStore().addAll(objects);
    }

}
