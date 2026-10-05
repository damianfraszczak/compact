package pl.edu.wat.wcy.cop.app.client.ol.forms;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiField;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.menu.ColorMenu;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractEditor;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.utils.CopLogger;
import pl.edu.wat.wcy.cop.app.shared.ol.OLStyleOptions;
// Edits ol style settings.

public class OLStyleEditor extends AbstractEditor<OLStyleOptions> {
    // @com.google.gwt.editor.client.Editor.Ignore
    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    @UiField
    CheckBox hasFill = new CheckBox();
    @UiField
    TextField opacity = FormUtils.createRequiredTextField();

    @UiField
    TextField releaseAreaColor = FormUtils.createRequiredTextField();
    @UiField
    TextField hazardArea1Color = FormUtils.createRequiredTextField();
    @UiField
    TextField hazardArea2Color = FormUtils.createRequiredTextField();
    @UiField
    TextField hazardArea3Color = FormUtils.createRequiredTextField();

    /**
     *
     */
    public OLStyleEditor() {
        super();
        init();
        CopLogger.getInstance().debug(this, releaseAreaColor.getCurrentValue());
        addRow(MESSAGES.layerdialog_fillOrStroke(), hasFill);
        addRow(MESSAGES.layerdialog_opacity(), opacity);
        addRow(MESSAGES.layerdialog_hazardArea1Color(), addHazardArea1ColorMenu());
        addRow(MESSAGES.layerdialog_hazardArea2Color(), addHazardArea2ColorMenu());
        addRow(MESSAGES.layerdialog_hazardArea3Color(), addHazardArea3ColorMenu());
        addRow(MESSAGES.layerdialog_releaseAreaColor(), addReleaseAreaColorMenu());
    }

    private TextButton addReleaseAreaColorMenu() {
        final TextButton releaseAreaColorButton = new TextButton("FE0000");
        final ColorMenu releaseAreaColorMenu = new ColorMenu();
        releaseAreaColorMenu.setFocusOnShow(false);
        releaseAreaColorMenu.getPalette().addValueChangeHandler(new ValueChangeHandler<String>() {

            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                releaseAreaColorMenu.hide();
                releaseAreaColorButton.setText(event.getValue());
                releaseAreaColor.setValue(event.getValue());
            }
        });
        releaseAreaColorButton.setMenu(releaseAreaColorMenu);
        return releaseAreaColorButton;
    }

    private TextButton addHazardArea1ColorMenu() {
        final TextButton hazardArea1ColorButton = new TextButton("FEFE00");
        final ColorMenu hazardArea1ColorMenu = new ColorMenu();
        hazardArea1ColorMenu.setFocusOnShow(false);
        hazardArea1ColorMenu.getPalette().addValueChangeHandler(new ValueChangeHandler<String>() {

            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                hazardArea1ColorMenu.hide();
                hazardArea1ColorButton.setText(event.getValue());
                hazardArea1Color.setValue(event.getValue());
            }
        });
        hazardArea1ColorButton.setMenu(hazardArea1ColorMenu);
        return hazardArea1ColorButton;
    }

    private TextButton addHazardArea2ColorMenu() {
        final TextButton hazardArea2ColorButton = new TextButton("FEC300");
        final ColorMenu hazardArea2ColorMenu = new ColorMenu();
        hazardArea2ColorMenu.setFocusOnShow(false);
        hazardArea2ColorMenu.getPalette().addValueChangeHandler(new ValueChangeHandler<String>() {

            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                hazardArea2ColorMenu.hide();
                hazardArea2ColorButton.setText(event.getValue());
                hazardArea2Color.setValue(event.getValue());
            }
        });
        hazardArea2ColorButton.setMenu(hazardArea2ColorMenu);
        return hazardArea2ColorButton;
    }

    private TextButton addHazardArea3ColorMenu() {
        final TextButton hazardArea3ColorButton = new TextButton("FE9000");
        final ColorMenu hazardArea3ColorMenu = new ColorMenu();
        hazardArea3ColorMenu.setFocusOnShow(false);
        hazardArea3ColorMenu.getPalette().addValueChangeHandler(new ValueChangeHandler<String>() {

            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                hazardArea3ColorMenu.hide();
                hazardArea3ColorButton.setText(event.getValue());
                hazardArea3Color.setValue(event.getValue());
            }
        });
        hazardArea3ColorButton.setMenu(hazardArea3ColorMenu);
        return hazardArea3ColorButton;
    }

    private void init() {
        hazardArea3Color.setValue("FE9000");
        hazardArea2Color.setValue("FEC300");
        hazardArea1Color.setValue("FEFE00");
        releaseAreaColor.setValue("FE0000");

    }
}
