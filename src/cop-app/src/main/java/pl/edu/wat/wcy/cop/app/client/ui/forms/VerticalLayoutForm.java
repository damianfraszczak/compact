/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
// Defines vertical layout form UI.


public class VerticalLayoutForm implements AbstractForm.LayoutForm {
    protected int rowCount;
    protected VerticalLayoutContainer container = new VerticalLayoutContainer();
    protected VerticalLayoutData data = new VerticalLayoutData(1, -1, new Margins(5, 5, 0, 5));

    protected ScrollPanel scrollPanel = new ScrollPanel();

    /**
     *
     */
    public VerticalLayoutForm() {
        super();
        scrollPanel.getElement().setAttribute("style", "overflow-x:hidden");
        scrollPanel.add(container);
    }

    @Override
    public Widget asWidget() {
        return scrollPanel;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm.LayoutForm#addRow(
     * com.google.gwt.user.client.ui.Widget, java.lang.String)
     */
    @Override
    public void addRow(Widget widget, String label) {
        FieldLabel flabel = new FieldLabel(widget, label);
        flabel.setWidth(120);
        container.add(flabel, data);
        rowCount++;

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm.LayoutForm#addRow(
     * java.lang.String, com.google.gwt.user.client.ui.Widget,
     * com.sencha.gxt.core.client.util.Margins)
     */
    @Override
    public void addRow(String label, Widget widget, Margins marigns) {
        FieldLabel flabel = new FieldLabel(widget, label);
        flabel.setWidth(120);
        container.add(flabel, new VerticalLayoutData(1, -1, marigns));
        rowCount++;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm.LayoutForm#addRow(
     * java.lang.String, com.google.gwt.user.client.ui.Widget)
     */
    @Override
    public void addRow(String label, Widget widget) {
        FieldLabel flabel = new FieldLabel(widget, label);
        flabel.setWidth(120);
        container.add(flabel, data);
        rowCount++;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm.LayoutForm#addRow(
     * java.lang.String, com.google.gwt.user.client.ui.IsWidget)
     */
    @Override
    public void addRow(String label, IsWidget widget) {
        FieldLabel flabel = new FieldLabel(widget.asWidget(), label);
        flabel.setWidth(120);
        container.add(new FieldLabel(flabel, label), data);
        rowCount++;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm.LayoutForm#addRow(
     * com.google.gwt.user.client.ui.Widget)
     */
    @Override
    public void addRow(Widget widget) {
        container.add(widget, data);
        rowCount++;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm.LayoutForm#
     * addRowWithMargins(com.google.gwt.user.client.ui.Widget)
     */
    @Override
    public void addRowWithMargins(Widget widget) {
        rowCount++;

    }

    @Override
    public int getRowsCount() {
        return rowCount;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm.LayoutForm#
     * getContainer()
     */
    @Override
    public IsWidget getContainer() {
        return null;
    }

}
