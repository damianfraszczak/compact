/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import pl.edu.wat.wcy.cop.app.client.domain.json.EncodersDecoders;
import pl.edu.wat.wcy.cop.app.client.ui.forms.editors.HumanResourceEditor;
import pl.edu.wat.wcy.cop.app.client.utils.DateUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.HumanResourceDto;

import java.util.Date;
// Defines human resource form UI.


public class HumanResourceForm extends AbstractForm<HumanResourceDto, HumanResourceEditor> {
    /**
     * @param model
     * @param okHandler
     */
    public HumanResourceForm(HumanResourceDto model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public HumanResourceForm(HumanResourceDto model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    /**
     * @param model
     * @param okHandler
     * @param readOnly
     */
    public HumanResourceForm(HumanResourceDto model, SelectHandler okHandler, boolean readOnly) {
        super(model, okHandler, readOnly);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     * @param readOnly
     */
    public HumanResourceForm(HumanResourceDto model, String title, SelectHandler okHandler, boolean readOnly) {
        super(model, title, okHandler, readOnly);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildDriver()
     */
    @Override
    protected SimpleBeanEditorDriver<HumanResourceDto, HumanResourceEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildEditor()
     */
    @Override
    protected HumanResourceEditor buildEditor() {

        return GWT.create(HumanResourceEditor.class);
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
        model.setTime(DateUtils
                .getDateTimeFromDateAndTime(editor.time.getValue(), editor.time.getValue()).getTime());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#getEncoderDecoder()
     */
    @Override
    protected JsonEncoderDecoder<HumanResourceDto> getEncoderDecoder() {
        return EncodersDecoders.HUMAN_RESOURCE_ENCODER_DECODER;
    }

    @Override
    protected int getMaxRowsCount() {
        return editor.getRowsCount();
    }

    interface Driver extends SimpleBeanEditorDriver<HumanResourceDto, HumanResourceEditor> {
    }

    @Override
    protected String getDefaultTitle() {
        return "Zasób ludzki";
    }
}
