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
import pl.edu.wat.wcy.cop.app.client.mvp.CesarActivityMapper;
import pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationActivity;
import pl.edu.wat.wcy.cop.app.client.ol.GeoUtils;
import pl.edu.wat.wcy.cop.app.client.ol.OLNativeMethods;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm;
import pl.edu.wat.wcy.cop.app.client.ui.forms.SearchAndRescueForm;
import pl.edu.wat.wcy.cop.app.client.ui.forms.SearchAndRescueMultiPointZoneForm;
import pl.edu.wat.wcy.cop.app.client.ui.grid.DtoGridsConfigs;
import pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel;
import pl.edu.wat.wcy.cop.app.client.ui.pa.SearchAndRescueMultiPointZonePropertyAccess;
import pl.edu.wat.wcy.cop.app.client.utils.DefaultAsyncCallback;
import pl.edu.wat.wcy.cop.app.client.utils.gui.NotificationUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueSegmentDto;
// Displays search and rescue segment grid data.

public class SearchAndRescueSegmentGrid extends GridPanel<SearchAndRescueSegmentDto> {

    private SearchAndRescueDto sarModel;
    private SearchAndRescueForm searchAndRescueForm;

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getColumnModel()
     */
    @Override
    protected ColumnModel<SearchAndRescueSegmentDto> getColumnModel() {
        return DtoGridsConfigs.SAR_MULTI_POINT_ZONE_COLUMN_MODEL;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getModelKey()
     */
    @Override
    protected ModelKeyProvider<SearchAndRescueSegmentDto> getModelKey() {
        return SearchAndRescueMultiPointZonePropertyAccess.INSTANCE.key();
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getFilters()
     */
    @Override
    protected Filter<SearchAndRescueSegmentDto, String>[] getFilters() {
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
    protected void showFormForObject(SearchAndRescueSegmentDto model, SelectHandler selectHandler, boolean editable) {
        new SearchAndRescueMultiPointZoneForm(model, selectHandler, editable);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#createNewObject()
     */
    @Override
    protected SearchAndRescueSegmentDto createNewObject() {
        SearchAndRescueSegmentDto obj = new SearchAndRescueSegmentDto();
        int number = 1 + getStore().getStore().size();
        obj.setName("Sector #" + number);
        return obj;
    }

    @Override
    protected void processAdd(SearchAndRescueSegmentDto object) {
        super.processAdd(object);
        drawOnMap(object);
    }


    @Override
    protected void processUpdate(SearchAndRescueSegmentDto object) {
        super.processUpdate(object);
        drawOnMap(object);
    }

    @Override
    protected void processRemove(SearchAndRescueSegmentDto object) {
        super.processRemove(object);
        drawOnMap(object);
    }

    private void drawOnMap(SearchAndRescueSegmentDto object) {
        CesarActivityMapper cam = (CesarActivityMapper) CopGinInjector.INSTANCE.activityMapper();
        if(cam.getCurrentActivity() instanceof VisualizationActivity){
            searchAndRescueForm.saveChanges();
            VisualizationActivity va = (VisualizationActivity) cam.getCurrentActivity();
            va.processUpdate(this.sarModel, new DefaultAsyncCallback<Object>() {


                @Override
                public void onSuccess(Object searchAndRescueDto) {
                    searchAndRescueForm.initModel((SearchAndRescueDto)searchAndRescueDto);
                }
            });
        }

    }


    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectAdded(java.lang.
     * Object)
     */
    @Override
    protected void objectAdded(SearchAndRescueSegmentDto model) {
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
    protected void objectRemoved(SearchAndRescueSegmentDto object) {
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
    protected void objectUpdated(SearchAndRescueSegmentDto object) {
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
        return event.getObject() instanceof SearchAndRescueSegmentDto;
    }

    @Override
    protected void onAddButtonSelect() {
        // super.onAddButtonSelect();
        AbstractForm openedWindow = AbstractForm.getFormByType(SearchAndRescueForm.class);
        if (openedWindow != null) {
            openedWindow.hide();
        }
        SearchAndRescueSegmentDto model = createNewObject();
        NotificationUtils.showToast("Drawing mode", "Dodajesz nowy sektor. 2-klikiem zakończysz edycję.");
        CopGinInjector.INSTANCE.getMapManager().drawFeatrue("Polygon", new DefaultAsyncCallback<Feature>() {
            @Override
            public void onSuccess(Feature feature) {
                Coordinate[][] coords = OLNativeMethods.getFeatureCordsAsArray(feature);
                model.setPoints(GeoUtils.createGeoPointsFromMapCoordinates(coords[0]));
                model.setArea(GeoUtils.getArea(feature));
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

    public void setSARObject(SearchAndRescueDto model) {
        this.sarModel = model;
    }

    public void setForm(SearchAndRescueForm searchAndRescueForm) {
        this.searchAndRescueForm = searchAndRescueForm;
    }
}
