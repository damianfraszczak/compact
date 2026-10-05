/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.mvp;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
// Defines the contract for shell view.

public interface ShellView extends IsWidget {
    AcceptsOneWidget getDisplay();
}
