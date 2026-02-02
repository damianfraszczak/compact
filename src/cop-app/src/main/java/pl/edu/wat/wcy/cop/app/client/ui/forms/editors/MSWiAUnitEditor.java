/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms.editors;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.HtmlEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.signs.MSWiAViewerFrame;
import pl.edu.wat.wcy.cop.app.client.ui.forms.VBoxLayoutForm;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.shared.MSWiASymbol;
import pl.edu.wat.wcy.cop.app.shared.domain.MSWiAUnitDto;
// Edits ms wi a unit settings.

public class MSWiAUnitEditor implements Editor<MSWiAUnitDto>, IsWidget {
    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    public TextField code = FormUtils.createRequiredTextField();
    @UiField
    GeoPointEditor point = GWT.create(GeoPointEditor.class);
    @UiField
    MapSymbolEditor mapSymbol = GWT.create(MapSymbolEditor.class);
    HtmlEditor description = FormUtils.createHtmlEditor();
    TextField name = FormUtils.createRequiredTextField();

    // private String chapterDeliminator;
    // private int formation;
    // private int type;
    @Ignore
    TabPanel container = new TabPanel();

    /**
     *
     */
    public MSWiAUnitEditor() {
        super();
        VBoxLayoutForm form = new VBoxLayoutForm();
        TextButton button = new TextButton(MESSAGES.mswiaunit_change_code());

        button.addSelectHandler(new SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                MSWiAViewerFrame frame = new MSWiAViewerFrame();
                frame.setOkHandler(new SelectHandler() {

                    @Override
                    public void onSelect(SelectEvent event) {
                        MSWiASymbol selectedSymbol = frame.getSelectedSymol();
                        if (selectedSymbol != null)
                            code.setText(selectedSymbol.getCode());
                    }
                });
                frame.show();
            }
        });

        form.addRow(MESSAGES.mswiaunit_code(), FormUtils.createHorizontalPanelWith2Elements(code, button, 0.8, 0.2));

        code.setEnabled(false);
        form.addRow(MESSAGES.mswiaunit_description(), description);
        form.addRow(MESSAGES.mswiaunit_name(), name);

        container.add(GxtComponentsUtils.createContentPanel(form), MESSAGES.mswia());
        container.add(GxtComponentsUtils.createContentPanel(mapSymbol.asWidget()), MESSAGES.mapsymbol());
        container.add(GxtComponentsUtils.createContentPanel(point.asWidget()), MESSAGES.geopoint());

        container.setActiveWidget(container.getWidget(0));
        container.setTabScroll(true);
        container.setBodyBorder(false);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget() {
        return container;
    }

    public int getRowsCount() {
        return mapSymbol.getRowsCount();
    }
}
