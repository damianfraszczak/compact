/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.tree;

import com.google.gwt.dom.client.Style.Overflow;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.gestures.ScrollGestureRecognizer;
import com.sencha.gxt.core.client.gestures.ScrollGestureRecognizer.ScrollDirection;
import com.sencha.gxt.core.client.gestures.TouchEventToGestureAdapter;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.event.CheckChangeEvent;
import com.sencha.gxt.widget.core.client.event.CheckChangeEvent.CheckChangeHandler;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.Tree.CheckCascade;
import com.sencha.gxt.widget.core.client.tree.Tree.CheckState;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;
import pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject;

import java.util.LinkedList;
import java.util.List;
// Provides tree utilities.


public class TreeUtils {

    public static <O, K> Tree<TreeElement<O, K>, String> buildTree(boolean checkable) {
        TreeStore<TreeElement<O, K>> store = new TreeStore<TreeElement<O, K>>((item) -> item.getKey().toString());
        Tree<TreeElement<O, K>, String> tree = new Tree<TreeElement<O, K>, String>(store,
                new ValueProvider<TreeElement<O, K>, String>() {
                    @Override
                    public String getValue(TreeElement<O, K> object) {
                        // TODO Auto-generated method stub
                        return object.getObjectDesct();
                    }

                    @Override
                    public void setValue(TreeElement<O, K> object, String value) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public String getPath() {
                        // TODO Auto-generated method stub
                        return "object";
                    }

                });
        tree.setCheckable(checkable);
        tree.setCheckStyle(CheckCascade.TRI);
        tree.setAutoLoad(true);
        tree.addCheckChangeHandler(new CheckChangeHandler<TreeElement<O, K>>() {

            @Override
            public void onCheckChange(CheckChangeEvent<TreeElement<O, K>> event) {
                setNodeChecked(event.getItem(), event.getChecked());
            }

        });
        return tree;
    }

    /**
     * @return
     */
    public static <O, K> TreeGrid<TreeElement<O, K>> buildTreeGrid(ColumnModel<TreeElement<O, K>> columnModel,
                                                                   ColumnConfig<TreeElement<O, K>, ?> columnConfig) {
        IdentityValueProvider<TreeElement<O, K>> identity = new IdentityValueProvider<TreeElement<O, K>>("checked");
        final CheckBoxSelectionModel<TreeElement<O, K>> selectionModel = new CheckBoxSelectionModel<TreeElement<O, K>>(
                identity);
        List<ColumnConfig<TreeElement<O, K>, ?>> checkboxColumnConfig = new LinkedList<ColumnConfig<TreeElement<O, K>, ?>>(
                columnModel.getColumns());
        checkboxColumnConfig.add(0, selectionModel.getColumn());
        TreeStore<TreeElement<O, K>> store = new TreeStore<TreeElement<O, K>>((item) -> item.getKey().toString());
        TreeGrid<TreeElement<O, K>> treeGrid = new TreeGrid<>(store, new ColumnModel<>(checkboxColumnConfig),
                columnConfig);
        treeGrid.setSelectionModel(selectionModel);
        treeGrid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        treeGrid.getView().setStripeRows(true);
        treeGrid.getView().setColumnLines(true);
        treeGrid.getView().setAutoFill(true);
        treeGrid.setColumnReordering(true);
        treeGrid.setColumnResize(true);
        treeGrid.getElement().getStyle().setOverflowY(Overflow.AUTO);
        new TouchEventToGestureAdapter(treeGrid,
                new ScrollGestureRecognizer(treeGrid.getElement(), ScrollDirection.VERTICAL));

        return treeGrid;
    }

    public static <O, K> void setNodeChecked(Tree<TreeElement<O, K>, String> tree, TreeElement<?, ?> node,
                                             boolean checked) {
        tree.setChecked((TreeElement<O, K>) node, checked ? CheckState.CHECKED : CheckState.UNCHECKED);
    }

    /**
     * @param treeElement
     * @param checkState
     */
    public static <O, K> void setNodeChecked(TreeElement<O, K> treeElement, CheckState checkState) {
        treeElement.setChecked(CheckState.CHECKED.equals(checkState));
    }

    /**
     * @param tree
     * @param result
     */
    public static <O, K> void addRootWithChildren(Tree<TreeElement<O, K>, String> tree, TreeElement<O, K> result) {
        processNode(tree, null, result);
    }

    /**
     * @param tree
     * @param result
     */
    public static <O, K> void addRootWithChildren(TreeGrid<TreeElement<O, K>> tree, TreeElement<O, K> result) {
        processNode(tree, null, result);

    }

    /**
     * @param tree
     * @param parent
     * @param nodes
     */
    @SafeVarargs
    public static <O, K> void addNodeToParent(Tree<TreeElement<O, K>, String> tree, TreeElement<O, K> parent,
                                              TreeElement<? extends O, K>... nodes) {
        List<TreeElement<O, K>> list = new LinkedList<>();
        for (TreeElement<? extends O, K> node : nodes) {
            list.add((TreeElement<O, K>) node);
        }
        tree.getStore().add(parent, list);
        for (TreeElement<? extends O, K> node : nodes) {
            tree.setChecked((TreeElement<O, K>) node, node.isChecked() ? CheckState.CHECKED : CheckState.UNCHECKED);
        }
    }

    /**
     *
     * @param tree
     * @param node
     */
    public static <O, K> void updateNode(Tree<TreeElement<O, K>, String> tree, TreeElement<?, ?> node) {
        setNodeChecked(tree, node, node.isChecked());
        tree.getStore().update((TreeElement<O, K>) node);
    }

    /**
     *
     * @param tree
     * @param node
     */
    public static <O, K> void removeNode(Tree<TreeElement<O, K>, String> tree, TreeElement<?, ?> node) {
        tree.getStore().remove((TreeElement<O, K>) node);
    }

    /**
     * @param object
     * @param root
     */
    private static <O, K> void processNode(Tree<TreeElement<O, K>, String> tree, TreeElement<O, K> root,
                                           TreeElement<O, K> node) {
        TreeStore<TreeElement<O, K>> store = tree.getStore();
        if (root == null) {
            root = node;
            store.add(root);
        } else {
            store.add(root, node);
        }
        tree.setChecked(node, node.isChecked() ? CheckState.CHECKED : CheckState.UNCHECKED);
        for (TreeElement<O, K> childNode : node.getChildren()) {
            processNode(tree, node, childNode);
        }
    }

    /**
     *
     * @param tree
     * @param root
     * @param node
     */
    public static <O, K> void processNode(TreeGrid<TreeElement<O, K>> tree, TreeElement<O, K> root,
                                          TreeElement<O, K> node) {
        TreeStore<TreeElement<O, K>> store = tree.getTreeStore();
        if (root == null) {
            root = node;
            store.add(root);
        } else {
            store.add(root, node);
        }
        for (TreeElement<O, K> childNode : node.getChildren()) {
            processNode(tree, node, childNode);
        }

    }

    /**
     *
     * @param selectedElement
     * @param newIndex
     */
    public static void replaceToIndex(TreeElement<UniqueObject<String>, String> selectedElement, int newIndex) {
        // TODO Auto-generated method stub

    }

}
