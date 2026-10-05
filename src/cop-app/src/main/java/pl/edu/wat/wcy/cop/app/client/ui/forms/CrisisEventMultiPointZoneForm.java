/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import pl.edu.wat.wcy.cop.app.client.domain.json.EncodersDecoders;
import pl.edu.wat.wcy.cop.app.client.ui.forms.editors.CrisisEventMultiPointZoneEditor;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.CrisisEventCricleZoneDto;
import pl.edu.wat.wcy.cop.app.shared.domain.CrisisEventMultiPointZoneDto;
// Defines crisis event multi point zone form UI.

public class CrisisEventMultiPointZoneForm
        extends AbstractForm<CrisisEventMultiPointZoneDto, CrisisEventMultiPointZoneEditor> {
    /**
     * @param model
     * @param okHandler
     * @param readOnly
     */
    public CrisisEventMultiPointZoneForm(CrisisEventMultiPointZoneDto model, SelectHandler okHandler,
                                         boolean readOnly) {
        super(model, okHandler, readOnly);
    }

    /**
     * @param model
     * @param okHandler
     */
    public CrisisEventMultiPointZoneForm(CrisisEventMultiPointZoneDto model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     * @param readOnly
     */
    public CrisisEventMultiPointZoneForm(CrisisEventMultiPointZoneDto model, String title, SelectHandler okHandler,
                                         boolean readOnly) {
        super(model, title, okHandler, readOnly);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public CrisisEventMultiPointZoneForm(CrisisEventMultiPointZoneDto model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildDriver()
     */
    @Override
    protected SimpleBeanEditorDriver<CrisisEventMultiPointZoneDto, CrisisEventMultiPointZoneEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildEditor()
     */
    @Override
    protected CrisisEventMultiPointZoneEditor buildEditor() {
        return GWT.create(CrisisEventMultiPointZoneEditor.class);
    }

    /* (non-Javadoc)
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#getEncoderDecoder()
     */
    @Override
    protected JsonEncoderDecoder<CrisisEventMultiPointZoneDto> getEncoderDecoder() {
        return EncodersDecoders.CrisisEventMultiPointZoneEncoderDecoderInstance;
    } @Override
    protected void beforeShow() {
        super.beforeShow();
        this.editor.style.beforeShow(this.model.getStyle());
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
        this.editor.style.afterOkClicked(this.model.getStyle());
    }
    @Override
    protected int getMaxRowsCount() {
        return editor.getRowsCount();
    }
    interface Driver extends SimpleBeanEditorDriver<CrisisEventMultiPointZoneDto, CrisisEventMultiPointZoneEditor> {
    }
    @Override
    protected String getDefaultTitle() {
        return "Crisis event : Area zone";
    }

    private static int NEW_OBJ_COUNT = 1;
    @Override
    protected void verifyModelState(CrisisEventMultiPointZoneDto model) {
        super.verifyModelState(model);
        if (StringUtils.isNullOrEmpty(model.getName())) {
            model.setDescription(this.getDefaultTitle() + " #" + NEW_OBJ_COUNT);
            NEW_OBJ_COUNT++;
        }

    }
}
