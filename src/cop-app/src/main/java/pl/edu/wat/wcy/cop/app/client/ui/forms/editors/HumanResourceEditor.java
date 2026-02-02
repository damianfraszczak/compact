package pl.edu.wat.wcy.cop.app.client.ui.forms.editors;

import com.google.gwt.uibinder.client.UiField;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractEditor;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.Qualification;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.HumanResourceDto;
// Edits human resource settings.

public class HumanResourceEditor extends AbstractEditor<HumanResourceDto> {
    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    @UiField
    NumberField<Integer> number = new NumberField<Integer>(new NumberPropertyEditor.IntegerPropertyEditor());

    @Ignore
    public DateField time = new DateField();
    @UiField
    ComboBox<Qualification> qualification = FormUtils.createEnumComboBox(Qualification.class);

    public HumanResourceEditor() {
        super();
        addRow("Czas", time);
        addRow("Kwalifikacja", qualification);
        addRow("Liczba", number);
    }

}
