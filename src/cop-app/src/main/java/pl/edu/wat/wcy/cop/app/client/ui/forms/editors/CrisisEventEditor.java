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
import com.sencha.gxt.widget.core.client.form.*;
import com.sencha.gxt.widget.core.client.grid.Grid;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.ui.forms.VBoxLayoutForm;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtListStoreEditor;
import pl.edu.wat.wcy.cop.app.client.visualisation.grid.CrisisEventCricleZoneGrid;
import pl.edu.wat.wcy.cop.app.client.visualisation.grid.CrisisEventMultiPointZoneGridPanel;
import pl.edu.wat.wcy.cop.app.shared.domain.CrisisEventCricleZoneDto;
import pl.edu.wat.wcy.cop.app.shared.domain.CrisisEventDto;
import pl.edu.wat.wcy.cop.app.shared.domain.CrisisEventMultiPointZoneDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.crisis.CrisisEventStatusDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.crisis.CrisisEventTypeDto;
// Edits crisis event settings.


public class CrisisEventEditor implements Editor<CrisisEventDto>, IsWidget {
    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    @Ignore
    public DateField startDate = new DateField();
    @Ignore
    public DateField endDate = new DateField();
    @Ignore
    public TimeField timestampEnd = new TimeField();
    @Ignore
    public TimeField timestampStart = new TimeField();
    TextField htrcId = FormUtils.createRequiredTextField();
    ComboBox<CrisisEventTypeDto> type = FormUtils.createEnumComboBox(CrisisEventTypeDto.class);
    ComboBox<CrisisEventStatusDto> status = FormUtils.createEnumComboBox(CrisisEventStatusDto.class);
    TextField substanceName = new TextField();
    TextField threatLevel = new TextField();
    TextField province = new TextField();
    TextField city = new TextField();
    TextField source = new TextField();
    HtmlEditor description = FormUtils.createHtmlEditor();
    @UiField
    GeoPointEditor point = GWT.create(GeoPointEditor.class);
    @UiField
    @Path("mapSymbol")
    MapSymbolEditor mapSymbol = GWT.create(MapSymbolEditor.class);
    @Ignore
    TabPanel container = new TabPanel();

    @UiField
    Grid<CrisisEventCricleZoneDto> circleZonesGrid;
    GxtListStoreEditor<CrisisEventCricleZoneDto> circleZones;

    @UiField
    Grid<CrisisEventMultiPointZoneDto> areaZonesGrid;
    GxtListStoreEditor<CrisisEventMultiPointZoneDto> areaZones;

    /**
     *
     */
    public CrisisEventEditor() {
        super();
        VBoxLayoutForm form = new VBoxLayoutForm();
        form.addRow(MESSAGES.crisisevent_htrcId(), htrcId);
        form.addRow(MESSAGES.crisisevent_type(), type);
        form.addRow(MESSAGES.crisisevent_status(), status);
        form.addRow(MESSAGES.crisisevent_substanceName(), substanceName);
        form.addRow(MESSAGES.crisisevent_threatLevel(), threatLevel);
        form.addRow(MESSAGES.crisisevent_province(), province);
        form.addRow(MESSAGES.crisisevent_city(), city);
        form.addRow(MESSAGES.crisisevent_source(), source);
        form.addRow(MESSAGES.crisisevent_dateStart(), startDate);
        form.addRow(MESSAGES.crisisevent_timeStart(), timestampStart);
        form.addRow(MESSAGES.crisisevent_dateEnd(), endDate);
        form.addRow(MESSAGES.crisisevent_timeEnd(), timestampEnd);
        form.addRow(MESSAGES.crisisevent_description(), description);

        container.add(GxtComponentsUtils.createContentPanel(form), MESSAGES.crisisevent());

        container.add(GxtComponentsUtils.createContentPanel(mapSymbol.asWidget()), MESSAGES.mapsymbol());
        container.add(GxtComponentsUtils.createContentPanel(point.asWidget()), MESSAGES.geopoint());

        CrisisEventCricleZoneGrid circleZoneTreeGridPanel = new CrisisEventCricleZoneGrid();
        CrisisEventMultiPointZoneGridPanel multiPointZoneTreeGridPanel = new CrisisEventMultiPointZoneGridPanel();

        circleZonesGrid = circleZoneTreeGridPanel.getGrid();
        circleZones = circleZoneTreeGridPanel.getStore();
        container.add(
                GxtComponentsUtils.createContentPanel(circleZoneTreeGridPanel), MESSAGES.crisisevent_zone_circle());

        areaZonesGrid = multiPointZoneTreeGridPanel.getGrid();
        areaZones = multiPointZoneTreeGridPanel.getStore();

        container.add(GxtComponentsUtils.createContentPanel(multiPointZoneTreeGridPanel),
                MESSAGES.crisisevent_zone_multipoint());

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
