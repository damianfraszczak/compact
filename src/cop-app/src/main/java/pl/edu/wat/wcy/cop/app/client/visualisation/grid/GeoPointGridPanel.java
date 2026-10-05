/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.grid;

import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.filters.Filter;
import pl.edu.wat.wcy.cop.app.client.events.ObjectEvent;
import pl.edu.wat.wcy.cop.app.client.ui.forms.GeoPointForm;
import pl.edu.wat.wcy.cop.app.client.ui.grid.DtoGridsConfigs;
import pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel;
import pl.edu.wat.wcy.cop.app.client.ui.pa.GeoPointPropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.GeoPointDto;
// Renders geo point grid panel UI.

public class GeoPointGridPanel extends GridPanel<GeoPointDto> {

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getColumnModel()
     */
    @Override
    protected ColumnModel<GeoPointDto> getColumnModel() {
        return DtoGridsConfigs.GEO_POINTS_COLUMN_MODEL;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getModelKey()
     */
    @Override
    protected ModelKeyProvider<GeoPointDto> getModelKey() {
        return GeoPointPropertyAccess.key;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getFilters()
     */
    @Override
    protected Filter<GeoPointDto, String>[] getFilters() {
        return new Filter[]{};
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#showFormForObject(java.
     * lang.Object,
     * com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler,
     * boolean)
     */
    @Override
    protected void showFormForObject(GeoPointDto model, SelectHandler selectHandler, boolean editable) {
        new GeoPointForm(model, selectHandler, editable);

    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#createNewObject()
     */
    @Override
    protected GeoPointDto createNewObject() {
        return new GeoPointDto();
    }

    /* (non-Javadoc)
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#shouldProcessEvent(pl.edu.wat.wcy.cop.app.client.events.ObjectEvent)
     */
    @Override
    protected boolean shouldProcessEvent(ObjectEvent event) {
        return false;
    }

}
