package pl.edu.wat.wcy.cop.app.client.ui.forms.editors;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ui.forms.VBoxLayoutForm;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtListStoreEditor;
import pl.edu.wat.wcy.cop.app.client.visualisation.grid.HumanResourceGridPanel;
import pl.edu.wat.wcy.cop.app.client.visualisation.grid.TechnicalResourceGridPanel;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchUnitDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.HumanResourceDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.TechnicalResourceDto;
// Edits search unit settings.

public class SearchUnitEditor implements Editor<SearchUnitDto>, IsWidget {
    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();


    @UiField
    TextField name = FormUtils.createRequiredTextField();
//    @UiField
//    ComboBox<SearchUnitType> type = FormUtils.createEnumComboBox(SearchUnitType.class);
    @UiField
    NumberField<Double> sweepWidth = FormUtils.createDoubleField(true);
    @UiField
    NumberField<Double> sweepSpeed = FormUtils.createDoubleField(true);

    @UiField
    Grid<HumanResourceDto> humanResourcesGrid;
    GxtListStoreEditor<HumanResourceDto> humanResources;

    @UiField
    Grid<TechnicalResourceDto> technicalResourcesGrid;
    GxtListStoreEditor<TechnicalResourceDto> technicalResources;


    @Ignore
    TabPanel container = new TabPanel();

    public SearchUnitEditor() {
        super();
        VBoxLayoutForm form = new VBoxLayoutForm();
        form.addRow("Nazwa", name);

        form.addRow("Sweep width", sweepWidth);
        form.addRow("Sweep speed", sweepSpeed);
        container.add(GxtComponentsUtils.createContentPanel(form), "Dane grupy poszukiwawczej");


        HumanResourceGridPanel humanResourceGridPanel = new HumanResourceGridPanel();
        humanResourceGridPanel.setCanEditObjects(false);
        humanResourcesGrid = humanResourceGridPanel.getGrid();
        humanResources = humanResourceGridPanel.getStore();
        container.add(GxtComponentsUtils.createContentPanel(humanResourceGridPanel), "Zasoby ludzkie");


        TechnicalResourceGridPanel technicalResourceGridPanel = new TechnicalResourceGridPanel();
        technicalResourceGridPanel.setCanEditObjects(false);
        technicalResourcesGrid = technicalResourceGridPanel.getGrid();
        technicalResources = technicalResourceGridPanel.getStore();
        container.add(GxtComponentsUtils.createContentPanel(technicalResourceGridPanel), "Zasoby sprzętowe");

        container.setActiveWidget(container.getWidget(0));
        container.setTabScroll(true);
        container.setBodyBorder(false);
    }

    @Override
    public Widget asWidget() {
        return container;
    }

    public int getRowsCount() {
        return 6;
    }
}
