package pl.edu.wat.wcy.cop.decisionsupport.gridtools;

import com.bbn.openmap.proj.Length;
import com.bbn.openmap.proj.coords.LatLonPoint;
import pl.edu.wat.wcy.cop.decisionsupport.DTEDDataProvider;
import pl.edu.wat.wcy.cop.decisionsupport.merger.Vertex;
import pl.edu.wat.wcy.cop.decisionsupport.merger.VertexType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
// Displays abstract geo grid data.


public abstract class AbstractGeoGrid {
    protected LatLonPoint center;
    protected double epsilon;
    protected int sampling;
    private double radius;
    private boolean isCircle;

    public AbstractGeoGrid() {
        super();
    }

    protected static int convertAccuracyToSampling(double accuracy, double radius, DTEDDataProvider dtedDataProvider) {
        int sampling;
        double dtedSpacing = Length.METER.fromRadians(dtedDataProvider.getDtedSpacing());
        double diameter = Length.METER.fromRadians(2 * radius);
        double tmp = (dtedSpacing - diameter) * Math.pow(accuracy, 0.006) + diameter;
        if (radius != 0 && tmp != 0) {
            sampling = (int) Math.ceil(diameter / tmp);
        } else {
            sampling = 1;
        }
        if (sampling < 1) {
            sampling = 1;
        }
        return sampling;
    }

    /**
     * @param lat      in decimal degrees
     * @param lon      in decimal degrees
     * @param radius   in radians
     * @param sampling
     * @param isCircle
     */
    public void initGeoGrid(double lat, double lon, double radius, int sampling, boolean isCircle) {
        this.radius = radius;
        this.isCircle = isCircle;
        this.center = new LatLonPoint.Double(lat, lon);
        this.sampling = sampling;
        epsilon = radius / sampling * 2;
    }

    public void init() {
        double shift = 0.5 - sampling * 0.5;
        double diagonal = epsilon / Math.sqrt(2);
        double maxDistance = radius + diagonal;
        for (int i = 0; i < sampling; i++) {
            for (int j = 0; j < sampling; j++) {
                double x = i + shift;
                double y = j + shift;
                double distance = epsilon * Math.sqrt(x * x + y * y);
                if (isCircle && distance > maxDistance) {
                    continue;
                }
                double azimuth = Math.atan2(x, y);
                LatLonPoint latLonPoint = center.getPoint(distance, azimuth);
                process(latLonPoint, i, j);
            }
        }
    }

    protected abstract void process(LatLonPoint latLonPoint, int i, int j);

    protected LatLonPoint translateVertexToLatLon(Vertex vertex) {
        double x = vertex.getX() - sampling * 0.5;
        double y = vertex.getY() - sampling * 0.5;
        double distance = epsilon * Math.sqrt(x * x + y * y);
        double azimuth = Math.atan2(x, y);
        return center.getPoint(distance, azimuth);
    }

    protected List<Double> getPolygonForPoint(Point point) {
        List<Double> list = new ArrayList<>();
        VertexType[] vertexTypes = VertexType.values();
        for (int i = 0; i < vertexTypes.length + 1; i++) {
            Vertex vertex = new Vertex(point, vertexTypes[i % vertexTypes.length]);
            LatLonPoint latLonPoint = translateVertexToLatLon(vertex);
            list.add((double) latLonPoint.getLatitude());
            list.add((double) latLonPoint.getLongitude());
        }
        return list;
    }

}
