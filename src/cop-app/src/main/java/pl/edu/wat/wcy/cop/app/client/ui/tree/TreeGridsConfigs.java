/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.tree;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.filters.DateFilter;
import com.sencha.gxt.widget.core.client.grid.filters.Filter;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.ui.pa.*;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.App6AMilitaryUnitTreeElement;
import pl.edu.wat.wcy.cop.app.shared.domain.*;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayerType;

import java.util.Arrays;
import java.util.List;
// Represents tree grids configs.


public class TreeGridsConfigs {
    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    public static List<ColumnConfig<OLLayer, ?>> LAYERS_COLUMN_CONFIG;
    public static List<ColumnConfig<App6AMilitaryUnitTreeElement, ?>> APP6A_OBJECTS_COLUMN_CONFIG;
    public static List<ColumnConfig<ADatP3ReportDto, ?>> ADATP3_REPORTS_COLUMN_CONFIG;
    public static List<ColumnConfig<CrisisEventDto, ?>> CRISIS_EVENTS_COLUMN_CONFIG;
    public static List<ColumnConfig<PointOfInterestDto, ?>> POIS_COLUMN_CONFIG;
    public static List<ColumnConfig<MSWiAUnitDto, ?>> MSWIA_OBJECTS_COLUMN_CONFIG;
    public static ColumnModel<OLLayer> LAYERS_COLUMN_MODEL;
    public static ColumnModel<App6AMilitaryUnitTreeElement> APP6A_OBJECTS_COLUMN_MODEL;
    public static ColumnModel<ADatP3ReportDto> ADATP3_REPORTS_COLUMN_MODEL;
    public static ColumnModel<CrisisEventDto> CRISIS_EVENTS_COLUMN_MODEL;
    public static ColumnModel<PointOfInterestDto> POIS_COLUMN_MODEL;
    public static ColumnModel<MSWiAUnitDto> MSWIA_OBJECTS_COLUMN_MODEL;
    public static Filter<OLLayer, String>[] LAYERS_FILTERS;
    public static Filter<MSWiAUnitDto, String>[] MSWIA_OBJECTS_FILTERS;
    public static Filter<PointOfInterestDto, String>[] POIS_FILTERS;
    public static Filter<CrisisEventDto, String>[] CRISIS_EVENTS_FILTERS;
    public static Filter<App6AMilitaryUnitDto, String>[] APP6A_OBJECTS_FILTERS;
    public static Filter<ADatP3ReportDto, String>[] ADATP3_REPORT_FILTERS;
    private static int DEFAULT_COLUMN_WIDTH = 200;

    static {
        initLayersConfig();
        initApp6aConfig();
        initAdatp3Config();
        initCrisisEventsConfig();
        initPoisConfig();
        initMSWiAConfig();
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
    private static void initMSWiAConfig() {
        MSWIA_OBJECTS_COLUMN_CONFIG = Arrays.asList(
                createColumnConfig(MSWiAObjectPropertyAccess.INSTANCE.name(), MESSAGES.mswiaunit_name()),
                createColumnConfig(MSWiAObjectPropertyAccess.INSTANCE.description(), MESSAGES.mswiaunit_description()),
                createColumnConfig(MSWiAObjectPropertyAccess.INSTANCE.code(), MESSAGES.mswiaunit_description()));
        MSWIA_OBJECTS_COLUMN_MODEL = new ColumnModel<>(MSWIA_OBJECTS_COLUMN_CONFIG);
        MSWIA_OBJECTS_FILTERS = new Filter[]{new StringFilter<>(MSWiAObjectPropertyAccess.INSTANCE.name()),
                new StringFilter<>(MSWiAObjectPropertyAccess.INSTANCE.code()),
                new StringFilter<>(MSWiAObjectPropertyAccess.INSTANCE.description()),};
    }

    /**
     *
     */
    private static void initPoisConfig() {
        POIS_COLUMN_CONFIG = Arrays.asList(
                createColumnConfig(PointOfInterestPropertyAccess.INSTANCE.name(), MESSAGES.poi_name()),
                createColumnConfig(PointOfInterestPropertyAccess.INSTANCE.description(), MESSAGES.poi_description()));
        POIS_COLUMN_MODEL = new ColumnModel<>(POIS_COLUMN_CONFIG);
        POIS_FILTERS = new Filter[]{new StringFilter<>(PointOfInterestPropertyAccess.INSTANCE.name()),
                new StringFilter<>(PointOfInterestPropertyAccess.INSTANCE.description()),};
    }

    /**
     *
     */
    private static void initCrisisEventsConfig() {
        CRISIS_EVENTS_COLUMN_CONFIG = Arrays.asList(

                createColumnConfig(CrisisEventPropertyAccess.INSTANCE.htrcId(), MESSAGES.crisisevent_htrcId()),
                createColumnConfig(CrisisEventPropertyAccess.INSTANCE.type(), MESSAGES.crisisevent_type()),
                createColumnConfig(CrisisEventPropertyAccess.INSTANCE.substanceName(),
                        MESSAGES.crisisevent_substanceName()),
                createColumnConfig(CrisisEventPropertyAccess.INSTANCE.threatLevel(),
                        MESSAGES.crisisevent_threatLevel()),
                createColumnConfig(CrisisEventPropertyAccess.INSTANCE.city(), MESSAGES.crisisevent_city()),
                createColumnConfig(CrisisEventPropertyAccess.INSTANCE.province(), MESSAGES.crisisevent_province()),
                createColumnConfig(CrisisEventPropertyAccess.INSTANCE.source(), MESSAGES.crisisevent_source()),
                createColumnConfig(CrisisEventPropertyAccess.INSTANCE.status(), MESSAGES.crisisevent_status()),
                createColumnConfig(CrisisEventPropertyAccess.INSTANCE.description(),
                        MESSAGES.crisisevent_description()),
                createColumnConfig(CrisisEventPropertyAccess.timestampStart, MESSAGES.crisisevent_timestampStart()),
                createColumnConfig(CrisisEventPropertyAccess.timestampEnd, MESSAGES.crisisevent_timestampEnd())

        );
        CRISIS_EVENTS_COLUMN_MODEL = new ColumnModel<>(CRISIS_EVENTS_COLUMN_CONFIG);
        CRISIS_EVENTS_FILTERS = new Filter[]{new StringFilter<>(CrisisEventPropertyAccess.INSTANCE.htrcId()),
                new StringFilter<>(CrisisEventPropertyAccess.INSTANCE.substanceName()),
                new StringFilter<>(CrisisEventPropertyAccess.INSTANCE.city()),
                new StringFilter<>(CrisisEventPropertyAccess.INSTANCE.province()),
                new StringFilter<>(CrisisEventPropertyAccess.INSTANCE.source()),
                new StringFilter<>(CrisisEventPropertyAccess.INSTANCE.threatLevel()),
                new StringFilter<>(CrisisEventPropertyAccess.INSTANCE.description()),

                new DateFilter<>(CrisisEventPropertyAccess.INSTANCE.timestampEnd),
                new DateFilter<>(CrisisEventPropertyAccess.INSTANCE.timestampStart),};
    }

    /**
     *
     */
    private static void initAdatp3Config() {
        ADATP3_REPORTS_COLUMN_CONFIG = Arrays.asList(new ColumnConfig<ADatP3ReportDto, String>(
                ADatP3ReportPropertyAccess.INSTANCE.name(), DEFAULT_COLUMN_WIDTH, MESSAGES.report_content()));
        ADATP3_REPORTS_COLUMN_MODEL = new ColumnModel<>(ADATP3_REPORTS_COLUMN_CONFIG);
        ADATP3_REPORT_FILTERS = new Filter[]{new StringFilter<>(ADatP3ReportPropertyAccess.INSTANCE.name()),

        };
    }

    /**
     *
     */
    private static void initApp6aConfig() {
        APP6A_OBJECTS_COLUMN_CONFIG = Arrays.asList(
                createColumnConfig(App6AMilitaryUnitTreeElementPropertyAccess.INSTANCE.name(), MESSAGES.app6a_code()),
                createColumnConfig(App6AMilitaryUnitTreeElementPropertyAccess.INSTANCE.code(), MESSAGES.app6a_name()),
                createColumnConfig(App6AMilitaryUnitTreeElementPropertyAccess.INSTANCE.description(),
                        MESSAGES.app6a_description()),
                createColumnConfig(App6AMilitaryUnitTreeElementPropertyAccess.INSTANCE.affiliation(),
                        MESSAGES.app6a_affiliation()),
                createColumnConfig(App6AMilitaryUnitTreeElementPropertyAccess.INSTANCE.battleDimension(),
                        MESSAGES.app6a_battleDimension()),
                createColumnConfig(App6AMilitaryUnitTreeElementPropertyAccess.INSTANCE.countryCode(),
                        MESSAGES.app6a_countryCode()),
                createColumnConfig(App6AMilitaryUnitTreeElementPropertyAccess.INSTANCE.functionId(),
                        MESSAGES.app6a_functionId()),
                createColumnConfig(App6AMilitaryUnitTreeElementPropertyAccess.INSTANCE.mobilitySize(),
                        MESSAGES.app6a_mobilitySize()),
                createColumnConfig(App6AMilitaryUnitTreeElementPropertyAccess.INSTANCE.orderOfBattle(),
                        MESSAGES.app6a_orderOfBattle()),
                createColumnConfig(App6AMilitaryUnitTreeElementPropertyAccess.INSTANCE.status(),
                        MESSAGES.app6a_status()),
                createColumnConfig(App6AMilitaryUnitTreeElementPropertyAccess.INSTANCE.unitType(),
                        MESSAGES.app6a_unitType()));
        APP6A_OBJECTS_COLUMN_MODEL = new ColumnModel<>(APP6A_OBJECTS_COLUMN_CONFIG);

        APP6A_OBJECTS_FILTERS = new Filter[]{new StringFilter<>(App6AMilitaryUnitPropertyAccess.INSTANCE.name()),
                new StringFilter<>(App6AMilitaryUnitTreeElementPropertyAccess.INSTANCE.code()),
                new StringFilter<>(App6AMilitaryUnitTreeElementPropertyAccess.INSTANCE.description()),
                new StringFilter<>(App6AMilitaryUnitTreeElementPropertyAccess.INSTANCE.countryCode()),
                new StringFilter<>(App6AMilitaryUnitTreeElementPropertyAccess.INSTANCE.status()),};
    }

    private static <M, V> ColumnConfig<M, V> createColumnConfig(ValueProvider<M, V> valueProvider, String message) {
        return new ColumnConfig<M, V>(valueProvider, DEFAULT_COLUMN_WIDTH, message);
    }

}
