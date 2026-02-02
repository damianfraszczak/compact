/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms.editors;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiField;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.ui.forms.VBoxLayoutForm;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.domain.CrisisEventCricleZoneDto;
// Edits crisis event cricle zone settings.


public class CrisisEventCricleZoneEditor extends VBoxLayoutForm implements Editor<CrisisEventCricleZoneDto> {

    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    @UiField
    TextField name = FormUtils.createRequiredTextField();
    @UiField
    TextField description = FormUtils.createRequiredTextField();
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
    public CrisisEventCricleZoneEditor() {
        super();
        addRow(MESSAGES.crisisevent_zone_name(), name);
        addRow(MESSAGES.crisisevent_zone_description(), description);
        addRow(MESSAGES.crisisevent_zone_visible(), visible);
        addRow(MESSAGES.crisisevent_zone_radius(), radius);

        addRow(point.asWidget());
        addRow(style.asWidget());
    }

}
