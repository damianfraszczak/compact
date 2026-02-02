/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.domain.spec.ScenarioClientModel;
import pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationView.Presenter;
import pl.edu.wat.wcy.cop.app.client.ol.GroupingLayersManagementTree;
import pl.edu.wat.wcy.cop.app.client.ol.OrderedLayersManagementTree;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.visualisation.grid.*;
// Renders app management panel UI.


public class AppManagementPanel implements IsWidget {

    private ContentPanel widget;

    private AccordionLayoutContainer container;

    @Inject
    private GroupingLayersManagementTree groupingLayerTreePanel;
    @Inject
    private OrderedLayersManagementTree orderedLayerTreePanel;
    @Inject
    private App6AMilitaryUnitClientModelGridPanel app6aTreePanel;
    @Inject
    private MSWiAUnitClientModelGridPanel mswiaTreePanel;
    @Inject
    private CrisisEventClientModelGridPanel crisisTreePanel;
    @Inject
    private PointOfInterestClientModelGridPanel potTreePanel;
    @Inject
    private SearchAndRescueClientModelGridPanel sarTreePanel;
    @Inject
    private GpxTraceClientModelGridPanel gpxTreePanel;
    @Inject
    private ADatP3ReportsClientModelGridPanel reportsTreePanel;
    @Inject
    private Messages messages;

    private ScenarioClientModel scenario;

    private ToolButton clearSituationButton = new ToolButton(ToolButton.CLOSE);

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget() {
        if (widget == null) {
            createGui();
        }
        return widget;
    }

    /**
     *
     */
    private void createGui() {
        widget = new ContentPanel();
        widget.setHeaderVisible(false);
        container = new AccordionLayoutContainer();

        container.add(GxtComponentsUtils.createContentPanel(app6aTreePanel, messages.appmanagementtabs_app6atab()));
        container.add(GxtComponentsUtils.createContentPanel(crisisTreePanel, messages.appmanagementtabs_events()));
        container.add(GxtComponentsUtils.createContentPanel(reportsTreePanel, messages.appmanagementtabs_reportstab()));
        container.add(GxtComponentsUtils.createContentPanel(sarTreePanel, "Search and resuce"));
        container.add(GxtComponentsUtils.createContentPanel(gpxTreePanel, "GPX Traces"));
        container.add(GxtComponentsUtils.createContentPanel(potTreePanel, messages.appmanagementtabs_poitab()));
        container.setActiveWidget(container.getWidget(0));

        app6aTreePanel.setSendEvents(true);
        mswiaTreePanel.setSendEvents(true);
        crisisTreePanel.setSendEvents(true);
        potTreePanel.setSendEvents(true);
        sarTreePanel.setSendEvents(true);
        reportsTreePanel.setSendEvents(true);
        reportsTreePanel.setSendEvents(true);

        BorderLayoutContainer blc = new BorderLayoutContainer();
        TabPanel tabPanel = new TabPanel();
        tabPanel.add(groupingLayerTreePanel, "GIS Layers");
        tabPanel.add(orderedLayerTreePanel, "Layers list");

        blc.setNorthWidget(GxtComponentsUtils.createContentPanel(tabPanel), getBorderData(200));
        blc.setCenterWidget(container);

        widget.add(blc);
        widget.addTool(clearSituationButton);
    }

    private BorderLayoutContainer.BorderLayoutData getBorderData(int size) {
        BorderLayoutContainer.BorderLayoutData northData = new BorderLayoutContainer.BorderLayoutData(size);
        northData.setCollapsible(false);
        northData.setSplit(false);
        northData.setCollapseMini(false);
        northData.setCollapsed(false);
        northData.setCollapseHeaderVisible(false);
        return northData;
    }

    /**
     * @param scenario
     */
    public void setScenario(ScenarioClientModel scenario) {
        this.scenario = scenario;
        app6aTreePanel.setObjects(scenario.getApp6aMilitaryUnits());
        mswiaTreePanel.setObjects(scenario.getMswiaUnits());
        crisisTreePanel.setObjects(scenario.getCrisisEvents());
        potTreePanel.setObjects(scenario.getPoi());
        sarTreePanel.setObjects(scenario.getSarUnits());
        gpxTreePanel.setObjects(scenario.getGpx());
        reportsTreePanel.setObjects(scenario.getReports());
    }

    public void setPresenter(Presenter presenter) {
        clearSituationButton.addSelectHandler(new SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                presenter.clearSituation();

            }
        });
    }
}
