package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import pl.edu.wat.wcy.cop.app.client.ui.forms.editors.ADatP3CoordinatesEditor;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtDefaultContainer;
import pl.edu.wat.wcy.cop.app.shared.domain.ADatP3CoordinatesDto;
// Defines a dat 3 coordinates form UI.

public class ADatP3CoordinatesForm extends AbstractForm<ADatP3CoordinatesDto, ADatP3CoordinatesEditor> {
    /**
     * @param model
     * @param okHandler
     */
    public ADatP3CoordinatesForm(ADatP3CoordinatesDto model, SelectEvent.SelectHandler okHandler) {
        super(model, okHandler);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public ADatP3CoordinatesForm(ADatP3CoordinatesDto model, String title, SelectEvent.SelectHandler okHandler) {
        super(model, title, okHandler);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param model
     * @param okHandler
     * @param readOnly
     */
    public ADatP3CoordinatesForm(ADatP3CoordinatesDto model, SelectEvent.SelectHandler okHandler, boolean readOnly) {
        super(model, okHandler, readOnly);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     * @param readOnly
     */
    public ADatP3CoordinatesForm(ADatP3CoordinatesDto model, String title, SelectEvent.SelectHandler okHandler, boolean readOnly) {
        super(model, title, okHandler, readOnly);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildDriver()
     */
    @Override
    protected SimpleBeanEditorDriver<ADatP3CoordinatesDto, ADatP3CoordinatesEditor> buildDriver() {

        return GWT.create(Driver.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildEditor()
     */
    @Override
    protected ADatP3CoordinatesEditor buildEditor() {

        return GWT.create(ADatP3CoordinatesEditor.class);
    }

    @Override
    protected void initComponents() {
        driver = buildDriver();
        editor = buildEditor();
        window.setWidget(new GxtDefaultContainer(editor));
        driver.initialize(editor);
        driver.edit(model);

        editor.featureType.setValue(model.getFeatureType());
        editor.setRows();
        editor.setCoordinates(model.getMgrsCoordinates(), model.getLonLatCoordinates());
        beforeShow();
        window.show();
    }

    @Override
    protected int getMaxRowsCount() {
        return editor.getRowsCount();
    }

    @Override
    protected String getDefaultTitle() {
        return "Położenie ADatP#";
    }
    interface Driver extends SimpleBeanEditorDriver<ADatP3CoordinatesDto, ADatP3CoordinatesEditor> {
    }
}
