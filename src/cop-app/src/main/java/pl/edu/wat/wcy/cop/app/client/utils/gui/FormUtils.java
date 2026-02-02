/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.gui;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.gestures.ScrollGestureRecognizer;
import com.sencha.gxt.core.client.gestures.ScrollGestureRecognizer.ScrollDirection;
import com.sencha.gxt.core.client.gestures.TouchEventToGestureAdapter;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.state.client.GridFilterStateHandler;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.*;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GroupingView;
import com.sencha.gxt.widget.core.client.grid.filters.Filter;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import org.apache.commons.lang3.tuple.Pair;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
// Provides form utilities.


public class FormUtils {

    public static TextField createRequiredTextField() {
        TextField field = new TextField();
        field.setAllowBlank(false);
        return field;
    }

    /**
     * @return
     */
    public static TextArea createRequiredTextArea() {
        TextArea area = createGxtTextArea();
        area.setAllowBlank(true);
        return area;
    }

    /**
     * @return
     */
    public static TextArea createGxtTextArea() {
        TextArea area = new TextArea();
        area.setHeight(200);
        return area;
    }

    /**
     * @return
     */
    public static com.google.gwt.user.client.ui.TextArea createGWTTextArea() {
        com.google.gwt.user.client.ui.TextArea textArea = new com.google.gwt.user.client.ui.TextArea();
        textArea.setVisibleLines(15);
        return textArea;
    }

    /**
     *
     * @param min
     * @param max
     * @return
     */
    public static NumberField<Double> createRangeDoubleField(double min, double max) {
        return createRangeDoubleField(min, max, true);
    }

    /**
     *
     * @param min
     * @param max
     * @param allowDecimal
     * @return
     */
    public static NumberField<Double> createRangeDoubleField(double min, double max, boolean allowDecimal) {
        NumberField<Double> field = new NumberField<Double>(new NumberPropertyEditor.DoublePropertyEditor(AppConstants.DEFAULT_NUMBER_FORMAT));
        field.addValidator(new DoubleRangeValueValidator(min, max));
        field.setAllowDecimals(allowDecimal);
        return field;
    }

    /**
     *
     * @param min
     * @param max
     * @return
     */
    public static NumberField<Integer> createRangeIntegerField(int min, int max) {
        NumberField<Integer> field = new NumberField<Integer>(new NumberPropertyEditor.IntegerPropertyEditor());
        field.addValidator(new IntegerRangeValueValidator(min, max));
        field.setAllowDecimals(false);
        return field;
    }

    public static <T extends Enum> ComboBox<T> createEnumComboBox(Class<T> enumClass) {
        GxtEnumModelProvider<T> provider = new GxtEnumModelProvider<T>();
        return createComboBox(Arrays.asList(enumClass.getEnumConstants()), provider, provider);
    }

    public static <T extends Enum> ListStore<T> createEnumListStore(Class<T> enumClass) {
        GxtEnumModelProvider<T> provider = new GxtEnumModelProvider<T>();
        ListStore<T> store = new ListStore<T>(provider);
        store.addAll(Arrays.asList(enumClass.getEnumConstants()));
        return store;
    }

    /**
     *
     * @param values
     * @param labelProvider
     * @param modelProvider
     * @return
     */
    public static <T> ComboBox<T> createComboBox(Collection<T> values, LabelProvider<T> labelProvider,
                                                 ModelKeyProvider<T> modelProvider) {
        ListStore<T> store = new ListStore<T>(modelProvider);
        store.addAll(values);
        ComboBox<T> combo = new ComboBox<T>(store, labelProvider);
        setDefaultOptionsForGxtComboBox(combo);
        return combo;
    }

    /**
     *
     * @param comboBox
     */
    public static <T> void setDefaultOptionsForGxtComboBox(ComboBox<T> comboBox) {
        comboBox.setForceSelection(true);
        comboBox.setTypeAhead(true);
        comboBox.setTriggerAction(TriggerAction.ALL);
        comboBox.setEditable(false);

    }

    /**
     *
     * @param cls
     * @return
     */
    public static <T extends Enum> DualListField<T, String> createEnumDualList(Class<T> cls) {
        DualListField<T, String> dualList = new DualListField<T, String>(new GxtEnumModelProvider<T>(),
                new GxtEnumValueProvider<T>(), new TextCell());
        dualList.getFromStore().addAll(Arrays.asList(cls.getEnumConstants()));
        return dualList;
    }

    /**
     *
     * @param columnModel
     * @param modelKeyProvider
     * @return
     */
    public static <T> Pair<Grid<T>, GxtListStoreEditor<T>> createEditorGrid(ColumnModel<T> columnModel,
                                                                            ModelKeyProvider<T> modelKeyProvider, Filter<T, String>... filters) {
        GroupingView<T> groupingView = new GroupingView<>();
        groupingView.setShowGroupedColumn(true);
        groupingView.setForceFit(true);
        Grid<T> grid = new Grid<T>(new ListStore<T>(modelKeyProvider), columnModel, groupingView);
        configureGrid(grid, filters);
        grid.getStore().setAutoCommit(true);
        grid.getView().setAutoExpandColumn(columnModel.getColumn(0));
        return Pair.of(grid, new GxtListStoreEditor<T>(grid.getStore()));
    }

    /**
     * @param filters
     */
    public static <T> void configureGrid(Grid<T> grid, Filter<T, String>[] filters) {
        grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        grid.getView().setStripeRows(true);
        grid.getView().setColumnLines(true);
        grid.getView().setAutoFill(true);
        grid.setColumnReordering(true);
        grid.setColumnResize(true);
        grid.getElement().getStyle().setOverflowY(Overflow.AUTO);
        new TouchEventToGestureAdapter(grid, new ScrollGestureRecognizer(grid.getElement(), ScrollDirection.VERTICAL));

        if (filters.length > 0) {
            GridFilters<T> gridFilters = new GridFilters<>();
            gridFilters.initPlugin(grid);
            gridFilters.setLocal(true);
            for (Filter<T, String> filter : filters) {
                gridFilters.addFilter(filter);
            }
            GridFilterStateHandler<T> handler = new GridFilterStateHandler<>(grid, gridFilters);
            handler.loadState();
        }

    }

    /**
     *
     * @param minValue
     * @param maxValue
     * @param increment
     * @return
     */
    public static SpinnerField<Double> createRangeDoubleSpinnerField(double minValue, double maxValue,
                                                                     double increment) {
        SpinnerField<Double> field = new SpinnerField<>(new NumberPropertyEditor.DoublePropertyEditor(AppConstants.DEFAULT_NUMBER_FORMAT));
        field.setMinValue(minValue);
        field.setMaxValue(maxValue);
        field.setIncrement(increment);
        return field;
    }

    public static IsWidget createHorizontalPanelWith2Elements(IsWidget first, IsWidget second, double ratioFirst,
                                                              double ratioSecond) {
        return createHorizontalPanelWith2Elements(first, second, ratioFirst, ratioSecond, null, null);
    }

    public static IsWidget createHorizontalPanelWith2Elements(IsWidget first, IsWidget second, double ratioFirst,
                                                              double ratioSecond, Margins firstMargins, Margins secondMargins) {
        HorizontalLayoutContainer container = new HorizontalLayoutContainer();
        container.add(first, new HorizontalLayoutData(ratioFirst, 1.0D, firstMargins));
        container.add(second, new HorizontalLayoutData(ratioSecond, -1.0D, secondMargins));
        return container;
    }

    public static IsWidget createVerticalPanelWith2Elements(IsWidget first, IsWidget second, boolean firstFill) {
        VerticalLayoutContainer container = new VerticalLayoutContainer();
        if (firstFill) {
            container.add(first, new VerticalLayoutData(1, 1));
            container.add(second, new VerticalLayoutData(1, -1));
        } else {
            container.add(first, new VerticalLayoutData(1, -1));
            container.add(second, new VerticalLayoutData(1, 1));
        }
        return container;
    }

    public static HtmlEditor createHtmlEditor() {
        HtmlEditor editor = new HtmlEditor();
        editor.setHeight(150);
        return editor;
    }

    public static TextField createDisabledField() {
        TextField field = new TextField();
        field.setEnabled(false);
        return field;
    }

    public static NumberField<Double> createDoubleField(boolean disabled) {
        NumberField<Double> field = new NumberField<Double>(new NumberPropertyEditor.DoublePropertyEditor(AppConstants.DEFAULT_NUMBER_FORMAT));
        field.setEnabled(!disabled);
        return field;
    }

    public static NumberField<Integer> createIntegerField(boolean disabled) {
        NumberField<Integer> field = new NumberField<Integer>(new NumberPropertyEditor.IntegerPropertyEditor());
        field.setEnabled(!disabled);


        return field;
    }

    public static IntegerSpinnerField createIntegerSpinnerField(boolean disabled) {
        IntegerSpinnerField field = new IntegerSpinnerField();
        field.setEnabled(!disabled);
        return field;
    }
}

class DoubleRangeValueValidator extends AbstractValidator<Double> {

    private Double min;
    private Double max;

    /**
     * @param min
     * @param max
     */
    public DoubleRangeValueValidator(Double min, Double max) {
        super();
        this.min = min;
        this.max = max;
    }

    @Override
    public List<EditorError> validate(Editor<Double> field, Double value) {
        if (value == null) {
            return null;
        }

        if (value < min || value > max) {
            return createError(new DefaultEditorError(field, "Value must be in range " + min + " - " + max, value));
        }
        return null;
    }

}

class IntegerRangeValueValidator extends AbstractValidator<Integer> {

    private Integer min;
    private Integer max;

    /**
     * @param min
     * @param max
     */
    public IntegerRangeValueValidator(Integer min, Integer max) {
        super();
        this.min = min;
        this.max = max;
    }

    @Override
    public List<EditorError> validate(Editor<Integer> field, Integer value) {
        if (value == null) {
            return null;
        }

        if (value < min || value > max) {
            return createError(new DefaultEditorError(field, "Value must be in range " + min + " - " + max, value));
        }
        return null;
    }

}
