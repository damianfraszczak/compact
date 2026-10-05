/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiField;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayerFormat;
// Defines vector tile layer form UI.


public class VectorTileLayerForm extends OLLayerForm<OLLayer, OLVectorTileLayerEditor> {
    public VectorTileLayerForm(OLLayer model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    public VectorTileLayerForm(OLLayer model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    @Override
    protected SimpleBeanEditorDriver<OLLayer, OLVectorTileLayerEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    @Override
    protected OLVectorTileLayerEditor buildEditor() {
        return GWT.create(OLVectorTileLayerEditor.class);
    }

    interface Driver extends SimpleBeanEditorDriver<OLLayer, OLVectorTileLayerEditor> {
    }

}

class OLVectorTileLayerEditor extends OLLayerEditor<OLLayer> {
    @UiField
    TextField url = FormUtils.createRequiredTextField();
    @UiField
    ComboBox<OLLayerFormat> layerFormat = FormUtils.createEnumComboBox(OLLayerFormat.class);

    public OLVectorTileLayerEditor() {
        this(CopGinInjector.INSTANCE.getMessages());

    }

    public OLVectorTileLayerEditor(Messages messages) {
        super(messages);
        addRow(messages.layerdialog_url(), url);
        addRow(messages.layer_vector_format(), layerFormat);
        layerFormat.setValue(OLLayerFormat.KML);
        url.setEnabled(true);
    }

}
