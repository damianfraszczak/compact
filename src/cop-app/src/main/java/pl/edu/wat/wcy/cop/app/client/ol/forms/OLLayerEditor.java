package pl.edu.wat.wcy.cop.app.client.ol.forms;

import com.google.gwt.uibinder.client.UiField;
import com.sencha.gxt.widget.core.client.Slider;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractEditor;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
// Edits ol layer settings.


public class OLLayerEditor<T extends OLLayer> extends AbstractEditor<T> {
    @UiField
    TextField name = FormUtils.createRequiredTextField();
    @UiField
    CheckBox visible = new CheckBox();
    @Ignore
    Slider opacity = new Slider();

    public OLLayerEditor() {
        this(CopGinInjector.INSTANCE.getMessages());
    }

    /**
     *
     */
    public OLLayerEditor(Messages messages) {
        super();
        addRow(messages.layerdialog_name(), name);
        addRow(messages.layerdialog_opacity(), GxtComponentsUtils.createSliderWithMessage(opacity));
        addRow(messages.layerdialog_visible(), visible);
    }


}
