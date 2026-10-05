package pl.edu.wat.wcy.cop.app.client.ui.grid;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
// Represents boolean cell.

public class BooleanCell extends AbstractCell<Boolean> {


    @Override
    public void render(Context context, Boolean value, SafeHtmlBuilder sb) {
        if (value == null) {
            value = false;
        }
        String v = value ? "Tak" : "Nie";
        sb.appendHtmlConstant("<span>" + v + "</span>");

    }
}