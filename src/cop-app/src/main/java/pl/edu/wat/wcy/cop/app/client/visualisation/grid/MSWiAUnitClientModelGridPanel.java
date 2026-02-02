/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.grid;

import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.filters.Filter;
import com.sencha.gxt.widget.core.client.menu.Menu;
import ol.Coordinate;
import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.domain.spec.MSWiAUnitClientModel;
import pl.edu.wat.wcy.cop.app.client.events.ObjectEvent;
import pl.edu.wat.wcy.cop.app.client.ol.GeoUtils;
import pl.edu.wat.wcy.cop.app.client.ui.forms.MSWiAUnitForm;
import pl.edu.wat.wcy.cop.app.client.ui.grid.ClientModelsGridsConfigs;
import pl.edu.wat.wcy.cop.app.client.ui.pa.MSWiAObjectClientModelPropertyAccess;
import pl.edu.wat.wcy.cop.app.client.utils.DefaultAsyncCallback;
import pl.edu.wat.wcy.cop.app.client.utils.gui.NotificationUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.MSWiAUnitDto;
// Renders ms wi a unit client model grid panel UI.


public class MSWiAUnitClientModelGridPanel extends VisualisationGridPanel<MSWiAUnitClientModel> {

    /**
     *
     */
    public MSWiAUnitClientModelGridPanel() {
        super();
        setSendEvents(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getColumnModel()
     */
    @Override
    protected ColumnModel<MSWiAUnitClientModel> getColumnModel() {
        return ClientModelsGridsConfigs.MSWIA_OBJECTS_COLUMN_MODEL;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getModelKey()
     */
    @Override
    protected ModelKeyProvider<MSWiAUnitClientModel> getModelKey() {
        return MSWiAObjectClientModelPropertyAccess.INSTANCE.key();
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getFilters()
     */
    @Override
    protected Filter<MSWiAUnitClientModel, String>[] getFilters() {
        return ClientModelsGridsConfigs.MSWIA_OBJECTS_FILTERS;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getFormForObject(java.
     * lang.Object,
     * com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler,
     * boolean)
     */
    @Override
    protected void showFormForObject(MSWiAUnitClientModel model, SelectHandler selectHandler, boolean editable) {
        new MSWiAUnitForm(model.getObject(), selectHandler);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#createNewObject()
     */
    @Override
    protected MSWiAUnitClientModel createNewObject() {
        return new MSWiAUnitClientModel(new MSWiAUnitDto(), null, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectAdded(java.lang.
     * Object)
     */
    @Override
    protected void objectAdded(MSWiAUnitClientModel object) {

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectUpdated(java.lang
     * .Object)
     */
    @Override
    protected void objectUpdated(MSWiAUnitClientModel object) {

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectRemoved(java.lang
     * .Object)
     */
    @Override
    protected void objectRemoved(MSWiAUnitClientModel object) {

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#shouldProcessEvent(pl.
     * edu.wat.wcy.cop.app.client.events.ObjectEvent)
     */
    @Override
    protected boolean shouldProcessEvent(ObjectEvent event) {
        return event.getObject() instanceof MSWiAUnitClientModel;
    }

    @Override
    protected Menu getContextMenuForObject(MSWiAUnitClientModel object) {
        return VisualisationGridPanel.extendMenuForShowOnMapMenuItem(super.getContextMenuForObject(object),
                object.getFeatures());
    }

    @Override
    protected void onAddButtonSelect() {
        // super.onAddButtonSelect();

        MSWiAUnitClientModel model = createNewObject();
        NotificationUtils.showToast("Drawing mode", "Dodajesz nowy MSWIA. 2-klikiem zakończysz edycję.");
        CopGinInjector.INSTANCE.getMapManager().drawFeatrue("Point", new DefaultAsyncCallback<Feature>() {
            @Override
            public void onSuccess(Feature feature) {
                Coordinate[] coords = GeoUtils.getCoordinatesOfFeature(feature);
                model.getObject().setPoint(GeoUtils.createGeoPointsFromMapCoordinates(coords[0]));
                showFormForObject(model, new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        processAddFromForm(model);
                    }

                }, true);
            }
        });


    }
}
