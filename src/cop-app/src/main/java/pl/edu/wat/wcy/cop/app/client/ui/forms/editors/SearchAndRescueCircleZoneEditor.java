package pl.edu.wat.wcy.cop.app.client.ui.forms.editors;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.form.*;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ui.forms.VBoxLayoutForm;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueCircleZoneDto;
// Edits search and rescue circle zone settings.

public class SearchAndRescueCircleZoneEditor  implements Editor<SearchAndRescueCircleZoneDto>, IsWidget {

    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    @UiField
    TextField name = FormUtils.createRequiredTextField();
    @UiField
    HtmlEditor description = FormUtils.createHtmlEditor();
    @UiField
    CheckBox visible = new CheckBox();
    @UiField
    GeoPointEditor point = GWT.create(GeoPointEditor.class);
    @UiField
    NumberField<Double> radius = new NumberField<Double>(new NumberPropertyEditor.DoublePropertyEditor(AppConstants.DEFAULT_NUMBER_FORMAT));
    @UiField
    NumberField<Integer> dispersion = FormUtils.createIntegerField(true);
    @UiField
    public FeatureStyleEditor style = GWT.create(FeatureStyleEditor.class);
    @Ignore
    TabPanel container = new TabPanel();
    /**
     *
     */
    public SearchAndRescueCircleZoneEditor() {
        super();
        VBoxLayoutForm form = new VBoxLayoutForm();
        form.addRow(MESSAGES.poi_zone_name(), name);
        form.addRow(MESSAGES.poi_zone_description(), description);
        form.addRow(MESSAGES.poi_zone_visible(), visible);
        form.addRow(MESSAGES.poi_zone_radius(), radius);
        form.addRow("Dispersion", dispersion);
        container.add(GxtComponentsUtils.createContentPanel(form.asWidget()), "Dane strefy");
        container.add(GxtComponentsUtils.createContentPanel(point.asWidget()), "Położenie");
        container.add(GxtComponentsUtils.createContentPanel(style.asWidget()), "Grafika");
        radius.setEditable(false);
        point.setEditable(false);
    }

    @Override
    public Widget asWidget() {
        return container;
    }

    public int getRowsCount() {
        return 6;
    }
}
