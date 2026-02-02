/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import org.fusesource.restygwt.client.Method;
import pl.edu.wat.wcy.cop.app.client.services.server.SymbolServiceProvider;
import pl.edu.wat.wcy.cop.app.client.signs.EnumCodeUtils;
import pl.edu.wat.wcy.cop.app.client.ui.forms.editors.APP6AMilitaryUnitEditor;
import pl.edu.wat.wcy.cop.app.client.utils.LoggingMethodCallback;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.client.utils.images.IconsUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.App6AMilitaryUnitDto;
import pl.edu.wat.wcy.cop.app.shared.domain.MSWiAUnitDto;
import pl.edu.wat.wcy.cop.app.shared.response.OkResponse;
// Defines 6 a military unit form UI.

public class APP6AMilitaryUnitForm extends AbstractForm<App6AMilitaryUnitDto, APP6AMilitaryUnitEditor> {
    /**
     * @param model
     * @param okHandler
     */
    public APP6AMilitaryUnitForm(App6AMilitaryUnitDto model, SelectHandler okHandler) {
        super(model, okHandler);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public APP6AMilitaryUnitForm(App6AMilitaryUnitDto model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    /**
     * @param model
     * @param okHandler
     * @param readOnly
     */
    public APP6AMilitaryUnitForm(App6AMilitaryUnitDto model, SelectHandler okHandler, boolean readOnly) {
        super(model, okHandler, readOnly);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     * @param readOnly
     */
    public APP6AMilitaryUnitForm(App6AMilitaryUnitDto model, String title, SelectHandler okHandler, boolean readOnly) {
        super(model, title, okHandler, readOnly);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildDriver()
     */
    @Override
    protected SimpleBeanEditorDriver<App6AMilitaryUnitDto, APP6AMilitaryUnitEditor> buildDriver() {
        return GWT.create(Driver.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildEditor()
     */
    @Override
    protected APP6AMilitaryUnitEditor buildEditor() {
        return GWT.create(APP6AMilitaryUnitEditor.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#beforeShow()
     */
    @Override
    protected void beforeShow() {
        super.beforeShow();
        if (!StringUtils.isNullOrEmpty(model.getFunctionId())) {
            editor.functionIdCode = model.getFunctionId();
        }
        editor.recalculateApp6aCodeHandler = new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
                loadSymbol();
            }
        };
        loadSymbol();
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
        getModel().setFunctionId(editor.functionIdCode);
        getModel().setCode(editor.code.getText());
    }

    /**
     *
     */
    protected void loadSymbol() {
        // utowrzenie sztucznego mu z obecnie wybranymi wartosciami
        App6AMilitaryUnitDto dto = new App6AMilitaryUnitDto();
        dto.setFunctionId(editor.functionIdCode);
        editor.affiliation.finishEditing();
        dto.setAffiliation(editor.affiliation.getValue());
        editor.battleDimension.finishEditing();
        dto.setBattleDimension(editor.battleDimension.getValue());
        editor.unitType.finishEditing();
        dto.setUnitType(editor.unitType.getValue());
        editor.mobilitySize.finishEditing();
        dto.setMobilitySize(editor.mobilitySize.getValue());
        String code = EnumCodeUtils.getCode(dto, editor.functionIdCode);
        if (StringUtils.isNullOrEmpty(code)) {
            code = getModel().getCode();
        }
        editor.code.setText(code);
        SymbolServiceProvider.INSTANCE.getBase64ImageFromCache(code, new LoggingMethodCallback<OkResponse<String>>() {

            @Override
            protected void success(Method method, OkResponse<String> response) {
                editor.setImage(IconsUtils.getBase64ImgSrc(response.getContent()));
            }
        });

    }

    @Override
    protected String getDefaultTitle() {
        return "Military unit";
    }

    @Override
    protected int getMaxRowsCount() {
        return editor.getRowsCount();
    }

    private static int NEW_OBJ_COUNT = 1;

    @Override
    protected void verifyModelState(App6AMilitaryUnitDto model) {
        super.verifyModelState(model);
        if (StringUtils.isNullOrEmpty(model.getName())) {
            model.setDescription("Unit #" + NEW_OBJ_COUNT);
            NEW_OBJ_COUNT++;
        }
        if (StringUtils.isNullOrEmpty(model.getCode())) {
            model.setCode(EnumCodeUtils.getCode(model, "U-----"));
        }
    }

    interface Driver extends SimpleBeanEditorDriver<App6AMilitaryUnitDto, APP6AMilitaryUnitEditor> {
    }

}
