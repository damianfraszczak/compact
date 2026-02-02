/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.grid;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.StoreFilterField;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.filters.Filter;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import org.apache.commons.lang3.tuple.Pair;
import pl.edu.wat.wcy.cop.app.client.events.*;
import pl.edu.wat.wcy.cop.app.client.utils.CopLogger;
import pl.edu.wat.wcy.cop.app.client.utils.gui.*;
// Renders grid panel UI.


public abstract class GridPanel<T> implements IsWidget {
    private final GridPanelEventBinder eventBinder = GWT.create(GridPanelEventBinder.class);
    @Inject
    protected EventBus eventBus;
    protected ToolBar toolBar;
    private VBoxLayoutContainer container;
    private Grid<T> grid;
    private GxtListStoreEditor<T> store;
    private boolean canEditObjects = true;
    private boolean sendEvents = false;
    private boolean readOnly = false;

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget() {
        if (container == null) {
            container = new VBoxLayoutContainer(VBoxLayoutContainer.VBoxLayoutAlign.STRETCH);

            Pair<Grid<T>, GxtListStoreEditor<T>> pairGrid = FormUtils.createEditorGrid(getColumnModel(), getModelKey(),
                    getFilters());
            grid = pairGrid.getLeft();
            store = pairGrid.getRight();
            grid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<T>() {

                @Override
                public void onSelectionChanged(SelectionChangedEvent<T> event) {
                    T selectedObject = null;
                    if (!event.getSelection().isEmpty()) {
                        selectedObject = event.getSelection().get(0);
                    }
                    if (!isReadOnly()) {
                        grid.setContextMenu(getContextMenuForObject(selectedObject));
                    }

                }
            });

            toolBar = new ToolBar();
            createToolBar();
            container.add(toolBar);
            addGrid(container, grid);
        }
        // grid.setSize("100%", "100%");
        // container.setSize("100%", "100%");
        // w celu upewnienia zbindowania bindera
        setEventBus(eventBus);
        return initContainer(container);
    }

    protected void addGrid(VBoxLayoutContainer container, Grid<T> grid) {
        container.add(grid);
    }

    protected Widget initContainer(Widget widgets) {
        return widgets;
    }

    /**
     * @return the toolBar
     */
    public ToolBar getToolBar() {
        if (container == null) {
            asWidget();
        }
        return toolBar;
    }

    /**
     * @param toolBar
     *            the toolBar to set
     */
    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
    }

    /**
     * @return the sendEvents
     */
    public boolean isSendEvents() {
        return sendEvents;
    }

    /**
     * @param sendEvents
     *            the sendEvents to set
     */
    public void setSendEvents(boolean sendEvents) {
        this.sendEvents = sendEvents;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * @return the eventBus
     */
    public EventBus getEventBus() {
        return eventBus;
    }

    /**
     * @param eventBus
     *            the eventBus to set
     */
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        if (eventBus != null) {
            eventBinder.bindEventHandlers(this, eventBus);
        }
    }

    /**
     *
     * @param object
     * @return
     */
    protected Menu getContextMenuForObject(T object) {
        Menu menu = new Menu();

        menu.add(GxtComponentsUtils.createAddMenuItem(new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
                onAddButtonSelect();

            }

        }));
        if (object != null)
            menu.add(GxtComponentsUtils.createEditMenuItem(new SelectionHandler<Item>() {

                @Override
                public void onSelection(SelectionEvent<Item> event) {
                    onUpdateButtonSelect();
                }

            }));
        if (object != null)
            menu.add(GxtComponentsUtils.createRemoveMenuItem(new SelectionHandler<Item>() {

                @Override
                public void onSelection(SelectionEvent<Item> event) {
                    onRemoveButtonSelect();
                }
            }));

        return menu;
    }

    @EventHandler
    public void onObjectToAddEvent(ObjectAddedEvent event) {
        if (canSendEvent() && shouldProcessEvent(event)) {
            processAdd((T) event.getObject());
        }
    }

    @EventHandler
    public void onObjectToUpdateEvent(ObjectUpdatedEvent event) {
        if (canSendEvent() && shouldProcessEvent(event)) {
            processUpdate((T) event.getObject());
        }
    }

    @EventHandler
    public void onObjectToRemoveEvent(ObjectRemovedEvent event) {
        if (canSendEvent() && shouldProcessEvent(event)) {
            processRemove((T) event.getObject());
        }
    }

    /**
     * @return the grid
     */
    public Grid<T> getGrid() {
        if (container == null) {
            asWidget();
        }
        return grid;
    }

    /**
     * @return the store
     */
    public GxtListStoreEditor<T> getStore() {
        if (container == null) {
            asWidget();
        }
        return store;
    }

    /**
     * @return the canEditObjects
     */
    public boolean isCanEditObjects() {
        return canEditObjects;
    }

    /**
     * @param canEditObjects
     *            the canEditObjects to set
     */
    public void setCanEditObjects(boolean canEditObjects) {
        this.canEditObjects = canEditObjects;
    }

    /**
     *
     */
    protected void createToolBar() {
        // TODO przywrocenie filtraowania po poporawie
        // StoreFilterField<T> filter = createFilter();
        // toolBar.add(filter);
        // filter.bind(grid.getStore());
        if (!isReadOnly()) {
            toolBar.add(GxtComponentsUtils.createAddTextButton(new SelectHandler() {
                @Override
                public void onSelect(SelectEvent event) {
                    onAddButtonSelect();
                }

            }));
            toolBar.add(GxtComponentsUtils.createEditTextButton(new SelectHandler() {

                @Override
                public void onSelect(SelectEvent event) {
                    onUpdateButtonSelect();
                }
            }));
            toolBar.add(GxtComponentsUtils.createRemoveTextButton(new SelectHandler() {

                @Override
                public void onSelect(SelectEvent event) {
                    onRemoveButtonSelect();
                }
            }));
        }


    }

    /**
     * @return
     */
    protected StoreFilterField<T> createFilter() {
        return new StoreFilterField<T>() {

            @Override
            protected boolean doSelect(Store<T> store, T parent, T item, String filter) {
                return filter(item, filter);
            }
        };
    }

    /**
     * @param item
     * @param filter
     * @return
     */
    protected boolean filter(T item, String filter) {
        return item.toString().toLowerCase().contains(filter);
    }

    /**
     *
     */
    protected void onAddButtonSelect() {
        T model = createNewObject();
        showFormForObject(model, new SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                processAddFromForm(model);
            }

        }, true);

    }

    /**
     *
     */
    protected void onUpdateButtonSelect() {
        T object = grid.getSelectionModel().getSelectedItem();
        if (object == null) {
            DialogUtils.showErrorDialog_NotSelected();
        } else {
            showFormForObject(object, new SelectHandler() {
                @Override
                public void onSelect(SelectEvent event) {
                    if (isValid(object)) {
                        if (canSendEvent()) {
                            CopLogger.getInstance().debug(this, "Wysylam zdarzenie aktualizacji obiektu");
                            eventBus.fireEvent(new ObjectToUpdateEvent<T>(this, object));
                        } else {
                            processUpdate(object);
                        }
                    }

                }

            }, isCanEditObjects());
        }

    }

    /**
     *
     */
    protected void onRemoveButtonSelect() {
        T object = grid.getSelectionModel().getSelectedItem();

        if (canSendEvent()) {
            CopLogger.getInstance().debug(this, "Wysylam zdarzenie usuniecia obiektu");
            eventBus.fireEvent(new ObjectToRemoveEvent<T>(this, object));
        } else {
            processRemove(object);
        }

    }

    /**
     * @return
     */
    protected boolean canSendEvent() {
        return isSendEvents() && eventBus != null;
    }

    protected boolean isValid(T model) {
        return true;
    }

    /**
     * @return
     */
    protected abstract ColumnModel<T> getColumnModel();

    /**
     * @return
     */
    protected abstract ModelKeyProvider<T> getModelKey();

    /**
     * @return
     */
    protected abstract Filter<T, String>[] getFilters();

    /**
     * @param model
     * @param selectHandler
     */
    protected abstract void showFormForObject(T model, SelectHandler selectHandler, boolean editable);

    /**
     * @return
     */
    protected abstract T createNewObject();

    /**
     *
     * @param object
     */
    protected void objectAdded(T object) {
    }

    /**
     *
     * @param object
     */
    protected void objectUpdated(T object) {
    }

    /**
     * @param object
     */
    protected void objectRemoved(T object) {
    }

    /**
     * @param event
     * @return
     */
    protected abstract boolean shouldProcessEvent(ObjectEvent event);

    /**
     * @param object
     */
    protected void processAdd(T object) {
        grid.getStore().add(object);
        grid.getStore().commitChanges();
        objectAdded(object);
    }

    /**
     * @param object
     */
    protected void processUpdate(T object) {
        grid.getStore().update(object);
        objectUpdated(object);

    }

    /**
     * @param object
     */
    protected void processRemove(T object) {
        grid.getStore().remove(object);
        objectRemoved(object);

    }

    /**
     * @param model
     */
    protected void processAddFromForm(T model) {
        if (isValid(model)) {
            if (canSendEvent()) {
                CopLogger.getInstance().debug(this, "Wysylam zdarzenie dodania nowego obiektu");
                eventBus.fireEvent(new ObjectToAddEvent<T>(this, model));
            } else {
                processAdd(model);
            }

        }

    }

    interface GridPanelEventBinder extends EventBinder<GridPanel> {
    }
}
