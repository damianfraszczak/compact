/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiField;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextArea;
import elemental.events.Event;
import elemental.events.EventListener;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.domain.ImportDataObject;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FileUploadFieldExt;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GwtUtils;
// Defines import data form UI.

public class ImportDataForm extends AbstractForm<ImportDataObject, ImportDataEditor> {

    public ImportDataForm(ImportDataObject model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    public ImportDataForm(ImportDataObject model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    @Override
    protected String getDefaultTitle() {
        return "Import danych";
    }

    @Override
    protected SimpleBeanEditorDriver<ImportDataObject, ImportDataEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    @Override
    protected ImportDataEditor buildEditor() {
        return GWT.create(ImportDataEditor.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#createButtonPanel(
     * com.sencha.gxt.widget.core.client.Dialog)
     */
    @Override
    protected void createButtonPanel(Dialog window) {
        window.addButton(new TextButton(messages.layer_vector_parsebtn(), new SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                GwtUtils.readFiles(editor.vectorFile, new EventListener() {

                    @Override
                    public void handleEvent(Event evt) {
                        editor.content.setValue(GwtUtils.getStringFromFileReadEvent(evt));
                    }
                });

            }
        }));
        super.createButtonPanel(window);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#afterOkClicked()
     */
    @Override
    protected void afterOkClicked() {
        getModel().setContent(editor.content.getValue());
    }
    @Override
    protected int getMaxRowsCount() {
        return editor.getRowsCount();
    }
    interface Driver extends SimpleBeanEditorDriver<ImportDataObject, ImportDataEditor> {
    }
}

class ImportDataEditor extends AbstractEditor<ImportDataObject> {
    @UiField
    TextArea content = FormUtils.createRequiredTextArea();
    @Ignore
    FileUploadFieldExt vectorFile = new FileUploadFieldExt();

    public ImportDataEditor() {
        this(CopGinInjector.INSTANCE.getMessages());
    }

    public ImportDataEditor(Messages messages) {
        addRow("Zawartość", content);
        addRow(messages.layer_vector_uploadbtn(), vectorFile);
        vectorFile.setAllowBlank(true);
    }

}
