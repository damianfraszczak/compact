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
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import pl.edu.wat.wcy.cop.app.client.domain.json.EncodersDecoders;
import pl.edu.wat.wcy.cop.app.client.ui.forms.editors.SearchAndRescueSegmentEditor;
import pl.edu.wat.wcy.cop.app.client.utils.MeasuresPresentationUtils;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueSegmentDto;
// Defines search and rescue multi point zone form UI.

public class SearchAndRescueMultiPointZoneForm extends AbstractForm<SearchAndRescueSegmentDto, SearchAndRescueSegmentEditor> {


    /**
     * @param model
     * @param okHandler
     * @param readOnly
     */
    public SearchAndRescueMultiPointZoneForm(SearchAndRescueSegmentDto model, SelectHandler okHandler, boolean readOnly) {
        super(model, okHandler, readOnly);

    }


    /**
     * @param model
     * @param okHandler
     */
    public SearchAndRescueMultiPointZoneForm(SearchAndRescueSegmentDto model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     * @param readOnly
     */
    public SearchAndRescueMultiPointZoneForm(SearchAndRescueSegmentDto model, String title, SelectHandler okHandler, boolean readOnly) {
        super(model, title, okHandler, readOnly);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public SearchAndRescueMultiPointZoneForm(SearchAndRescueSegmentDto model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildDriver()
     */
    @Override
    protected SimpleBeanEditorDriver<SearchAndRescueSegmentDto, SearchAndRescueSegmentEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildEditor()
     */
    @Override
    protected SearchAndRescueSegmentEditor buildEditor() {
        return GWT.create(SearchAndRescueSegmentEditor.class);
    }

    /* (non-Javadoc)
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#getEncoderDecoder()
     */

    @Override
    protected void beforeShow() {
        super.beforeShow();
        this.editor.style.beforeShow(this.model.getStyle());
        this.editor.poc.setValue(this.model.getPoc() > 1 ? this.model.getPoc() : this.model.getPoc() * 100);
        this.editor.areaInHa.setText(MeasuresPresentationUtils.getAreaInHaString(this.model.getArea()));
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
        this.model.setPoc(this.model.getPoc() > 1 ? this.model.getPoc() / 100 : this.model.getPoc());
    }

    @Override
    protected JsonEncoderDecoder<SearchAndRescueSegmentDto> getEncoderDecoder() {
        return EncodersDecoders.SARSegmentEncoderDecoderInstance;
    }

    @Override
    protected int getMaxRowsCount() {
        return editor.getRowsCount();
    }

    interface Driver extends SimpleBeanEditorDriver<SearchAndRescueSegmentDto, SearchAndRescueSegmentEditor> {
    }

    @Override
    protected String getDefaultTitle() {
        return "Sektor";
    }


    @Override
    protected void verifyModelState(SearchAndRescueSegmentDto model) {
        super.verifyModelState(model);
    }


}
