/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.gui;

import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.sencha.gxt.data.shared.ListStore;

import java.util.List;
// Edits gxt list store settings.


public class GxtListStoreEditor<T> implements ValueAwareEditor<List<T>> {
    private final ListStore<T> store;
    private List<T> model;

    /**
     * Creates an editor use with a {@link ListStore}.
     *
     * @param store
     *            the list store that uses this editor
     */
    public GxtListStoreEditor(ListStore<T> store) {
        this.store = store;
    }

    @Override
    public void flush() {
        // make any modifications to the models themselves
        store.commitChanges();

        // flush out the contents of the list, so structural changes are made as
        // well
        if (model != null) {
            model.clear();
            model.addAll(store.getAll());
        } // TODO add an else here to create a list?
    }

    /**
     * Returns the list store that uses this editor.
     *
     * @return the list store that uses this editor.
     */
    public ListStore<T> getStore() {
        return store;
    }

    @Override
    public void onPropertyChange(String... paths) {
        // as ListEditor, noop
    }

    @Override
    public void setDelegate(EditorDelegate<List<T>> delegate) {
        // ignore for now, this could be used to pass errors into the view
    }

    @Override
    public void setValue(List<T> value) {
        // replace the data in the store (maybe use a loader for this?)
        store.clear();
        if (value != null && value.size() > 0) {
            store.addAll(value);
        }

        // store a copy of the original list, so changes can be pushed to that
        this.model = value;
    }
}
