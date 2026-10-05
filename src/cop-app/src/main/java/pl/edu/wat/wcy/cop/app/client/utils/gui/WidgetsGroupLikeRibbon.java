/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.gui;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.button.ButtonGroup;

import java.util.LinkedList;
import java.util.List;
// Represents widgets group like ribbon.


public abstract class WidgetsGroupLikeRibbon implements IsWidget {

    public static final String WIDGETS_GROUP_LIKE_RIBBON_DATA_KEY = "WIDGETS_GROUP_LIKE_RIBBON_DATA_KEY";
    protected List<Widget> widgets = new LinkedList<>();
    protected ButtonGroup group;
    protected FlexTable flexTable;

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget() {
        if (group == null) {
            flexTable = new FlexTable();
            group = new ButtonGroup();
            group.setHeading(getButtonGroupHeading());
            group.add(flexTable);

            addRibbonElementsUsingConfigureWidgetPlacement();
        }
        return group;
    }

    public void updateComponentState() {

    }

    public Component getComponentByKey(Object key) {
        Component foundComponent = null;
        for (int i = 0; i < getWidgets().size(); i++) {
            Widget widget = getWidgets().get(i);
            if (widget instanceof Component) {
                Component processedComponent = (Component) widget;
                Object data = processedComponent.getData(WIDGETS_GROUP_LIKE_RIBBON_DATA_KEY);
                if (data != null && key.equals(data)) {
                    foundComponent = processedComponent;
                    i = getWidgets().size();
                }
            }
        }
        return foundComponent;
    }

    /**
     *
     */
    protected abstract void addRibbonElementsUsingConfigureWidgetPlacement();

    /**
     * @return
     */
    protected abstract String getButtonGroupHeading();

    protected void configureWidgetPlacement(Widget widget, int row, int column) {
        this.configureWidgetPlacement(widget, row, column, null);
    }

    protected void configureWidgetPlacement(Widget widget, int row, int column, Object data) {
        if (widget instanceof Component) {
            ((Component) widget).setData(WIDGETS_GROUP_LIKE_RIBBON_DATA_KEY, data);
        }
        flexTable.setWidget(row, column, widget);
        widgets.add(widget);
    }

    /**
     * @return the widgets
     */
    public List<Widget> getWidgets() {
        return widgets;
    }

}
