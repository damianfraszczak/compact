/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.grid;

import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.filters.Filter;
import ol.Coordinate;
import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.events.ObjectEvent;
import pl.edu.wat.wcy.cop.app.client.ol.GeoUtils;
import pl.edu.wat.wcy.cop.app.client.ol.OLNativeMethods;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm;
import pl.edu.wat.wcy.cop.app.client.ui.forms.CrisisEventForm;
import pl.edu.wat.wcy.cop.app.client.ui.forms.CrisisEventMultiPointZoneForm;
import pl.edu.wat.wcy.cop.app.client.ui.forms.PointOfInterestForm;
import pl.edu.wat.wcy.cop.app.client.ui.grid.DtoGridsConfigs;
import pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel;
import pl.edu.wat.wcy.cop.app.client.ui.pa.CrisisEventMultiPointZonePropertyAccess;
import pl.edu.wat.wcy.cop.app.client.utils.DefaultAsyncCallback;
import pl.edu.wat.wcy.cop.app.client.utils.gui.NotificationUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.CrisisEventMultiPointZoneDto;
import pl.edu.wat.wcy.cop.app.shared.domain.PoiMultiPointZoneDto;
// Renders crisis event multi point zone grid panel UI.


public class CrisisEventMultiPointZoneGridPanel extends GridPanel<CrisisEventMultiPointZoneDto> {

    /**
     *
     */
    public CrisisEventMultiPointZoneGridPanel() {
        super();
        setSendEvents(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getColumnModel()
     */
    @Override
    protected ColumnModel<CrisisEventMultiPointZoneDto> getColumnModel() {
        return DtoGridsConfigs.CRISIS_EVENTS_MULTI_POINT_ZONE_COLUMN_MODEL;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getModelKey()
     */
    @Override
    protected ModelKeyProvider<CrisisEventMultiPointZoneDto> getModelKey() {
        return CrisisEventMultiPointZonePropertyAccess.INSTANCE.key();
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getFilters()
     */
    @Override
    protected Filter<CrisisEventMultiPointZoneDto, String>[] getFilters() {
        return new Filter[]{};
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getFormForObject(java.
     * lang.Object,
     * com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler)
     */
    @Override
    protected void showFormForObject(CrisisEventMultiPointZoneDto model, SelectHandler selectHandler,
                                     boolean editable) {
        new CrisisEventMultiPointZoneForm(model, selectHandler, editable);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#createNewObject()
     */
    @Override
    protected CrisisEventMultiPointZoneDto createNewObject() {
        return new CrisisEventMultiPointZoneDto();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectAdded(java.lang.
     * Object)
     */
    @Override
    protected void objectAdded(CrisisEventMultiPointZoneDto model) {

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectRemoved(java.lang
     * .Object)
     */
    @Override
    protected void objectRemoved(CrisisEventMultiPointZoneDto object) {

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectUpdated(java.lang
     * .Object)
     */
    @Override
    protected void objectUpdated(CrisisEventMultiPointZoneDto object) {

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
        return event.getObject() instanceof CrisisEventMultiPointZoneDto;
    }

    @Override
    protected void onAddButtonSelect() {
        // super.onAddButtonSelect();
        AbstractForm openedWindow = AbstractForm.getFormByType(CrisisEventForm.class);
        if (openedWindow != null) {
            openedWindow.hide();
        }
        CrisisEventMultiPointZoneDto model = createNewObject();
        NotificationUtils.showToast("Drawing mode", "You are adding a new area. Double-click to finish editing.");
        CopGinInjector.INSTANCE.getMapManager().drawFeatrue("Polygon", new DefaultAsyncCallback<Feature>() {
            @Override
            public void onSuccess(Feature feature) {
                Coordinate[][] coords = OLNativeMethods.getFeatureCordsAsArray(feature);
                model.setPoints(GeoUtils.createGeoPointsFromMapCoordinates(coords[0]));
                if (openedWindow != null) {
                    openedWindow.show();
                }
                showFormForObject(model, new SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        processAddFromForm(model);
                    }

                }, true);
            }
        });


    }
}
