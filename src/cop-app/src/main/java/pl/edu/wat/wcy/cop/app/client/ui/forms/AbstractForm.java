package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.utils.DefaultAsyncCallback;
import pl.edu.wat.wcy.cop.app.client.utils.gui.DialogUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
// Defines abstract form UI.

public abstract class AbstractForm<M, E extends IsWidget & Editor<M>> {

    protected static Messages messages = CopGinInjector.INSTANCE.getMessages();
    protected Dialog window;
    protected boolean readOnly = false;
    protected E editor;
    protected M model;
    protected SimpleBeanEditorDriver<M, E> driver;
    protected SelectHandler okHandler;

    protected boolean showCancel = true;
    private static LinkedList<AbstractForm> OPENED_FORMS = new LinkedList<AbstractForm>();

    public static List<AbstractForm> getVisibleForms() {
        return OPENED_FORMS.stream().filter(x -> x.isVisible()).collect(Collectors.toList());
    }

    private boolean isVisible() {
        return this.window.isVisible();
    }

    public static void addForm(AbstractForm form) {
        OPENED_FORMS.add(0, form);
        if (OPENED_FORMS.size() == 10) {
            OPENED_FORMS.removeLast();
        }
    }

    public static AbstractForm getFormByType(Class cls) {
        return OPENED_FORMS.stream().filter(x -> x.getClass().equals(cls)).findFirst().orElse(null);
    }

    /**
     * @param model
     * @param okHandler
     */
    public AbstractForm(M model, SelectHandler okHandler) {
        this(model, messages.defaultdialog_title(), okHandler, false);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public AbstractForm(M model, String title, SelectHandler okHandler) {
        this(model, title, okHandler, false);
    }

    /**
     * @param model
     * @param okHandler
     * @param readOnly
     */
    public AbstractForm(M model, SelectHandler okHandler, boolean readOnly) {
        this(model, messages.defaultdialog_title(), okHandler, readOnly);
    }

    public AbstractForm(M model, String title, SelectHandler okHandler, boolean readOnly) {
        this.model = model;
        this.okHandler = okHandler;
        verifyModelState(model);
        initComponents();
        prepareUI(title);

    }


    protected void verifyModelState(M model) {

    }

    /**
     * @return the model
     */
    public M getModel() {
        return model;
    }

    /**
     * @return the readOnly
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * @param readOnly the readOnly to set
     */
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * @param title
     */
    protected void prepareUI(String title) {
        window = new Dialog();
        window.setModal(true);
        window.setBlinkModal(true);
        window.setBodyBorder(false);
        window.setResizable(true);
        window.setHideOnButtonClick(true);
        window.setShadow(true);
        window.setPredefinedButtons();
        window.setMaximizable(true);
        createButtonPanel(window);

        if (title != null) {
            if (messages.defaultdialog_title().equals(title)) {
                title = getDefaultTitle();
            }
            window.setHeading(title);
        }
        window.addShowHandler(new ShowHandler() {

            @Override
            public void onShow(ShowEvent event) {

            }
        });
        createImportExportTool();
        window.setPixelSize(getDefinedDialogWidth(), getDefinedDialogHeight());
        beforeShow();
        window.show();
        window.setWidget(editor);
        addForm(this);
    }

    public void show() {
        if (window != null) {
            window.show();
        }
    }

    public void hide() {
        if (window != null) {
            window.hide();
        }
    }

    protected abstract String getDefaultTitle();


    protected int getDefinedDialogHeight() {
        return Math.min(GxtUtils.MIN_HEIGHT, getMaxRowsCount() * GxtUtils.FORM_FIELD_SIZE);
    }


    protected int getDefinedDialogWidth() {
        return GxtUtils.MIN_WIDTH;
    }

    /**
     *
     */
    private void createImportExportTool() {
        JsonEncoderDecoder<M> encoderDecoder = getEncoderDecoder();
        if (encoderDecoder != null) {
            ToolButton importButton = new ToolButton(ToolButton.PLUS);
            importButton.setToolTip("JSON import");
            importButton.addSelectHandler(x -> {
                DialogUtils.showMultiLineInputDialog(messages.data_import(), messages.data_import_json(),
                        new DefaultAsyncCallback<String>() {

                            @Override
                            public void onSuccess(String result) {
                                M model = encoderDecoder.decode(result);
                                initModel(model);
                            }
                        });

            });
            window.addTool(importButton);

            ToolButton exportButton = new ToolButton(ToolButton.SAVE);
            exportButton.addSelectHandler(x -> {
                DialogUtils.showAlertDialog(messages.data_export(), encoderDecoder.encode(model).toString());

            });
            window.addTool(exportButton);
        }

    }

    /**
     * @param window
     */
    protected void createButtonPanel(Dialog window) {
        if(showCancel){
            window.addButton(new TextButton(messages.defaultdialog_cancel(), new SelectHandler() {
                @Override
                public void onSelect(SelectEvent event) {
                    window.hide();
                }
            }));
        }
        if (!isReadOnly()) {
            window.addButton(new TextButton(messages.defaultdialog_ok(), new SelectHandler() {

                @Override
                public void onSelect(SelectEvent event) {
                    onSaveClick(event);

                }
            }));
        }

    }

    protected void onSaveClick(SelectEvent event) {
        saveChanges();
        if (validate()) {
            window.hide();
            afterOkClicked();
            okHandler.onSelect(event);
        }
    }

    public void saveChanges(){
        driver.flush();
    }

    protected void initComponents() {
        driver = buildDriver();
        editor = buildEditor();
        driver.initialize(editor);
        driver.edit(model);

    }

    /**
     * @param model
     */
    public void initModel(M model) {
        this.model = model;
        driver.edit(this.model);
    }

    /**
     * @return
     */
    protected boolean validate() {
        return true;
    }

    /**
     *
     */
    protected void beforeShow() {
    }

    protected abstract int getMaxRowsCount();

    /**
     *
     */
    protected void afterOkClicked() {
    }

    protected abstract SimpleBeanEditorDriver<M, E> buildDriver();

    protected abstract E buildEditor();

    protected JsonEncoderDecoder<M> getEncoderDecoder() {
        return null;
    }

    public interface LayoutForm extends IsWidget {

        void addRow(Widget widget, String label);

        void addRow(String label, Widget widget, Margins marigns);

        void addRow(String label, Widget widget);

        void addRow(String label, IsWidget widget);

        void addRow(Widget widget);

        void addRowWithMargins(Widget widget);

        int getRowsCount();

        IsWidget getContainer();
    }

}
