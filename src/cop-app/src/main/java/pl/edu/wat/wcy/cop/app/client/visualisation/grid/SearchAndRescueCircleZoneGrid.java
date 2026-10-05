package pl.edu.wat.wcy.cop.app.client.visualisation.grid;

import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.filters.Filter;
import ol.Coordinate;
import ol.Feature;
import ol.geom.Circle;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.events.ObjectEvent;
import pl.edu.wat.wcy.cop.app.client.ol.GeoUtils;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm;
import pl.edu.wat.wcy.cop.app.client.ui.forms.SearchAndRescueCircleZoneForm;
import pl.edu.wat.wcy.cop.app.client.ui.forms.SearchAndRescueForm;
import pl.edu.wat.wcy.cop.app.client.ui.grid.DtoGridsConfigs;
import pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel;
import pl.edu.wat.wcy.cop.app.client.ui.pa.SearchAndRescueCircleZonePropertyAccess;
import pl.edu.wat.wcy.cop.app.client.utils.DefaultAsyncCallback;
import pl.edu.wat.wcy.cop.app.client.utils.gui.NotificationUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueCircleZoneDto;
// Displays search and rescue circle zone grid data.

public class SearchAndRescueCircleZoneGrid extends GridPanel<SearchAndRescueCircleZoneDto> {

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getColumnModel()
     */
    @Override
    protected ColumnModel<SearchAndRescueCircleZoneDto> getColumnModel() {
        return DtoGridsConfigs.SAR_CIRCLE_ZONE_COLUMN_MODEL;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getModelKey()
     */
    @Override
    protected ModelKeyProvider<SearchAndRescueCircleZoneDto> getModelKey() {
        return SearchAndRescueCircleZonePropertyAccess.INSTANCE.key();
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getFilters()
     */
    @Override
    protected Filter<SearchAndRescueCircleZoneDto, String>[] getFilters() {
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
    protected void showFormForObject(SearchAndRescueCircleZoneDto model, SelectEvent.SelectHandler selectHandler, boolean editable) {
        new SearchAndRescueCircleZoneForm(model, selectHandler, editable);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#createNewObject()
     */
    @Override
    protected SearchAndRescueCircleZoneDto createNewObject() {
        return new SearchAndRescueCircleZoneDto();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectAdded(java.lang.
     * Object)
     */
    @Override
    protected void objectAdded(SearchAndRescueCircleZoneDto model) {
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
    protected void objectRemoved(SearchAndRescueCircleZoneDto object) {
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
    protected void objectUpdated(SearchAndRescueCircleZoneDto object) {
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
        return event.getObject() instanceof SearchAndRescueCircleZoneDto;
    }

    @Override
    protected void onAddButtonSelect() {
        // super.onAddButtonSelect();
        AbstractForm openedWindow = AbstractForm.getFormByType(SearchAndRescueForm.class);
        if (openedWindow != null) {
            openedWindow.hide();
        }
        SearchAndRescueCircleZoneDto model = createNewObject();
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
