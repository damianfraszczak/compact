package pl.edu.wat.wcy.cop.app.client.ui.forms.editors;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiField;
import com.sencha.gxt.widget.core.client.Slider;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.TextField;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractEditor;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.FeatureStyleDto;

import static com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.IntegerPropertyEditor;
// Edits feature style settings.

public class FeatureStyleEditor extends AbstractEditor<FeatureStyleDto> {

    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    @UiField
    TextField strokeColor = FormUtils.createRequiredTextField();
    @UiField
    TextField fillColor = FormUtils.createRequiredTextField();
    @UiField
    NumberField<Integer> strokeWidth = new NumberField<Integer>(new IntegerPropertyEditor());
    @UiField
    TextField dashArray = FormUtils.createRequiredTextField();
    @Ignore
    public Slider fillAlpha = new Slider();
    @Ignore
    public Slider strokeAlpha = new Slider();

    public FeatureStyleEditor() {
        addRow("Stroke color", GxtComponentsUtils.createColorPicker(strokeColor));
        addRow("Stroke width", strokeWidth);
        addRow("Line style (e.g., 10 5, values separated by a space)", dashArray);
        addRow("Line transparency", GxtComponentsUtils.createSliderWithMessage(strokeAlpha));
        addRow("Fill color", GxtComponentsUtils.createColorPicker(fillColor));
        addRow("Fill transparency", GxtComponentsUtils.createSliderWithMessage(fillAlpha));

    }

    public void beforeShow(FeatureStyleDto model) {
        if (model != null) {
            fillAlpha.setValue((int) (model.getFillAlpha() * 100));
            fillAlpha.setMinValue(0);
            fillAlpha.setMaxValue(100);
            ValueChangeEvent.fire(fillAlpha, fillAlpha.getValue());
            strokeAlpha.setValue((int) (model.getStrokeAlpha() * 100));
            strokeAlpha.setMinValue(0);
            strokeAlpha.setMaxValue(100);
            ValueChangeEvent.fire(strokeAlpha, strokeAlpha.getValue());

        }

    }

    public void afterOkClicked(FeatureStyleDto model) {
        if (model != null) {
            model.setFillAlpha(fillAlpha.getValue() / 100.0);
            model.setStrokeAlpha(strokeAlpha.getValue() / 100.0);
        }

    }
}
