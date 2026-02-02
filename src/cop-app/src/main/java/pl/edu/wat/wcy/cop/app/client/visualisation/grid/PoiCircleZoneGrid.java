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
import ol.geom.Circle;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.events.ObjectEvent;
import pl.edu.wat.wcy.cop.app.client.ol.GeoUtils;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm;
import pl.edu.wat.wcy.cop.app.client.ui.forms.PoiCircleZoneForm;
import pl.edu.wat.wcy.cop.app.client.ui.forms.PointOfInterestForm;
import pl.edu.wat.wcy.cop.app.client.ui.grid.DtoGridsConfigs;
import pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel;
import pl.edu.wat.wcy.cop.app.client.ui.pa.PoiCircleZonePropertyAccess;
import pl.edu.wat.wcy.cop.app.client.utils.DefaultAsyncCallback;
import pl.edu.wat.wcy.cop.app.client.utils.gui.NotificationUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.PoiCircleZoneDto;
// Displays poi circle zone grid data.

public class PoiCircleZoneGrid extends GridPanel<PoiCircleZoneDto> {

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getColumnModel()
     */
    @Override
    protected ColumnModel<PoiCircleZoneDto> getColumnModel() {
        return DtoGridsConfigs.POIS_CIRCLE_ZONE_COLUMN_MODEL;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getModelKey()
     */
    @Override
    protected ModelKeyProvider<PoiCircleZoneDto> getModelKey() {
        return PoiCircleZonePropertyAccess.INSTANCE.key();
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getFilters()
     */
    @Override
    protected Filter<PoiCircleZoneDto, String>[] getFilters() {
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
    protected void showFormForObject(PoiCircleZoneDto model, SelectHandler selectHandler, boolean editable) {
        new PoiCircleZoneForm(model, selectHandler, editable);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#createNewObject()
     */
    @Override
    protected PoiCircleZoneDto createNewObject() {
        return new PoiCircleZoneDto();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectAdded(java.lang.
     * Object)
     */
    @Override
    protected void objectAdded(PoiCircleZoneDto model) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectRemoved(java.lang
     * .Object)
     */
    @Override
    protected void objectRemoved(PoiCircleZoneDto object) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectUpdated(java.lang
     * .Object)
     */
    @Override
    protected void objectUpdated(PoiCircleZoneDto object) {
        // TODO Auto-generated method stub

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
        return event.getObject() instanceof PoiCircleZoneDto;
    }
    @Override
    protected void onAddButtonSelect() {
        // super.onAddButtonSelect();
        AbstractForm openedWindow = AbstractForm.getFormByType(PointOfInterestForm.class);
        if (openedWindow != null) {
            openedWindow.hide();
        }
        PoiCircleZoneDto model = createNewObject();
        NotificationUtils.showToast("Drawing mode", "Dodajesz nowy obszar kołowy. 2-klikiem zakończysz edycję.");
        CopGinInjector.INSTANCE.getMapManager().drawFeatrue("Circle", new DefaultAsyncCallback<Feature>() {
            @Override
            public void onSuccess(Feature feature) {
                Coordinate[] coords = GeoUtils.getCoordinatesOfFeature(feature);
                model.setPoint(GeoUtils.createGeoPointsFromMapCoordinates(coords[0]));
                model.setRadius(GeoUtils.getRadiusInMeters((Circle) feature.getGeometry()));
                if (openedWindow != null) {
                    openedWindow.show();
                }
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
