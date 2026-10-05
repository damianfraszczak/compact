package pl.edu.wat.wcy.cop.app.client.ol.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiField;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
// Defines wmts layer form UI.


public class WmtsLayerForm extends OLLayerForm<OLLayer, WmtsOLLayerEditor> {
    public WmtsLayerForm(OLLayer model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    public WmtsLayerForm(OLLayer model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    @Override
    protected SimpleBeanEditorDriver<OLLayer, WmtsOLLayerEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    @Override
    protected WmtsOLLayerEditor buildEditor() {
        return GWT.create(WmtsOLLayerEditor.class);
    }

    interface Driver extends SimpleBeanEditorDriver<OLLayer, WmtsOLLayerEditor> {
    }
}

class WmtsOLLayerEditor extends OLLayerEditor<OLLayer> {
    /**
     * private String layer; private String format; private String matrixStyle;
     * private String style; private String projection; private boolean wrapX;
     */
    @UiField
    TextField url = FormUtils.createRequiredTextField();
    @UiField
    TextField layer = FormUtils.createRequiredTextField();
    @UiField
    TextField format = FormUtils.createRequiredTextField();
    @UiField
    TextField matrixStyle = FormUtils.createRequiredTextField();
    @UiField
    TextField style = FormUtils.createRequiredTextField();
    @UiField
    TextField projection = FormUtils.createRequiredTextField();
    @UiField
    CheckBox wrapX = new CheckBox();

    public WmtsOLLayerEditor() {
        this(CopGinInjector.INSTANCE.getMessages());
    }

    public WmtsOLLayerEditor(Messages messages) {

        super(messages);
        addRow(messages.layerdialog_url(), url);
        addRow(messages.layerdialog_layer(), layer);
        addRow(messages.layerdialog_format(), format);
        addRow(messages.layerdialog_matrixStyle(), matrixStyle);
        addRow(messages.layerdialog_style(), style);
        addRow(messages.layerdialog_projection(), projection);
        addRow(messages.layerdialog_wrapX(), wrapX);
    }

}
