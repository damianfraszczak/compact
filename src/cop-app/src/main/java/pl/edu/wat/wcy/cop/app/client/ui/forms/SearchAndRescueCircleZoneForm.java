/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import pl.edu.wat.wcy.cop.app.client.domain.json.EncodersDecoders;
import pl.edu.wat.wcy.cop.app.client.ui.forms.editors.SearchAndRescueCircleZoneEditor;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueCircleZoneDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueSegmentDto;
// Defines search and rescue circle zone form UI.

public class SearchAndRescueCircleZoneForm extends AbstractForm<SearchAndRescueCircleZoneDto, SearchAndRescueCircleZoneEditor> {
    /**
     * @param model
     * @param okHandler
     * @param readOnly
     */
    public SearchAndRescueCircleZoneForm(SearchAndRescueCircleZoneDto model, SelectHandler okHandler, boolean readOnly) {
        super(model, okHandler, readOnly);
    }

    /**
     * @param model
     * @param okHandler
     */
    public SearchAndRescueCircleZoneForm(SearchAndRescueCircleZoneDto model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     * @param readOnly
     */
    public SearchAndRescueCircleZoneForm(SearchAndRescueCircleZoneDto model, String title, SelectHandler okHandler, boolean readOnly) {
        super(model, title, okHandler, readOnly);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public SearchAndRescueCircleZoneForm(SearchAndRescueCircleZoneDto model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildDriver()
     */
    @Override
    protected SimpleBeanEditorDriver<SearchAndRescueCircleZoneDto, SearchAndRescueCircleZoneEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildEditor()
     */
    @Override
    protected SearchAndRescueCircleZoneEditor buildEditor() {
        return GWT.create(SearchAndRescueCircleZoneEditor.class);
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
    /* (non-Javadoc)
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#getEncoderDecoder()
     */
    @Override
    protected JsonEncoderDecoder<SearchAndRescueCircleZoneDto> getEncoderDecoder() {
        return EncodersDecoders.SARCricleZoneEncoderDecoderInstance;
    }
    @Override
    protected int getMaxRowsCount() {
        return editor.getRowsCount() + 1;
    }
    interface Driver extends SimpleBeanEditorDriver<SearchAndRescueCircleZoneDto, SearchAndRescueCircleZoneEditor> {
    }
    @Override
    protected String getDefaultTitle() {
        return "SAR zone : Circle zone";
    }

    private static int NEW_OBJ_COUNT = 1;
    @Override
    protected void verifyModelState(SearchAndRescueCircleZoneDto model) {
        super.verifyModelState(model);
        if (StringUtils.isNullOrEmpty(model.getName())) {
            model.setName("Zone #" + NEW_OBJ_COUNT);
            NEW_OBJ_COUNT++;
        }
    }
}
