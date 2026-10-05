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
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.domain.json.EncodersDecoders;
import pl.edu.wat.wcy.cop.app.client.domain.json.EncodersDecoders.GeoPointsDtoList;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.ui.forms.VBoxLayoutForm;
import pl.edu.wat.wcy.cop.app.client.utils.DefaultAsyncCallback;
import pl.edu.wat.wcy.cop.app.client.utils.gui.DialogUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtListStoreEditor;
import pl.edu.wat.wcy.cop.app.client.visualisation.grid.GeoPointGridPanel;
import pl.edu.wat.wcy.cop.app.shared.domain.CrisisEventMultiPointZoneDto;
import pl.edu.wat.wcy.cop.app.shared.domain.GeoPointDto;
// Edits crisis event multi point zone settings.


public class CrisisEventMultiPointZoneEditor implements Editor<CrisisEventMultiPointZoneDto>, IsWidget {
    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    @UiField
    TextField name = FormUtils.createRequiredTextField();
    @UiField
    TextField description = FormUtils.createRequiredTextField();
    @UiField
    CheckBox visible = new CheckBox();
    @UiField
    Grid<GeoPointDto> pointsGird;
    GxtListStoreEditor<GeoPointDto> points;
    @Ignore
    TabPanel container = new TabPanel();
    @UiField
    public FeatureStyleEditor style = GWT.create(FeatureStyleEditor.class);

    /**
     *
     */
    public CrisisEventMultiPointZoneEditor() {
        super();
        VBoxLayoutForm form = new VBoxLayoutForm();
        form.addRow(MESSAGES.crisisevent_zone_name(), name);
        form.addRow(MESSAGES.crisisevent_zone_description(), description);
        form.addRow(MESSAGES.crisisevent_zone_visible(), visible);
        form.addRow(style.asWidget());
        container.add(GxtComponentsUtils.createContentPanel(form), MESSAGES.crisisevent_zone_multipoint());
        GeoPointGridPanel gridPanel = new GeoPointGridPanel();
        pointsGird = gridPanel.getGrid();
        points = gridPanel.getStore();
        ContentPanel pointsPanel = GxtComponentsUtils.createContentPanel(gridPanel.asWidget());
        // TODO
        createImportExportPanel(pointsPanel);
        container.add(pointsPanel,
                MESSAGES.crisisevent_zone_points());

        container.setActiveWidget(container.getWidget(0));
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

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget() {
        return container;
    }

    public int getRowsCount() {
        return 5;
    }
}
