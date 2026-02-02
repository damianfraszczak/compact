/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.tree;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.Callback;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.data.shared.IconProvider;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.StoreFilterField;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.sencha.gxt.widget.core.client.tree.Tree;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.images.AppImages;
// Renders tree panel UI.


public abstract class TreePanel<O, K> implements IsWidget {
    protected static Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    protected ContentPanel panel;
    protected Tree<TreeElement<O, K>, String> tree;
    protected ToolBar toolBar;

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget() {
        if (panel == null) {
            createGui();
        }
        return panel;
    }

    /**
     *
     */
    public void expandAll() {
        tree.expandAll();
    }

    /**
     *
     */
    public void collapseAll() {
        tree.collapseAll();
    }

    /**
     *
     */
    public void reloadData() {
        if (panel == null) {
            asWidget();
        }
        tree.getStore().clear();
        loadTreeDataFromRoot(new Callback<TreeElement<O, K>, Exception>() {

            @Override
            public void onFailure(Exception reason) {
            }

            @Override
            public void onSuccess(TreeElement<O, K> result) {
                tree.getStore().clear();
                TreeUtils.addRootWithChildren(tree, result);
                tree.expandAll();
            }

        });
    }

    /**
     * @return
     */
    public TreeElement<O, K> getSelectedItem() {
        return tree.getSelectionModel().getSelectedItem();
    }

    /**
     * @param callback
     */
    protected abstract void loadTreeDataFromRoot(Callback<TreeElement<O, K>, Exception> callback);

    /**
     *
     */
    protected void createGui() {
        tree = TreeUtils.buildTree(isCheckable());
        tree.setCell(createTreeCell());
        tree.setIconProvider(createIconProvider());
        toolBar = buildToolbar();
        VerticalLayoutContainer vlc = new VerticalLayoutContainer();
        vlc.add(toolBar, new VerticalLayoutData(1, -1));
        vlc.add(tree, new VerticalLayoutData(1, 1));
        panel = new ContentPanel();
        panel.setHeaderVisible(false);
        panel.add(vlc);
    }

    /**
     * @return
     */
    protected Cell<String> createTreeCell() {
        SimpleSafeHtmlCell<String> cell = new SimpleSafeHtmlCell<String>(SimpleSafeHtmlRenderer.getInstance(),
                "contextmenu", "click", "touchend") {
            @Override
            public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event,
                                       ValueUpdater<String> valueUpdater) {
                if (event.getButton() == NativeEvent.BUTTON_RIGHT) {
                    event.preventDefault();
                    TreeElement<O, K> selectedElement = tree.getSelectionModel().getSelectedItem();
                    Menu menu = getContextMenuForObject(selectedElement);
                    if (menu != null) {
                        menu.showAt(event.getClientX(), event.getClientY());
                    }
                }
                // click
                else if ("touchend".equals(event.getType()) || "click".equals(event.getType())) {
                    onItemClick(tree.getSelectionModel().getSelectedItem());
                }
            }
        };
        return cell;
    }

    /**
     * @return
     */
    protected IconProvider<TreeElement<O, K>> createIconProvider() {
        return new IconProvider<TreeElement<O, K>>() {

            @Override
            public ImageResource getIcon(TreeElement<O, K> model) {
                return model.getImage();
            }
        };
    }

    /**
     * @return
     */
    protected ToolBar buildToolbar() {
        ToolBar toolbar = new ToolBar();
        Menu menu = new Menu();
        StoreFilterField<TreeElement<O, K>> filter = createFilter();
        // toolbar.add(filter);
        filter.bind(tree.getStore());
        menu.add(GxtComponentsUtils.createMenuItem(MESSAGES.treepanel_treeactions_expandall(),
                new SelectionHandler<Item>() {
                    @Override
                    public void onSelection(SelectionEvent<Item> event) {
                        tree.expandAll();
                    }
                }, AppImages.INSTANCE.expand()));
        menu.add(GxtComponentsUtils.createMenuItem(MESSAGES.treepanel_treeactions_collapseall(),
                new SelectionHandler<Item>() {
                    @Override
                    public void onSelection(SelectionEvent<Item> event) {
                        tree.collapseAll();
                    }
                }, AppImages.INSTANCE.collapse()));
        TextButton menuButton = new TextButton(MESSAGES.treepanel_treeactions());
        addItemsToToolbar(toolbar, filter, menuButton);
        menuButton.setMenu(menu);
        // toolbar.add(menuButton);
        return toolbar;
    }

    protected void addItemsToToolbar(ToolBar toolbar, Widget... components) {
        for (Widget w : components) {
            toolbar.add(w);
        }
    }

    protected StoreFilterField<TreeElement<O, K>> createFilter() {
        return new StoreFilterField<TreeElement<O, K>>() {

            @Override
            protected boolean doSelect(Store<TreeElement<O, K>> store, TreeElement<O, K> parent, TreeElement<O, K> item,
                                       String filter) {
                return filter(item, filter);
            }
        };
    }

    /**
     * @param selectedElement
     * @return
     */
    protected Menu getContextMenuForObject(TreeElement<O, K> selectedElement) {
        return null;
    }

    /**
     * @param selectedItem
     */
    protected void onItemClick(TreeElement<O, K> selectedItem) {
    }

    /**
     * @param item
     * @param filter
     * @return
     */
    protected boolean filter(TreeElement<O, K> item, String filter) {
        return item.getKey().toString().toLowerCase().contains(filter)
                || item.getObjectDesct().toLowerCase().contains(filter);
    }

    /**
     * @return
     */
    protected boolean isCheckable() {
        return true;
    }
}
