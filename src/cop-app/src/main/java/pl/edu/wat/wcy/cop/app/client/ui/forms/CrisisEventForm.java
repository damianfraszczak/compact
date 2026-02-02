/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import pl.edu.wat.wcy.cop.app.client.domain.json.EncodersDecoders;
import pl.edu.wat.wcy.cop.app.client.ui.forms.editors.CrisisEventEditor;
import pl.edu.wat.wcy.cop.app.client.utils.DateUtils;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.CrisisEventDto;
import pl.edu.wat.wcy.cop.app.shared.domain.PointOfInterestDto;

import java.util.Date;
// Defines crisis event form UI.

public class CrisisEventForm extends AbstractForm<CrisisEventDto, CrisisEventEditor> {
    /**
     * @param model
     * @param okHandler
     */
    public CrisisEventForm(CrisisEventDto model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public CrisisEventForm(CrisisEventDto model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    /**
     * @param model
     * @param okHandler
     * @param readOnly
     */
    public CrisisEventForm(CrisisEventDto model, SelectHandler okHandler, boolean readOnly) {
        super(model, okHandler, readOnly);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     * @param readOnly
     */
    public CrisisEventForm(CrisisEventDto model, String title, SelectHandler okHandler, boolean readOnly) {
        super(model, title, okHandler, readOnly);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildDriver()
     */
    @Override
    protected SimpleBeanEditorDriver<CrisisEventDto, CrisisEventEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildEditor()
     */
    @Override
    protected CrisisEventEditor buildEditor() {

        return GWT.create(CrisisEventEditor.class);
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
        editor.timestampStart.setValue(new Date(getModel().getTimestampStart()));
        editor.timestampEnd.setValue(new Date(getModel().getTimestampEnd()));
        editor.startDate.setValue(new Date(getModel().getTimestampStart()));
        editor.endDate.setValue(new Date(getModel().getTimestampEnd()));
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
        model.setTimestampStart(DateUtils
                .getDateTimeFromDateAndTime(editor.startDate.getValue(), editor.timestampStart.getValue()).getTime());
        model.setTimestampEnd(DateUtils
                .getDateTimeFromDateAndTime(editor.endDate.getValue(), editor.timestampEnd.getValue()).getTime());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#getEncoderDecoder()
     */
    @Override
    protected JsonEncoderDecoder<CrisisEventDto> getEncoderDecoder() {
        return EncodersDecoders.CrisisEventEncoderDecoderInstance;
    }
    @Override
    protected int getMaxRowsCount() {
        return editor.getRowsCount();
    }
    interface Driver extends SimpleBeanEditorDriver<CrisisEventDto, CrisisEventEditor> {
    }

    @Override
    protected String getDefaultTitle() {
        return "Crisis event";
    }

    private static int NEW_OBJ_COUNT = 1;
    @Override
    protected void verifyModelState(CrisisEventDto model) {
        super.verifyModelState(model);
        if (StringUtils.isNullOrEmpty(model.getName())) {
            model.setHtrcId("Crisis event #" + NEW_OBJ_COUNT);
            NEW_OBJ_COUNT++;
        }
    }
}
