/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.forms.editors;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
// Edits with code image object settings.


public abstract class WithCodeImageObjectEditor<T> implements Editor<T>, IsWidget {

    @Ignore
    protected Image symbolImage = new Image();

    public void setImage(String symbolImageUrl) {
        symbolImage.setUrl("");
        symbolImage.setUrl(symbolImageUrl);
    }

    protected Widget createImageSymbol() {
        HBoxLayoutContainer c = new HBoxLayoutContainer();
        c.setPadding(new Padding(5));
        c.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
        c.setPack(BoxLayoutPack.CENTER);
        BoxLayoutData layoutData = new BoxLayoutData(new Margins(0, 5, 0, 0));
        c.add(symbolImage, layoutData);
        symbolImage.setHeight("100px");
        symbolImage.setWidth("100px");
        c.setHeight(120);
        return c;

    }
}
