package pl.edu.wat.wcy.cop.app.client.ol.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiField;
import com.sencha.gxt.widget.core.client.Slider;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
// Defines wms layer form UI.


public class WmsLayerForm extends OLLayerForm<OLLayer, OLWmsEditor> {

    public WmsLayerForm(OLLayer model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    public WmsLayerForm(OLLayer model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    @Override
    protected SimpleBeanEditorDriver<OLLayer, OLWmsEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    @Override
    protected OLWmsEditor buildEditor() {
        return GWT.create(OLWmsEditor.class);
    }

    @Override
    protected void beforeShow() {
        super.beforeShow();
        Slider ratioSlider = this.editor.ratio;
        ratioSlider.setValue((int) (this.model.getRatio() * 100));
        ratioSlider.setMinValue(1);
        ratioSlider.setMaxValue(100);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#afterOkClicked()
     */
    @Override
    protected void afterOkClicked() {
        super.afterOkClicked();
        model.setRatio(this.editor.ratio.getValue() / 100);
    }

    interface Driver extends SimpleBeanEditorDriver<OLLayer, OLWmsEditor> {
    }
}

class OLWmsEditor extends OLLayerEditor<OLLayer> {
    @UiField
    TextField url = FormUtils.createRequiredTextField();
    @UiField
    TextField layers = FormUtils.createRequiredTextField();
    @com.google.gwt.editor.client.Editor.Ignore
    Slider ratio = new Slider();

    public OLWmsEditor() {
        this(CopGinInjector.INSTANCE.getMessages());
    }

    public OLWmsEditor(Messages messages) {
        super(messages);
        addRow(messages.layerdialog_url(), url);
        addRow(messages.layerdialog_layers(), layers);
        addRow(messages.layerdialog_ratio(), ratio);

    }

}
