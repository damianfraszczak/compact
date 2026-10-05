/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import pl.edu.wat.wcy.cop.app.client.domain.json.EncodersDecoders;
import pl.edu.wat.wcy.cop.app.client.ui.forms.editors.PointOfInterestEditor;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.PointOfInterestDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueCircleZoneDto;
// Defines point of interest form UI.

public class PointOfInterestForm extends AbstractForm<PointOfInterestDto, PointOfInterestEditor> {
    /**
     * @param model
     * @param okHandler
     */
    public PointOfInterestForm(PointOfInterestDto model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public PointOfInterestForm(PointOfInterestDto model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    /**
     * @param model
     * @param okHandler
     * @param readOnly
     */
    public PointOfInterestForm(PointOfInterestDto model, SelectHandler okHandler, boolean readOnly) {
        super(model, okHandler, readOnly);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     * @param readOnly
     */
    public PointOfInterestForm(PointOfInterestDto model, String title, SelectHandler okHandler, boolean readOnly) {
        super(model, title, okHandler, readOnly);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildDriver()
     */
    @Override
    protected SimpleBeanEditorDriver<PointOfInterestDto, PointOfInterestEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildEditor()
     */
    @Override
    protected PointOfInterestEditor buildEditor() {
        return GWT.create(PointOfInterestEditor.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#getEncoderDecoder()
     */
    @Override
    protected JsonEncoderDecoder<PointOfInterestDto> getEncoderDecoder() {
        return EncodersDecoders.PoiEncoderDecoderInstance;
    }
    @Override
    protected int getMaxRowsCount() {
        return editor.getRowsCount();
    }
    interface Driver extends SimpleBeanEditorDriver<PointOfInterestDto, PointOfInterestEditor> {
    }
    @Override
    protected String getDefaultTitle() {
        return "POI";
    }
    private static int NEW_OBJ_COUNT = 1;
    @Override
    protected void verifyModelState(PointOfInterestDto model) {
        super.verifyModelState(model);
        if (StringUtils.isNullOrEmpty(model.getName())) {
            model.setName("POI #" + NEW_OBJ_COUNT);
            NEW_OBJ_COUNT++;
        }
    }
}
