/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ol.GisApplicationMenu;
import pl.edu.wat.wcy.cop.app.client.ol.OLGuiDecisionSupportUtils;
import pl.edu.wat.wcy.cop.app.client.ol.OLGuiUtils;
import pl.edu.wat.wcy.cop.app.client.ol.OLMapManager;
import pl.edu.wat.wcy.cop.app.client.ol.extra.*;
import pl.edu.wat.wcy.cop.app.client.services.server.DecisionSupportProvider;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.images.GisImages;
// Represents app toolbar.


public class AppToolbar implements IsWidget {

    private VerticalLayoutContainer widget;
    @Inject
    private GisApplicationMenu gisApplicationMenu;

    @Inject
    private OLMouseModeRibbonComponent mouseModeComponent;
    @Inject
    private OLCoordsFormatingModeRibbonComponent coordsModeComponent;
    @Inject
    private OLScaleLineModeRibbonComponent scaleModeComponent;
    @Inject
    private OLControlsRibbonComponent controlsComponent;
    @Inject
    private OLInteractionRibbonComponent interactionsComponent;
    @Inject
    private OLAddLayerRibbonComponent addLayerComponent;

    // toolbar menu
    @Inject
    private Messages messages;
    @Inject
    private OLMapManager olMapManager;
    @Inject
    private DecisionSupportProvider decisionSupportProvider;
    @Inject
    private GisImages gisImages;
    private TextButton gisMenuButton;

    /**
     *
     */
    public void updateComponentState() {
        coordsModeComponent.updateComponentState();
        scaleModeComponent.updateComponentState();
        controlsComponent.updateComponentState();
        interactionsComponent.updateComponentState();
    }

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
        ToolBar toolBar = new ToolBar();
        widget = new VerticalLayoutContainer();

        createToolbar(toolBar);
//        toolBar.add(addLayerComponent);
//        toolBar.add(mouseModeComponent);
//        toolBar.add(coordsModeComponent);
//        toolBar.add(controlsComponent);
//        toolBar.add(interactionsComponent);
//        toolBar.add(scaleModeComponent);
        widget.add(toolBar, new VerticalLayoutData(1, -1));
        // toolBar.add(gisApplicationMenu.asWidget());
        // toolBar.add(new Label(), GxtComponentsUtils.createBoxLayoutDataWithFlex(new Margins(0, 5, 0, 0), 3));
        // toolBar.add(new UserInformationWidget().asWidget());

    }

    private void createToolbar(ToolBar toolBar) {
        // toolBar.add(gisApplicationMenu);

        toolBar.add(GxtComponentsUtils.createButtonMenu(messages.layeradd_title(),
                OLGuiUtils.createAddLayerMenu(olMapManager, gisImages, messages), gisImages.layer_add()));


        toolBar.add(GxtComponentsUtils.createButtonMenu(messages.gismenu_measuremt(),
                OLGuiUtils.createMeasurmentsMenu(olMapManager, gisImages, messages), gisImages.measure()));
        toolBar.add(GxtComponentsUtils.createButtonMenu(messages.gismenu_scaleline(),
                OLGuiUtils.createScaleLineTypeMenu(olMapManager, gisImages, messages), gisImages.scale_line()));
        toolBar.add(GxtComponentsUtils.createButtonMenu(messages.gismenu_coords(),
                OLGuiUtils.createCoordsFormatterMenu(olMapManager, gisImages, messages), gisImages.coords()));
        // toolBar.add(GxtComponentsUtils.createMenuMenuItem(messages.gismenu_measuremt(),
        // OLGuiUtils.createControlsMenu(olMapManager, gisImages, messages),
        // gisImages.measurement()));
        toolBar.add(GxtComponentsUtils.createButtonMenu(messages.gismenu_decisionsupport(),
                OLGuiDecisionSupportUtils.createDecisionSupportMenu(olMapManager, decisionSupportProvider, messages), gisImages.controls()));

//        toolBar.add(GxtComponentsUtils.createTextButton("Refresh POC", new SelectEvent.SelectHandler() {
//            @Override
//            public void onSelect(SelectEvent selectEvent) {
//                ((VisualizationActivity) ((CesarActivityMapper) CesarGinInjector.INSTANCE.activityMapper()).getCurrentActivity())
//                        .refreshSegmentsDetails();
//            }
//        }, null));
    }

}
