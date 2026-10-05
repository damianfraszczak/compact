package pl.edu.wat.wcy.cop.app.client.visualisation.grid;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.filters.Filter;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import ol.Coordinate;
import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.domain.ImportDataObject;
import pl.edu.wat.wcy.cop.app.client.domain.spec.SearchAndRescueClientModel;
import pl.edu.wat.wcy.cop.app.client.events.ObjectEvent;
import pl.edu.wat.wcy.cop.app.client.ol.GeoUtils;
import pl.edu.wat.wcy.cop.app.client.ui.forms.ImportDataForm;
import pl.edu.wat.wcy.cop.app.client.ui.forms.SearchAndRescueForm;
import pl.edu.wat.wcy.cop.app.client.ui.grid.ClientModelsGridsConfigs;
import pl.edu.wat.wcy.cop.app.client.ui.pa.SearchAndRescueClientModelPropertyAccess;
import pl.edu.wat.wcy.cop.app.client.utils.DefaultAsyncCallback;
import pl.edu.wat.wcy.cop.app.client.utils.gui.DialogUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueDto;

import java.util.LinkedList;
import java.util.List;
// Renders search and rescue client model grid panel UI.

public class SearchAndRescueClientModelGridPanel extends VisualisationGridPanel<SearchAndRescueClientModel> {

    /**
     *
     */
    public SearchAndRescueClientModelGridPanel() {
        super();
        setSendEvents(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getColumnModel()
     */
    @Override
    protected ColumnModel<SearchAndRescueClientModel> getColumnModel() {
        return ClientModelsGridsConfigs.SAR_OBJECTS_COLUMN_MODEL;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getModelKey()
     */
    @Override
    protected ModelKeyProvider<SearchAndRescueClientModel> getModelKey() {
        return SearchAndRescueClientModelPropertyAccess.INSTANCE.key();
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getFilters()
     */
    @Override
    protected Filter<SearchAndRescueClientModel, String>[] getFilters() {
        return ClientModelsGridsConfigs.SAR_OBJECTS_FILTERS;
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
    protected void showFormForObject(SearchAndRescueClientModel model, SelectEvent.SelectHandler selectHandler, boolean editable) {
        new SearchAndRescueForm(model.getObject(), selectHandler);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#createNewObject()
     */
    @Override
    protected SearchAndRescueClientModel createNewObject() {
        return new SearchAndRescueClientModel(new SearchAndRescueDto(), new Feature[0]);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectAdded(java.lang.
     * Object)
     */
    @Override
    protected void objectAdded(SearchAndRescueClientModel object) {
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectUpdated(java.lang
     * .Object)
     */
    @Override
    protected void objectUpdated(SearchAndRescueClientModel object) {
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectRemoved(java.lang
     * .Object)
     */
    @Override
    protected void objectRemoved(SearchAndRescueClientModel object) {
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
        return event.getObject() instanceof SearchAndRescueClientModel;
    }

    @Override
    protected void createToolBar() {
        super.createToolBar();
        toolBar.add(GxtComponentsUtils.createTextButton("CSV Import", new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
                ImportDataObject obj = new ImportDataObject();
                new ImportDataForm(obj, new SelectEvent.SelectHandler() {

                    @Override
                    public void onSelect(SelectEvent event) {
                        try {
                            String content = obj.getContent();
                            String[] lines = content.split("\n");
                            List<SearchAndRescueClientModel> pois = new LinkedList<SearchAndRescueClientModel>();
                            for (String line : lines) {
                                String[] poiData = line.split(";");
                                String name = poiData[0];
                                double lat = Double.parseDouble(poiData[1]);
                                double lon = Double.parseDouble(poiData[2]);
                                String description = poiData[3];
                                String group = null;
                                if (poiData.length > 3) {
                                    group = poiData[4];
                                }
//                                SearchAndRescueDto dto = new SearchAndRescueDto(name, description,
//                                        new GeoPointDto(lat, lon), SearchAndRescueTypeDto.CROSSHAIRS);
//                                dto.setMapGroup(group);
//                                pois.add(new SearchAndRescueClientModel(dto, null, null));
                            }
                            for (SearchAndRescueClientModel poi : pois) {
                                SearchAndRescueClientModelGridPanel.this.processAddFromForm(poi);
                            }
                        } catch (Exception ex) {
                            DialogUtils.showAlertDialog("Blad parsowania", ex.getMessage());
                        }

                    }
                });

            }
        }, null));

    }

    @Override
    protected Menu getContextMenuForObject(SearchAndRescueClientModel object) {
        Menu menu = VisualisationGridPanel.extendMenuForShowOnMapMenuItem(super.getContextMenuForObject(object),
                object.getFeatures());
        return menu;
    }

    @Override
    protected void onAddButtonSelect() {
        // super.onAddButtonSelect();

        SearchAndRescueClientModel model = createNewObject();
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
