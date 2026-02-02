/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.tree;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.domain.spec.ScenarioClientModel;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
// Renders visualisation panel UI.


public class VisualisationPanel implements IsWidget {

    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    private ScenarioClientModel scenario;

    private AccordionLayoutContainer container;

    @Inject
    private ADatP3TreePanel reportsTreePanel;
    @Inject
    private App6AMilitaryUnitTreePanel app6aTreePanel;
    @Inject
    private MSWiAUnitVisualizationTreePanel mswiaTreePanel;
    @Inject
    private CrisisEventVisualizationTreePanel crisisTreePanel;
    @Inject
    private PoiVisualizationTreePanel poiTreePanel;

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget() {
        if (container == null) {
            container = new AccordionLayoutContainer();

            container.add(
                    GxtComponentsUtils.createContentPanel(reportsTreePanel, MESSAGES.appmanagementtabs_reportstab()));
            container.add(GxtComponentsUtils.createContentPanel(app6aTreePanel, MESSAGES.appmanagementtabs_app6atab()));
            container.add(GxtComponentsUtils.createContentPanel(mswiaTreePanel, MESSAGES.appmanagementtabs_mswiatab()));
            container.add(GxtComponentsUtils.createContentPanel(crisisTreePanel, MESSAGES.appmanagementtabs_events()));
            container.add(GxtComponentsUtils.createContentPanel(poiTreePanel, MESSAGES.appmanagementtabs_poitab()));
            container.setActiveWidget(container.getWidget(0));
        }
        return container;
    }

    /**
     * @return the scenario
     */
    public ScenarioClientModel getScenario() {
        return scenario;
    }

    /**
     * @param scenario
     *            the scenario to set
     */
    public void setScenario(ScenarioClientModel scenario) {
        this.scenario = scenario;
        reportsTreePanel.setReports(scenario.getReports());
        app6aTreePanel.setObjects(scenario.getApp6aMilitaryUnits());
        mswiaTreePanel.setObjects(scenario.getMswiaUnits());
        crisisTreePanel.setObjects(scenario.getCrisisEvents());
        poiTreePanel.setObjects(scenario.getPoi());
        reportsTreePanel.setReports(scenario.getReports());

    }

}
