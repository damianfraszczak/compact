package pl.edu.wat.wcy.cop.app.client.ui.grid;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
// Represents double cell.

public class DoubleCell <T extends Number> extends AbstractCell<T> {
    final static NumberFormat number = NumberFormat.getFormat("0.00");

    @Override
    public void render(Context context, T value, SafeHtmlBuilder sb) {
        if(value == null){
            value = (T) new Double(0);
        }
        String v = number.format((Double) value * 100);
        sb.appendHtmlConstant("<span>" + v + "</span>");
    }
}