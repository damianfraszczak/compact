/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import pl.edu.wat.wcy.cop.app.client.domain.json.EncodersDecoders;
import pl.edu.wat.wcy.cop.app.client.signs.EnumCodeUtils;
import pl.edu.wat.wcy.cop.app.client.ui.forms.editors.PoiMultiPointZoneEditor;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.App6AMilitaryUnitDto;
import pl.edu.wat.wcy.cop.app.shared.domain.PoiMultiPointZoneDto;
// Defines poi multi point zone form UI.

public class PoiMultiPointZoneForm extends AbstractForm<PoiMultiPointZoneDto, PoiMultiPointZoneEditor> {
    /**
     * @param model
     * @param okHandler
     * @param readOnly
     */
    public PoiMultiPointZoneForm(PoiMultiPointZoneDto model, SelectHandler okHandler, boolean readOnly) {
        super(model, okHandler, readOnly);
    }

    /**
     * @param model
     * @param okHandler
     */
    public PoiMultiPointZoneForm(PoiMultiPointZoneDto model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     * @param readOnly
     */
    public PoiMultiPointZoneForm(PoiMultiPointZoneDto model, String title, SelectHandler okHandler, boolean readOnly) {
        super(model, title, okHandler, readOnly);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public PoiMultiPointZoneForm(PoiMultiPointZoneDto model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildDriver()
     */
    @Override
    protected SimpleBeanEditorDriver<PoiMultiPointZoneDto, PoiMultiPointZoneEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildEditor()
     */
    @Override
    protected PoiMultiPointZoneEditor buildEditor() {
        return GWT.create(PoiMultiPointZoneEditor.class);
    }

    /* (non-Javadoc)
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#getEncoderDecoder()
     */

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
    protected JsonEncoderDecoder<PoiMultiPointZoneDto> getEncoderDecoder() {
        return EncodersDecoders.PoiMultiPointZoneEncoderDecoderInstance;
    }

    @Override
    protected int getMaxRowsCount() {
        return editor.getRowsCount();
    }
    interface Driver extends SimpleBeanEditorDriver<PoiMultiPointZoneDto, PoiMultiPointZoneEditor> {
    }
    @Override
    protected String getDefaultTitle() {
        return "POI : Area zone";
    }
    private static int NEW_OBJ_COUNT = 1;
    @Override
    protected void verifyModelState(PoiMultiPointZoneDto model) {
        super.verifyModelState(model);
        if (StringUtils.isNullOrEmpty(model.getName())) {
            model.setDescription(this.getDefaultTitle() + " #" + NEW_OBJ_COUNT);
            NEW_OBJ_COUNT++;
        }

    }
}
