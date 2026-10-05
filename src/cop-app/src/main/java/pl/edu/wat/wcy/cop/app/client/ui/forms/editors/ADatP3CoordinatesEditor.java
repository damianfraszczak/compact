package pl.edu.wat.wcy.cop.app.client.ui.forms.editors;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellBrowser;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import ol.Coordinate;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractEditor;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.CBRNType;
import pl.edu.wat.wcy.cop.app.shared.domain.ADatP3CoordinatesDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
// Edits a dat 3 coordinates settings.

public class ADatP3CoordinatesEditor extends AbstractEditor<ADatP3CoordinatesDto> {
    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    @UiField
    public TextArea featureType = FormUtils.createRequiredTextArea();
    @UiField
    TextArea cbrnType = FormUtils.createRequiredTextArea();
    @UiField
    TextArea lat = FormUtils.createRequiredTextArea();
    @UiField
    TextArea lon = FormUtils.createRequiredTextArea();
    @UiField
    TextArea dateOfIncydent = FormUtils.createRequiredTextArea();
    @UiField
    TextArea releaseAresRadius = FormUtils.createRequiredTextArea();
    @UiField
    TextArea hazardAreaDistance = FormUtils.createRequiredTextArea();
    @UiField
    TextArea hazardAreaDistanceR1 = FormUtils.createRequiredTextArea();
    @UiField
    TextArea hazardAreaDistanceR2 = FormUtils.createRequiredTextArea();
    @UiField
    TextArea hazardAreaDistanceR3 = FormUtils.createRequiredTextArea();

    /**
     *
     */
    public ADatP3CoordinatesEditor() {
        super();
        addRow("Type", featureType);
    }

    public void setRows() {
        setReadOnly();
        addRow(MESSAGES.report_cbrnType(), cbrnType);
        addRow(MESSAGES.report_lattitude(), lat);
        addRow(MESSAGES.report_longitude(), lon);
        addRow(MESSAGES.report_dateOfIncydent(), dateOfIncydent);
        if (releaseAresRadius.getCurrentValue() != null)
            addRow(MESSAGES.report_releaseArea(), releaseAresRadius);

        if (cbrnType.getCurrentValue().equals(CBRNType.BIO.getName()) || cbrnType.getCurrentValue().equals(CBRNType.CHEM.getName())) {
            addRow(MESSAGES.report_hazard1(), hazardAreaDistance);
        } else {
            addRow(MESSAGES.report_hazard1(), hazardAreaDistanceR1);
            addRow(MESSAGES.report_hazard2(), hazardAreaDistanceR2);
            addRow(MESSAGES.report_hazard3(), hazardAreaDistanceR3);
        }
    }

    public void setCoordinates(HashMap<String, List<String>> mgrsCoordinates, HashMap<String, List<Coordinate>> lonLatCoordinates) {
        if (mgrsCoordinates.get("0").size() > 1) {
            this.getContainer().setHeight("500px");
            TreeViewModel model = new CoordinatesTreeModel(mgrsCoordinates, lonLatCoordinates);
            CellBrowser browser = new CellBrowser(model, null);
            browser.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
            browser.setHeight("600px");
            browser.setWidth("800px");

            ScrollPanel scrollPanel = new ScrollPanel();
            scrollPanel.getElement().setAttribute("style", "width:400px;height:300px;border:1px solid #B5BBC8; overflow:scroll");
            scrollPanel.add(browser);

            addRow("Koordynaty", scrollPanel);
        }
    }

    private void setReadOnly() {
        featureType.setReadOnly(true);
        cbrnType.setReadOnly(true);
        lat.setReadOnly(true);
        lon.setReadOnly(true);
        dateOfIncydent.setReadOnly(true);
        releaseAresRadius.setReadOnly(true);
        hazardAreaDistance.setReadOnly(true);
        hazardAreaDistanceR1.setReadOnly(true);
        hazardAreaDistanceR2.setReadOnly(true);
        hazardAreaDistanceR3.setReadOnly(true);
    }

    private static class CoordinateList {
        private String name;
        private List<String> coordinates = new ArrayList<>();

        public CoordinateList(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<String> coordinates) {
            this.coordinates = coordinates;
        }

        public void addCoordinate(String coordinate) {
            this.coordinates.add(coordinate);
        }
    }

    private static class HazardAreasList {
        private String name;
        private List<CoordinateList> hazardArea = new ArrayList<>();

        public HazardAreasList(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<CoordinateList> getHazardArea() {
            return hazardArea;
        }

        public CoordinateList addHazardArea(CoordinateList hazardArea) {
            this.hazardArea.add(hazardArea);
            return hazardArea;
        }
    }

    private static class CoordinatesTreeModel implements TreeViewModel {

        private final SingleSelectionModel<String> selectionModel
                = new SingleSelectionModel<String>();
        private List<HazardAreasList> hazardAreasLists = new ArrayList<>();

        public CoordinatesTreeModel(HashMap<String, List<String>> mgrsAdatP3, HashMap<String, List<Coordinate>> lonLatAdatP3) {
            this.hazardAreasLists = new ArrayList<HazardAreasList>();
            {
                HazardAreasList mgrs = new HazardAreasList("MGRS points");
                hazardAreasLists.add(mgrs);
                for (int i = 0; i < mgrsAdatP3.size(); i++) {
                    CoordinateList points = mgrs.addHazardArea(new CoordinateList(String.valueOf(i)));
                    for (String s : mgrsAdatP3.get(String.valueOf(i))) {
                        points.addCoordinate(s);
                    }
                }
                HazardAreasList lonLat = new HazardAreasList("Lon lat points");
                hazardAreasLists.add(lonLat);
                for (int i = 0; i < lonLatAdatP3.size(); i++) {
                    CoordinateList points = lonLat.addHazardArea(new CoordinateList(String.valueOf(i)));
                    for (Coordinate s : lonLatAdatP3.get(String.valueOf(i))) {
                        points.addCoordinate(s.getY() + " " + s.getX());
                    }
                }
            }

        }

        @Override
        public <T> NodeInfo<?> getNodeInfo(T value) {
            if (value == null) {
                // LEVEL 0.
                ListDataProvider<HazardAreasList> dataProvider
                        = new ListDataProvider<HazardAreasList>(hazardAreasLists);

                Cell<HazardAreasList> cell = new AbstractCell<HazardAreasList>() {
                    @Override
                    public void render(Context context, HazardAreasList hazardAreasList, SafeHtmlBuilder safeHtmlBuilder) {
                        safeHtmlBuilder.appendEscaped(hazardAreasList.getName());
                    }

                };

                // Return a node info that pairs the data provider and the cell.
                return new DefaultNodeInfo<HazardAreasList>(dataProvider, cell);
            } else if (value instanceof HazardAreasList) {
                // LEVEL 1.
                ListDataProvider<CoordinateList> dataProvider
                        = new ListDataProvider<CoordinateList>(
                        ((HazardAreasList) value).getHazardArea());
                Cell<CoordinateList> cell = new AbstractCell<CoordinateList>() {
                    @Override
                    public void render(Context context, CoordinateList coordinateList, SafeHtmlBuilder safeHtmlBuilder) {
                        if (value != null) {
                            safeHtmlBuilder.appendEscaped(String.valueOf(coordinateList.getName()));
                        }
                    }
                };
                return new DefaultNodeInfo<CoordinateList>(dataProvider, cell);
            } else if (value instanceof CoordinateList) {
                // LEVEL 2 - LEAF.
                ListDataProvider<String> dataProvider
                        = new ListDataProvider<String>(
                        ((CoordinateList) value).getCoordinates());

                // Use the shared selection model.
                return new DefaultNodeInfo<String>(dataProvider, new TextCell(),
                        selectionModel, null);
            }
            return null;
        }

        @Override
        public boolean isLeaf(Object o) {
            return o instanceof String;
        }
    }
}
