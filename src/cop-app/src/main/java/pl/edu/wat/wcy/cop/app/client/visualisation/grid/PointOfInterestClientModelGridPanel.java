/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.grid;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.filters.Filter;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import ol.Coordinate;
import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.domain.ImportDataObject;
import pl.edu.wat.wcy.cop.app.client.domain.spec.PointOfInterestClientModel;
import pl.edu.wat.wcy.cop.app.client.events.ObjectEvent;
import pl.edu.wat.wcy.cop.app.client.ol.GeoUtils;
import pl.edu.wat.wcy.cop.app.client.ui.forms.ImportDataForm;
import pl.edu.wat.wcy.cop.app.client.ui.forms.PointOfInterestForm;
import pl.edu.wat.wcy.cop.app.client.ui.grid.ClientModelsGridsConfigs;
import pl.edu.wat.wcy.cop.app.client.ui.pa.PointOfInterestClientModelPropertyAccess;
import pl.edu.wat.wcy.cop.app.client.utils.DefaultAsyncCallback;
import pl.edu.wat.wcy.cop.app.client.utils.gui.DialogUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.NotificationUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.GeoPointDto;
import pl.edu.wat.wcy.cop.app.shared.domain.PointOfInterestDto;
import pl.edu.wat.wcy.cop.app.shared.domain.PointOfInterestTypeDto;

import java.util.LinkedList;
import java.util.List;
// Renders point of interest client model grid panel UI.


public class PointOfInterestClientModelGridPanel extends VisualisationGridPanel<PointOfInterestClientModel> {

    /**
     *
     */
    public PointOfInterestClientModelGridPanel() {
        super();
        setSendEvents(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getColumnModel()
     */
    @Override
    protected ColumnModel<PointOfInterestClientModel> getColumnModel() {
        return ClientModelsGridsConfigs.POIS_COLUMN_MODEL;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getModelKey()
     */
    @Override
    protected ModelKeyProvider<PointOfInterestClientModel> getModelKey() {
        return PointOfInterestClientModelPropertyAccess.INSTANCE.key();
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getFilters()
     */
    @Override
    protected Filter<PointOfInterestClientModel, String>[] getFilters() {
        return ClientModelsGridsConfigs.POIS_FILTERS;
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
    protected void showFormForObject(PointOfInterestClientModel model, SelectHandler selectHandler, boolean editable) {
        new PointOfInterestForm(model.getObject(), selectHandler);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#createNewObject()
     */
    @Override
    protected PointOfInterestClientModel createNewObject() {
        return new PointOfInterestClientModel(new PointOfInterestDto(), null, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectAdded(java.lang.
     * Object)
     */
    @Override
    protected void objectAdded(PointOfInterestClientModel object) {
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectUpdated(java.lang
     * .Object)
     */
    @Override
    protected void objectUpdated(PointOfInterestClientModel object) {
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectRemoved(java.lang
     * .Object)
     */
    @Override
    protected void objectRemoved(PointOfInterestClientModel object) {
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
        return event.getObject() instanceof PointOfInterestClientModel;
    }

    @Override
    protected void createToolBar() {
        super.createToolBar();
        toolBar.add(GxtComponentsUtils.createTextButton("CSV Import", new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
                ImportDataObject obj = new ImportDataObject();
                new ImportDataForm(obj, new SelectHandler() {

                    @Override
                    public void onSelect(SelectEvent event) {
                        try {
                            String content = obj.getContent();
                            String[] lines = content.split("\n");
                            List<PointOfInterestClientModel> pois = new LinkedList<PointOfInterestClientModel>();
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
                                PointOfInterestDto dto = new PointOfInterestDto(name, description,
                                        new GeoPointDto(lat, lon), PointOfInterestTypeDto.CROSSHAIRS);
                                dto.setMapGroup(group);
                                pois.add(new PointOfInterestClientModel(dto, null, null));
                            }
                            for (PointOfInterestClientModel poi : pois) {
                                PointOfInterestClientModelGridPanel.this.processAddFromForm(poi);
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
    protected Menu getContextMenuForObject(PointOfInterestClientModel object) {
        Menu menu = VisualisationGridPanel.extendMenuForShowOnMapMenuItem(super.getContextMenuForObject(object),
                object.getFeatures());
        return menu;
    }
    @Override
    protected void onAddButtonSelect() {
        // super.onAddButtonSelect();

        PointOfInterestClientModel model = createNewObject();
        NotificationUtils.showToast("Drawing mode", "Dodajesz nowy POI. 2-klikiem zakończysz edycję.");
        CopGinInjector.INSTANCE.getMapManager().drawFeatrue(
                "Point", new DefaultAsyncCallback<Feature>() {
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
