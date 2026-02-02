/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import pl.edu.wat.wcy.cop.app.client.domain.json.EncodersDecoders;
import pl.edu.wat.wcy.cop.app.client.ui.forms.editors.CrisisEventCricleZoneEditor;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.CrisisEventCricleZoneDto;
import pl.edu.wat.wcy.cop.app.shared.domain.PoiCircleZoneDto;
// Defines crisis event circle zone form UI.

public class CrisisEventCircleZoneForm extends AbstractForm<CrisisEventCricleZoneDto, CrisisEventCricleZoneEditor> {
    /**
     * @param model
     * @param okHandler
     * @param readOnly
     */
    public CrisisEventCircleZoneForm(CrisisEventCricleZoneDto model, SelectHandler okHandler, boolean readOnly) {
        super(model, okHandler, readOnly);
    }

    /**
     * @param model
     * @param okHandler
     */
    public CrisisEventCircleZoneForm(CrisisEventCricleZoneDto model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     * @param readOnly
     */
    public CrisisEventCircleZoneForm(CrisisEventCricleZoneDto model, String title, SelectHandler okHandler,
                                     boolean readOnly) {
        super(model, title, okHandler, readOnly);
    }

    @Override
    protected int getMaxRowsCount() {
        return editor.getRowsCount() + 1;
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public CrisisEventCircleZoneForm(CrisisEventCricleZoneDto model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildDriver()
     */
    @Override
    protected SimpleBeanEditorDriver<CrisisEventCricleZoneDto, CrisisEventCricleZoneEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildEditor()
     */
    @Override
    protected CrisisEventCricleZoneEditor buildEditor() {
        return GWT.create(CrisisEventCricleZoneEditor.class);
    }

    /* (non-Javadoc)
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#getEncoderDecoder()
     */
    @Override
    protected JsonEncoderDecoder<CrisisEventCricleZoneDto> getEncoderDecoder() {
        return EncodersDecoders.CrisisEventCricleZoneEncoderDecoderInstance;
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

    interface Driver extends SimpleBeanEditorDriver<CrisisEventCricleZoneDto, CrisisEventCricleZoneEditor> {
    }

    @Override
    protected String getDefaultTitle() {
        return "Crisis event : Circle zone";
    }

    private static int NEW_OBJ_COUNT = 1;
    @Override
    protected void verifyModelState(CrisisEventCricleZoneDto model) {
        super.verifyModelState(model);
        if (StringUtils.isNullOrEmpty(model.getName())) {
            model.setDescription(this.getDefaultTitle() + " #" + NEW_OBJ_COUNT);
            NEW_OBJ_COUNT++;
        }

    }
}
