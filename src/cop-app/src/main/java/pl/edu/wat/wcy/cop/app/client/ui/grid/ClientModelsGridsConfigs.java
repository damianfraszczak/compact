/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.grid;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.cell.core.client.form.CheckBoxCell;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.filters.*;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.domain.spec.*;
import pl.edu.wat.wcy.cop.app.client.ui.pa.*;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.utils.images.IconsUtils;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.CBRNType;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.domain.ADatP3ReportDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.AffiliationEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.BattleDimensionEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.UnitSizeEnumDto;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayerType;

import java.util.Arrays;
import java.util.List;
// Represents client models grids configs.

public class ClientModelsGridsConfigs {
    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();

    public static List<ColumnConfig<OLLayer, ?>> LAYERS_COLUMN_CONFIG;
    public static List<ColumnConfig<App6AMilitaryUnitClientModel, ?>> APP6A_OBJECTS_COLUMN_CONFIG;
    public static List<ColumnConfig<ADatP3ReportClientModel, ?>> ADATP3_REPORTS_COLUMN_CONFIG;
    // public static List<ColumnConfig<ADatP3ReportClientModel, ?>>
    // ADATP3_REPORTS_COLUMN_CONFIG;
    public static List<ColumnConfig<CrisisEventClientModel, ?>> CRISIS_EVENTS_COLUMN_CONFIG;
    public static List<ColumnConfig<PointOfInterestClientModel, ?>> POIS_COLUMN_CONFIG;
    public static List<ColumnConfig<MSWiAUnitClientModel, ?>> MSWIA_OBJECTS_COLUMN_CONFIG;
    public static List<ColumnConfig<SearchAndRescueClientModel, ?>> SAR_OBJECTS_COLUMN_CONFIG;
    public static List<ColumnConfig<GpxTraceClientModel, ?>> GPX_OBJECTS_COLUMN_CONFIG;

    public static ColumnModel<OLLayer> LAYERS_COLUMN_MODEL;
    public static ColumnModel<App6AMilitaryUnitClientModel> APP6A_OBJECTS_COLUMN_MODEL;
    public static ColumnModel<ADatP3ReportClientModel> ADATP3_REPORTS_COLUMN_MODEL;
    // public static ColumnModel<ADatP3ReportClientModel>
    // ADATP3_REPORTS_COLUMN_MODEL;
    public static ColumnModel<CrisisEventClientModel> CRISIS_EVENTS_COLUMN_MODEL;
    public static ColumnModel<PointOfInterestClientModel> POIS_COLUMN_MODEL;
    public static ColumnModel<MSWiAUnitClientModel> MSWIA_OBJECTS_COLUMN_MODEL;
    public static ColumnModel<SearchAndRescueClientModel> SAR_OBJECTS_COLUMN_MODEL;
    public static ColumnModel<GpxTraceClientModel> GPX_OBJECTS_COLUMN_MODEL;

    public static Filter<OLLayer, String>[] LAYERS_FILTERS;
    public static Filter<MSWiAUnitClientModel, String>[] MSWIA_OBJECTS_FILTERS;
    public static Filter<PointOfInterestClientModel, String>[] POIS_FILTERS;
    public static Filter<CrisisEventClientModel, String>[] CRISIS_EVENTS_FILTERS;
    public static Filter<App6AMilitaryUnitClientModel, String>[] APP6A_OBJECTS_FILTERS;
    public static Filter<ADatP3ReportClientModel, String>[] ADATP3_REPORT_FILTERS;
    public static Filter<SearchAndRescueClientModel, String>[] SAR_OBJECTS_FILTERS;
    public static Filter<GpxTraceClientModel, String>[] GPX_OBJECTS_FILTERS;
    private static final int DEFAULT_COLUMN_WIDTH = 200;

    // public static Filter<ADatP3ReportClientModel, String>[]
    // ADATP3_REPORT_FILTERS;
    static {
        initAllConfigs();
    }

    private static void initAllConfigs() {
        initLayersConfig();
        initApp6aConfig();
        initAdatp3Config();
        initCrisisEventsConfig();
        initPoisConfig();
        initMSWiAConfig();
        initSARConfig();
        initGpxConfig();
    }


    /**
     *
     */
    private static void initLayersConfig() {
        LAYERS_COLUMN_CONFIG = Arrays.asList(
                new ColumnConfig<OLLayer, String>(OLLayerPropertyAccess.INSTANCE.name(), DEFAULT_COLUMN_WIDTH,
                        MESSAGES.layerdialog_name()),
                new ColumnConfig<OLLayer, OLLayerType>(OLLayerPropertyAccess.INSTANCE.layerType(), DEFAULT_COLUMN_WIDTH,
                        MESSAGES.layerdialog_name()) {

                }

        );
        LAYERS_COLUMN_MODEL = new ColumnModel<>(LAYERS_COLUMN_CONFIG);
        LAYERS_FILTERS = new Filter[]{new StringFilter<>(OLLayerPropertyAccess.INSTANCE.name()),

        };
    }

    /**
     *
     */
    private static void initAdatp3Config() {
        ADATP3_REPORTS_COLUMN_CONFIG = Arrays.asList(
                createVisibleColumnConfig(ADatP3ReportClientModelPropertyAccess.INSTANCE.checked()),
                createColumnConfig(ADatP3ReportClientModelPropertyAccess.INSTANCE.dateOfIncydent(),
                        MESSAGES.report_dateOfIncydent()),
                createColumnConfig(ADatP3ReportClientModelPropertyAccess.INSTANCE.cbrnType(),
                        MESSAGES.report_cbrnType()));
        ADATP3_REPORTS_COLUMN_MODEL = new ColumnModel<>(ADATP3_REPORTS_COLUMN_CONFIG);
        ADATP3_REPORT_FILTERS = new Filter[]{new StringFilter<>(ADatP3ReportPropertyAccess.INSTANCE.name()),
                new ListFilter<ADatP3ReportDto, CBRNType>(ADatP3ReportPropertyAccess.INSTANCE.cbrnType(),
                        FormUtils.createEnumListStore(CBRNType.class))

        };
    }

    /**
     *
     */
    private static void initMSWiAConfig() {
        MSWIA_OBJECTS_COLUMN_CONFIG = Arrays.asList(
                createVisibleColumnConfig(MSWiAObjectClientModelPropertyAccess.INSTANCE.checked()),
                createColumnConfig(MSWiAObjectClientModelPropertyAccess.INSTANCE.name(), MESSAGES.mswiaunit_name()),
                createColumnConfig(MSWiAObjectClientModelPropertyAccess.INSTANCE.code(),
                        MESSAGES.mswiaunit_description()),
                createColumnConfig(MSWiAObjectClientModelPropertyAccess.INSTANCE.description(),
                        MESSAGES.mswiaunit_description()));
        MSWIA_OBJECTS_COLUMN_MODEL = new ColumnModel<>(MSWIA_OBJECTS_COLUMN_CONFIG);
        hideColumnsFrom(APP6A_OBJECTS_COLUMN_MODEL, 3);
        MSWIA_OBJECTS_FILTERS = new Filter[]{
                new BooleanFilter<>(MSWiAObjectClientModelPropertyAccess.INSTANCE.checked()),
                new StringFilter<>(MSWiAObjectClientModelPropertyAccess.INSTANCE.name()),
                new StringFilter<>(MSWiAObjectClientModelPropertyAccess.INSTANCE.code()),
                new StringFilter<>(MSWiAObjectClientModelPropertyAccess.INSTANCE.description()),};
    }

    private static void initSARConfig() {
        SAR_OBJECTS_COLUMN_CONFIG = Arrays.asList(
                createVisibleColumnConfig(SearchAndRescueClientModelPropertyAccess.INSTANCE.checked()),
                createColumnConfig(SearchAndRescueClientModelPropertyAccess.INSTANCE.name(), MESSAGES.mswiaunit_name()),

                createColumnConfig(SearchAndRescueClientModelPropertyAccess.INSTANCE.personBehaviour(),
                        "Zachowanie")
        );

        SAR_OBJECTS_COLUMN_MODEL = new ColumnModel<>(SAR_OBJECTS_COLUMN_CONFIG);

        SAR_OBJECTS_FILTERS = new Filter[]{
                new BooleanFilter<>(SearchAndRescueClientModelPropertyAccess.INSTANCE.checked()),
                new StringFilter<>(SearchAndRescueClientModelPropertyAccess.INSTANCE.name()),};
    }

    private static void initGpxConfig() {
        GPX_OBJECTS_COLUMN_CONFIG = Arrays.asList(
                createVisibleColumnConfig(GpxTraceClientModelPropertyAccess.INSTANCE.checked()),
                createColumnConfig(GpxTraceClientModelPropertyAccess.INSTANCE.name(), MESSAGES.mswiaunit_name()),
                createColumnConfig(GpxTraceClientModelPropertyAccess.INSTANCE.description(),
                        MESSAGES.mswiaunit_description())
        );

        GPX_OBJECTS_COLUMN_MODEL = new ColumnModel<>(GPX_OBJECTS_COLUMN_CONFIG);

        GPX_OBJECTS_FILTERS = new Filter[]{
                new BooleanFilter<>(GpxTraceClientModelPropertyAccess.INSTANCE.checked()),
                new StringFilter<>(GpxTraceClientModelPropertyAccess.INSTANCE.name()),
                new StringFilter<>(GpxTraceClientModelPropertyAccess.INSTANCE.description()),};
    }

    /**
     *
     */
    private static void initPoisConfig() {
        POIS_COLUMN_CONFIG = Arrays
                .asList(createVisibleColumnConfig(PointOfInterestClientModelPropertyAccess.INSTANCE.checked()),
                        createColumnConfig(PointOfInterestClientModelPropertyAccess.INSTANCE.name(),
                                MESSAGES.poi_name()),
                        createColumnConfig(PointOfInterestClientModelPropertyAccess.INSTANCE.description(),
                                MESSAGES.poi_description()));
        POIS_COLUMN_MODEL = new ColumnModel<>(POIS_COLUMN_CONFIG);
        POIS_FILTERS = new Filter[]{new BooleanFilter<>(PointOfInterestClientModelPropertyAccess.INSTANCE.checked()),
                new StringFilter<>(PointOfInterestClientModelPropertyAccess.INSTANCE.name()),
                new StringFilter<>(PointOfInterestClientModelPropertyAccess.INSTANCE.description()),};
    }

    /**
     *
     */
    private static void initCrisisEventsConfig() {
        CRISIS_EVENTS_COLUMN_CONFIG = Arrays.asList(
                createVisibleColumnConfig(CrisisEventClientModelPropertyAccess.INSTANCE.checked()),
                createColumnConfig(CrisisEventClientModelPropertyAccess.INSTANCE.htrcId(),
                        MESSAGES.crisisevent_htrcId()),
                createColumnConfig(CrisisEventClientModelPropertyAccess.INSTANCE.type(), MESSAGES.crisisevent_type()),
                createColumnConfig(CrisisEventClientModelPropertyAccess.INSTANCE.substanceName(),
                        MESSAGES.crisisevent_substanceName()),
                createColumnConfig(CrisisEventClientModelPropertyAccess.INSTANCE.threatLevel(),
                        MESSAGES.crisisevent_threatLevel()),
                createColumnConfig(CrisisEventClientModelPropertyAccess.INSTANCE.city(), MESSAGES.crisisevent_city()),
                createColumnConfig(CrisisEventClientModelPropertyAccess.INSTANCE.province(),
                        MESSAGES.crisisevent_province()),
                createColumnConfig(CrisisEventClientModelPropertyAccess.INSTANCE.source(),
                        MESSAGES.crisisevent_source()),
                createColumnConfig(CrisisEventClientModelPropertyAccess.INSTANCE.status(),
                        MESSAGES.crisisevent_status()),
                createColumnConfig(CrisisEventClientModelPropertyAccess.INSTANCE.description(),
                        MESSAGES.crisisevent_description()),
                createColumnConfig(CrisisEventClientModelPropertyAccess.timestampStart,
                        MESSAGES.crisisevent_timestampStart()),
                createColumnConfig(CrisisEventClientModelPropertyAccess.timestampEnd,
                        MESSAGES.crisisevent_timestampEnd()));

        CRISIS_EVENTS_COLUMN_MODEL = new ColumnModel<>(CRISIS_EVENTS_COLUMN_CONFIG);
        hideColumnsFrom(CRISIS_EVENTS_COLUMN_MODEL, 3);
        CRISIS_EVENTS_FILTERS = new Filter[]{new StringFilter<>(CrisisEventPropertyAccess.INSTANCE.htrcId()),
                new StringFilter<>(CrisisEventClientModelPropertyAccess.INSTANCE.substanceName()),
                new StringFilter<>(CrisisEventClientModelPropertyAccess.INSTANCE.city()),
                new StringFilter<>(CrisisEventClientModelPropertyAccess.INSTANCE.province()),
                new StringFilter<>(CrisisEventClientModelPropertyAccess.INSTANCE.source()),
                new StringFilter<>(CrisisEventClientModelPropertyAccess.INSTANCE.threatLevel()),
                new StringFilter<>(CrisisEventClientModelPropertyAccess.INSTANCE.description()),

                new DateFilter<>(CrisisEventClientModelPropertyAccess.INSTANCE.timestampEnd),
                new DateFilter<>(CrisisEventClientModelPropertyAccess.INSTANCE.timestampStart),};
    }

    /**
     *
     */
    private static void initApp6aConfig() {
        APP6A_OBJECTS_COLUMN_CONFIG = Arrays.asList(
                createVisibleColumnConfig(App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.checked()),
                createBase64BasedIconColumnConfig(App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.base64Image()),
                createColumnConfig(App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.code(), MESSAGES.app6a_name()),
                createColumnConfig(App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.name(), MESSAGES.app6a_code()),

                createColumnConfig(App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.description(),
                        MESSAGES.app6a_description()),
                createColumnConfig(App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.affiliation(),
                        MESSAGES.app6a_affiliation()),
                createColumnConfig(App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.battleDimension(),
                        MESSAGES.app6a_battleDimension()),
                createColumnConfig(App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.countryCode(),
                        MESSAGES.app6a_countryCode()),
                createColumnConfig(App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.functionId(),
                        MESSAGES.app6a_functionId()),
                createColumnConfig(App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.mobilitySize(),
                        MESSAGES.app6a_mobilitySize()),
                createColumnConfig(App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.orderOfBattle(),
                        MESSAGES.app6a_orderOfBattle()),
                createColumnConfig(App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.status(),
                        MESSAGES.app6a_status()),
                createColumnConfig(App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.unitType(),
                        MESSAGES.app6a_unitType()));

        APP6A_OBJECTS_COLUMN_MODEL = new ColumnModel<>(APP6A_OBJECTS_COLUMN_CONFIG);

        hideColumnsFrom(APP6A_OBJECTS_COLUMN_MODEL, 3);
        APP6A_OBJECTS_FILTERS = new Filter[]{new StringFilter<>(App6AMilitaryUnitPropertyAccess.INSTANCE.name()),
                new BooleanFilter<>(App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.checked()),
                new StringFilter<>(App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.code()),
                new StringFilter<>(App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.description()),
                new StringFilter<>(App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.countryCode()),
                new StringFilter<>(App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.status()),
                new ListFilter<App6AMilitaryUnitClientModel, AffiliationEnumDto>(
                        App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.affiliation(),
                        FormUtils.createEnumListStore(AffiliationEnumDto.class)),
                new ListFilter<App6AMilitaryUnitClientModel, BattleDimensionEnumDto>(
                        App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.battleDimension(),
                        FormUtils.createEnumListStore(BattleDimensionEnumDto.class)),
                new ListFilter<App6AMilitaryUnitClientModel, UnitSizeEnumDto>(
                        App6AMilitaryUnitClientModelPropertyAccess.INSTANCE.mobilitySize(),
                        FormUtils.createEnumListStore(UnitSizeEnumDto.class)),};
    }

    /**
     *
     * @param valueProvider
     *
     * @return
     */
    private static <M, Boolean> ColumnConfig<M, Boolean> createVisibleColumnConfig(
            ValueProvider<M, Boolean> valueProvider) {
        ColumnConfig<M, Boolean> cf = createColumnConfig(valueProvider, MESSAGES.visibility());
        cf.setFixed(true);
        cf.setWidth(40);
        CheckBoxCell cell = new CheckBoxCell();
        cf.setCell((Cell<Boolean>) cell);
        return cf;
    }

    /**
     *
     * @param valueProvider
     *
     * @return
     */
    private static <M> ColumnConfig<M, String> createBase64BasedIconColumnConfig(
            ValueProvider<M, String> valueProvider) {
        ColumnConfig<M, String> cf = createColumnConfig(valueProvider, MESSAGES.icon());
        cf.setFixed(true);
        cf.setWidth(AppConstants.DEFAULT_ICON_WIDTH * 2);
        cf.setCell(new AbstractCell<String>() {

            @Override
            public void render(com.google.gwt.cell.client.Cell.Context context, String value, SafeHtmlBuilder sb) {
                if (value != null) {
                    String imageSource = IconsUtils.getBase64ImgSrc(value);
                    String s = "<img  src=\"" + imageSource + "\" align=\"middle\"" + " style=\"width: "
                            + AppConstants.DEFAULT_ICON_WIDTH + "px; height:" + AppConstants.DEFAULT_ICON_HEIGHT
                            + "px;\"/>";
                    sb.appendHtmlConstant("<span>" + s + "</span>");
                }

            }
        });

        return cf;
    }

    /**
     *
     * @param valueProvider
     * @param message
     * @return
     */
    private static <M, V> ColumnConfig<M, V> createColumnConfig(ValueProvider<M, V> valueProvider, String message) {
        return new ColumnConfig<M, V>(valueProvider, DEFAULT_COLUMN_WIDTH, message);
    }

    /**
     *
     * @param columnModel
     * @param startIndex
     */
    private static <M> void hideColumnsFrom(ColumnModel<M> columnModel, int startIndex) {
        for (int i = startIndex; i < columnModel.getColumnCount(); i++) {
            columnModel.setHidden(i, true);
        }
    }

    /**
     *
     * @param columnModel
     * @param indexes
     */
    private static <M> void hideColumns(ColumnModel<M> columnModel, int... indexes) {
        Arrays.stream(indexes).forEach(x -> columnModel.setHidden(x, true));
    }

}
