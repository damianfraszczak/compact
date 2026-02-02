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
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.HtmlEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ui.forms.VBoxLayoutForm;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtListStoreEditor;
import pl.edu.wat.wcy.cop.app.client.visualisation.grid.PoiCircleZoneGrid;
import pl.edu.wat.wcy.cop.app.client.visualisation.grid.PoiMultiPointZoneGrid;
import pl.edu.wat.wcy.cop.app.shared.domain.PoiCircleZoneDto;
import pl.edu.wat.wcy.cop.app.shared.domain.PoiMultiPointZoneDto;
import pl.edu.wat.wcy.cop.app.shared.domain.PointOfInterestDto;
import pl.edu.wat.wcy.cop.app.shared.domain.PointOfInterestTypeDto;
// Edits point of interest settings.


public class PointOfInterestEditor implements Editor<PointOfInterestDto>, IsWidget {
    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();

    @UiField
    GeoPointEditor point = GWT.create(GeoPointEditor.class);
    @UiField
    @Path("mapSymbol")
    MapSymbolEditor mapSymbol = GWT.create(MapSymbolEditor.class);
    @UiField
    TextField name = FormUtils.createRequiredTextField();
    @UiField
    HtmlEditor description = FormUtils.createHtmlEditor();
    @UiField
    ComboBox<PointOfInterestTypeDto> type = FormUtils.createEnumComboBox(PointOfInterestTypeDto.class);
    @Ignore
    TabPanel container = new TabPanel();
    @UiField
    Grid<PoiCircleZoneDto> circleZonesGrid;
    GxtListStoreEditor<PoiCircleZoneDto> circleZones;

    @UiField
    Grid<PoiMultiPointZoneDto> areaZonesGrid;
    GxtListStoreEditor<PoiMultiPointZoneDto> areaZones;

    /**
     *
     */
    public PointOfInterestEditor() {
        super();
        VBoxLayoutForm form = new VBoxLayoutForm();
        form.addRow(MESSAGES.poi_name(), name);
        //TODO - tekst dla typu poi
        form.addRow("Typ", type);
        form.addRow(MESSAGES.poi_description(), description);

        container.add(GxtComponentsUtils.createContentPanel(form), MESSAGES.poi());
        container.add(GxtComponentsUtils.createContentPanel(mapSymbol.asWidget()), MESSAGES.mapsymbol());
        container.add(GxtComponentsUtils.createContentPanel(point.asWidget()), MESSAGES.geopoint());

        PoiCircleZoneGrid circleZoneTreeGridPanel = new PoiCircleZoneGrid();
        PoiMultiPointZoneGrid multiPointZoneTreeGridPanel = new PoiMultiPointZoneGrid();

        circleZonesGrid = circleZoneTreeGridPanel.getGrid();
        circleZones = circleZoneTreeGridPanel.getStore();
        container.add(GxtComponentsUtils.createContentPanel(circleZoneTreeGridPanel), MESSAGES.poi_zone_circle());

        areaZonesGrid = multiPointZoneTreeGridPanel.getGrid();
        areaZones = multiPointZoneTreeGridPanel.getStore();

        container.add(GxtComponentsUtils.createContentPanel(multiPointZoneTreeGridPanel), MESSAGES.poi_zone_multipoint());

        container.setActiveWidget(container.getWidget(0));
        container.setTabScroll(true);
        container.setBodyBorder(false);
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
        return mapSymbol.getRowsCount();
    }
}
