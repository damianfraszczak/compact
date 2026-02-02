package pl.edu.wat.wcy.cop.app.client.ui.grid;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import com.google.gwt.i18n.client.NumberFormat;
// Represents percentage cell.

public class PercentageCell<T extends Number> extends AbstractCell<T> {
    final static NumberFormat number = NumberFormat.getFormat("0.00");

    @Override
    public void render(Context context, T value, SafeHtmlBuilder sb) {
        if (value == null) {
            value = (T) new Double(0);
        }
        double val = value.doubleValue();
        if (val < 1.0) {
            String v = number.format(val * 100);
            sb.appendHtmlConstant("<span>" + v + " %</span>");
        } else {
            String v = number.format(val);
            sb.appendHtmlConstant("<span>" + v + " %</span>");
        }

    }
}
