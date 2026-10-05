/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import pl.edu.wat.wcy.cop.app.client.ui.forms.editors.GeoPointEditor;
import pl.edu.wat.wcy.cop.app.shared.domain.GeoPointDto;
// Defines geo point form UI.

public class GeoPointForm extends AbstractForm<GeoPointDto, GeoPointEditor> {
    /**
     * @param model
     * @param okHandler
     * @param readOnly
     */
    public GeoPointForm(GeoPointDto model, SelectHandler okHandler, boolean readOnly) {
        super(model, okHandler, readOnly);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param model
     * @param okHandler
     */
    public GeoPointForm(GeoPointDto model, SelectHandler okHandler) {
        super(model, okHandler);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     * @param readOnly
     */
    public GeoPointForm(GeoPointDto model, String title, SelectHandler okHandler, boolean readOnly) {
        super(model, title, okHandler, readOnly);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public GeoPointForm(GeoPointDto model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildDriver()
     */
    @Override
    protected SimpleBeanEditorDriver<GeoPointDto, GeoPointEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildEditor()
     */
    @Override
    protected GeoPointEditor buildEditor() {
        return GWT.create(GeoPointEditor.class);
    }

    interface Driver extends SimpleBeanEditorDriver<GeoPointDto, GeoPointEditor> {
    }
    @Override
    protected int getMaxRowsCount() {
        return editor.getRowsCount();
    }
    @Override
    protected String getDefaultTitle() {
        return "Punkt";
    }
}
