/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.signs;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElement;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement;
import pl.edu.wat.wcy.cop.app.client.utils.resources.copResources;
import pl.edu.wat.wcy.cop.app.shared.MSWiASymbol;
// Supplies ms wi a symbols data.

public class MSWiASymbolsProvider {

    private static MSWiASymbolBeanParserFactory factoryInstance = GWT.create(MSWiASymbolBeanParserFactory.class);

    private static MSWiASymbol mainSymbol;
    private static TreeStore<MSWiASymbol> symbolsStore;
    private static DefaultTreeElement<MSWiASymbol, String> rootElement;

    public synchronized static MSWiASymbol getMainSymbol() {
        loadIfNecessary();
        return mainSymbol;
    }

    public static TreeStore<MSWiASymbol> getSymbolsStore() {
        createStoreIfNecessary();
        return symbolsStore;
    }

    public static TreeElement<MSWiASymbol, String> getRootTreeElement() {
        createStoreIfNecessary();
        return rootElement;
    }

    /**
     *
     */
    private static void loadIfNecessary() {
        if (mainSymbol == null) {
            mainSymbol = factoryInstance.create().parse(copResources.INSTANCE.MswiaSymbols().getText());
        }
    }

    /**
     *
     */
    private static void createStoreIfNecessary() {
        if (symbolsStore == null) {
            symbolsStore = new TreeStore<MSWiASymbol>(new MSWiASymbolKeyProvider());
            initStoreData(symbolsStore);
        }

    }

    /**
     *
     */
    private static void initStoreData(TreeStore<MSWiASymbol> store) {
        MSWiASymbol root = MSWiASymbolsProvider.getMainSymbol();
        rootElement = new MSWiATreeElement(root, null);
        for (MSWiASymbol base : root.getSymbols()) {
            store.add(base);
            processSymbol(store, rootElement, base);
        }

    }

    private static void processSymbol(TreeStore<MSWiASymbol> store, TreeElement<MSWiASymbol, String> parent,
                                      MSWiASymbol symbol) {
        for (MSWiASymbol child : symbol.getSymbols()) {
            TreeElement<MSWiASymbol, String> childTreeElement = new MSWiATreeElement(child, null);
            store.add(symbol, child);
            parent.addChild(childTreeElement);
            processSymbol(store, childTreeElement, child);
        }
    }
}

class MSWiASymbolKeyProvider implements ModelKeyProvider<MSWiASymbol> {
    @Override
    public String getKey(MSWiASymbol item) {
        return item.getCode();
    }
}
