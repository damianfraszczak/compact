package pl.edu.wat.wcy.cop.decisionsupport.gridtools;

import com.bbn.openmap.proj.Length;
import com.bbn.openmap.proj.coords.LatLonPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.cop.decisionsupport.DTEDDataProvider;
import pl.edu.wat.wcy.cop.decisionsupport.merger.Merger;
import pl.edu.wat.wcy.cop.decisionsupport.merger.PointFactory;
import pl.edu.wat.wcy.cop.decisionsupport.merger.Polygon;
import pl.edu.wat.wcy.cop.decisionsupport.merger.Vertex;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Service
@Scope("prototype")
// Displays flood grid data.
public class FloodGrid extends AbstractGeoGrid {

    private Map<Integer, List<Point>> pointsMap = new HashMap<>();
    private Integer minElevation;
    private Point minElevationPoint;
    private Integer minHeight;
    private Map<Integer, List<List<Double>>> floodAreaMap = new HashMap<>();
    private int floodHeight;
    private List<Double> minElevationPointPolygon;
    private double floodArea;
    private double floodVolume;

    @Autowired
    private DTEDDataProvider dtedDataProvider;

    public FloodGrid() {
        super();
    }

    /**
     * @param lat         in decimal degrees
     * @param lon         in decimal degrees
     * @param radius      in radians
     * @param accuracy    between 0 and 1double lat, double lon, double radius, int
     *                    sampling, boolean isCircle
     * @param floodHeight in meters
     */
    public void initFloodGird(double lat, double lon, double radius, double accuracy, int floodHeight) {
        super.initGeoGrid(lat, lon, radius, convertAccuracyToSampling(accuracy, radius, dtedDataProvider), true);
        this.floodHeight = floodHeight;
        pointsMap.clear();
        floodAreaMap.clear();
        minElevation = null;
        minHeight = null;
        init();
    }

    public Map<Integer, List<List<Double>>> getFloodAreaMap() {
        return floodAreaMap;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMinElevation() {
        return minElevation;
    }

    @Override
    public void init() {
        super.init();
        int floodedAreaCounter = 0;
        int floodedVolumeCounter = 0;
        int limitElevation = minElevation + floodHeight;
        for (Entry<Integer, List<Point>> entry : pointsMap.entrySet()) {
            if (entry.getKey() > limitElevation) {
                continue;
            }

            Merger merger = new Merger(sampling, sampling) {

                @Override
                public boolean isValid(Point point) {
                    return entry.getValue().contains(point);
                }
            };
            List<Polygon> polygons = merger.getPolygons();
            List<List<Double>> list = new ArrayList<>();
            for (Polygon polygon : polygons) {
                List<Vertex> vertices = polygon.getVertices();
                List<Double> verticesList = new ArrayList<>(vertices.size() * 2);
                for (int i = 0; i < vertices.size(); i++) {
                    Vertex vertex = vertices.get(i);
                    LatLonPoint translatedVertex = translateVertexToLatLon(vertex);
                    verticesList.add((double) translatedVertex.getLatitude());
                    verticesList.add((double) translatedVertex.getLongitude());
                }
                list.add(verticesList);
            }
            int height = limitElevation - entry.getKey();

            floodedAreaCounter += entry.getValue().size();
            floodedVolumeCounter += entry.getValue().size() * height;

            if (minHeight == null) {
                minHeight = height;
            } else if (height < minHeight) {
                minHeight = height;
            }
            floodAreaMap.put(height, list);
        }
        double epsilonInMeters = Length.METER.fromRadians(this.epsilon);
        double squareArea = epsilonInMeters * epsilonInMeters;
        floodArea = floodedAreaCounter * squareArea;
        floodVolume = floodedVolumeCounter * squareArea;
        minElevationPointPolygon = getPolygonForPoint(minElevationPoint);
    }

    private List<Point> initPointsList(int elevation, Point point) {
        List<Point> list = new ArrayList<>();
        pointsMap.put(elevation, list);
        if (minElevation == null || elevation < minElevation) {
            minElevation = elevation;
            minElevationPoint = point;
        }
        return list;
    }

    @Override
    protected void process(LatLonPoint latLonPoint, int i, int j) {
        int elevation = dtedDataProvider.getElevation(latLonPoint);
        List<Point> list = pointsMap.get(elevation);
        Point point = PointFactory.createPoint(i, j);
        if (list == null) {
            list = initPointsList(elevation, point);
        }
        list.add(point);
    }

    public List<Double> getMinElevationPointPolygon() {
        return minElevationPointPolygon;
    }

    public double getFloodArea() {
        return floodArea;
    }

    public double getFloodVolume() {
        return floodVolume;
    }
}
