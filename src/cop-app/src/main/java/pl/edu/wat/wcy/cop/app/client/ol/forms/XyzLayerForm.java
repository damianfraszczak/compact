package pl.edu.wat.wcy.cop.app.client.ol.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiField;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
// Defines xyz layer form UI.


public class XyzLayerForm extends OLLayerForm<OLLayer, XYZOLLayerEditor> {
    public XyzLayerForm(OLLayer model, SelectHandler okHandler) {
        super(model, okHandler);
        // TODO Auto-generated constructor stub
    }

    public XyzLayerForm(OLLayer model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected SimpleBeanEditorDriver<OLLayer, XYZOLLayerEditor> buildDriver() {
        // TODO Auto-generated method stub
        return GWT.create(Driver.class);
    }

    @Override
    protected XYZOLLayerEditor buildEditor() {
        // TODO Auto-generated method stub
        return GWT.create(XYZOLLayerEditor.class);
    }

    interface Driver extends SimpleBeanEditorDriver<OLLayer, XYZOLLayerEditor> {
    }

}

class XYZOLLayerEditor extends OLLayerEditor<OLLayer> {
    @UiField
    TextField url = FormUtils.createRequiredTextField();

    public XYZOLLayerEditor() {
        super();
        // TODO Auto-generated constructor stub
    }

    public XYZOLLayerEditor(Messages messages) {
        super(messages);
        addRow("Url", url);
    }

}
