package pl.edu.wat.wcy.cop.app.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.wcy.cop.app.shared.GwtEndpoints;
import pl.edu.wat.wcy.cop.app.shared.request.FloodAreaRequest;
import pl.edu.wat.wcy.cop.app.shared.request.LosAreaRequest;
import pl.edu.wat.wcy.cop.app.shared.request.TerrainProfileRequest;
import pl.edu.wat.wcy.cop.app.shared.response.ErrorCode;
import pl.edu.wat.wcy.cop.app.shared.response.capability.FloodAreaCapability;
import pl.edu.wat.wcy.cop.app.shared.response.capability.LosAreaCapability;
import pl.edu.wat.wcy.cop.app.shared.response.capability.PolygonWithHeight;
import pl.edu.wat.wcy.cop.decisionsupport.NoDTEDDataException;
import pl.edu.wat.wcy.cop.decisionsupport.OMGeoUtils;
import pl.edu.wat.wcy.cop.decisionsupport.gridtools.FloodGrid;
import pl.edu.wat.wcy.cop.decisionsupport.gridtools.LosGrid;
import pl.edu.wat.wcy.cop.decisionsupport.linetools.TerrainProfile;
import pl.edu.wat.wcy.cop.decisionsupport.linetools.TerrainProfile.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@RestController
// Handles decision support requests.
public class DecisionSupportController {

    @Autowired
    private FloodGrid floodGrid;
    @Autowired
    private LosGrid losGrid;
    @Autowired
    private TerrainProfile terrainProfile;

    @RequestMapping(value = GwtEndpoints.GWT_FLOOD_AREA, method = RequestMethod.POST)
    public ResponseEntity<?> getFloodAreaPoints(@RequestBody FloodAreaRequest request) {
        return getFloodAreaPoints(request.getLat(), request.getLon(), request.getRadius(), request.getFloodHeight(),
                request.getAccuracy());
    }

    @RequestMapping(value = GwtEndpoints.GWT_LOS_AREA, method = RequestMethod.POST)
    public ResponseEntity<?> getLosAreaPoints(@RequestBody LosAreaRequest request) {
        return getLosAreaPoints(request.getLat(), request.getLon(), request.getRadius(), request.getObserverHeight(),
                request.getAccuracy());
    }

    @RequestMapping(value = GwtEndpoints.GWT_TERRAIN_PROFILE, method = RequestMethod.POST)
    public ResponseEntity<?> getTerrainProfile(@RequestBody TerrainProfileRequest request) {
        return getTerrainProfile(request.getCoordinates(), request.getAccuracy());
    }

    public ResponseEntity<?> getTerrainProfile(List<Double> coordinates, double accuracy) {
        try {
            terrainProfile.init(coordinates, accuracy);
            Result terrainProfileResult = terrainProfile.getResult();
            return WebUtil.createOkEntity(terrainProfileResult);
        } catch (NoDTEDDataException e) {
            return WebUtil.createErrorEntity(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.GLOBAL, e.getMessage());
        }
    }

    /**
     * @param lat         in decimal degrees
     * @param lon         in decimal degrees
     * @param radius      in decimal degrees
     * @param floodHeight in meters
     * @param accuracy    between 0 and 1
     * @return
     */
    public ResponseEntity<?> getFloodAreaPoints(double lat, double lon, double radius, int floodHeight,
                                                double accuracy) {
        try {
            double radiusInRadians = OMGeoUtils.getCircleRadiusInRadians(lat, lon, radius);
            floodGrid.initFloodGird(lat, lon, radiusInRadians, accuracy, floodHeight);
            List<PolygonWithHeight> polygonsWithHeight = getPolygonsWithHeight(floodGrid);
            FloodAreaCapability floodAreaCapability = new FloodAreaCapability();
            floodAreaCapability.setPolygonsWithHeight(polygonsWithHeight);
            floodAreaCapability.setMinElevation(floodGrid.getMinElevation());
            floodAreaCapability.setMinHeight(floodGrid.getMinHeight());
            floodAreaCapability.setLat(lat);
            floodAreaCapability.setLon(lon);
            floodAreaCapability.setAccuracy(accuracy);
            floodAreaCapability.setFloodHeight(floodHeight);
            floodAreaCapability.setRadius(radius);
            floodAreaCapability.setMinElevationPointPolygon(floodGrid.getMinElevationPointPolygon());
            floodAreaCapability.setFloodArea(floodGrid.getFloodArea());
            floodAreaCapability.setFloodVolume(floodGrid.getFloodVolume());
            return WebUtil.createOkEntity(floodAreaCapability);
        } catch (NoDTEDDataException e) {
            return WebUtil.createErrorEntity(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.GLOBAL, e.getMessage());
        }
    }


    /**
     * @param lat            in decimal degrees
     * @param lon            in decimal degrees
     * @param radius         in decimal degrees
     * @param observerHeight in meters
     * @param accuracy       between 0 and 1
     * @return
     */
    public ResponseEntity<?> getLosAreaPoints(double lat, double lon, double radius, int observerHeight,
                                              double accuracy) {
        try {
            double radiusInRadians = OMGeoUtils.getCircleRadiusInRadians(lat, lon, radius);
            losGrid.initLosGird(lat, lon, radiusInRadians, accuracy, observerHeight);
            LosAreaCapability losAreaCapability = new LosAreaCapability();
            losAreaCapability.setLat(lat);
            losAreaCapability.setLon(lon);
            losAreaCapability.setAccuracy(accuracy);
            losAreaCapability.setObserverHeight(observerHeight);
            losAreaCapability.setRadius(radius);
            losAreaCapability.setLosArea(losGrid.getLosArea());
            losAreaCapability.setLosAreaList(losGrid.getLosAreaList());
            return WebUtil.createOkEntity(losAreaCapability);
        } catch (NoDTEDDataException e) {
            return WebUtil.createErrorEntity(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.GLOBAL, e.getMessage());
        }
    }


    private List<PolygonWithHeight> getPolygonsWithHeight(FloodGrid grid) {
        List<PolygonWithHeight> list = new ArrayList<>();
        Map<Integer, List<List<Double>>> map = grid.getFloodAreaMap();
        for (Entry<Integer, List<List<Double>>> entry : map.entrySet()) {
            list.add(new PolygonWithHeight(entry.getKey(), entry.getValue()));
        }
        return list;
    }

}
