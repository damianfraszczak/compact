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
import java.util.List;

@Service
@Scope("prototype")
// Displays los grid data.
public class LosGrid extends AbstractGeoGrid {

    private List<Point> pointsList = new ArrayList<>();
    private List<List<Double>> losAreaList = new ArrayList<>();
    private int observerHeight;
    private double losArea;

    @Autowired
    private DTEDDataProvider dtedDataProvider;

    public LosGrid() {
        super();
    }

    /**
     * @param lat            in decimal degrees
     * @param lon            in decimal degrees
     * @param radius         in radians
     * @param accuracy       between 0 and 1double lat, double lon, double radius, int
     *                       sampling, boolean isCircle
     * @param observerHeight in meters
     */
    public void initLosGird(double lat, double lon, double radius, double accuracy, int observerHeight) {
        super.initGeoGrid(lat, lon, radius, convertAccuracyToSampling(accuracy, radius, dtedDataProvider), true);
        this.observerHeight = observerHeight;
        pointsList.clear();
        losAreaList.clear();
        init();
    }

    public List<List<Double>> getLosAreaList() {
        return losAreaList;
    }

    @Override
    public void init() {
        super.init();
        Merger merger = new Merger(sampling, sampling) {
            @Override
            public boolean isValid(Point point) {
                return pointsList.contains(point);
            }
        };
        List<Polygon> polygons = merger.getPolygons();
        for (Polygon polygon : polygons) {
            List<Vertex> vertices = polygon.getVertices();
            List<Double> verticesList = new ArrayList<>(vertices.size() * 2);
            for (int i = 0; i < vertices.size(); i++) {
                Vertex vertex = vertices.get(i);
                LatLonPoint translatedVertex = translateVertexToLatLon(vertex);
                verticesList.add((double) translatedVertex.getLatitude());
                verticesList.add((double) translatedVertex.getLongitude());
            }
            losAreaList.add(verticesList);
        }
        double epsilonInMeters = Length.METER.fromRadians(this.epsilon);
        double squareArea = epsilonInMeters * epsilonInMeters;
        losArea = pointsList.size() * squareArea;
    }

    @Override
    protected void process(LatLonPoint latLonPoint, int i, int j) {
        boolean los = dtedDataProvider.isLos(center, observerHeight, latLonPoint);
        if (los) {
            Point point = PointFactory.createPoint(i, j);
            pointsList.add(point);
        }
    }

    public double getLosArea() {
        return losArea;
    }

}
