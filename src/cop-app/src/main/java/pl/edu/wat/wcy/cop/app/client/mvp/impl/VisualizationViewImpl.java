/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.mvp.impl;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.CollapseItemEvent;
import com.sencha.gxt.widget.core.client.event.CollapseItemEvent.CollapseItemHandler;
import com.sencha.gxt.widget.core.client.event.ExpandItemEvent;
import com.sencha.gxt.widget.core.client.event.ExpandItemEvent.ExpandItemHandler;
import pl.edu.wat.wcy.cop.app.client.domain.spec.ScenarioClientModel;
import pl.edu.wat.wcy.cop.app.client.ol.MapWidget;
import pl.edu.wat.wcy.cop.app.client.ui.AppManagementPanel;
import pl.edu.wat.wcy.cop.app.client.ui.AppToolbar;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtUtils;
// Represents visualization view impl.

public class VisualizationViewImpl implements VisualizationView {
    private SimpleContainer widget;
    private BorderLayoutContainer container;

    private MapWidget mapWidget;
    private AppManagementPanel appManagementPanel;
    private AppToolbar appToolbar;
    private Presenter presenter;
    private ScenarioClientModel scenario;

    @Inject
    public VisualizationViewImpl(MapWidget mapWidget, AppManagementPanel panel, AppToolbar appToolbar) {
        super();
        this.mapWidget = mapWidget;
        this.appManagementPanel = panel;
        this.appToolbar = appToolbar;
    }

    @Override
    public Widget asWidget() {
        if (widget == null) {
            prepareUI();
        }
        return widget;
    }

    /**
     *
     */
    private void prepareUI() {
        widget = new SimpleContainer();
        container = new BorderLayoutContainer();

        setEastWidget();
        setCenterWidget();
        setNorthWidget();
        GxtUtils.initThemeBasedPropsForContainer(container);
        widget.setWidget(container);
        container.addCollapseHandler(new CollapseItemHandler<ContentPanel>() {

            @Override
            public void onCollapse(CollapseItemEvent<ContentPanel> event) {
                mapWidget.updateMapSize();
            }

        });
        container.addExpandHandler(new ExpandItemHandler<ContentPanel>() {

            @Override
            public void onExpand(ExpandItemEvent<ContentPanel> event) {
                mapWidget.updateMapSize();
            }
        });


    }

    /**
     *
     */
    private void setEastWidget() {
        BorderLayoutData eastData = new BorderLayoutData(450);
        eastData.setCollapsible(true);
        eastData.setSplit(true);
        eastData.setCollapseMini(true);
        eastData.setCollapsed(false);
        eastData.setCollapseHeaderVisible(true);
        eastData.setMargins(GxtUtils.getDefaultMarginForTheme());
        container.setEastWidget(appManagementPanel, eastData);

    }

    /**
     *
     */
    private void setCenterWidget() {
        container.setCenterWidget(mapWidget, new MarginData(GxtUtils.getDefaultMarginForTheme()));
        BorderLayoutContainer blc = new BorderLayoutContainer();
        blc.setCenterWidget(mapWidget);
        blc.setNorthWidget(appToolbar, new BorderLayoutData(40));

        container.setCenterWidget(blc, new MarginData(GxtUtils.getDefaultMarginForTheme()));
    }

    /**
     *
     */
    private void setNorthWidget() {
        BorderLayoutData northData = new BorderLayoutData(200);
        northData.setCollapsible(true);
        northData.setSplit(true);
        northData.setCollapseMini(true);
        northData.setCollapsed(
                false);
        northData.setCollapseHeaderVisible(false);
        // northData.setMargins(GxtUtils.getDefaultMarginForTheme());

//		container.setNorthWidget(GxtComponentsUtils.createContentPanel(appToolbar, ""), northData);
        //container.setNorthWidget(GxtComponentsUtils.createContentPanel(appToolbar, ""), northData);
//        VerticalLayoutContainer vlc = new VerticalLayoutContainer();
//        vlc.add(appToolbar, new VerticalLayoutContainer.VerticalLayoutData(1, -1));
        container.setNorthWidget(appToolbar);
    }

    /**
     *
     */
    private void setSouthWidget() {
        // TODO Auto-generated method stub

    }

    /**
     *
     */
    private void setWestWidget() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationView#setPresenter(
     * pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationView.Presenter)
     */
    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
        this.appManagementPanel.setPresenter(presenter);

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationView#setScenario(pl
     * .edu.wat.wcy.cop.app.client.domain.ScenarioClientModel)
     */
    @Override
    public void setScenario(ScenarioClientModel scenario) {
        this.scenario = scenario;
        appManagementPanel.setScenario(scenario);
        appToolbar.updateComponentState();
    }

}

