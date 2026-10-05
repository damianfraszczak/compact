/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiField;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractEditor;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
// Defines graticulate layer form UI.


public class GraticulateLayerForm extends AbstractForm<OLLayer, GraticulateLayerEditor> {
    public GraticulateLayerForm(OLLayer model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    public GraticulateLayerForm(OLLayer model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    @Override
    protected String getDefaultTitle() {
        return "Warstwa Graticulate";
    }

    @Override
    protected int getMaxRowsCount() {
        return editor.getRowsCount();
    }

    @Override
    protected SimpleBeanEditorDriver<OLLayer, GraticulateLayerEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    @Override
    protected GraticulateLayerEditor buildEditor() {
        return GWT.create(GraticulateLayerEditor.class);
    }

    interface Driver extends SimpleBeanEditorDriver<OLLayer, GraticulateLayerEditor> {
    }

}

class GraticulateLayerEditor extends AbstractEditor<OLLayer> {
    @UiField
    TextField name = FormUtils.createRequiredTextField();

    public GraticulateLayerEditor() {
        this(CopGinInjector.INSTANCE.getMessages());
    }

    /**
     *
     */
    public GraticulateLayerEditor(Messages messages) {
        super();
        addRow(messages.layerdialog_name(), name);
    }

}
