/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms.editors;

import com.google.gwt.uibinder.client.UiField;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.TextField;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractEditor;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.MapSymbolDto;
// Edits map symbol settings.

public class MapSymbolEditor extends AbstractEditor<MapSymbolDto> {
    static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();

    @UiField
    TextField cNDescription = new TextField();
    @UiField
    TextField cSDescription = new TextField();
    @UiField
    TextField lCDescription = new TextField();
    @UiField
    TextField lNDescription = new TextField();
    @UiField
    TextField lSDescription = new TextField();
    @UiField
    TextField rCDescription = new TextField();
    @UiField
    TextField rNDescription = new TextField();
    @UiField
    TextField rSDescription = new TextField();
    @UiField
    TextField symbolName = new TextField();
    @UiField
    NumberField<Integer> iconHeight = FormUtils.createRangeIntegerField(10, 100);
    @UiField
    NumberField<Integer> iconWidth = FormUtils.createRangeIntegerField(10, 100);
    @UiField
    NumberField<Integer> iconOpacity = FormUtils.createRangeIntegerField(0, 100);
    @UiField
    CheckBox showDescription = new CheckBox();

    public MapSymbolEditor() {
        super();
        addRow(MESSAGES.mapsymbol_symbolName(), symbolName);
        addRow(MESSAGES.mapsymbol_lNDescription(), lNDescription);
        addRow(MESSAGES.mapsymbol_cNDescription(), cNDescription);
        addRow(MESSAGES.mapsymbol_rNDescription(), rNDescription);

        addRow(MESSAGES.mapsymbol_lCDescription(), lCDescription);
        addRow(MESSAGES.mapsymbol_rCDescription(), rCDescription);

        addRow(MESSAGES.mapsymbol_lSDescription(), lSDescription);
        addRow(MESSAGES.mapsymbol_cSDescription(), cSDescription);
        addRow(MESSAGES.mapsymbol_rSDescription(), rSDescription);

        addRow(MESSAGES.mapsymbol_iconHeight(), iconHeight);
        addRow(MESSAGES.mapsymbol_iconWidth(), iconWidth);
        addRow(MESSAGES.mapsymbol_iconOpacity(), iconOpacity);
        addRow(MESSAGES.mapsymbol_showDescription(), showDescription);
    }

}
