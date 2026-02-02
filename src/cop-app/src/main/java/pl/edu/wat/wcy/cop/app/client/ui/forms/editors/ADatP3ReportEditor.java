/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms.editors;

import com.google.gwt.uibinder.client.UiField;
import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.InfoConfig;
import ol.Coordinate;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ol.GeoUtils;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractEditor;
import pl.edu.wat.wcy.cop.app.client.utils.DateUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.NotificationUtils;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.ADatP3;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.ADatP3Parser;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.CBRNType;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.Papa;
import pl.edu.wat.wcy.cop.app.shared.domain.ADatP3ReportDto;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// Edits a dat 3 report settings.


public class ADatP3ReportEditor extends AbstractEditor<ADatP3ReportDto> {
    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();


    @UiField
    TextArea content = new TextArea();

    @Ignore
    TextField cbrnType = new TextField();
    @Ignore
    TextField mapCenter = new TextField();
    @Ignore
    TextField papa = new TextField();
    @Ignore
    TextArea lonLatCoordinates = new TextArea();
    @Ignore
    TextField delta = new TextField();

    /**
     *
     */
    public ADatP3ReportEditor() {
        super();
        content.setHeight(120);
        lonLatCoordinates.setHeight(120);
        cbrnType.disable();
        mapCenter.disable();
        papa.disable();
        lonLatCoordinates.disable();
        delta.disable();

        addRow(MESSAGES.report_content(), content);

        ButtonBar buttonBar = new ButtonBar();
        buttonBar.setPack(BoxLayoutContainer.BoxLayoutPack.END);
        buttonBar.add(new TextButton(MESSAGES.report_parseContent(), e -> fillFields(content.getValue())));
        addRow(buttonBar);

        addRow(MESSAGES.report_cbrnType(), cbrnType);
        addRow(MESSAGES.report_mapCenter(), mapCenter);
        addRow(MESSAGES.report_papa(), papa);
        addRow(MESSAGES.report_lonLatCoordinates(), lonLatCoordinates);
        addRow(MESSAGES.report_delta(), delta);
    }

    private void fillFields(String value) {
        ADatP3 aDatP3 = getADatP3(value);

        fillCbrnType(aDatP3);
        fillMapCenter(aDatP3);
        fillPapa(aDatP3);
        fillLonLatCoordinates(aDatP3);
        fillDelta(aDatP3);
    }

    private void fillDelta(ADatP3 aDatP3) {
        Date delta = aDatP3.getDelta();
        if (delta != null) {
            this.delta.setText(DateUtils.formatDate(delta));
        } else {
            this.delta.setText("");
        }
    }

    private void fillLonLatCoordinates(ADatP3 aDatP3) {
        HashMap<String, List<Coordinate>> lonLatCoordinates = aDatP3.getLonLatCoordinates();
        if (lonLatCoordinates != null) {
            StringBuilder lonLatCoordinatesStringBuilder = new StringBuilder();
            for (Map.Entry<String, List<Coordinate>> entry : lonLatCoordinates.entrySet()) {
                if (lonLatCoordinatesStringBuilder.length() > 0) {
                    lonLatCoordinatesStringBuilder.append("\n");
                }
                lonLatCoordinatesStringBuilder.append(entry.getKey() + ": [\n");
                for (Coordinate coordinate : entry.getValue()) {
                    lonLatCoordinatesStringBuilder.append("\t" + GeoUtils.getDMSFormat(coordinate) + "\n");
                }
                lonLatCoordinatesStringBuilder.append("]");
            }
            this.lonLatCoordinates.setText(lonLatCoordinatesStringBuilder.toString());
        } else {
            this.lonLatCoordinates.setText("");
        }
    }

    private void fillPapa(ADatP3 aDatP3) {
        Papa papa = aDatP3.getPapa();
        if (papa != null) {
            this.papa.setText(papa.toString());
        } else {
            this.papa.setText("");
        }
    }

    private void fillMapCenter(ADatP3 aDatP3) {
        Coordinate mapCenter = aDatP3.getMapCenter();
        if (mapCenter != null) {
            this.mapCenter.setText(GeoUtils.getDMSFormat(mapCenter));
        } else {
            this.mapCenter.setText("");
        }
    }

    private void fillCbrnType(ADatP3 aDatP3) {
        CBRNType cbrnType = aDatP3.getCbrnType();
        if (cbrnType != null) {
            this.cbrnType.setText(cbrnType.getName());
        } else {
            this.cbrnType.setText("");
        }
    }

    private ADatP3 getADatP3(String value) {
        try {
            return new ADatP3Parser().parseADatP3Content(value);
        } catch (Throwable e) {
            NotificationUtils.showToast("Error", "Can not parse AdatP-3 report. Message : " + e.getMessage(),
                    InfoConfig.InfoPosition.TOP_RIGHT, 5000);
            return new ADatP3();
        }
    }

}
