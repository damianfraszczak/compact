/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.grid;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.filters.DateFilter;
import com.sencha.gxt.widget.core.client.grid.filters.Filter;
import com.sencha.gxt.widget.core.client.grid.filters.ListFilter;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ui.pa.*;
import pl.edu.wat.wcy.cop.app.client.utils.DateUtils;
import pl.edu.wat.wcy.cop.app.client.utils.MeasuresPresentationUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.FormUtils;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.CBRNType;
import pl.edu.wat.wcy.cop.app.shared.domain.*;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.AffiliationEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.BattleDimensionEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.UnitSizeEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.*;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.Qualification;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.HumanResourceDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.TechnicalResourceDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.searchplan.SearchAndRescueSearchPlanEntryDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.searchplan.SearchUnitDomainDto;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayerType;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
// Represents dto grids configs.


public class DtoGridsConfigs {

    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();
    public static List<ColumnConfig<OLLayer, ?>> LAYERS_COLUMN_CONFIG;
    public static List<ColumnConfig<App6AMilitaryUnitDto, ?>> APP6A_OBJECTS_COLUMN_CONFIG;
    public static List<ColumnConfig<ADatP3ReportDto, ?>> ADATP3_REPORTS_COLUMN_CONFIG;
    public static List<ColumnConfig<CrisisEventDto, ?>> CRISIS_EVENTS_COLUMN_CONFIG;
    public static List<ColumnConfig<PointOfInterestDto, ?>> POIS_COLUMN_CONFIG;
    public static List<ColumnConfig<MSWiAUnitDto, ?>> MSWIA_OBJECTS_COLUMN_CONFIG;
    public static List<ColumnConfig<GeoPointDto, ?>> GEO_POINTS_COLUMN_CONFIG;
    public static List<ColumnConfig<CrisisEventCricleZoneDto, ?>> CRISIS_EVENTS_CIRCLE_ZONE_COLUMN_CONFIG;
    public static List<ColumnConfig<CrisisEventMultiPointZoneDto, ?>> CRISIS_EVENTS_MULTI_POINT_ZONE_COLUMN_CONFIG;
    public static List<ColumnConfig<PoiCircleZoneDto, ?>> POIS_CIRCLE_ZONE_COLUMN_CONFIG;
    public static List<ColumnConfig<PoiMultiPointZoneDto, ?>> POIS_MULTI_POINT_ZONE_COLUMN_CONFIG;
    public static List<ColumnConfig<SearchAndRescueDto, ?>> SAR_OBJECTS_COLUMN_CONFIG;
    public static List<ColumnConfig<SearchAndRescueCircleZoneDto, ?>> SAR_CIRCLE_ZONE_COLUMN_CONFIG;
    public static List<ColumnConfig<SearchAndRescueSegmentDto, ?>> SAR_MULTI_POINT_ZONE_COLUMN_CONFIG;
    public static List<ColumnConfig<GpxTraceDto, ?>> GPX_TRACE_COLUMN_CONFIG;
    public static List<ColumnConfig<HumanResourceDto, ?>> HUMAN_RESOURCE_COLUMN_CONFIG;
    public static List<ColumnConfig<TechnicalResourceDto, ?>> TECHNICAL_RESOURCE_COLUMN_CONFIG;
    public static List<ColumnConfig<SearchUnitDto, ?>> SEARCH_UNIT_COLUMN_CONFIG;
    public static List<ColumnConfig<SearchAndRescueSearchPlanEntryDto, ?>> SAR_PLAN_COLUMN_CONFIG;

    public static ColumnModel<OLLayer> LAYERS_COLUMN_MODEL;
    public static ColumnModel<App6AMilitaryUnitDto> APP6A_OBJECTS_COLUMN_MODEL;
    public static ColumnModel<ADatP3ReportDto> ADATP3_REPORTS_COLUMN_MODEL;
    public static ColumnModel<CrisisEventDto> CRISIS_EVENTS_COLUMN_MODEL;
    public static ColumnModel<PointOfInterestDto> POIS_COLUMN_MODEL;
    public static ColumnModel<MSWiAUnitDto> MSWIA_OBJECTS_COLUMN_MODEL;
    public static ColumnModel<GeoPointDto> GEO_POINTS_COLUMN_MODEL;
    public static ColumnModel<CrisisEventCricleZoneDto> CRISIS_EVENTS_CIRCLE_ZONE_COLUMN_MODEL = null;
    public static ColumnModel<CrisisEventMultiPointZoneDto> CRISIS_EVENTS_MULTI_POINT_ZONE_COLUMN_MODEL = null;
    public static ColumnModel<PoiCircleZoneDto> POIS_CIRCLE_ZONE_COLUMN_MODEL;
    public static ColumnModel<PoiMultiPointZoneDto> POIS_MULTI_POINT_ZONE_COLUMN_MODEL;
    public static ColumnModel<SearchAndRescueDto> SAR_OBJECTS_COLUMN_MODEL;
    public static ColumnModel<SearchAndRescueCircleZoneDto> SAR_CIRCLE_ZONE_COLUMN_MODEL;
    public static ColumnModel<SearchAndRescueSegmentDto> SAR_MULTI_POINT_ZONE_COLUMN_MODEL;
    public static ColumnModel<GpxTraceDto> GPX_TRACE_COLUMN_MODEL;
    public static ColumnModel<HumanResourceDto> HUMAN_RESOURCE_COLUMN_MODEL;
    public static ColumnModel<TechnicalResourceDto> TECHNICAL_RESOURCE_COLUMN_MODEL;
    public static ColumnModel<SearchUnitDto> SEARCH_UNIT_COLUMN_MODEL;
    public static ColumnModel<SearchAndRescueSearchPlanEntryDto> SAR_PLAN_COLUMN_MODEL;


    public static Filter<OLLayer, String>[] LAYERS_FILTERS;
    public static Filter<MSWiAUnitDto, String>[] MSWIA_OBJECTS_FILTERS;
    public static Filter<PointOfInterestDto, String>[] POIS_FILTERS;

    public static Filter<CrisisEventDto, String>[] CRISIS_EVENTS_FILTERS;
    public static Filter<App6AMilitaryUnitDto, String>[] APP6A_OBJECTS_FILTERS;
    public static Filter<ADatP3ReportDto, String>[] ADATP3_REPORT_FILTERS;
    public static Filter<SearchAndRescueDto, String>[] SAR_OBJECTS_FILTERS;

    private static final int DEFAULT_COLUMN_WIDTH = 200;

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
        initGeoPointsConfig();
        initCircleZonesConfig();
        initMultiPointZonesConfig();
        initSearchAndRescueConfig();
    }

    /**
     *
     */
    private static void initCircleZonesConfig() {
        CRISIS_EVENTS_CIRCLE_ZONE_COLUMN_CONFIG = Arrays.asList(
                new ColumnConfig<CrisisEventCricleZoneDto, String>(CrisisEventCricleZonePropertyAccess.INSTANCE.name(),
                        DEFAULT_COLUMN_WIDTH, MESSAGES.crisisevent_zone_name()),
                new ColumnConfig<CrisisEventCricleZoneDto, String>(
                        CrisisEventCricleZonePropertyAccess.INSTANCE.description(), DEFAULT_COLUMN_WIDTH,
                        MESSAGES.crisisevent_zone_description()),
                new ColumnConfig<CrisisEventCricleZoneDto, Double>(
                        CrisisEventCricleZonePropertyAccess.INSTANCE.radius(), DEFAULT_COLUMN_WIDTH,
                        MESSAGES.crisisevent_zone_radius()) {

                }

        );
        CRISIS_EVENTS_CIRCLE_ZONE_COLUMN_MODEL = new ColumnModel<>(CRISIS_EVENTS_CIRCLE_ZONE_COLUMN_CONFIG);

        POIS_CIRCLE_ZONE_COLUMN_CONFIG = Arrays.asList(
                new ColumnConfig<PoiCircleZoneDto, String>(PoiCircleZonePropertyAccess.INSTANCE.name(),
                        DEFAULT_COLUMN_WIDTH, MESSAGES.poi_zone_name()),
                new ColumnConfig<PoiCircleZoneDto, Double>(PoiCircleZonePropertyAccess.INSTANCE.radius(),
                        DEFAULT_COLUMN_WIDTH, MESSAGES.poi_zone_radius()),
                new ColumnConfig<PoiCircleZoneDto, String>(PoiCircleZonePropertyAccess.INSTANCE.description(),
                        DEFAULT_COLUMN_WIDTH, MESSAGES.poi_zone_description())

        );
        POIS_CIRCLE_ZONE_COLUMN_MODEL = new ColumnModel<>(POIS_CIRCLE_ZONE_COLUMN_CONFIG);

        SAR_CIRCLE_ZONE_COLUMN_CONFIG = Arrays.asList(
                new ColumnConfig<SearchAndRescueCircleZoneDto, String>(SearchAndRescueCircleZonePropertyAccess.INSTANCE.name(),
                        DEFAULT_COLUMN_WIDTH, MESSAGES.poi_zone_name()),
                new ColumnConfig<SearchAndRescueCircleZoneDto, Double>(SearchAndRescueCircleZonePropertyAccess.INSTANCE.radius(),
                        DEFAULT_COLUMN_WIDTH, MESSAGES.poi_zone_radius()),
                new ColumnConfig<SearchAndRescueCircleZoneDto, Integer>(SearchAndRescueCircleZonePropertyAccess.INSTANCE.dispersion(),
                        DEFAULT_COLUMN_WIDTH, "Dispersion"),
                new ColumnConfig<SearchAndRescueCircleZoneDto, String>(SearchAndRescueCircleZonePropertyAccess.INSTANCE.description(),
                        DEFAULT_COLUMN_WIDTH, MESSAGES.poi_zone_description())

        );
        SAR_CIRCLE_ZONE_COLUMN_MODEL = new ColumnModel<>(SAR_CIRCLE_ZONE_COLUMN_CONFIG);

    }

    /**
     *
     */
    private static void initMultiPointZonesConfig() {
        CRISIS_EVENTS_MULTI_POINT_ZONE_COLUMN_CONFIG = Arrays.asList(
                new ColumnConfig<CrisisEventMultiPointZoneDto, String>(
                        CrisisEventMultiPointZonePropertyAccess.INSTANCE.name(), DEFAULT_COLUMN_WIDTH,
                        MESSAGES.crisisevent_zone_name()),
                new ColumnConfig<CrisisEventMultiPointZoneDto, String>(
                        CrisisEventMultiPointZonePropertyAccess.INSTANCE.description(), DEFAULT_COLUMN_WIDTH,
                        MESSAGES.crisisevent_zone_description()));
        CRISIS_EVENTS_MULTI_POINT_ZONE_COLUMN_MODEL = new ColumnModel<>(CRISIS_EVENTS_MULTI_POINT_ZONE_COLUMN_CONFIG);

        POIS_MULTI_POINT_ZONE_COLUMN_CONFIG = Arrays.asList(
                new ColumnConfig<PoiMultiPointZoneDto, String>(PoiMultiPointZonePropertyAccess.INSTANCE.name(),
                        DEFAULT_COLUMN_WIDTH, MESSAGES.crisisevent_zone_name()),
                new ColumnConfig<PoiMultiPointZoneDto, String>(PoiMultiPointZonePropertyAccess.INSTANCE.description(),
                        DEFAULT_COLUMN_WIDTH, MESSAGES.poi_zone_description()));
        POIS_MULTI_POINT_ZONE_COLUMN_MODEL = new ColumnModel<>(POIS_MULTI_POINT_ZONE_COLUMN_CONFIG);


        SAR_MULTI_POINT_ZONE_COLUMN_CONFIG = Arrays.asList(
                new ColumnConfig<SearchAndRescueSegmentDto, String>(SearchAndRescueMultiPointZonePropertyAccess.INSTANCE.name(),
                        DEFAULT_COLUMN_WIDTH, MESSAGES.crisisevent_zone_name()),
                createColumnConfig(SearchAndRescueMultiPointZonePropertyAccess.INSTANCE.area(), "Powierzchnia",
                        new AbstractCell<Double>() {

                            @Override
                            public void render(Context context, Double aDouble, SafeHtmlBuilder safeHtmlBuilder) {
                                safeHtmlBuilder.append(
                                        SafeHtmlUtils.fromSafeConstant(
                                                MeasuresPresentationUtils.getAreaInHaString(aDouble)));
                            }
                        }),
                createColumnConfig(SearchAndRescueMultiPointZonePropertyAccess.INSTANCE.poc(), "POC (POA)", new PercentageCell())

        );
        SAR_MULTI_POINT_ZONE_COLUMN_MODEL = new ColumnModel<>(SAR_MULTI_POINT_ZONE_COLUMN_CONFIG);


        GPX_TRACE_COLUMN_CONFIG = Arrays.asList(
                new ColumnConfig<GpxTraceDto, String>(GpxTracePropertyAccess.INSTANCE.name(),
                        DEFAULT_COLUMN_WIDTH, MESSAGES.crisisevent_zone_name()),
                new ColumnConfig<GpxTraceDto, String>(GpxTracePropertyAccess.INSTANCE.description(),
                        DEFAULT_COLUMN_WIDTH, MESSAGES.poi_zone_description()));
        GPX_TRACE_COLUMN_MODEL = new ColumnModel<>(GPX_TRACE_COLUMN_CONFIG);

    }

    /**
     *
     */
    private static void initGeoPointsConfig() {
        GEO_POINTS_COLUMN_CONFIG = Arrays.asList(
                new ColumnConfig<GeoPointDto, Double>(GeoPointPropertyAccess.INSTANCE.lat(), DEFAULT_COLUMN_WIDTH,
                        MESSAGES.geopoint_lat()),
                new ColumnConfig<GeoPointDto, Double>(GeoPointPropertyAccess.INSTANCE.lon(), DEFAULT_COLUMN_WIDTH,
                        MESSAGES.geopoint_lon()) {

                }

        );
        GEO_POINTS_COLUMN_MODEL = new ColumnModel<>(GEO_POINTS_COLUMN_CONFIG);

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


    private static void initSearchAndRescueConfig() {
        SAR_OBJECTS_COLUMN_CONFIG = Arrays.asList(
                createColumnConfig(SearchAndRescuePropertyAccess.INSTANCE.name(), MESSAGES.mswiaunit_name()),
                createColumnConfig(SearchAndRescuePropertyAccess.INSTANCE.personBehaviour(), "Zachowanie"));
        SAR_OBJECTS_COLUMN_MODEL = new ColumnModel<>(SAR_OBJECTS_COLUMN_CONFIG);
        SAR_OBJECTS_FILTERS = new Filter[]{new StringFilter<>(SearchAndRescuePropertyAccess.INSTANCE.name()),
                new StringFilter<>(SearchAndRescuePropertyAccess.INSTANCE.description()),};

        HUMAN_RESOURCE_COLUMN_CONFIG = Arrays.asList(
                createColumnConfig(HumanResourcesPropertyAccess.INSTANCE.time, "Data", new AbstractCell<Date>() {
                    @Override
                    public void render(Context context, Date date, SafeHtmlBuilder safeHtmlBuilder) {
                        safeHtmlBuilder.append(
                                SafeHtmlUtils.fromSafeConstant(
                                        DateUtils.formatOnlyDate(date)));
                    }
                }),
                createColumnConfig(HumanResourcesPropertyAccess.INSTANCE.qualification(), "Kwalifikacje"),
                createColumnConfig(HumanResourcesPropertyAccess.INSTANCE.number(), "Liczba"));
        HUMAN_RESOURCE_COLUMN_MODEL = new ColumnModel<>(HUMAN_RESOURCE_COLUMN_CONFIG);

        TECHNICAL_RESOURCE_COLUMN_CONFIG = Arrays.asList(
                createColumnConfig(TechnicalResourcesPropertyAccess.INSTANCE.time, "Data"),
                createColumnConfig(TechnicalResourcesPropertyAccess.INSTANCE.category(), "Kategoria"),
                createColumnConfig(TechnicalResourcesPropertyAccess.INSTANCE.number(), "Liczba"));
        TECHNICAL_RESOURCE_COLUMN_MODEL = new ColumnModel<>(TECHNICAL_RESOURCE_COLUMN_CONFIG);

        SEARCH_UNIT_COLUMN_CONFIG = Arrays.asList(
                createColumnConfig(SearchUnitPropertyAccess.INSTANCE.name(), MESSAGES.mswiaunit_name()));
        SEARCH_UNIT_COLUMN_MODEL = new ColumnModel<>(SEARCH_UNIT_COLUMN_CONFIG);


        SAR_PLAN_COLUMN_CONFIG = Arrays.asList(
                createColumnConfig(SearchAndRescueSearchPlanEntryDtoPropertyAccess.INSTANCE.searchEnded(), "Zakończono poszuliwania ", new BooleanCell()),
                createColumnConfig(SearchAndRescueSearchPlanEntryDtoPropertyAccess.INSTANCE.name(), MESSAGES.mswiaunit_name()),
                createColumnConfig(SearchAndRescueSearchPlanEntryDtoPropertyAccess.INSTANCE.area(), "Powierzchnia",
                        new AbstractCell<Double>() {

                            @Override
                            public void render(Context context, Double aDouble, SafeHtmlBuilder safeHtmlBuilder) {
                                String result = "N/A";
                                if (aDouble != null && aDouble > 0) {
                                    result = MeasuresPresentationUtils.getAreaInHaString(aDouble);
                                }
                                safeHtmlBuilder.append(
                                        SafeHtmlUtils.fromSafeConstant(result));
                            }
                        }),
                createColumnConfig(SearchAndRescueSearchPlanEntryDtoPropertyAccess.INSTANCE.poc(), "POC (POA)", new PercentageCell()),
                createColumnConfig(SearchAndRescueSearchPlanEntryDtoPropertyAccess.INSTANCE.terrainCategory(), "Typ pokrycia"),
                createColumnConfig(SearchAndRescueSearchPlanEntryDtoPropertyAccess.INSTANCE.searchUnits(), "Typ formacji", new AbstractCell<List<SearchUnitDomainDto>>() {
                    @Override
                    public void render(Context context, List<SearchUnitDomainDto> searchUnitTypes, SafeHtmlBuilder safeHtmlBuilder) {
                        String inner = "";
                        if (searchUnitTypes != null) {
                            for (SearchUnitDomainDto dto : searchUnitTypes) {
                                inner += "<tr style='text-align:center'><td style='text-align:center'>" + dto.getType() + "</td></tr>";
                            }
                        }

                        safeHtmlBuilder.append(
                                SafeHtmlUtils.fromTrustedString(
                                        "<table style='width:100%;'>" +
                                                inner +
                                                "</table>"));
                    }
                }),
                createColumnConfig(SearchAndRescueSearchPlanEntryDtoPropertyAccess.INSTANCE.esr(), "ESR", new DoubleCell()),
//                createColumnConfig(SearchAndRescueSearchPlanEntryDtoPropertyAccess.INSTANCE.crew(), "Obsada", new AbstractCell<List<HumanResourceDto>>() {
//                    @Override
//                    public void render(Context context, List<HumanResourceDto> humanResource, SafeHtmlBuilder safeHtmlBuilder) {
//                        long experienced = humanResource.stream().filter(x -> x.getQualification() == Qualification.HIGH).mapToInt(x -> x.getNumber()).sum();
//                        long unexperienced = humanResource.stream().filter(x -> x.getQualification() == Qualification.LOW).mapToInt(x -> x.getNumber()).sum();
//
//                        safeHtmlBuilder.append(
//                                SafeHtmlUtils.fromTrustedString(
//                                        "<table style='width:100%;'>" +
//                                                "<tr style='text-align:center'><td style='text-align:center'>D</td><td style='text-align:center'>" + experienced + "</td></tr>" +
//                                                "<tr style='text-align:center'><td style='text-align:center'>N</td><td style='text-align:center'>" + unexperienced + "</td></tr>" +
//                                                "</table>"));
//                    }
//                }),
                createColumnConfig(SearchAndRescueSearchPlanEntryDtoPropertyAccess.INSTANCE.searchUnits(), "Obsada", new AbstractCell<List<SearchUnitDomainDto>>() {
                    @Override
                    public void render(Context context, List<SearchUnitDomainDto> items, SafeHtmlBuilder safeHtmlBuilder) {
                        String inner = "";
                        if (items != null) {
                            for (SearchUnitDomainDto item : items) {
                                List<HumanResourceDto> humanResource = item.getHumanResources();
                                long experienced = humanResource.stream().filter(x -> x.getQualification() == Qualification.HIGH).mapToInt(x -> x.getNumber()).sum();
                                long unexperienced = humanResource.stream().filter(x -> x.getQualification() == Qualification.LOW).mapToInt(x -> x.getNumber()).sum();
                                inner += "<tr style='text-align:center'><td style='text-align:center'>D</td><td style='text-align:center'>" + experienced + "</td></tr>" +
                                        "<tr style='text-align:center'><td style='text-align:center'>N</td><td style='text-align:center'>" + unexperienced + "</td></tr>";

                            }
                        }
                        safeHtmlBuilder.append(
                                SafeHtmlUtils.fromTrustedString(
                                        "<table style='width:100%;'>" +
                                                inner +
                                                "</table>"));


                    }
                }),
                createColumnConfig(SearchAndRescueSearchPlanEntryDtoPropertyAccess.INSTANCE.formationSquad(), "Skład formacji"),
                createColumnConfig(SearchAndRescueSearchPlanEntryDtoPropertyAccess.INSTANCE.communicationResources(), "Srodki łączności"),
                createColumnConfig(SearchAndRescueSearchPlanEntryDtoPropertyAccess.INSTANCE.leaveTime, "Czas wyjścia", new AbstractCell<Date>() {
                    @Override
                    public void render(Context context, Date date, SafeHtmlBuilder safeHtmlBuilder) {
                        safeHtmlBuilder.append(
                                SafeHtmlUtils.fromSafeConstant(
                                        DateUtils.formatOnlyTime(date)));
                    }
                }),
                createColumnConfig(SearchAndRescueSearchPlanEntryDtoPropertyAccess.INSTANCE.backTime, "Czas powrotu", new AbstractCell<Date>() {
                    @Override
                    public void render(Context context, Date date, SafeHtmlBuilder safeHtmlBuilder) {
                        safeHtmlBuilder.append(
                                SafeHtmlUtils.fromSafeConstant(
                                        DateUtils.formatOnlyTime(date)));
                    }
                }),
                createColumnConfig(SearchAndRescueSearchPlanEntryDtoPropertyAccess.INSTANCE.ec(), "EC", new DoubleCell()),
                createColumnConfig(SearchAndRescueSearchPlanEntryDtoPropertyAccess.INSTANCE.pod(), "POD", new PercentageCell())
//                createColumnConfig(SearchAndRescueSearchPlanEntryDtoPropertyAccess.INSTANCE.(), "Kategoria"),
//                createColumnConfig(SearchAndRescueSearchPlanEntryDtoPropertyAccess.INSTANCE.category(), "Kategoria"),

        );

        SAR_PLAN_COLUMN_MODEL = new ColumnModel<>(SAR_PLAN_COLUMN_CONFIG);
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
        ADATP3_REPORTS_COLUMN_CONFIG = Arrays.asList(
                createColumnConfig(ADatP3ReportPropertyAccess.INSTANCE.dateOfIncydent(),
                        MESSAGES.report_dateOfIncydent()),
                createColumnConfig(ADatP3ReportPropertyAccess.INSTANCE.cbrnType(), MESSAGES.report_cbrnType()),
                createColumnConfig(ADatP3ReportPropertyAccess.INSTANCE.lat(), MESSAGES.report_lattitude()),
                createColumnConfig(ADatP3ReportPropertyAccess.INSTANCE.lon(), MESSAGES.report_longitude()),
                createColumnConfig(ADatP3ReportPropertyAccess.INSTANCE.name(), MESSAGES.report_content()));
        ADATP3_REPORTS_COLUMN_MODEL = new ColumnModel<>(ADATP3_REPORTS_COLUMN_CONFIG);
        ADATP3_REPORT_FILTERS = new Filter[]{new StringFilter<>(ADatP3ReportPropertyAccess.INSTANCE.name()),
                new ListFilter<ADatP3ReportDto, CBRNType>(ADatP3ReportPropertyAccess.INSTANCE.cbrnType(),
                        FormUtils.createEnumListStore(CBRNType.class))

        };
    }

    /**
     *
     */
    private static void initApp6aConfig() {
        APP6A_OBJECTS_COLUMN_CONFIG = Arrays.asList(
                createColumnConfig(App6AMilitaryUnitPropertyAccess.INSTANCE.name(), MESSAGES.app6a_code()),
                createColumnConfig(App6AMilitaryUnitPropertyAccess.INSTANCE.code(), MESSAGES.app6a_name()),
                createColumnConfig(App6AMilitaryUnitPropertyAccess.INSTANCE.description(),
                        MESSAGES.app6a_description()),
                createColumnConfig(App6AMilitaryUnitPropertyAccess.INSTANCE.affiliation(),
                        MESSAGES.app6a_affiliation()),
                createColumnConfig(App6AMilitaryUnitPropertyAccess.INSTANCE.battleDimension(),
                        MESSAGES.app6a_battleDimension()),
                createColumnConfig(App6AMilitaryUnitPropertyAccess.INSTANCE.countryCode(),
                        MESSAGES.app6a_countryCode()),
                createColumnConfig(App6AMilitaryUnitPropertyAccess.INSTANCE.functionId(), MESSAGES.app6a_functionId()),
                createColumnConfig(App6AMilitaryUnitPropertyAccess.INSTANCE.mobilitySize(),
                        MESSAGES.app6a_mobilitySize()),
                createColumnConfig(App6AMilitaryUnitPropertyAccess.INSTANCE.orderOfBattle(),
                        MESSAGES.app6a_orderOfBattle()),
                createColumnConfig(App6AMilitaryUnitPropertyAccess.INSTANCE.status(), MESSAGES.app6a_status()),
                createColumnConfig(App6AMilitaryUnitPropertyAccess.INSTANCE.unitType(), MESSAGES.app6a_unitType()));
        APP6A_OBJECTS_COLUMN_MODEL = new ColumnModel<>(APP6A_OBJECTS_COLUMN_CONFIG);

        APP6A_OBJECTS_FILTERS = new Filter[]{new StringFilter<>(App6AMilitaryUnitPropertyAccess.INSTANCE.name()),
                new StringFilter<>(App6AMilitaryUnitPropertyAccess.INSTANCE.code()),
                new StringFilter<>(App6AMilitaryUnitPropertyAccess.INSTANCE.description()),
                new StringFilter<>(App6AMilitaryUnitPropertyAccess.INSTANCE.countryCode()),
                new StringFilter<>(App6AMilitaryUnitPropertyAccess.INSTANCE.status()),
                new ListFilter<App6AMilitaryUnitDto, AffiliationEnumDto>(
                        App6AMilitaryUnitPropertyAccess.INSTANCE.affiliation(),
                        FormUtils.createEnumListStore(AffiliationEnumDto.class)),
                new ListFilter<App6AMilitaryUnitDto, BattleDimensionEnumDto>(
                        App6AMilitaryUnitPropertyAccess.INSTANCE.battleDimension(),
                        FormUtils.createEnumListStore(BattleDimensionEnumDto.class)),
                new ListFilter<App6AMilitaryUnitDto, UnitSizeEnumDto>(
                        App6AMilitaryUnitPropertyAccess.INSTANCE.mobilitySize(),
                        FormUtils.createEnumListStore(UnitSizeEnumDto.class)),};
    }

    private static <M, V> ColumnConfig<M, V> createColumnConfig(ValueProvider<M, V> valueProvider, String message) {
        ColumnConfig<M, V> config = new ColumnConfig<M, V>(valueProvider, DEFAULT_COLUMN_WIDTH, message);
        config.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        config.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        return config;
    }

    private static <M, V> ColumnConfig<M, V> createColumnConfig(ValueProvider<M, V> valueProvider, String message, Cell<V> customCell) {
        ColumnConfig<M, V> config = createColumnConfig(valueProvider, message);
        config.setCell(customCell);
        return config;
    }

}
