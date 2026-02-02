package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.Window;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import org.fusesource.restygwt.client.Method;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.domain.json.EncodersDecoders;
import pl.edu.wat.wcy.cop.app.client.mvp.CesarActivityMapper;
import pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationActivity;
import pl.edu.wat.wcy.cop.app.client.ui.forms.editors.SearchAndRescueEditor;
import pl.edu.wat.wcy.cop.app.client.utils.DefaultAsyncCallback;
import pl.edu.wat.wcy.cop.app.client.utils.GwtScheduler;
import pl.edu.wat.wcy.cop.app.client.utils.LoggingMethodCallback;
import pl.edu.wat.wcy.cop.app.client.utils.gui.DialogUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.NotificationUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.SearchPlanState;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.searchplan.SearchPlanDomainDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.searchplan.SearchPlanDomainWrapper;
import pl.edu.wat.wcy.cop.app.shared.response.OkResponse;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
// Defines search and rescue form UI.

public class SearchAndRescueForm extends AbstractForm<SearchAndRescueDto, SearchAndRescueEditor> {
    private static int NEW_OBJ_COUNT = 1;

    /**
     * @param model
     * @param okHandler
     */
    public SearchAndRescueForm(SearchAndRescueDto model, SelectEvent.SelectHandler okHandler) {
        super(model, okHandler);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public SearchAndRescueForm(SearchAndRescueDto model, String title, SelectEvent.SelectHandler okHandler) {
        super(model, title, okHandler);
    }

    /**
     * @param model
     * @param okHandler
     * @param readOnly
     */
    public SearchAndRescueForm(SearchAndRescueDto model, SelectEvent.SelectHandler okHandler, boolean readOnly) {
        super(model, okHandler, readOnly);
    }

    /**
     * @param model
     * @param title
     * @param okHandler
     * @param readOnly
     */
    public SearchAndRescueForm(SearchAndRescueDto model, String title, SelectEvent.SelectHandler okHandler, boolean readOnly) {
        super(model, title, okHandler, readOnly);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildDriver()
     */
    @Override
    protected SimpleBeanEditorDriver<SearchAndRescueDto, SearchAndRescueEditor> buildDriver() {
        return GWT.create(SearchAndRescueForm.Driver.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#buildEditor()
     */
    @Override
    protected SearchAndRescueEditor buildEditor() {
        return GWT.create(SearchAndRescueEditor.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#getEncoderDecoder()
     */
    @Override
    protected JsonEncoderDecoder<SearchAndRescueDto> getEncoderDecoder() {
        return EncodersDecoders.SAREncoderDecoderInstance;
    }

    @Override
    protected void beforeShow() {
        super.beforeShow();
        this.editor.style.beforeShow(this.model.getStyle());
        this.editor.areaZoneTreeGridPanel.setSARObject(this.getModel());

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
        return editor.getRowsCount();
    }


    interface Driver extends SimpleBeanEditorDriver<SearchAndRescueDto, SearchAndRescueEditor> {
    }

    @Override
    protected String getDefaultTitle() {
        return "SAR zone";
    }

    @Override
    protected void verifyModelState(SearchAndRescueDto model) {
        super.verifyModelState(model);

    }

    @Override
    protected void prepareUI(String title) {
        editor.areaZoneTreeGridPanel.setForm(this);
        showCancel = false;
        super.prepareUI(title);

    }

    private int reloadIndex = 1;

    public void reloadAccordion() {
        GwtScheduler.delay(100, () -> {
            window.forceLayout();
            window.setPixelSize(window.getElement().getBounds().getWidth() + reloadIndex,
                    window.getElement().getBounds().getHeight());
            reloadIndex *= -1;
        });
    }

    public void startPlan(SearchPlanDomainWrapper plan) {
        driver.flush();
        plan.getDomain().setStartedDate(new Date().getTime());
        plan.getDomain().setState(SearchPlanState.STARTED);
        reloadAccordion();

        NotificationUtils.showToast("Plan poszukiwań", "Rozpoczeto wykonywanie planu");
    }

    public void finishPlan(SearchPlanDomainWrapper plan) {
        driver.flush();
        plan.getDomain().setFinishedDate(new Date().getTime());
        plan.getDomain().setState(SearchPlanState.FINISHED);
        reloadAccordion();
        NotificationUtils.showToast("Plan poszukiwań", "Zakończono wykonywanie planu");
    }

    public void newPlan() {
        if (getModel().getSearchAndRescueSearchPlans() == null) {
            getModel().setSearchAndRescueSearchPlans(new LinkedList<>());
        }
        getModel().getSearchAndRescueSearchPlans().add(new SearchPlanDomainDto());

        initModel(getModel());
        reloadAccordion();
        NotificationUtils.showToast("Plan poszukiwań", "Dodano nowy plan");
    }

    public void doPlan(SearchPlanDomainDto plan) {
        driver.flush();
        if (model.getAreaZones().isEmpty()) {
            DialogUtils.showAlertDialog("Plan poszukiwań", "Nie można wyznaczyć planu bez wyznaczonych sektorów");
            return;
        }
//        if (plan.getHumanResources().isEmpty()) {
//            DialogUtils.showAlertDialog("Plan poszukiwań", "Nie można wyznaczyć planu bez przydzielonych osób");
//            return;
//        }
        model.setRegeneratePlan(true);
        plan.setRegeneratePlan(true);
        CesarActivityMapper cam = (CesarActivityMapper) CopGinInjector.INSTANCE.activityMapper();
        VisualizationActivity va = (VisualizationActivity) cam.getCurrentActivity();
        va.processUpdate(model, new DefaultAsyncCallback<Object>() {
            @Override
            public void onSuccess(Object object) {
                SearchAndRescueDto res = (SearchAndRescueDto) object;
                initModel(res);
                reloadAccordion();
                NotificationUtils.showToast("Plan poszukiwań", "Nowy plan poszukiwań został załadowany");
            }
        });
    }


    @Override
    protected int getDefinedDialogWidth() {
        return super.getDefinedDialogWidth() + 800;
    }

    @Override
    protected int getDefinedDialogHeight() {
        return super.getDefinedDialogHeight() + 100;
    }

}