package pl.edu.wat.wcy.cop.app.client.ol.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
// Defines ol tile layer form UI.


public class OLTileLayerForm extends OLLayerForm<OLLayer, OLTileLayerEditor> {
    public OLTileLayerForm(OLLayer model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    public OLTileLayerForm(OLLayer model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    @Override
    protected SimpleBeanEditorDriver<OLLayer, OLTileLayerEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    @Override
    protected OLTileLayerEditor buildEditor() {
        return GWT.create(OLTileLayerEditor.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ol.forms.OLLayerForm#beforeShow()
     */
    @Override
    protected void beforeShow() {
        super.beforeShow();
        String url = "";
        switch (getModel().getLayerType()) {
            case BING:
                break;
            case IMAGE:
                break;
            case KML:
                break;
            case TILE:
                url = getModel().getType().getUrl();
                break;
            case WEATHER:
                url = getModel().getWeatherSource().getUrl();
                break;
            case WMS:
                break;
            case WMTS:
                break;
            case XYZ:
                break;
            default:
                break;

        }
        editor.url.setText(url);
    }

    interface Driver extends SimpleBeanEditorDriver<OLLayer, OLTileLayerEditor> {
    }

}

class OLTileLayerEditor extends OLLayerEditor<OLLayer> {
    @Ignore
    TextField url = new TextField();

    public OLTileLayerEditor() {
        this(CopGinInjector.INSTANCE.getMessages());

    }

    public OLTileLayerEditor(Messages messages) {
        super(messages);
        addRow(messages.layerdialog_url(), url);
        url.setEnabled(false);
    }

}
