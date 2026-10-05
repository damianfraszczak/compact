/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms.editors;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.signs.App6AMenuFactory;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm.LayoutForm;
import pl.edu.wat.wcy.cop.app.client.ui.forms.VBoxLayoutForm;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.App6AMilitaryUnitDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.AffiliationEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.BattleDimensionEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.UnitSizeEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.UnitTypeEnumDto;
// Edits 6 a military unit settings.


public class APP6AMilitaryUnitEditor extends WithCodeImageObjectEditor<App6AMilitaryUnitDto> {
    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    public TextField code = FormUtils.createRequiredTextField();
    public ComboBox<AffiliationEnumDto> affiliation = FormUtils.createEnumComboBox(AffiliationEnumDto.class);
    public ComboBox<BattleDimensionEnumDto> battleDimension = FormUtils
            .createEnumComboBox(BattleDimensionEnumDto.class);
    public ComboBox<UnitSizeEnumDto> mobilitySize = FormUtils.createEnumComboBox(UnitSizeEnumDto.class);
    public ComboBox<UnitTypeEnumDto> unitType = FormUtils.createEnumComboBox(UnitTypeEnumDto.class);
    public String functionIdCode = "U-----";
    @Ignore
    public SelectionHandler<Item> recalculateApp6aCodeHandler;
    @UiField
    GeoPointEditor point = GWT.create(GeoPointEditor.class);
    @UiField
    MapSymbolEditor mapSymbol = GWT.create(MapSymbolEditor.class);
    TextField description = new TextField();
    TextField nameAdditional = new TextField();
    TextField codingScheme = new TextField();
    TextField status = new TextField();
    TextField countryCode = FormUtils.createRequiredTextField();
    TextField orderOfBattle = new TextField();
    @Ignore
    TabPanel container = new TabPanel();
    @Ignore
    TextButton app6aButton;

    int maxRowCount = 0;

    /**
     *
     */
    public APP6AMilitaryUnitEditor() {
        super();

        app6aButton = App6AMenuFactory.getUnitMenu(createApp6aFunctionIdChangedHandler());
        LayoutForm detailsForm = new VBoxLayoutForm();
        detailsForm.addRow(MESSAGES.app6a_code(), code);
        code.setEnabled(false);
        detailsForm.addRow(MESSAGES.app6a_description(), description);
        detailsForm.addRow(MESSAGES.app6a_name(), nameAdditional);
        detailsForm.addRow(MESSAGES.app6a_codingScheme(), codingScheme);
        detailsForm.addRow(MESSAGES.app6a_status(), status);
        detailsForm.addRow(MESSAGES.app6a_countryCode(), countryCode);
        detailsForm.addRow(MESSAGES.app6a_orderOfBattle(), orderOfBattle);

        LayoutForm app6aForm = new VBoxLayoutForm();
        app6aForm.addRow(MESSAGES.app6a_affiliation(), affiliation);
        affiliation
                .addSelectionHandler(GxtComponentsUtils.createDefaultSelectionHander(createApp6aCodeChangedHandler()));

        app6aForm.addRow(MESSAGES.app6a_battleDimension(), battleDimension);
        battleDimension
                .addSelectionHandler(GxtComponentsUtils.createDefaultSelectionHander(createApp6aCodeChangedHandler()));

        app6aForm.addRow(MESSAGES.app6a_mobilitySize(), mobilitySize);
        mobilitySize
                .addSelectionHandler(GxtComponentsUtils.createDefaultSelectionHander(createApp6aCodeChangedHandler()));
        app6aForm.addRow(MESSAGES.app6a_unitType(), unitType);
        unitType.addSelectionHandler(GxtComponentsUtils.createDefaultSelectionHander(createApp6aCodeChangedHandler()));

        app6aForm.addRow(MESSAGES.app6a_functionId(), app6aButton);
        app6aForm.addRowWithMargins(createImageSymbol());

        container.add(GxtComponentsUtils.createContentPanel(detailsForm), MESSAGES.app6a());
        container.add(GxtComponentsUtils.createContentPanel(app6aForm), MESSAGES.app6a_semantic());
        container.add(GxtComponentsUtils.createContentPanel(mapSymbol.asWidget()), MESSAGES.mapsymbol());
        container.add(GxtComponentsUtils.createContentPanel(point.asWidget()), MESSAGES.geopoint());

        container.setActiveWidget(container.getWidget(0));

        maxRowCount = detailsForm.getRowsCount();

        container.setAnimScroll(true);
        container.setTabScroll(true);


    }



    /**
     * @return
     */
    private SelectionHandler<Item> createApp6aFunctionIdChangedHandler() {
        return new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
                MenuItem it = (MenuItem) event.getSelectedItem();
                app6aButton.setText(it.getText());
                functionIdCode = event.getSelectedItem().getData("app6aCode");
                recalculateApp6aCode();
            }
        };
    }

    /**
     * @return
     */
    private SelectionHandler<Item> createApp6aCodeChangedHandler() {
        return new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
                recalculateApp6aCode();
            }
        };
    }

    /**
     *
     */
    protected void recalculateApp6aCode() {
        if (recalculateApp6aCodeHandler != null) {
            recalculateApp6aCodeHandler.onSelection(null);
        }

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
        return maxRowCount;
    }
}
