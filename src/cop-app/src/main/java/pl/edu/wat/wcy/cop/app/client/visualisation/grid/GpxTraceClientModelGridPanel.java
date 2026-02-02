package pl.edu.wat.wcy.cop.app.client.visualisation.grid;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.filters.Filter;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.domain.ImportDataObject;
import pl.edu.wat.wcy.cop.app.client.domain.spec.GpxTraceClientModel;
import pl.edu.wat.wcy.cop.app.client.events.ObjectEvent;
import pl.edu.wat.wcy.cop.app.client.ui.forms.GpxTraceForm;
import pl.edu.wat.wcy.cop.app.client.ui.forms.ImportDataForm;
import pl.edu.wat.wcy.cop.app.client.ui.grid.ClientModelsGridsConfigs;
import pl.edu.wat.wcy.cop.app.client.ui.pa.GpxTraceClientModelPropertyAccess;
import pl.edu.wat.wcy.cop.app.client.utils.gui.DialogUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.GpxTraceDto;

import java.util.LinkedList;
import java.util.List;
// Renders gpx trace client model grid panel UI.

public class GpxTraceClientModelGridPanel extends VisualisationGridPanel<GpxTraceClientModel> {

    /**
     *
     */
    public GpxTraceClientModelGridPanel() {
        super();
        setSendEvents(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getColumnModel()
     */
    @Override
    protected ColumnModel<GpxTraceClientModel> getColumnModel() {
        return ClientModelsGridsConfigs.GPX_OBJECTS_COLUMN_MODEL;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getModelKey()
     */
    @Override
    protected ModelKeyProvider<GpxTraceClientModel> getModelKey() {
        return GpxTraceClientModelPropertyAccess.INSTANCE.key();
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getFilters()
     */
    @Override
    protected Filter<GpxTraceClientModel, String>[] getFilters() {
        return ClientModelsGridsConfigs.GPX_OBJECTS_FILTERS;
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
    protected void showFormForObject(GpxTraceClientModel model, SelectEvent.SelectHandler selectHandler, boolean editable) {
        new GpxTraceForm(model.getObject(), selectHandler);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#createNewObject()
     */
    @Override
    protected GpxTraceClientModel createNewObject() {
        return new GpxTraceClientModel(new GpxTraceDto(), new Feature[0]);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectAdded(java.lang.
     * Object)
     */
    @Override
    protected void objectAdded(GpxTraceClientModel object) {
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectUpdated(java.lang
     * .Object)
     */
    @Override
    protected void objectUpdated(GpxTraceClientModel object) {
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectRemoved(java.lang
     * .Object)
     */
    @Override
    protected void objectRemoved(GpxTraceClientModel object) {
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
        return event.getObject() instanceof GpxTraceClientModel;
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
                            List<GpxTraceClientModel> pois = new LinkedList<GpxTraceClientModel>();
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
//                                GpxTraceDto dto = new GpxTraceDto(name, description,
//                                        new GeoPointDto(lat, lon), GpxTraceTypeDto.CROSSHAIRS);
//                                dto.setMapGroup(group);
//                                pois.add(new GpxTraceClientModel(dto, null, null));
                            }
                            for (GpxTraceClientModel poi : pois) {
                                GpxTraceClientModelGridPanel.this.processAddFromForm(poi);
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
    protected Menu getContextMenuForObject(GpxTraceClientModel object) {
        Menu menu = VisualisationGridPanel.extendMenuForShowOnMapMenuItem(super.getContextMenuForObject(object),
                object.getFeatures());
        return menu;
    }

}
