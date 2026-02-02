package pl.edu.wat.wcy.cop.app.client.visualisation.grid;

import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.filters.Filter;
import pl.edu.wat.wcy.cop.app.client.events.ObjectEvent;
import pl.edu.wat.wcy.cop.app.client.ui.forms.SearchAndRescueForm;
import pl.edu.wat.wcy.cop.app.client.ui.grid.DtoGridsConfigs;
import pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel;
import pl.edu.wat.wcy.cop.app.client.ui.pa.SearchAndRescuePropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueDto;
// Renders search and rescue grid panel UI.

public class SearchAndRescueGridPanel extends GridPanel<SearchAndRescueDto> {

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getColumnModel()
     */
    @Override
    protected ColumnModel<SearchAndRescueDto> getColumnModel() {
        return DtoGridsConfigs.SAR_OBJECTS_COLUMN_MODEL;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getModelKey()
     */
    @Override
    protected ModelKeyProvider<SearchAndRescueDto> getModelKey() {
        return SearchAndRescuePropertyAccess.INSTANCE.key();
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
    protected void showFormForObject(SearchAndRescueDto model, SelectEvent.SelectHandler selectHandler, boolean editable) {
        new SearchAndRescueForm(model, selectHandler, editable);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#createNewObject()
     */
    @Override
    protected SearchAndRescueDto createNewObject() {
        return new SearchAndRescueDto();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectAdded(java.lang.
     * Object)
     */
    @Override
    protected void objectAdded(SearchAndRescueDto model) {
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectRemoved(java.lang
     * .Object)
     */
    @Override
    protected void objectRemoved(SearchAndRescueDto object) {
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectUpdated(java.lang
     * .Object)
     */
    @Override
    protected void objectUpdated(SearchAndRescueDto object) {
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getFilters()
     */
    @Override
    protected Filter<SearchAndRescueDto, String>[] getFilters() {
        return DtoGridsConfigs.SAR_OBJECTS_FILTERS;
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
        return event.getObject() instanceof SearchAndRescueDto;
    }

//    @Override
//    protected void onAddButtonSelect() {
//        // super.onAddButtonSelect();
//        SearchAndRescueDto model = createNewObject();
//        NotificationUtils.showToast("Drawing mode", "Dodajesz nową strefę poszukiwania. 2-klikiem zakończysz edycję.");
//        CesarGinInjector.INSTANCE.getMapManager().drawFeatrue("Point", new DefaultAsyncCallback<Feature>() {
//            @Override
//            public void onSuccess(Feature feature) {
//                Coordinate[] coords = GeoUtils.getCoordinatesOfFeature(feature);
//                model.setPoint(GeoUtils.createGeoPointsFromMapCoordinates(coords[0]));
//                showFormForObject(model, new SelectEvent.SelectHandler() {
//                    @Override
//                    public void onSelect(SelectEvent event) {
//                        processAddFromForm(model);
//                    }
//
//                }, true);
//            }
//        });
//
//
//    }
}
