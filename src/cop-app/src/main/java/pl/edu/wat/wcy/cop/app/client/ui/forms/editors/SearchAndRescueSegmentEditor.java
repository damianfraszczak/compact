/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms.editors;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.form.*;
import com.sencha.gxt.widget.core.client.grid.Grid;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.domain.json.EncodersDecoders;
import pl.edu.wat.wcy.cop.app.client.domain.json.EncodersDecoders.GeoPointsDtoList;
import pl.edu.wat.wcy.cop.app.client.ui.forms.VBoxLayoutForm;
import pl.edu.wat.wcy.cop.app.client.utils.DefaultAsyncCallback;
import pl.edu.wat.wcy.cop.app.client.utils.gui.DialogUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtListStoreEditor;
import pl.edu.wat.wcy.cop.app.client.visualisation.grid.GeoPointGridPanel;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.domain.GeoPointDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueSegmentDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.TerrainCategory;
// Edits search and rescue segment settings.


public class SearchAndRescueSegmentEditor implements Editor<SearchAndRescueSegmentDto>, IsWidget {
    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    @UiField
    TextField name = FormUtils.createRequiredTextField();
    @Ignore
    public TextField areaInHa = FormUtils.createDisabledField();

    @UiField
    public NumberField<Double> poc = new NumberField<>(new NumberPropertyEditor.DoublePropertyEditor(AppConstants.POC_NUMBER_FORMAT));
    @UiField
    NumberField<Double> aes = new NumberField<>(new NumberPropertyEditor.DoublePropertyEditor(AppConstants.DEFAULT_NUMBER_FORMAT));
    @UiField
    HtmlEditor description = FormUtils.createHtmlEditor();
    @UiField
    CheckBox visible = new CheckBox();
    @UiField
    Grid<GeoPointDto> pointsGird;
    GxtListStoreEditor<GeoPointDto> points;
    @Ignore
    TabPanel container = new TabPanel();
    @UiField
    public FeatureStyleEditor style = GWT.create(FeatureStyleEditor.class);

    @UiField
    ComboBox<TerrainCategory> terrainCategory = FormUtils.createEnumComboBox(TerrainCategory.class);

    /**
     *
     */
    public SearchAndRescueSegmentEditor() {
        super();
        VBoxLayoutForm form = new VBoxLayoutForm();
        form.addRow(MESSAGES.poi_zone_name(), name);
        form.addRow(MESSAGES.poi_zone_description(), description);
        form.addRow("Pole w ha", areaInHa);
        form.addRow("Kategoria terenu", terrainCategory);
        form.addRow("POC", poc);
        form.addRow("AES", aes);
        form.addRow(MESSAGES.poi_zone_visible(), visible);
        form.addRow(style.asWidget());

        container.add(GxtComponentsUtils.createContentPanel(form), MESSAGES.poi_zone_multipoint());


        GeoPointGridPanel gridPanel = new GeoPointGridPanel();
        pointsGird = gridPanel.getGrid();
        points = gridPanel.getStore();
        ContentPanel pointsPanel = GxtComponentsUtils.createContentPanel(gridPanel.asWidget());
        createImportExportPanel(pointsPanel);
        container.add(GxtComponentsUtils.createContentPanel(pointsPanel), "Połozenie");


        container.setActiveWidget(container.getWidget(0));

        container.add(GxtComponentsUtils.createContentPanel(style.asWidget()), "Grafika");
        terrainCategory.setReadOnly(true);


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

    /**
     * @param pointsPanel
     */
    private void createImportExportPanel(ContentPanel pointsPanel) {
        ToolButton importButton = new ToolButton(ToolButton.PLUS);
        importButton.addSelectHandler(x -> {
            DialogUtils.showMultiLineInputDialog(MESSAGES.data_import(), "Podaj punkty w postaci tablicy obiektow json",
                    new DefaultAsyncCallback<String>() {

                        @Override
                        public void onSuccess(String result) {
                            GeoPointsDtoList pointsToImport = EncodersDecoders.ListOfGeoPointsEncoderDecoderInstance
                                    .decode(result);
                            points.getStore().addAll(pointsToImport.getPoints());

                        }
                    });

        });
        pointsPanel.addTool(importButton);

        ToolButton exportButton = new ToolButton(ToolButton.SAVE);
        exportButton.addSelectHandler(x -> {
            GeoPointsDtoList toExport = new GeoPointsDtoList();
            toExport.getPoints().addAll(points.getStore().getAll());
            DialogUtils.showAlertDialog(MESSAGES.data_export(),
                    EncodersDecoders.ListOfGeoPointsEncoderDecoderInstance.encode(toExport).toString());

        });
        pointsPanel.addTool(exportButton);
        pointsPanel.setHeaderVisible(true);
        pointsPanel.setHeading("Import and export data using the buttons next to it.");

    }

    public int getRowsCount() {
        return 8;
    }
}
