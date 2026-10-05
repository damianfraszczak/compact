/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.mvp.impl;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ui.forms.editors.ScenarioEditor;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtDefaultContainer;
import pl.edu.wat.wcy.cop.app.shared.domain.ScenarioDto;

import java.util.LinkedList;
import java.util.List;
// Represents configuration view impl.

public class ConfigurationViewImpl implements ConfigurationView {

    private IsWidget widget;
    @Inject
    private Driver driver;
    @Inject
    private ScenarioEditor editor;
    @Inject
    private Messages messages;
    private Presenter presenter;
    private ScenarioDto model;
    private ComboBox<Long> availableScenarioComboBox;

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget() {
        if (widget == null) {
            prepareUI();
        }
        return widget.asWidget();
    }

    /**
     *
     */
    private void prepareUI() {

        FramedPanel framedPanel = new FramedPanel();
        framedPanel.setHeading(messages.configuration_title());
        buildConfigurationForm(framedPanel);
        widget = framedPanel;
    }

    /**
     * @param layoutContainer
     */
    private void buildConfigurationForm(FramedPanel panel) {

        availableScenarioComboBox = FormUtils.createComboBox(new LinkedList<>(), new LabelProvider<Long>() {
            @Override
            public String getLabel(Long item) {
                return item + "";
            }
        }, new ModelKeyProvider<Long>() {

            @Override
            public String getKey(Long item) {
                return item + "";
            }
        });

        panel.setWidget(new GxtDefaultContainer(editor));
        panel.addButton(availableScenarioComboBox);
        panel.addButton(
                new TextButton(messages.configuration_load_selected_situation(), new SelectEvent.SelectHandler() {

                    @Override
                    public void onSelect(SelectEvent event) {
                        presenter.loadSelectedScenarioId(availableScenarioComboBox.getValue());
                    }
                }));
        panel.addButton(new TextButton(messages.configuration_generate_situation(), new SelectEvent.SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                presenter.generateSituation();
            }
        }));

        panel.addButton(new TextButton(messages.configuration_visualize(), new SelectEvent.SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                driver.flush();
                presenter.goToVisualisation();
            }
        }));

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.mvp.impl.ConfigurationView#setPresenter(
     * pl.edu.wat.wcy.cop.app.client.mvp.impl.ConfigurationView.Presenter)
     */
    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.mvp.impl.ConfigurationView#setModel(pl.
     * edu.wat.wcy.cop.app.shared.domain.VisualisationConfigurationModel)
     */
    @Override
    public void setModel(ScenarioDto model) {
        this.model = model;
        driver.initialize(editor);
        driver.edit(model);

    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.mvp.impl.ConfigurationView#
     * setAvailableScenariosIds(java.util.List)
     */
    @Override
    public void setAvailableScenariosIds(List<Long> availableScenarioIds) {
        availableScenarioComboBox.getStore().addAll(availableScenarioIds);
        availableScenarioComboBox.getStore().commitChanges();

    }

    interface Driver extends SimpleBeanEditorDriver<ScenarioDto, ScenarioEditor> {
    }

}
