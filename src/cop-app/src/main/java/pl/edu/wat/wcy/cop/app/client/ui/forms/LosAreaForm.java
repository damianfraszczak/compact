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
import pl.edu.wat.wcy.cop.app.shared.response.capability.LosAreaCapability;
// Defines los area form UI.

public abstract class LosAreaForm {
    private static Messages messages = CopGinInjector.INSTANCE.getMessages();
    private Dialog window;
    private VBoxLayoutContainer container = new VBoxLayoutContainer(VBoxLayoutAlign.STRETCH);
    private BoxLayoutData data = new BoxLayoutData(new Margins(5, 5, 0, 5));
    private LosAreaCapability losAreaCapability;
    private Slider observerHeightSlider;
    private Slider accuracySlider;

    public LosAreaForm(LosAreaCapability losAreaCapability) {
        this.losAreaCapability = losAreaCapability;
        prepareUI(messages.losAreaDialog_heading());
        initComponents();
    }

    protected void prepareUI(String title) {
        window = new Dialog();
        window.setWidth(GxtUtils.MIN_WIDTH);
        window.setResizable(false);
        window.setHideOnButtonClick(true);
        window.setShadow(true);
        window.setPredefinedButtons();
        TextButton recalculateButton = new TextButton(messages.losAreaDialog_recalculateButton());
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
        Double lat = losAreaCapability.getLat();
        Double lon = losAreaCapability.getLon();
        Double radius = losAreaCapability.getRadius();
        Double accuracy = losAreaCapability.getAccuracy();
        Integer observerHeight = losAreaCapability.getObserverHeight();

        Label positionLabel = new Label(GeoUtils.getDMSFormat(lat, lon));
        addRow(messages.losAreaDialog_centerPoint(), positionLabel);

        Coordinate center = OLFactory.createCoordinate(lon, lat);
        Circle circle = OLFeatureBuilder.createCircle(center, radius);
        double radiusInMeters = GeoUtils.getRadiusInMeters(circle);
        Label radiusLabel = new Label(MeasuresPresentationUtils.getLengthString(radiusInMeters));
        addRow(messages.losAreaDialog_radius(), radiusLabel);

        Label losAreaLabel = new Label(MeasuresPresentationUtils.getAreaString(losAreaCapability.getLosArea()));
        addRow(messages.losAreaDialog_losArea(), losAreaLabel);

        accuracySlider = new Slider();
        accuracySlider.setIncrement(1);
        accuracySlider.setValue((int) Math.round(accuracy * 100));
        accuracySlider.setMessage("{0}%");
        addRow(messages.losAreaDialog_accuracy(), accuracySlider);

        observerHeightSlider = new Slider();
        observerHeightSlider.setIncrement(1);
        observerHeightSlider.setValue(observerHeight);
        observerHeightSlider.setMessage("{0} m");
        addRow(messages.losAreaDialog_observerHeight(), observerHeightSlider);

        window.setWidget(container);
        window.show();
    }

    public Integer getObserverHeightSliderValue() {
        return observerHeightSlider.getValue();
    }

    public Integer getAccuracySliderValue() {
        return accuracySlider.getValue();
    }

    protected abstract void recalculate();

    public void hide() {
        window.hide();
    }


}
