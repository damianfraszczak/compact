/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.mvp.impl;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import org.fusesource.restygwt.client.Method;
import pl.edu.wat.wcy.cop.app.client.mvp.BaseActivity;
import pl.edu.wat.wcy.cop.app.client.services.server.VisualizationDataProvider;
import pl.edu.wat.wcy.cop.app.client.utils.CopAppUtils;
import pl.edu.wat.wcy.cop.app.client.utils.LoggingMethodCallback;
import pl.edu.wat.wcy.cop.app.client.utils.MessagesUtils;
import pl.edu.wat.wcy.cop.app.client.utils.resources.copResources;
import pl.edu.wat.wcy.cop.app.shared.domain.*;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.crisis.CrisisEventStatusDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.crisis.CrisisEventTypeDto;
import pl.edu.wat.wcy.cop.app.shared.ol.OLMapOptions;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayerType;
import pl.edu.wat.wcy.cop.app.shared.ol.sources.OLBingLayerSource;
import pl.edu.wat.wcy.cop.app.shared.ol.sources.OLTileLayerSource;
import pl.edu.wat.wcy.cop.app.shared.response.OkResponse;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
// Represents configuration activity.

public class ConfigurationActivity extends BaseActivity<ConfigurationPlace> implements ConfigurationView.Presenter {
    @Inject
    private PlaceController placeController;

    @Inject
    private ConfigurationView configurationView;
    @Inject
    private VisualizationDataProvider visualizationDataProvider;
    private ScenarioDto model;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.google.gwt.activity.shared.Activity#start(com.google.gwt.user.client.
     * ui.AcceptsOneWidget, com.google.gwt.event.shared.EventBus)
     */
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        init();
        panel.setWidget(configurationView);

        visualizationDataProvider.getScenarioList(new LoggingMethodCallback<OkResponse<List<Long>>>() {

            @Override
            protected void success(Method method, OkResponse<List<Long>> response) {
                configurationView.setAvailableScenariosIds(response.getContent());
            }
        });
    }

    /**
     *
     */
    private void init() {
        model = new ScenarioDto(new OLMapOptions());
        configurationView.setModel(model);
        configurationView.setPresenter(this);
        CopAppUtils.setVisualisationView(false);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.mvp.impl.ConfigurationView.Presenter#
     * loadSelectedScenario(pl.edu.wat.wcy.cop.app.shared.domain.ScenarioDto)
     */
    @Override
    public void loadSelectedScenarioId(Long scenarioId) {
        visualizationDataProvider.getScenario(scenarioId, new LoggingMethodCallback<OkResponse<ScenarioDto>>() {

            @Override
            protected void success(Method method, OkResponse<ScenarioDto> response) {
                model = response.getContent();
                configurationView.setModel(model);
            }
        });

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.mvp.impl.ConfigurationView.Presenter#
     * goToVisualisation()
     */
    @Override
    public void goToVisualisation() {
        visualizationDataProvider.createScenario(model, new LoggingMethodCallback<OkResponse<Long>>() {

            @Override
            public void success(Method method, OkResponse<Long> response) {
                placeController.goTo(new VisualizationPlace(response.getContent()));

            }
        });

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.mvp.impl.ConfigurationView.Presenter#
     * generateSituation()
     */
    @Override
    public void generateSituation() {
        ScenarioDto dto = new ScenarioDto(new OLMapOptions());
        dto.getReportsADatP3().add(new ADatP3ReportDto("EXER/BOLDBLAST2016//\n"
                + "MSGID/CBRN 3 BIO/APP-11(C)/CHANGE02/RCZ/1/JUN/-/-/NATO/NATO UNCLASSIFIED//\n"
                + "REF/-/CBRN 2 BIO/-/RCZ/200929ZJUN2016/8644//\n" + "GEODATUM/WGE//\n" + "DTG/200933ZJUN2016//\n"
                + "ORGIDDFT/RCZ/-/PL/-/-/-/-/-/O//\n" + "CBRNTYPE/BIO//\n" + "ALFA/-/PLJ410/-/B//\n"
                + "DELTA/200910ZJUN2016//\n" + "FOXTROT/MGRS:34UCF9730003000/AA//\n" + "GOLF/OBS/AIR/3/BOM/6//\n"
                + "INDIA/SURF/SN:YPEB/NP/DL/DEF//\n" + "MIKER/CLOUD/PUFF//\n" + "PAPAA/2KM/-/184KM/-//\n"
                + "PAPAX/201000ZJUN2016/MGRS:33UWA6816912462/MGRS:34UCF4405217506/MGRS:34UCF8023111886/MGRS:34UCF8642810175/MGRS:34UCF9792804898/MGRS:34UCF9879504331/MGRS:34UCF9926203407/MGRS:34UCF9920302373/MGRS:34UCE7666059232/MGRS:33UXU9849516250/MGRS:33UWA6816912462//\n"
                + "PAPAX/201000ZJUN2016/MGRS:34UCF2445712705/MGRS:34UCF8023111886/MGRS:34UCF8642810175/MGRS:34UCF9792804898/MGRS:34UCF9879504331/MGRS:34UCF9926203407/MGRS:34UCF9920302373/MGRS:34UCE6253545258/MGRS:34UCF2445712705//\n"
                + "PAPAX/200800ZJUN2016/MGRS:34UCF9926203407/MGRS:34UCF9920302373/MGRS:34UCF9863401508/MGRS:34UCE8264187319/MGRS:34UCF7761811639/MGRS:34UCF9792804898/MGRS:34UCF9879504331/MGRS:34UCF9926203407//"));

        dto.getPoi().add(new PointOfInterestDto("Point 1", "Test point 1", generateRandomPointNearWarsaw()));
        dto.getPoi().add(new PointOfInterestDto("Point 2", "Test point  2", generateRandomPointNearWarsaw()));
        dto.getPoi().add(new PointOfInterestDto("Point 3", "Test point  3", generateRandomPointNearWarsaw()));
        dto.getPoi().add(new PointOfInterestDto("Point 4", "Test point  4", generateRandomPointNearWarsaw()));

        dto.getApp6aMilitaryUnits()
                .add(new App6AMilitaryUnitDto("sfgpucath-ee---", "1kZ", generateRandomPointNearWarsaw()));
        dto.getApp6aMilitaryUnits()
                .add(new App6AMilitaryUnitDto("sfgpucath-ee---", "2kZ", generateRandomPointNearWarsaw()));
        dto.getApp6aMilitaryUnits()
                .add(new App6AMilitaryUnitDto("sngpucath-ee---", "3kZ", generateRandomPointNearWarsaw()));
        dto.getApp6aMilitaryUnits()
                .add(new App6AMilitaryUnitDto("sngpucath-ee---", "1kZ", generateRandomPointNearWarsaw()));
        dto.getApp6aMilitaryUnits()
                .add(new App6AMilitaryUnitDto("sugpucath-ee---", "1kZ", generateRandomPointNearWarsaw()));
        dto.getApp6aMilitaryUnits()
                .add(new App6AMilitaryUnitDto("shgpucath-ee---", "1kZ", generateRandomPointNearWarsaw()));

        dto.getMapConfiguration().getLayers()
                .add(new OLLayer(OLTileLayerSource.Wikimedia, OLTileLayerSource.Wikimedia.toString()));
        dto.getMapConfiguration().getLayers()
                .add(new OLLayer(OLTileLayerSource.Stamen, OLTileLayerSource.Stamen.toString()));
        dto.getMapConfiguration().getLayers().add(
                new OLLayer(OLBingLayerSource.AERIAL, MessagesUtils.bingLayerSourceToString(OLBingLayerSource.AERIAL)));
        dto.getMapConfiguration().getLayers().add(
                new OLLayer(OLBingLayerSource.ROAD, MessagesUtils.bingLayerSourceToString(OLBingLayerSource.ROAD)));
        dto.getMapConfiguration().getLayers().add(new OLLayer(OLLayerType.KML, "KML"));
        dto.getMapConfiguration().getLayers().get(5)
                .setUrl("https://openlayers.org/en/v4.0.1/examples/data/kml/2012-02-10.kml");


        dto.getCrisisEvents().add(new CrisisEventDto("1", CrisisEventTypeDto.CHEM, CrisisEventStatusDto.ONGOING,
                "LPG ", "local", "śląskie", "Ogrodzieniec", 1489057200000L, 1489150800000L, "PSP",
                "leakage of the LPG tank", null,
                Arrays.asList(
                        new CrisisEventMultiPointZoneDto(
                                "Yellow Wind Direction Confidence Lines 30 ppm = AEGL-1 (60 min)",
                                Arrays.asList(new GeoPointDto(49.920880, 19.023261),
                                        new GeoPointDto(49.910802, 19.038767), new GeoPointDto(49.882547, 19.162603),
                                        new GeoPointDto(49.882358, 18.913543), new GeoPointDto(49.913533, 19.008632))),
                        new CrisisEventMultiPointZoneDto(
                                "Orange Wind Direction Confidence Lines 160 ppm = AEGL-2 (60 min)",
                                Arrays.asList(new GeoPointDto(49.920880, 19.023261),
                                        new GeoPointDto(49.911461, 19.038475), new GeoPointDto(49.893425, 19.157409),
                                        new GeoPointDto(49.889470, 18.916981), new GeoPointDto(49.914052, 19.008852))),

                        new CrisisEventMultiPointZoneDto(
                                "Red Wind Direction Confidence Lines 1100 ppm = AEGL-3 (60 min)",
                                Arrays.asList(new GeoPointDto(49.920880, 19.023261),
                                        new GeoPointDto(49.913911, 19.035768), new GeoPointDto(49.900159, 19.113376),
                                        new GeoPointDto(49.898511, 18.937169), new GeoPointDto(49.914663, 19.009144),
                                        new GeoPointDto(49.920880, 19.023261))),

                        new CrisisEventMultiPointZoneDto("Yellow Threat Zone 30 ppm = AEGL-1 (60 min)",
                                Arrays.asList(new GeoPointDto(49.923611, 19.017482),
                                        new GeoPointDto(49.922397, 19.025988), new GeoPointDto(49.929262, 19.042498),
                                        new GeoPointDto(50.006540, 19.126762), new GeoPointDto(49.930722, 18.996125))),

                        new CrisisEventMultiPointZoneDto("Orange Threat Zone 160 ppm = AEGL-2 (60 min)",
                                Arrays.asList(new GeoPointDto(49.923611, 19.017482),
                                        new GeoPointDto(49.922397, 19.025988), new GeoPointDto(49.929262, 19.041621),
                                        new GeoPointDto(50.006540, 19.105696), new GeoPointDto(49.930722, 18.997294))),

                        new CrisisEventMultiPointZoneDto("Red Threat Zone 1100 ppm = AEGL-3 (60 min)",
                                Arrays.asList(new GeoPointDto(49.923611, 19.017482),
                                        new GeoPointDto(49.922175, 19.025809), new GeoPointDto(49.928415, 19.037598),
                                        new GeoPointDto(49.983982, 19.073366), new GeoPointDto(49.930204, 19.000440)))

                )));

        model = dto;
        configurationView.setModel(model);
    }

    private GeoPointDto generateRandomPointNearWarsaw() {
        return new GeoPointDto(52.14 + Math.random(), 21 + Math.random());
    }

    private GeoPointDto generateRandomPointNearCzechowice() {
        return new GeoPointDto(49.55 + Math.random(), 19 + Math.random());
    }

    @Override
    public void generatePreparedSituation() {
        ScenarioDto dto = new ScenarioDto(new OLMapOptions());

        dto.getMapConfiguration().getLayers()
                .add(new OLLayer(OLTileLayerSource.Wikimedia, OLTileLayerSource.Wikimedia.toString()));
        dto.getMapConfiguration().getLayers()
                .add(new OLLayer(OLTileLayerSource.Stamen, OLTileLayerSource.Stamen.toString()));
        dto.getMapConfiguration().getLayers().add(
                new OLLayer(OLBingLayerSource.AERIAL, MessagesUtils.bingLayerSourceToString(OLBingLayerSource.AERIAL)));
        dto.getMapConfiguration().getLayers().add(
                new OLLayer(OLBingLayerSource.ROAD, MessagesUtils.bingLayerSourceToString(OLBingLayerSource.ROAD)));

        model = dto;
        configurationView.setModel(model);
    }

}
