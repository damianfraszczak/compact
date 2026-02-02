/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import pl.edu.wat.wcy.cop.app.client.domain.json.EncodersDecoders;
import pl.edu.wat.wcy.cop.app.client.ui.forms.editors.TechnicalResourceEditor;
import pl.edu.wat.wcy.cop.app.client.utils.DateUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.TechnicalResourceDto;

import java.util.Date;
// Defines technical resource form UI.


public class TechnicalResourceForm extends AbstractForm<TechnicalResourceDto, TechnicalResourceEditor> {
    /**
     * @param model
     * @param okHandler
     */
    public TechnicalResourceForm(TechnicalResourceDto model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public TechnicalResourceForm(TechnicalResourceDto model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    /**
     * @param model
     * @param okHandler
     * @param readOnly
     */
    public TechnicalResourceForm(TechnicalResourceDto model, SelectHandler okHandler, boolean readOnly) {
        super(model, okHandler, readOnly);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     * @param readOnly
     */
    public TechnicalResourceForm(TechnicalResourceDto model, String title, SelectHandler okHandler, boolean readOnly) {
        super(model, title, okHandler, readOnly);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildDriver()
     */
    @Override
    protected SimpleBeanEditorDriver<TechnicalResourceDto, TechnicalResourceEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildEditor()
     */
    @Override
    protected TechnicalResourceEditor buildEditor() {

        return GWT.create(TechnicalResourceEditor.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#beforeShow()
     */
    @Override
    protected void beforeShow() {
        // TODO Auto-generated method stub
        super.beforeShow();
        editor.time.setValue(new Date(getModel().getTime()));
        model.setTime(DateUtils
                .getDateTimeFromDateAndTime(editor.time.getValue(), editor.time.getValue()).getTime());

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
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#getEncoderDecoder()
     */
    @Override
    protected JsonEncoderDecoder<TechnicalResourceDto> getEncoderDecoder() {
        return EncodersDecoders.TECHNICAL_RESOURCE_ENCODER_DECODER;
    }

    @Override
    protected int getMaxRowsCount() {
        return editor.getRowsCount();
    }

    interface Driver extends SimpleBeanEditorDriver<TechnicalResourceDto, TechnicalResourceEditor> {
    }

    @Override
    protected String getDefaultTitle() {
        return "Zasób sprzętowy";
    }
}
