package pl.edu.wat.wcy.cop.app.client.visualisation.grid;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.filters.Filter;
import pl.edu.wat.wcy.cop.app.client.events.ObjectEvent;
import pl.edu.wat.wcy.cop.app.client.ui.grid.DtoGridsConfigs;
import pl.edu.wat.wcy.cop.app.client.ui.grid.GridPanel;
import pl.edu.wat.wcy.cop.app.client.ui.pa.SearchAndRescueSearchPlanEntryDtoPropertyAccess;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtDefaultContainer;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.searchplan.SearchAndRescueSearchPlanEntryDto;
// Renders search and rescue search plan entry grid panel UI.

public class SearchAndRescueSearchPlanEntryGridPanel extends GridPanel<SearchAndRescueSearchPlanEntryDto> {

    public SearchAndRescueSearchPlanEntryGridPanel() {

    }

    @Override
    protected Widget initContainer(Widget widgets) {

        return new GxtDefaultContainer(super.initContainer(widgets)).setMinHeight(250);
    }

//    @Override
//    protected Widget initContainer(GxtDefaultContainer widgets) {
//        widgets.setMinHeight(250);
////        widgets.setPreferredHeight(250);
//        return super.initContainer(widgets);
//    }




    @Override
    protected ColumnModel<SearchAndRescueSearchPlanEntryDto> getColumnModel() {
        return DtoGridsConfigs.SAR_PLAN_COLUMN_MODEL;
    }

    @Override
    protected ModelKeyProvider<SearchAndRescueSearchPlanEntryDto> getModelKey() {
        return SearchAndRescueSearchPlanEntryDtoPropertyAccess.INSTANCE.key();
    }

    @Override
    protected Filter<SearchAndRescueSearchPlanEntryDto, String>[] getFilters() {
        return new Filter[0];
    }

    @Override
    protected void showFormForObject(SearchAndRescueSearchPlanEntryDto model, SelectEvent.SelectHandler selectHandler, boolean editable) {

    }

    @Override
    protected SearchAndRescueSearchPlanEntryDto createNewObject() {
        return null;
    }

    @Override
    protected boolean shouldProcessEvent(ObjectEvent event) {
        return false;
    }



}
