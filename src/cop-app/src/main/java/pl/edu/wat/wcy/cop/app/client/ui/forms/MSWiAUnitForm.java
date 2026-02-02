/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import pl.edu.wat.wcy.cop.app.client.domain.json.EncodersDecoders;
import pl.edu.wat.wcy.cop.app.client.ui.forms.editors.MSWiAUnitEditor;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.MSWiAUnitDto;
import pl.edu.wat.wcy.cop.app.shared.domain.PointOfInterestDto;
// Defines ms wi a unit form UI.

public class MSWiAUnitForm extends AbstractForm<MSWiAUnitDto, MSWiAUnitEditor> {
    /**
     * @param model
     * @param okHandler
     */
    public MSWiAUnitForm(MSWiAUnitDto model, SelectHandler okHandler) {
        super(model, okHandler);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param model
     * @param okHandler
     * @param readOnly
     */
    public MSWiAUnitForm(MSWiAUnitDto model, SelectHandler okHandler, boolean readOnly) {
        super(model, okHandler, readOnly);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     * @param readOnly
     */
    public MSWiAUnitForm(MSWiAUnitDto model, String title, SelectHandler okHandler, boolean readOnly) {
        super(model, title, okHandler, readOnly);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public MSWiAUnitForm(MSWiAUnitDto model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildDriver()
     */
    @Override
    protected SimpleBeanEditorDriver<MSWiAUnitDto, MSWiAUnitEditor> buildDriver() {
        // TODO Auto-generated method stub
        return GWT.create(Driver.class);
    }

    @Override
    protected void afterOkClicked() {
        super.afterOkClicked();
        getModel().setCode(editor.code.getText());
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildEditor()
     */
    @Override
    protected MSWiAUnitEditor buildEditor() {
        // TODO Auto-generated method stub
        return GWT.create(MSWiAUnitEditor.class);
    }

    /* (non-Javadoc)
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#getEncoderDecoder()
     */
    @Override
    protected JsonEncoderDecoder<MSWiAUnitDto> getEncoderDecoder() {
        return EncodersDecoders.MSWiAUnitEncoderDecoderInstance;
    }

    interface Driver extends SimpleBeanEditorDriver<MSWiAUnitDto, MSWiAUnitEditor> {
    }
    @Override
    protected int getMaxRowsCount() {
        return editor.getRowsCount();
    }
    @Override
    protected String getDefaultTitle() {
        return "MSWIA";
    }
    private static int NEW_OBJ_COUNT = 1;
    @Override
    protected void verifyModelState(MSWiAUnitDto model) {
        super.verifyModelState(model);
        if (StringUtils.isNullOrEmpty(model.getName())) {
            model.setName("MSWIA #" + NEW_OBJ_COUNT);
            NEW_OBJ_COUNT++;
        }
        if (StringUtils.isNullOrEmpty(model.getCode())) {
            model.setCode("");
        }
    }
}
