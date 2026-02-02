package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Slider;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import ol.Coordinate;
import ol.OLFactory;
import ol.geom.Circle;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ol.GeoUtils;
import pl.edu.wat.wcy.cop.app.client.ol.OLFeatureBuilder;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.utils.MeasuresPresentationUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtUtils;
import pl.edu.wat.wcy.cop.app.shared.response.capability.FloodAreaCapability;
// Defines flood area form UI.

public abstract class FloodAreaForm {
    private static Messages messages = CopGinInjector.INSTANCE.getMessages();
    private Dialog window;
    private VBoxLayoutContainer container = new VBoxLayoutContainer(VBoxLayoutAlign.STRETCH);
    private BoxLayoutData data = new BoxLayoutData(new Margins(5, 5, 0, 5));
    private FloodAreaCapability floodAreaCapability;
    private Slider floodHeightSlider;
    private Slider accuracySlider;

    public FloodAreaForm(FloodAreaCapability floodAreaCapability) {
        this.floodAreaCapability = floodAreaCapability;
        prepareUI(messages.floodAreaDialog_heading());
        initComponents();
    }

    protected void prepareUI(String title) {
        window = new Dialog();
        window.setWidth(GxtUtils.MIN_WIDTH);
        window.setResizable(false);
        window.setHideOnButtonClick(true);
        window.setShadow(true);
        window.setPredefinedButtons();
        TextButton recalculateButton = new TextButton(messages.floodAreaDialog_recalculateButton());
        recalculateButton.addSelectHandler(new SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                recalculate();
            }
        });
        window.addButton(recalculateButton);
        if (title != null) {
            window.setTitle(title);
            window.setHeading(title);
        }
    }

    protected void addRow(String label, Widget widget) {
        FieldLabel flabel = new FieldLabel(widget, label);
        flabel.setLabelWidth(GxtUtils.MIN_WIDTH / 3);
        container.add(flabel, data);
    }

    protected void initComponents() {
        Double lat = floodAreaCapability.getLat();
        Double lon = floodAreaCapability.getLon();
        Double radius = floodAreaCapability.getRadius();
        Double accuracy = floodAreaCapability.getAccuracy();
        Integer floodHeight = floodAreaCapability.getFloodHeight();

        Label positionLabel = new Label(GeoUtils.getDMSFormat(lat, lon));
        addRow(messages.floodAreaDialog_centerPoint(), positionLabel);

        Coordinate center = OLFactory.createCoordinate(lon, lat);
        Circle circle = OLFeatureBuilder.createCircle(center, radius);
        double radiusInMeters = GeoUtils.getRadiusInMeters(circle);
        Label radiusLabel = new Label(MeasuresPresentationUtils.getLengthString(radiusInMeters));
        addRow(messages.floodAreaDialog_radius(), radiusLabel);

        Label minElevationLabel = new Label(floodAreaCapability.getMinElevation() + " m.n.p.m.");
        addRow(messages.floodAreaDialog_minElevation(), minElevationLabel);

        Label floodAreaLabel = new Label(MeasuresPresentationUtils.getAreaString(floodAreaCapability.getFloodArea()));
        addRow(messages.floodAreaDialog_floodArea(), floodAreaLabel);

        Label floodVolumeLabel = new Label(
                MeasuresPresentationUtils.getVolumeString(floodAreaCapability.getFloodVolume()));
        addRow(messages.floodAreaDialog_floodVolume(), floodVolumeLabel);

        accuracySlider = new Slider();
        accuracySlider.setIncrement(1);
        accuracySlider.setValue((int) Math.round(accuracy * 100));
        accuracySlider.setMessage("{0}%");
        addRow(messages.floodAreaDialog_accuracy(), accuracySlider);

        floodHeightSlider = new Slider();
        floodHeightSlider.setIncrement(1);
        floodHeightSlider.setValue(floodHeight);
        floodHeightSlider.setMessage("{0} m");
        addRow(messages.floodAreaDialog_floodHeight(), floodHeightSlider);

        window.setWidget(container);
        window.show();
    }

    public Integer getFloodHeightSliderValue() {
        return floodHeightSlider.getValue();
    }

    public Integer getAccuracySliderValue() {
        return accuracySlider.getValue();
    }

    protected abstract void recalculate();

    public void hide() {
        window.hide();
    }

}
