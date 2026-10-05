/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.grid;

import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.filters.Filter;
import com.sencha.gxt.widget.core.client.info.InfoConfig;
import pl.edu.wat.wcy.cop.app.client.events.ObjectEvent;
import pl.edu.wat.wcy.cop.app.client.ui.forms.ADatP3ReportForm;
import pl.edu.wat.wcy.cop.app.client.ui.grid.DtoGridsConfigs;
import pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel;
import pl.edu.wat.wcy.cop.app.client.ui.pa.ADatP3ReportPropertyAccess;
import pl.edu.wat.wcy.cop.app.client.utils.gui.NotificationUtils;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.ADatP3;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.ADatP3Parser;
import pl.edu.wat.wcy.cop.app.shared.domain.ADatP3ReportDto;
// Displays a dat 3 reports grid data.


public class ADatP3ReportsGrid extends GridPanel<ADatP3ReportDto> {

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getColumnModel()
     */
    @Override
    protected ColumnModel<ADatP3ReportDto> getColumnModel() {
        return DtoGridsConfigs.ADATP3_REPORTS_COLUMN_MODEL;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getModelKey()
     */
    @Override
    protected ModelKeyProvider<ADatP3ReportDto> getModelKey() {
        return ADatP3ReportPropertyAccess.INSTANCE.key();
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
    protected void showFormForObject(ADatP3ReportDto model, SelectHandler selectHandler, boolean editable) {
        new ADatP3ReportForm(model, selectHandler, editable);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#createNewObject()
     */
    @Override
    protected ADatP3ReportDto createNewObject() {
        return new ADatP3ReportDto();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectAdded(java.lang.
     * Object)
     */
    @Override
    protected void objectAdded(ADatP3ReportDto model) {
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
    protected void objectRemoved(ADatP3ReportDto object) {
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
    protected void objectUpdated(ADatP3ReportDto object) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getFilters()
     */
    @Override
    protected Filter<ADatP3ReportDto, String>[] getFilters() {
        return DtoGridsConfigs.ADATP3_REPORT_FILTERS;
    }

    public void addObject(ADatP3ReportDto object) {
        getStore().getStore().add(object);
    }

    @Override
    protected boolean isValid(ADatP3ReportDto model) {
        ADatP3Parser aDatP3Parser = new ADatP3Parser();
        try {
            ADatP3 aDatP3 = aDatP3Parser.parseADatP3Content(model.getContent());
            model.setCbrnType(aDatP3.getCbrnType());
            model.setLat(String.valueOf(aDatP3.getMapCenter().getY()));
            model.setLon(String.valueOf(aDatP3.getMapCenter().getX()));
            model.setDateOfIncydent(aDatP3.getDelta());
            return true;
        } catch (Exception e) {
            NotificationUtils.showToast("Error", "Can not parse AdatP-3 report. Report was not added",
                    InfoConfig.InfoPosition.TOP_RIGHT, 5000);
            return false;
        }
    }/*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#shouldProcessEvent(
     * pl.edu.wat.wcy.cop.app.client.events.ObjectEvent)
     */

    @Override
    protected boolean shouldProcessEvent(ObjectEvent event) {
        return event.getObject() instanceof ADatP3ReportDto;
    }
}
