/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms.editors;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiField;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.TextField;
import ol.Coordinate;
import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ol.GeoUtils;
import pl.edu.wat.wcy.cop.app.client.ol.OLCoordsFormatter;
import pl.edu.wat.wcy.cop.app.client.services.LocalCache;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractEditor;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm;
import pl.edu.wat.wcy.cop.app.client.utils.*;
import pl.edu.wat.wcy.cop.app.client.utils.gui.DialogUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.NotificationUtils;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.domain.GeoPointDto;
import pl.edu.wat.wcy.cop.app.shared.ol.OLCordsFormattingType;

import java.util.List;
// Edits geo point settings.

public class GeoPointEditor extends AbstractEditor<GeoPointDto> {
    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    @UiField
    NumberField<Double> lat = FormUtils.createRangeDoubleField(-90, 90);
    @UiField
    NumberField<Double> lon = FormUtils.createRangeDoubleField(-180, 180);


    private boolean editable = true;

    public GeoPointEditor() {
        super();
        addRow(MESSAGES.geopoint_lat(), lat);
        addRow(MESSAGES.geopoint_lon(), lon);
        for (OLCordsFormattingType format : OLCordsFormattingType.values()) {
            final TextField field = new TextField();
            field.setEnabled(false);
            addRow(MessagesUtils.coordsFormatToString(format), field);
            ValueChangeHandler<Double> handler = new ValueChangeHandler<Double>() {
                @Override
                public void onValueChange(ValueChangeEvent<Double> valueChangeEvent) {
                    if (lat.getValue() != null && lon.getValue() != null) {
                        field.setText(OLCoordsFormatter.format(format, GeoUtils.createCoordinate(lat.getValue(), lon.getValue())));
                    }

                }
            };
            lat.addValueChangeHandler(handler);
            lon.addValueChangeHandler(handler);
        }
        GwtScheduler.delay(200, () -> {
            ValueChangeEvent.fire(lat, lat.getValue());
        });
        if (CopAppUtils.isVisualisationView()) {
            TextButton putOnMap = GxtComponentsUtils.createTextButton("Set on map", new SelectHandler() {

                @Override
                public void onSelect(SelectEvent event) {
                    List<AbstractForm> visibleForms = AbstractForm.getVisibleForms();
                    visibleForms.forEach(x -> x.hide());
                    NotificationUtils.showToast("Drawing mode", "You set a point. Double-click to finish editing.");
                    CopGinInjector.INSTANCE.getMapManager().drawFeatrue("Point", new DefaultAsyncCallback<Feature>() {
                        @Override
                        public void onSuccess(Feature feature) {
                            Coordinate[] coords = GeoUtils.getCoordinatesOfFeature(feature);
                            GeoPointDto p = GeoUtils.createGeoPointsFromMapCoordinates(coords[0]);
                            lat.setValue(p.getLat());
                            lon.setValue(p.getLon());
                            ValueChangeEvent.fire(lat, lat.getValue());
                            visibleForms.forEach(x -> x.show());
                        }
                    });

                }
            }, null);

            TextButton copyFromClipboard = GxtComponentsUtils.createTextButton(MESSAGES.geopoint_copy(), new SelectHandler() {

                @Override
                public void onSelect(SelectEvent event) {
                    double latValue = ParsingUtils.parseDouble(LocalCache.getInstance().get(AppConstants.COPIED_LAT));
                    double lonValue = ParsingUtils.parseDouble(LocalCache.getInstance().get(AppConstants.COPIED_LON));
                    if (ParsingUtils.isValid(latValue) && ParsingUtils.isValid(lonValue)) {
                        lat.setValue(latValue);
                        lon.setValue(lonValue);
                        ValueChangeEvent.fire(lat, latValue);
                    } else {
                        DialogUtils.showErrorDialog_NotSelected();
                    }
                }
            }, null);

            addRow(FormUtils.createHorizontalPanelWith2Elements(copyFromClipboard, putOnMap, 0.5, 0.5, new Margins(1), new Margins(1)));


        }

    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.lat.setEditable(editable);
        this.lon.setEditable(editable);
    }

}
