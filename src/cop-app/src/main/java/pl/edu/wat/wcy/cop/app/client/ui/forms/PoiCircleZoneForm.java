/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.Slider;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import pl.edu.wat.wcy.cop.app.client.domain.json.EncodersDecoders;
import pl.edu.wat.wcy.cop.app.client.ui.forms.editors.PoiCircleZoneEditor;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.PoiCircleZoneDto;
import pl.edu.wat.wcy.cop.app.shared.domain.PoiMultiPointZoneDto;
// Defines poi circle zone form UI.


public class PoiCircleZoneForm extends AbstractForm<PoiCircleZoneDto, PoiCircleZoneEditor> {
    /**
     * @param model
     * @param okHandler
     * @param readOnly
     */
    public PoiCircleZoneForm(PoiCircleZoneDto model, SelectHandler okHandler, boolean readOnly) {
        super(model, okHandler, readOnly);
    }

    /**
     * @param model
     * @param okHandler
     */
    public PoiCircleZoneForm(PoiCircleZoneDto model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     * @param readOnly
     */
    public PoiCircleZoneForm(PoiCircleZoneDto model, String title, SelectHandler okHandler, boolean readOnly) {
        super(model, title, okHandler, readOnly);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public PoiCircleZoneForm(PoiCircleZoneDto model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildDriver()
     */
    @Override
    protected SimpleBeanEditorDriver<PoiCircleZoneDto, PoiCircleZoneEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildEditor()
     */
    @Override
    protected PoiCircleZoneEditor buildEditor() {
        return GWT.create(PoiCircleZoneEditor.class);
    }

    /* (non-Javadoc)
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#getEncoderDecoder()
     */
    @Override
    protected JsonEncoderDecoder<PoiCircleZoneDto> getEncoderDecoder() {
        return EncodersDecoders.PoiCricleZoneEncoderDecoderInstance;
    }
    @Override
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
        return editor.getRowsCount() + 1;
    }
    interface Driver extends SimpleBeanEditorDriver<PoiCircleZoneDto, PoiCircleZoneEditor> {
    }
    @Override
    protected String getDefaultTitle() {
        return "POI : Circle zone";
    }

    private static int NEW_OBJ_COUNT = 1;
    @Override
    protected void verifyModelState(PoiCircleZoneDto model) {
        super.verifyModelState(model);
        if (StringUtils.isNullOrEmpty(model.getName())) {
            model.setDescription(this.getDefaultTitle() + " #" + NEW_OBJ_COUNT);
            NEW_OBJ_COUNT++;
        }

    }
}
