/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms.editors;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtListStoreEditor;
import pl.edu.wat.wcy.cop.app.client.visualisation.grid.*;
import pl.edu.wat.wcy.cop.app.shared.domain.*;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.GpxTraceDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueDto;
// Edits scenario settings.

public class ScenarioEditor implements Editor<ScenarioDto>, IsWidget {
    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();

    OLMapOptionsEditor mapConfiguration = GWT.create(OLMapOptionsEditor.class);

    @UiField
    Grid<App6AMilitaryUnitDto> app6aMilitaryUnitsGird;
    GxtListStoreEditor<App6AMilitaryUnitDto> app6aMilitaryUnits;

    @UiField
    Grid<MSWiAUnitDto> mswiaUnitsGird;
    GxtListStoreEditor<MSWiAUnitDto> mswiaUnits;

    @UiField
    Grid<ADatP3ReportDto> reportsADatP3Gird;
    GxtListStoreEditor<ADatP3ReportDto> reportsADatP3;

    @UiField
    Grid<CrisisEventDto> crisisEventsGrid;
    GxtListStoreEditor<CrisisEventDto> crisisEvents;

    @UiField
    Grid<PointOfInterestDto> poiGrid;
    GxtListStoreEditor<PointOfInterestDto> poi;


    @UiField
    Grid<SearchAndRescueDto> sarGrid;
    GxtListStoreEditor<SearchAndRescueDto> searchAndRescues;


    @UiField
    Grid<GpxTraceDto> gpxGrid;
    GxtListStoreEditor<GpxTraceDto> gpxTraces;

    private TabPanel tabPanel;

    public ScenarioEditor() {
        super();
        tabPanel = new TabPanel();
        tabPanel.add(mapConfiguration.asWidget(), MESSAGES.scenarioeditor_map());
        tabPanel.add(createSarGrid(), "SAR Zones");
        tabPanel.add(createGpxGrid(), "GPX traces");
         tabPanel.add(createReportsGrid(), MESSAGES.scenarioeditor_reports());
        tabPanel.add(createApp6aGrid(), MESSAGES.scenarioeditor_app6a());
//        tabPanel.add(createMSWiAGrid(), MESSAGES.scenarioeditor_mswia());

        tabPanel.add(createCrisisEventsGrid(), MESSAGES.scenarioeditor_criticalevents());
        tabPanel.add(createPOIEventsGrid(), MESSAGES.scenarioeditor_poi());
        tabPanel.setCloseContextMenu(false);
        tabPanel.setTabScroll(true);
        tabPanel.setBodyBorder(false);
    }

    private IsWidget createGpxGrid() {
        GpxTraceGrid gridPanel = new GpxTraceGrid();
        gpxGrid = gridPanel.getGrid();
        gpxTraces = gridPanel.getStore();
        return gridPanel;
    }

    private IsWidget createSarGrid() {
        SearchAndRescueGridPanel gridPanel = new SearchAndRescueGridPanel();
        sarGrid = gridPanel.getGrid();
        searchAndRescues = gridPanel.getStore();
        return gridPanel;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget() {
        return tabPanel;
    }

    /**
     * @return
     */
    private IsWidget createPOIEventsGrid() {
        PointOfInterestGridPanel gridPanel = new PointOfInterestGridPanel();
        poiGrid = gridPanel.getGrid();
        poi = gridPanel.getStore();
        return gridPanel;
    }

    /**
     * @return
     */
    private IsWidget createCrisisEventsGrid() {
        CrisisEventGridPanel gridPanel = new CrisisEventGridPanel();
        crisisEventsGrid = gridPanel.getGrid();
        crisisEvents = gridPanel.getStore();
        return gridPanel;
    }

    /**
     * @return
     */
    private IsWidget createMSWiAGrid() {
        MSWiAUnitGridPanel gridPanel = new MSWiAUnitGridPanel();
        mswiaUnitsGird = gridPanel.getGrid();
        mswiaUnits = gridPanel.getStore();
        return gridPanel;
    }

    /**
     *
     */
    private IsWidget createReportsGrid() {
        ADatP3ReportsGrid gridPanel = new ADatP3ReportsGrid();
        reportsADatP3Gird = gridPanel.getGrid();
        reportsADatP3 = gridPanel.getStore();
        return gridPanel;

    }

    private IsWidget createApp6aGrid() {
        App6AMilitaryUnitGridPanel gridPanel = new App6AMilitaryUnitGridPanel();
        app6aMilitaryUnitsGird = gridPanel.getGrid();
        app6aMilitaryUnits = gridPanel.getStore();
        return gridPanel;

    }

}
