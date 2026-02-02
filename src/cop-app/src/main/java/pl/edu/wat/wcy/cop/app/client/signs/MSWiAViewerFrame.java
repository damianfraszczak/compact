/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.signs;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import org.fusesource.restygwt.client.Method;
import pl.edu.wat.wcy.cop.app.client.services.server.SymbolServiceProvider;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractDialog;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreePanel;
import pl.edu.wat.wcy.cop.app.client.utils.LoggingMethodCallback;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtUtils;
import pl.edu.wat.wcy.cop.app.client.utils.images.IconsUtils;
import pl.edu.wat.wcy.cop.app.shared.MSWiASymbol;
import pl.edu.wat.wcy.cop.app.shared.response.OkResponse;
// Represents ms wi a viewer frame.

public class MSWiAViewerFrame extends AbstractDialog {
    private static int SYMBOL_PREVIEW_IMAGE_SIZE = 200;

    private MSWiATreePanel panel;
    private Image symbolPreview;

    public MSWiAViewerFrame(SelectHandler handler) {
        this(MESSAGES.mwsiaviewer_title(), handler);
    }

    public MSWiAViewerFrame() {
        this(MESSAGES.mwsiaviewer_title(), null);
    }

    public MSWiAViewerFrame(String title, SelectHandler handler) {
        super(title, handler);
    }

    /**
     * @return
     */
    public MSWiASymbol getSelectedSymol() {
        return panel.getSelectedItem().getObject();
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractDialog#beforeShow()
     */
    @Override
    protected void beforeShow() {
        super.beforeShow();
        panel.reloadData();
        GxtUtils.maximize(window);

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractDialog#getMainComponent(
     * )
     */
    @Override
    protected IsWidget getMainComponent() {
        BorderLayoutContainer con = new BorderLayoutContainer();
        BorderLayoutData eastData = new BorderLayoutData(GxtUtils.getScreenWidth() / 3);
        eastData.setMargins(GxtUtils.getDefaultMarginForTheme());
        con.setEastWidget(createImagePreview(), eastData);
        panel = new MSWiATreePanel(symbolPreview);
        con.setCenterWidget(panel, new MarginData(GxtUtils.getDefaultMarginForTheme()));
        return con;
    }

    private Widget createImagePreview() {
        symbolPreview = new Image();
        HBoxLayoutContainer c = new HBoxLayoutContainer();
        c.setPadding(new Padding(5));
        c.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
        c.setPack(BoxLayoutPack.CENTER);
        BoxLayoutData layoutData = new BoxLayoutData(new Margins(0, 5, 0, 0));
        c.add(symbolPreview, layoutData);
        symbolPreview.setHeight(SYMBOL_PREVIEW_IMAGE_SIZE + "px");
        symbolPreview.setWidth(SYMBOL_PREVIEW_IMAGE_SIZE + "px");
        c.setHeight(SYMBOL_PREVIEW_IMAGE_SIZE + 20);
        return c;

    }
}

class MSWiATreePanel extends TreePanel<MSWiASymbol, String> {

    private Image symbolPreview;

    /**
     * @param symbolPreview
     */
    public MSWiATreePanel(Image symbolPreview) {
        super();
        this.symbolPreview = symbolPreview;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.tree.TreePanel#loadTreeDataFromRoot(
     * com.google.gwt.core.client.Callback)
     */
    @Override
    protected void loadTreeDataFromRoot(Callback<TreeElement<MSWiASymbol, String>, Exception> callback) {
        callback.onSuccess(MSWiASymbolsProvider.getRootTreeElement());

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.tree.TreePanel#onItemClick(pl.edu.wat.
     * wcy.cop.app.client.ui.tree.TreeElement)
     */
    @Override
    protected void onItemClick(TreeElement<MSWiASymbol, String> selectedItem) {
        super.onItemClick(selectedItem);
        if (selectedItem.getObject() != null && selectedItem.getObject().getSymbols().isEmpty() && selectedItem.getObject().getCode() != null)  {
            SymbolServiceProvider.INSTANCE.getBase64ImageFromCache(selectedItem.getObject().getCode(),
                    new LoggingMethodCallback<OkResponse<String>>() {

                        @Override
                        protected void success(Method method, OkResponse<String> response) {
                            if(response.getContent() != null){
                                setImage(IconsUtils.getBase64ImgSrc(response.getContent()));
                            }

                        }
                    });
        }
    }

    private void setImage(String symbolImageUrl) {
        symbolPreview.setUrl("");
        if (symbolImageUrl != null)
            symbolPreview.setUrl(symbolImageUrl);
    }

    @Override
    protected boolean isCheckable() {
        return false;
    }
}
