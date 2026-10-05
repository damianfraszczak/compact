/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.ContentPanel;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.utils.CopLogger;
// Represents console widget.


public class ConsoleWidget implements IsWidget {

    @Inject
    private CopLogger copLogger;
    @Inject
    private Messages messages;

    private ContentPanel widget;

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget() {
//        if (widget == null) {
//             Grid<LogRecord> grid = FormUtils.createEditorGrid(columnModel,
//             LogRecordPropertyAccess.INSTANCE.key());
//             FormUtils.configureGrid(grid, filters);
//
//        }
        return widget;
    }

}
