/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms.editors;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiField;
import com.sencha.gxt.widget.core.client.form.*;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ui.forms.VBoxLayoutForm;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.domain.PoiCircleZoneDto;
// Edits poi circle zone settings.


public class PoiCircleZoneEditor extends VBoxLayoutForm implements Editor<PoiCircleZoneDto> {

    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    @UiField
    TextField name = FormUtils.createRequiredTextField();
    @UiField
    HtmlEditor description = FormUtils.createHtmlEditor();
    @UiField
    CheckBox visible = new CheckBox();
    @UiField
    GeoPointEditor point = GWT.create(GeoPointEditor.class);
    @UiField
    NumberField<Double> radius = new NumberField<Double>(new NumberPropertyEditor.DoublePropertyEditor(AppConstants.DEFAULT_NUMBER_FORMAT));
    @UiField
    public FeatureStyleEditor style = GWT.create(FeatureStyleEditor.class);


    /**
     *
     */
    public PoiCircleZoneEditor() {
        super();
        addRow(MESSAGES.poi_zone_name(), name);
        addRow(MESSAGES.poi_zone_description(), description);
        addRow(MESSAGES.poi_zone_visible(), visible);
        addRow(MESSAGES.poi_zone_radius(), radius);
        addRow(point.asWidget());
        addRow(style.asWidget());
    }



}
