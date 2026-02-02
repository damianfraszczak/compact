package pl.edu.wat.wcy.cop.app.client.ui.forms.editors;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.form.*;
import com.sencha.gxt.widget.core.client.grid.Grid;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ui.forms.VBoxLayoutForm;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtListStoreEditor;
import pl.edu.wat.wcy.cop.app.client.visualisation.grid.*;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueCircleZoneDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueSegmentDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchUnitDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.PersonBehaviour;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.SearchAndRescueAlgorithm;
// Edits search and rescue settings.

public class SearchAndRescueEditor implements Editor<SearchAndRescueDto>, IsWidget {
    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();


    @UiField
    GeoPointEditor point = GWT.create(GeoPointEditor.class);

    @UiField
    TextField name = FormUtils.createRequiredTextField();
    @UiField
    HtmlEditor description = FormUtils.createHtmlEditor();
    @UiField
    ComboBox<PersonBehaviour> personBehaviour = FormUtils.createEnumComboBox(PersonBehaviour.class);
    @UiField
    ComboBox<SearchAndRescueAlgorithm> algorithm = FormUtils.createEnumComboBox(SearchAndRescueAlgorithm.class);
    @UiField
    CheckBox mountainTerrain = new CheckBox();
    @UiField
    public FeatureStyleEditor style = GWT.create(FeatureStyleEditor.class);

    @UiField
    IntegerSpinnerField age = FormUtils.createIntegerSpinnerField(false);
    @UiField
    public Grid<SearchAndRescueCircleZoneDto> circleZonesGrid;
    public GxtListStoreEditor<SearchAndRescueCircleZoneDto> circleZones;

    @Ignore
    public SearchAndRescueSegmentGrid areaZoneTreeGridPanel;
    @UiField
    public Grid<SearchAndRescueSegmentDto> areaZonesGrid;
    public GxtListStoreEditor<SearchAndRescueSegmentDto> areaZones;

    @UiField
    Grid<SearchUnitDto> searchUnitsGrid;
    GxtListStoreEditor<SearchUnitDto> searchUnits;

//    @Ignore
//    public SearchAndRescuePlansEditorWithAccordion searchAndRescueSearchPlansWrapper;




    @UiField
    GeoPointEditor directionPoint = GWT.create(GeoPointEditor.class);
    @UiField
    public FeatureStyleEditor directionStyle = GWT.create(FeatureStyleEditor.class);
//
//    @UiField
//    public Grid<TechnicalResourceDto> technicalResourcesGrid;
//    public GxtListStoreEditor<TechnicalResourceDto> technicalResources;

    @Ignore
    TabPanel directionContainer = new TabPanel();
    //    @UiField
//    Grid<SearchUnitDto> searchUnitsGrid;
//    GxtListStoreEditor<SearchUnitDto> searchUnits;
    @Ignore
    TabPanel container = new TabPanel();


    public SearchAndRescueEditor() {
        super();
        VBoxLayoutForm form = new VBoxLayoutForm();
        form.addRow("Name", name);
        form.addRow("Description", description);
        form.addRow("Missing age", age);
        age.setMinValue(0);

        form.addRow("Behaviour", personBehaviour);
//        form.addRow("Algorithm", algorithm);
        form.addRow("Mountain terrain", mountainTerrain);


        container.add(GxtComponentsUtils.createContentPanel(form), "Basic information");
        directionContainer.add(GxtComponentsUtils.createContentPanel(directionPoint), "Position");
        directionContainer.add(GxtComponentsUtils.createContentPanel(directionStyle), "Graphics");

        container.add(GxtComponentsUtils.createContentPanel(directionContainer), "Search Direction Data");
        container.add(GxtComponentsUtils.createContentPanel(point.asWidget()), "IPP");


        SearchAndRescueCircleZoneGrid circleZoneTreeGridPanel = new SearchAndRescueCircleZoneGrid();
        circleZoneTreeGridPanel.setCanEditObjects(false);
        circleZonesGrid = circleZoneTreeGridPanel.getGrid();
        circleZones = circleZoneTreeGridPanel.getStore();
        container.add(GxtComponentsUtils.createContentPanel(circleZoneTreeGridPanel), "Circular zones");


         areaZoneTreeGridPanel = new SearchAndRescueSegmentGrid();

        areaZoneTreeGridPanel.setCanEditObjects(false);

        areaZonesGrid = areaZoneTreeGridPanel.getGrid();
        areaZones = areaZoneTreeGridPanel.getStore();
        container.add(GxtComponentsUtils.createContentPanel(areaZoneTreeGridPanel), "Sectors");

        SearchUnitGridPanel searchUnitTreeGridPanel = new SearchUnitGridPanel();
        searchUnitTreeGridPanel.setCanEditObjects(false);
        searchUnitsGrid = searchUnitTreeGridPanel.getGrid();
        searchUnits = searchUnitTreeGridPanel.getStore();
        // container.add(GxtComponentsUtils.createContentPanel(searchUnitTreeGridPanel), "Grupy poszukiwawcze");


//        TechnicalResourceGridPanel technicalResourceGridPanel = new TechnicalResourceGridPanel();
//        technicalResourceGridPanel.setCanEditObjects(false);
//        technicalResourcesGrid = technicalResourceGridPanel.getGrid();
//        technicalResources = technicalResourceGridPanel.getStore();
//        container.add(GxtComponentsUtils.createContentPanel(technicalResourceGridPanel), "Zasoby sprzętowe");

//        searchAndRescueSearchPlansWrapper = new SearchAndRescuePlansEditorWithAccordion();
//        container.add(GxtComponentsUtils.createContentPanel(searchAndRescueSearchPlansWrapper.asWidget()), "Search plans");


        //        GpxTraceGrid tracesTreeGridPanel = new GpxTraceGrid();
//        tracesTreeGridPanel.setCanEditObjects(false);
//        tracesGrid = tracesTreeGridPanel.getGrid();
//        traces = tracesTreeGridPanel.getStore();
//        container.add(GxtComponentsUtils.createContentPanel(tracesTreeGridPanel), "Slady GPX");

        container.add(GxtComponentsUtils.createContentPanel(style.asWidget()), "Graphics");
        container.setActiveWidget(container.getWidget(0));
        container.setTabScroll(true);
        container.setBodyBorder(false);
    }

    @Override
    public Widget asWidget() {
        return container;
    }

    public int getRowsCount() {
        return 14;
    }
}
