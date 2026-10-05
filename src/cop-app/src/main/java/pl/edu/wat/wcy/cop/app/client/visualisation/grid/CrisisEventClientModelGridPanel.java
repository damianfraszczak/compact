/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.grid;

import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.filters.Filter;
import com.sencha.gxt.widget.core.client.menu.Menu;
import pl.edu.wat.wcy.cop.app.client.domain.spec.CrisisEventClientModel;
import pl.edu.wat.wcy.cop.app.client.events.ObjectEvent;
import pl.edu.wat.wcy.cop.app.client.ui.forms.CrisisEventForm;
import pl.edu.wat.wcy.cop.app.client.ui.grid.ClientModelsGridsConfigs;
import pl.edu.wat.wcy.cop.app.client.ui.pa.CrisisEventClientModelPropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.CrisisEventDto;
// Renders crisis event client model grid panel UI.


public class CrisisEventClientModelGridPanel extends VisualisationGridPanel<CrisisEventClientModel> {

    /**
     *
     */
    public CrisisEventClientModelGridPanel() {
        super();
        setSendEvents(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getColumnModel()
     */
    @Override
    protected ColumnModel<CrisisEventClientModel> getColumnModel() {
        return ClientModelsGridsConfigs.CRISIS_EVENTS_COLUMN_MODEL;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getModelKey()
     */
    @Override
    protected ModelKeyProvider<CrisisEventClientModel> getModelKey() {
        return CrisisEventClientModelPropertyAccess.INSTANCE.key();
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getFilters()
     */
    @Override
    protected Filter<CrisisEventClientModel, String>[] getFilters() {
        return ClientModelsGridsConfigs.CRISIS_EVENTS_FILTERS;
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
    protected void showFormForObject(CrisisEventClientModel model, SelectHandler selectHandler, boolean editable) {
        new CrisisEventForm(model.getObject(), selectHandler);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#createNewObject()
     */
    @Override
    protected CrisisEventClientModel createNewObject() {
        return new CrisisEventClientModel(new CrisisEventDto(), null, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectAdded(java.lang.
     * Object)
     */
    @Override
    protected void objectAdded(CrisisEventClientModel object) {
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectUpdated(java.lang
     * .Object)
     */
    @Override
    protected void objectUpdated(CrisisEventClientModel object) {
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectRemoved(java.lang
     * .Object)
     */
    @Override
    protected void objectRemoved(CrisisEventClientModel object) {

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
        return event.getObject() instanceof CrisisEventClientModel;
    }/*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#
     * getContextMenuForObject (java.lang.Object)
     */

    @Override
    protected Menu getContextMenuForObject(CrisisEventClientModel object) {
        return VisualisationGridPanel.extendMenuForShowOnMapMenuItem(super.getContextMenuForObject(object),
                object.getFeatures());
    }
}
