/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.tree;

import com.google.gwt.core.client.Callback;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.IconProvider;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.images.AppImages;
// Renders tree grid panel UI.


public abstract class TreeGridPanel<O, K> implements IsWidget {
    protected static Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    protected ContentPanel panel;
    protected TreeGrid<TreeElement<O, K>> tree;
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
     * @param callback
     */
    protected abstract void loadTreeDataFromRoot(Callback<TreeElement<O, K>, Exception> callback);

    /**
     *
     */
    private void createGui() {
        tree = TreeUtils.buildTreeGrid(getColumnModel(), getTreeColumn());

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
    protected abstract ColumnConfig getTreeColumn();

    /**
     * @return
     */
    protected abstract ColumnModel getColumnModel();

    /**
     * @return
     */
    protected IconProvider<TreeElement<O, K>> createIconProvider() {
        // TODO Auto-generated method stub
        return new IconProvider<TreeElement<O, K>>() {

            @Override
            public ImageResource getIcon(TreeElement<O, K> model) {
                // TODO Auto-generated method stub
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
        menuButton.setMenu(menu);
        toolbar.add(menuButton);
        return toolbar;
    }

    /**
     *
     */
    public void reloadData() {
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

}
