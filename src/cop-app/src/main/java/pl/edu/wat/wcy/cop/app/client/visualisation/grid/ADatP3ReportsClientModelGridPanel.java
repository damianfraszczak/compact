/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.grid;

import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.filters.Filter;
import com.sencha.gxt.widget.core.client.info.InfoConfig;
import com.sencha.gxt.widget.core.client.menu.Menu;
import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.domain.spec.ADatP3ReportClientModel;
import pl.edu.wat.wcy.cop.app.client.events.ObjectEvent;
import pl.edu.wat.wcy.cop.app.client.ol.OLStyleManager;
import pl.edu.wat.wcy.cop.app.client.ol.forms.OLStyleForm;
import pl.edu.wat.wcy.cop.app.client.ui.forms.ADatP3CoordinatesForm;
import pl.edu.wat.wcy.cop.app.client.ui.forms.ADatP3ReportForm;
import pl.edu.wat.wcy.cop.app.client.ui.grid.ClientModelsGridsConfigs;
import pl.edu.wat.wcy.cop.app.client.ui.pa.ADatP3ReportClientModelPropertyAccess;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.NotificationUtils;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.ADatP3;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.ADatP3Parser;
import pl.edu.wat.wcy.cop.app.shared.domain.ADatP3CoordinatesDto;
import pl.edu.wat.wcy.cop.app.shared.domain.ADatP3ReportDto;
import pl.edu.wat.wcy.cop.app.shared.ol.OLStyleOptions;

import java.util.logging.Logger;
// Renders a dat 3 reports client model grid panel UI.


public class ADatP3ReportsClientModelGridPanel extends VisualisationGridPanel<ADatP3ReportClientModel> {

    /**
     *
     */
    public ADatP3ReportsClientModelGridPanel() {
        super();
        setSendEvents(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getColumnModel()
     */
    @Override
    protected ColumnModel<ADatP3ReportClientModel> getColumnModel() {
        return ClientModelsGridsConfigs.ADATP3_REPORTS_COLUMN_MODEL;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getModelKey()
     */
    @Override
    protected ModelKeyProvider<ADatP3ReportClientModel> getModelKey() {
        return ADatP3ReportClientModelPropertyAccess.INSTANCE.key();
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
    protected void showFormForObject(ADatP3ReportClientModel model, SelectHandler selectHandler, boolean editable) {
        new ADatP3ReportForm(model.getObject(), selectHandler, editable);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#createNewObject()
     */
    @Override
    protected ADatP3ReportClientModel createNewObject() {
        return new ADatP3ReportClientModel(new ADatP3ReportDto());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#objectAdded(java.lang.
     * Object)
     */
    @Override
    protected void objectAdded(ADatP3ReportClientModel model) {
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
    protected void objectRemoved(ADatP3ReportClientModel object) {
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
    protected void objectUpdated(ADatP3ReportClientModel object) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel#getFilters()
     */
    @Override
    protected Filter<ADatP3ReportClientModel, String>[] getFilters() {
        return ClientModelsGridsConfigs.ADATP3_REPORT_FILTERS;
    }

    @Override
    protected boolean isValid(ADatP3ReportClientModel model) {

        ADatP3Parser aDatP3Parser = new ADatP3Parser();
        try {
            ADatP3 aDatP3 = aDatP3Parser.parseADatP3Content(model.getObject().getContent());
            return true;
        } catch (Exception e) {
            NotificationUtils.showToast("Error", "Can not parse AdatP-3 report. Report was not added",
                    InfoConfig.InfoPosition.TOP_RIGHT, 5000);
            Logger.getLogger("pp").info(e.getMessage());
            return false;
        }
    }

    /**
     * @param aDatP3ReportClientModel
     * @return
     */
    @Override
    protected Menu getContextMenuForObject(ADatP3ReportClientModel aDatP3ReportClientModel) {
        Menu menu = super.getContextMenuForObject(aDatP3ReportClientModel);
        menu.add(GxtComponentsUtils.createMenuItem(MESSAGES.report_editStyle(), selectEvent -> {
            OLStyleOptions olStyleOptions = new OLStyleOptions();
            OLStyleForm styleForm = new OLStyleForm(olStyleOptions, new SelectEvent.SelectHandler() {
                @Override
                public void onSelect(SelectEvent event) {
                    updateReportStyle(aDatP3ReportClientModel, olStyleOptions);
                }
            });
        }, GIS_IMAGES.layer_edit()));
        menu.add(GxtComponentsUtils.createMenuItem(MESSAGES.report_showData(), selectionEvent -> {
            ADatP3CoordinatesDto aDatP3CoordinatesDto = new ADatP3CoordinatesDto(aDatP3ReportClientModel.getaDatP3());
            ADatP3CoordinatesForm aDatP3ReportForm = new ADatP3CoordinatesForm(aDatP3CoordinatesDto, selectEvent -> {
            });
        }, GIS_IMAGES.layers_vector()));
        VisualisationGridPanel.extendMenuForShowOnMapMenuItem(menu,
                aDatP3ReportClientModel.getFeatures());
        return menu;
    }

    private void updateReportStyle(ADatP3ReportClientModel aDatP3ReportClientModel, OLStyleOptions olStyleOptions) {
        if (aDatP3ReportClientModel.getFeatures(ADatP3ReportClientModel.AdatP3FeatureType.RELEASE) != null) {
            for (Feature feature : aDatP3ReportClientModel
                    .getFeatures(ADatP3ReportClientModel.AdatP3FeatureType.RELEASE)) {
                feature.setStyle(OLStyleManager.createStyle(olStyleOptions,
                        ADatP3ReportClientModel.AdatP3FeatureType.RELEASE,
                        aDatP3ReportClientModel.getFeatures(ADatP3ReportClientModel.AdatP3FeatureType.RELEASE)));

            }
        }

        for (Feature feature : aDatP3ReportClientModel.getFeatures(ADatP3ReportClientModel.AdatP3FeatureType.HAZARD)) {
            feature.setStyle(
                    OLStyleManager.createStyle(olStyleOptions, ADatP3ReportClientModel.AdatP3FeatureType.HAZARD,
                            aDatP3ReportClientModel.getFeatures(ADatP3ReportClientModel.AdatP3FeatureType.HAZARD)));
        }
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
        return event.getObject() instanceof ADatP3ReportClientModel;
    }


}
