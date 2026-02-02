/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms.editors;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DualListField;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.ui.forms.VBoxLayoutForm;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtListStoreEditor;
import pl.edu.wat.wcy.cop.app.client.visualisation.grid.OLLayerGridPanel;
import pl.edu.wat.wcy.cop.app.shared.ol.*;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
// Edits ol map options settings.


public class OLMapOptionsEditor implements Editor<OLMapOptions>, IsWidget {

    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();

    @UiField
    NumberField<Double> centerLat = FormUtils.createRangeDoubleField(-90, 90);
    @UiField
    NumberField<Double> centerLon = FormUtils.createRangeDoubleField(-180, 180);
    @UiField
    NumberField<Double> zoom = FormUtils.createRangeDoubleField(0, 28, false);
    @UiField
    NumberField<Double> maxZoom = FormUtils.createRangeDoubleField(0, 28, false);
    @UiField
    NumberField<Double> minZoom = FormUtils.createRangeDoubleField(0, 28, false);
    @UiField
    NumberField<Double> zoomFactor = FormUtils.createRangeDoubleField(0, 28, false);
    // @UiField
    // ComboBox<OLProjection> projection =
    // FormUtils.createEnumComboBox(OLProjection.class);
    @UiField
    DualListField<OLControlType, String> controls = FormUtils.createEnumDualList(OLControlType.class);
    @UiField
    DualListField<OLInteractionType, String> interactions = FormUtils.createEnumDualList(OLInteractionType.class);
    @UiField
    ComboBox<OLScaleLineUnit> scaleUnit = FormUtils.createEnumComboBox(OLScaleLineUnit.class);
    @UiField
    ComboBox<OLCordsFormattingType> cordsFormattingType = FormUtils.createEnumComboBox(OLCordsFormattingType.class);

    @UiField
    Grid<OLLayer> layersGird;
    GxtListStoreEditor<OLLayer> layers;

    @Ignore
    TabPanel container = new TabPanel();

    public OLMapOptionsEditor() {
        super();
        OLLayerGridPanel gridPanel = new OLLayerGridPanel();
        layersGird = gridPanel.getGrid();
        layers = gridPanel.getStore();

        VBoxLayoutForm form = new VBoxLayoutForm();
        form.addRow(MESSAGES.mapconfig_centerlat(), centerLat);
        form.addRow(MESSAGES.mapconfig_centerlon(), centerLon);
        form.addRow(MESSAGES.mapconfig_zoom(), zoom);
        form.addRow(MESSAGES.mapconfig_maxZoom(), maxZoom);
        form.addRow(MESSAGES.mapconfig_minZoom(), minZoom);
        form.addRow(MESSAGES.mapconfig_zoomFactor(), zoomFactor);
        container.add(GxtComponentsUtils.createContentPanel(form), MESSAGES.scenarioeditor_map_geo());
        form = new VBoxLayoutForm();

        // addRow(MESSAGES.mapconfig_projection(), projection);
        form.addRow(MESSAGES.mapconfig_controls(), controls);
        form.addRow(MESSAGES.mapconfig_interactions(), interactions);
        form.addRow(MESSAGES.mapconfig_scaleUnit(), scaleUnit);
        form.addRow(MESSAGES.mapconfig_cordsFormattingType(), cordsFormattingType);
        container.add(GxtComponentsUtils.createContentPanel(form), MESSAGES.scenarioeditor_map_controls());
        // VerticalLayoutContainer container = new VerticalLayoutContainer();
        // ToolBar toolbar = new ToolBar();
        // OLGuiUtils.createAddLayerMenu(olMapManager, gisImages, messages)
        // container.add(layersGird, new VerticalLayoutData(1, 1));
        container.add(gridPanel, MESSAGES.scenarioeditor_map_layers());

        container.setActiveWidget(container.getWidget(0));

    }

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget() {
        return container;
    }

}
