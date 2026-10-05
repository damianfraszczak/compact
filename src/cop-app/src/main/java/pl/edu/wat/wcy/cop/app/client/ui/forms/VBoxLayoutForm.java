/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
// Defines v box layout form UI.

public class VBoxLayoutForm implements AbstractForm.LayoutForm {
    protected int rowCount;
    protected VerticalLayoutContainer verticalContainer = new VerticalLayoutContainer();
    protected VBoxLayoutContainer container = new VBoxLayoutContainer(VBoxLayoutAlign.STRETCH);
    protected BoxLayoutData data = new BoxLayoutData(new Margins(5, 5, 0, 5));
    protected ScrollPanel scrollPanel = new ScrollPanel();

    /**
     *
     */
    public VBoxLayoutForm() {
        super();
        scrollPanel.getElement().setAttribute("style", "overflow-x:hidden");
        scrollPanel.add(container);
    }

    @Override
    public Widget asWidget() {
        return scrollPanel;
    }

    public void addRow(Widget widget, String label) {
        FieldLabel flabel = new FieldLabel(widget, label);
        flabel.setWidth(120);
        container.add(flabel, data);
        rowCount++;
    }

    public void addRow(Widget widget, BoxLayoutData customData) {
        container.add(widget, customData);
    }

    public void addRow(String label, Widget widget, BoxLayoutData customData) {
        FieldLabel flabel = new FieldLabel(widget, label);
        flabel.setWidth(120);
        container.add(flabel, customData);
        rowCount++;
    }

    public void addRow(String label, Widget widget, Margins marigns) {
        FieldLabel flabel = new FieldLabel(widget, label);
        flabel.setWidth(120);
        container.add(flabel, new BoxLayoutData(marigns));
        rowCount++;
    }

    public void addRow(String label, Widget widget) {
        FieldLabel flabel = new FieldLabel(widget, label);
        flabel.setWidth(120);
        container.add(flabel, data);
        rowCount++;
    }

    public void addRow(String label, IsWidget widget) {
        FieldLabel flabel = new FieldLabel(widget.asWidget(), label);
        flabel.setWidth(120);
        container.add(flabel, data);
        rowCount++;
    }

    public void addRow(Widget widget, Margins marigns) {
        container.add(widget, new BoxLayoutData(marigns));
        rowCount++;
    }

    public void addRow(Widget widget) {
        container.add(widget);
        rowCount++;
    }

    public void addRow(IsWidget widget) {
        container.add(widget);
        rowCount++;
    }

    public void addRowWithMargins(Widget widget) {
        container.add(widget, data);
        rowCount++;
    }

    @Override
    public int getRowsCount() {
        return rowCount;
    }

    public VBoxLayoutContainer getContainer() {
        return container;
    }
}
