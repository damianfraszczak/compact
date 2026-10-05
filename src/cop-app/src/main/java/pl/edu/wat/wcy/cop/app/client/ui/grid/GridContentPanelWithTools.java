/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.grid;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
// Represents grid content panel with tools.


public class GridContentPanelWithTools<T> implements IsWidget {

    private ContentPanel panel;
    private GridPanel<T> grid;

    /**
     * @return the grid
     */
    public GridPanel<T> getGrid() {
        return grid;
    }

    /**
     * @param grid
     *            the grid to set
     */
    public void setGrid(GridPanel<T> grid) {
        this.grid = grid;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget() {
        if (panel == null) {
            panel = new ContentPanel();
        }
        return panel;
    }

}
