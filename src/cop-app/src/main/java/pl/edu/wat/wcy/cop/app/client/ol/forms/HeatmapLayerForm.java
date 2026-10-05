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
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
// Defines heatmap layer form UI.

public class HeatmapLayerForm extends OLLayerForm<OLLayer, OLHeatmapEditor> {

    public HeatmapLayerForm(OLLayer model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    public HeatmapLayerForm(OLLayer model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    @Override
    protected SimpleBeanEditorDriver<OLLayer, OLHeatmapEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    @Override
    protected OLHeatmapEditor buildEditor() {
        return GWT.create(OLHeatmapEditor.class);
    }

    interface Driver extends SimpleBeanEditorDriver<OLLayer, OLHeatmapEditor> {
    }

}

class OLHeatmapEditor extends OLLayerEditor<OLLayer> {
    @UiField
    TextField url = FormUtils.createRequiredTextField();

    public OLHeatmapEditor() {
        this(CopGinInjector.INSTANCE.getMessages());
    }

    public OLHeatmapEditor(Messages messages) {
        super(messages);
        addRow(messages.layerdialog_url(), url);
    }

}
