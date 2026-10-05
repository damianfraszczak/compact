package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.series.*;
import com.sencha.gxt.chart.client.draw.Gradient;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.Stop;
import com.sencha.gxt.chart.client.draw.sprite.Sprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite.TextAnchor;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite.TextBaseline;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Slider;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ol.GeoUtils;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtUtils;
import pl.edu.wat.wcy.cop.app.shared.response.capability.TerrainProfileCapability;
import pl.edu.wat.wcy.cop.app.shared.response.capability.TerrainProfilePoint;
// Defines terrain profile form UI.

public abstract class TerrainProfileForm {
    private static final DataPropertyAccess dataAccess = GWT.create(DataPropertyAccess.class);
    private static Messages messages = CopGinInjector.INSTANCE.getMessages();
    private static boolean DEFAULT_SHOW_MARKERS = false;
    private Dialog window;
    private TerrainProfileCapability terrainProfileCapability;
    private Slider accuracySlider;


    public TerrainProfileForm(TerrainProfileCapability terrainProfileCapability) {
        this.terrainProfileCapability = terrainProfileCapability;
        prepareUI(messages.terrainProfileDialog_heading());
        initComponents();
    }

    protected void prepareUI(String title) {
        window = new Dialog();
        window.setWidth(GxtUtils.MIN_WIDTH);
        window.setResizable(false);
        window.setHideOnButtonClick(true);
        window.setShadow(true);
        window.setPredefinedButtons();
        window.setPixelSize(600, 700);
        TextButton recalculateButton = new TextButton(messages.terrainProfileDialog_recalculateButton());
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

    protected void initComponents() {

        final ListStore<Data> store = new ListStore<Data>(dataAccess.distanceKey());

        for (TerrainProfilePoint point : terrainProfileCapability.getTerrainProfilePoints()) {
            store.add(new Data(point.getDistance(), point.getElevation(), point.getLat(), point.getLon()));
        }

        TextSprite text = new TextSprite();
        text.setFont("Arial");
        text.setFontSize(10);
        text.setTextBaseline(TextBaseline.MIDDLE);

        TextSprite elevationTitle = new TextSprite(messages.terrainProfileDialog_elevation() + " [m]");
        elevationTitle.setFontSize(18);

        NumericAxis<Data> axisOfElevation = new NumericAxis<Data>();
        axisOfElevation.setTitleConfig(elevationTitle);
        axisOfElevation.setPosition(Position.LEFT);
        axisOfElevation.addField(dataAccess.elevation());
        axisOfElevation.setDisplayGrid(true);
        axisOfElevation.setLabelConfig(text);

        text = text.copy();
        text.setFontSize(11);
        text.setTextAnchor(TextAnchor.MIDDLE);

        TextSprite distanceTitle = new TextSprite(messages.terrainProfileDialog_distance() + " [m]");
        distanceTitle.setFontSize(18);
        NumericAxis<Data> axisOfDistance = new NumericAxis<Data>();
        axisOfDistance.setTitleConfig(distanceTitle);
        axisOfDistance.setPosition(Position.BOTTOM);
        axisOfDistance.addField(dataAccess.distance());
        axisOfDistance.setDisplayGrid(true);
        axisOfDistance.setLabelConfig(text);

        Sprite marker = Primitives.circle(0, 0, 4);
        marker.setFill(new RGB(60, 60, 60));
        SeriesToolTipConfig<Data> tooltip = new SeriesToolTipConfig<Data>();
        tooltip.setLabelProvider(new SeriesLabelProvider<Data>() {
            @Override
            public String getLabel(Data item, ValueProvider<? super Data, ? extends Number> valueProvider) {
                return GeoUtils.getDMSFormat(dataAccess.lat().getValue(item), dataAccess.lon().getValue(item));
            }
        });
        tooltip.setHideDelay(2000);

        final LineSeries<Data> series = new LineSeries<Data>();
        series.setYAxisPosition(Position.LEFT);
        series.setYField(dataAccess.elevation());
        series.setXAxisPosition(Position.BOTTOM);
        series.setXField(dataAccess.distance());
        series.setHighlighting(true);
        series.setStroke(new RGB(100, 100, 100));
        series.setStrokeWidth(3);
        series.setShowMarkers(DEFAULT_SHOW_MARKERS);
        series.setMarkerConfig(marker);
        series.setToolTipConfig(tooltip);
        series.setSmooth(true);

        SeriesRenderer<Data> fillRenderer = new SeriesRenderer<Data>() {

            @Override
            public void spriteRenderer(Sprite sprite, int index, ListStore<Data> store) {
                sprite.setOpacity(0.75);
                sprite.redraw();
            }

        };
        series.setFillRenderer(fillRenderer);

        Gradient gradient = new Gradient(90);
        gradient.addStop(new Stop(0, new RGB("#f42800")));
        gradient.addStop(new Stop(25, new RGB("#fcab5a")));
        gradient.addStop(new Stop(50, new RGB("#ffee5b")));
        gradient.addStop(new Stop(75, new RGB("#c9e171")));
        gradient.addStop(new Stop(100, new RGB("#4de133")));

        series.setFill(gradient);

        final Chart<Data> chart = new Chart<Data>();
        chart.setStore(store);
        chart.setDefaultInsets(30);
        chart.setShadowChart(false);
        chart.addAxis(axisOfDistance);
        chart.addAxis(axisOfElevation);
        chart.addGradient(gradient);
        chart.addSeries(series);

        ValueChangeHandler<Boolean> valueChangeHandler = new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                series.setShowMarkers(event.getValue());
                chart.redrawChart();
            }
        };

        CheckBox checkBox = new CheckBox();
        checkBox.setValue(DEFAULT_SHOW_MARKERS);
        checkBox.setBoxLabel(messages.terrainProfileDialog_showMarkers());
        checkBox.addValueChangeHandler(valueChangeHandler);

        checkBox.getElement().getFirstChildElement().getStyle().setProperty("textAlign", "center");

        accuracySlider = new Slider();
        accuracySlider.setIncrement(1);
        accuracySlider.setMessage("{0}%");
        Double accuracy = terrainProfileCapability.getAccuracy();
        if (accuracy == null) {
            accuracy = 0.5;
        }
        accuracySlider.setValue((int) Math.round(accuracy * 100));

        FieldLabel fieldLabel = new FieldLabel(accuracySlider, messages.terrainProfileDialog_accuracy());
        fieldLabel.setLabelWidth(GxtUtils.MIN_WIDTH / 3);
        VerticalLayoutContainer layout = new VerticalLayoutContainer();

        layout.add(checkBox, new VerticalLayoutData(1, -1, new Margins(10, 5, 0, 5)));
        layout.add(chart, new VerticalLayoutData(1, 1));
        layout.add(fieldLabel, new VerticalLayoutData(1, -1, new Margins(0, 5, 5, 5)));

        ContentPanel panel = new ContentPanel();
        panel.setHeaderVisible(false);
        panel.add(layout);

        window.setWidget(panel);
        window.show();
    }

    protected abstract void recalculate();

    public void hide() {
        window.hide();
    }

    public Integer getAccuracySliderValue() {
        return accuracySlider.getValue();
    }

    public interface DataPropertyAccess extends PropertyAccess<Data> {
        ValueProvider<Data, Integer> elevation();

        ValueProvider<Data, Double> distance();

        ValueProvider<Data, Double> lat();

        ValueProvider<Data, Double> lon();

        @Path("id")
        ModelKeyProvider<Data> distanceKey();
    }
}

class Data {
    private static int lastId = 0;
    private String id = "" + lastId++;

    private Double distance;
    private Integer elevation;
    private Double lat;
    private Double lon;

    public Data(Double distance, Integer elevation, Double lat, Double lon) {
        super();
        this.distance = distance;
        this.elevation = elevation;
        this.lat = lat;
        this.lon = lon;
    }

    public Integer getElevation() {
        return elevation;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

}
