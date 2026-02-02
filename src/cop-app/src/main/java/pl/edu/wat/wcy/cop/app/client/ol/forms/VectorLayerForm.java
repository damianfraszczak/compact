package pl.edu.wat.wcy.cop.app.client.ol.forms;

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
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FileUploadFieldExt;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GwtUtils;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
// Defines vector layer form UI.


public class VectorLayerForm extends OLLayerForm<OLLayer, OLVectorEditor> {

    public VectorLayerForm(OLLayer model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    public VectorLayerForm(OLLayer model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    @Override
    protected SimpleBeanEditorDriver<OLLayer, OLVectorEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    @Override
    protected OLVectorEditor buildEditor() {
        return GWT.create(OLVectorEditor.class);
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
                        editor.url.setValue(GwtUtils.getStringFromFileReadEvent(evt));
                    }
                });

            }
        }));
        super.createButtonPanel(window);
    }

    interface Driver extends SimpleBeanEditorDriver<OLLayer, OLVectorEditor> {
    }

}

class OLVectorEditor extends OLLayerEditor<OLLayer> {
    @UiField
    TextArea url = FormUtils.createRequiredTextArea();
    @Ignore
    FileUploadFieldExt vectorFile = new FileUploadFieldExt();

    public OLVectorEditor() {
        this(CopGinInjector.INSTANCE.getMessages());
    }

    public OLVectorEditor(Messages messages) {
        super(messages);
        addRow(messages.layerDialog_urlOrContent(), url);
        addRow(messages.layer_vector_uploadbtn(), vectorFile);
        vectorFile.setAllowBlank(true);
    }

}
