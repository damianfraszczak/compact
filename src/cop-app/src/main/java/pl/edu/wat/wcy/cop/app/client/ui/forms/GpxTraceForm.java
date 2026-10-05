/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import elemental.events.Event;
import elemental.events.EventListener;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import pl.edu.wat.wcy.cop.app.client.domain.json.EncodersDecoders;
import pl.edu.wat.wcy.cop.app.client.ui.forms.editors.GpxTraceEditor;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GwtUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.GpxTraceDto;
// Defines gpx trace form UI.

public class GpxTraceForm extends AbstractForm<GpxTraceDto, GpxTraceEditor> {
    /**
     * @param model
     * @param okHandler
     * @param readOnly
     */
    public GpxTraceForm(GpxTraceDto model, SelectHandler okHandler, boolean readOnly) {
        super(model, okHandler, readOnly);
    }

    /**
     * @param model
     * @param okHandler
     */
    public GpxTraceForm(GpxTraceDto model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     * @param readOnly
     */
    public GpxTraceForm(GpxTraceDto model, String title, SelectHandler okHandler, boolean readOnly) {
        super(model, title, okHandler, readOnly);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public GpxTraceForm(GpxTraceDto model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildDriver()
     */
    @Override
    protected SimpleBeanEditorDriver<GpxTraceDto, GpxTraceEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildEditor()
     */
    @Override
    protected GpxTraceEditor buildEditor() {
        return GWT.create(GpxTraceEditor.class);
    }

    /* (non-Javadoc)
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#getEncoderDecoder()
     */

    @Override
    protected void beforeShow() {
        super.beforeShow();
        this.editor.style.beforeShow(this.model.getStyle());
    }

    @Override
    protected void onSaveClick(SelectEvent event) {

        GwtUtils.readFiles(editor.contentFile, new EventListener() {

            @Override
            public void handleEvent(Event evt) {
                if(evt != null){
                    getModel().setContent(GwtUtils.getStringFromFileReadEvent(evt));
                }

                GpxTraceForm.super.onSaveClick(event);
            }
        });
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#afterOkClicked()
     */
    @Override
    protected void afterOkClicked() {

        this.editor.style.afterOkClicked(this.model.getStyle());


    }

    @Override
    protected JsonEncoderDecoder<GpxTraceDto> getEncoderDecoder() {
        return EncodersDecoders.GPX_TRACE_ENCODER_DECODER;
    }

    @Override
    protected int getMaxRowsCount() {
        return editor.getRowsCount();
    }

    interface Driver extends SimpleBeanEditorDriver<GpxTraceDto, GpxTraceEditor> {
    }

    @Override
    protected String getDefaultTitle() {
        return "GPX";
    }
}
