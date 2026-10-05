/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import pl.edu.wat.wcy.cop.app.client.domain.json.EncodersDecoders;
import pl.edu.wat.wcy.cop.app.client.ui.forms.editors.ADatP3ReportEditor;
import pl.edu.wat.wcy.cop.app.shared.domain.ADatP3ReportDto;
// Defines a dat 3 report form UI.

public class ADatP3ReportForm extends AbstractForm<ADatP3ReportDto, ADatP3ReportEditor> {
    /**
     * @param model
     * @param okHandler
     */
    public ADatP3ReportForm(ADatP3ReportDto model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public ADatP3ReportForm(ADatP3ReportDto model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    /**
     * @param model
     * @param okHandler
     * @param readOnly
     */
    public ADatP3ReportForm(ADatP3ReportDto model, SelectHandler okHandler, boolean readOnly) {
        super(model, okHandler, readOnly);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     * @param readOnly
     */
    public ADatP3ReportForm(ADatP3ReportDto model, String title, SelectHandler okHandler, boolean readOnly) {
        super(model, title, okHandler, readOnly);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildDriver()
     */
    @Override
    protected SimpleBeanEditorDriver<ADatP3ReportDto, ADatP3ReportEditor> buildDriver() {

        return GWT.create(Driver.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildEditor()
     */
    @Override
    protected ADatP3ReportEditor buildEditor() {

        return GWT.create(ADatP3ReportEditor.class);
    }

    /* (non-Javadoc)
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#getEncoderDecoder()
     */
    @Override
    protected JsonEncoderDecoder<ADatP3ReportDto> getEncoderDecoder() {
        return EncodersDecoders.ADatP3ReportEncoderDecoderInstance;
    }
    @Override
    protected int getMaxRowsCount() {
        return editor.getRowsCount();
    }

    interface Driver extends SimpleBeanEditorDriver<ADatP3ReportDto, ADatP3ReportEditor> {
    }
    @Override
    protected String getDefaultTitle() {
        return "Raport ADatP#";
    }
}
