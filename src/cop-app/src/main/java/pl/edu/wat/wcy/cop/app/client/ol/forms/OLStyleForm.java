package pl.edu.wat.wcy.cop.app.client.ol.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm;
import pl.edu.wat.wcy.cop.app.shared.ol.OLStyleOptions;
// Defines ol style form UI.

public class OLStyleForm extends AbstractForm<OLStyleOptions, OLStyleEditor> {
    /**
     * @param model
     * @param okHandler
     */
    public OLStyleForm(OLStyleOptions model, SelectEvent.SelectHandler okHandler) {
        super(model, okHandler);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public OLStyleForm(OLStyleOptions model, String title, SelectEvent.SelectHandler okHandler) {
        super(model, title, okHandler);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected String getDefaultTitle() {
        return "Styl warstwy";
    }

    @Override
    protected int getMaxRowsCount() {
        return editor.getRowsCount();
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildDriver()
     */
    @Override
    protected SimpleBeanEditorDriver<OLStyleOptions, OLStyleEditor> buildDriver() {

        return GWT.create(Driver.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildEditor()
     */
    @Override
    protected OLStyleEditor buildEditor() {

        return GWT.create(OLStyleEditor.class);
    }

    interface Driver extends SimpleBeanEditorDriver<OLStyleOptions, OLStyleEditor> {
    }

}

