/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.gui;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HasValue;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
// Represents widgets group like ribbon with buttons group.


public abstract class WidgetsGroupLikeRibbonWithButtonsGroup extends WidgetsGroupLikeRibbon {
    private ToggleGroup tgroup;
    private SelectHandler selectHandler;

    public void setSelectedButtonByKey(Object key) {
        Component component = getComponentByKey(key);
        if (component != null) {
            // niestety nie da sie parametryzowanego isntanceofa zrobic
            if (component instanceof HasValue<?>) {
                ((HasValue<Boolean>) component).setValue(true);
            }
        }
    }

    protected ToggleButton createToogleButton(String title, ImageResource icon, Object data, int row, int column) {
        return this.createToogleButton(title, icon, row, column, data);
    }

    protected ToggleButton createToogleButton(String title, ImageResource icon, int row, int column, Object data) {
        SelectHandler selectHandler = getSelectHandler();
        if (tgroup == null) {
            tgroup = new ToggleGroup();
        }
        ToggleButton button = GxtComponentsUtils.createToogleButton(title, icon);
        button.addSelectHandler(selectHandler);
        configureWidgetPlacement(button, row, column, data);
        tgroup.add(button);
        return button;
    }

    protected TextButton createTextButton(String title, ImageResource icon, Object data, int row, int column) {
        SelectHandler selectHandler = getSelectHandler();
        if (tgroup == null) {
            tgroup = new ToggleGroup();
        }
        TextButton button = GxtComponentsUtils.createTextButton(title, selectHandler, icon);
        configureWidgetPlacement(button, row, column, data);
        return button;
    }

    /**
     * @return
     */
    private SelectHandler getSelectHandler() {
        if (selectHandler == null) {
            selectHandler = getSelectHandlerImpl();
        }
        return selectHandler;
    }

    /**
     * @return
     */
    protected abstract SelectHandler getSelectHandlerImpl();
}
