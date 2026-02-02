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
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.EquipmentCategory;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.TechnicalResourceDto;
// Edits technical resource settings.

public class TechnicalResourceEditor extends AbstractEditor<TechnicalResourceDto> {
    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    @UiField
    NumberField<Integer> number = new NumberField<Integer>(new NumberPropertyEditor.IntegerPropertyEditor());

    @Ignore
    public DateField time = new DateField();
    @UiField
    ComboBox<EquipmentCategory> category = FormUtils.createEnumComboBox(EquipmentCategory.class);

    public TechnicalResourceEditor() {
        super();
        addRow("Czas", time);
        addRow("Kategoria", category);
        addRow("Liczba", number);
    }

}
