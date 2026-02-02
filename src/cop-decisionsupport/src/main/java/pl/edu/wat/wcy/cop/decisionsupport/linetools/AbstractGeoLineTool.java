package pl.edu.wat.wcy.cop.decisionsupport.linetools;

import com.bbn.openmap.proj.coords.LatLonPoint;

import java.util.List;
// Represents abstract geo line tool.

public abstract class AbstractGeoLineTool {

    public AbstractGeoLineTool() {
        super();
    }

    protected void init(List<Double> points) {
        double step = getStep();
        double absoluteDistance = 0;
        int size = points.size();
        for (int i = 2; i < size; i += 2) {
            LatLonPoint startingPoint = new LatLonPoint.Double(points.get(i - 2), points.get(i - 1));
            LatLonPoint targetPoint = new LatLonPoint.Double(points.get(i), points.get(i + 1));
            double azimuth = startingPoint.azimuth(targetPoint);
            double distance = startingPoint.distance(targetPoint);
            int k = 0;
            double toGo = k * step;
            while (toGo < distance) {
                LatLonPoint point = startingPoint.getPoint(k * step, azimuth);
                process(point, absoluteDistance + toGo);
                k++;
                toGo = k * step;
            }
            absoluteDistance += distance;
        }
        process(new LatLonPoint.Double(points.get(size - 2), points.get(size - 1)), absoluteDistance);
    }

    protected abstract void process(LatLonPoint point, double distance);

    protected abstract double getStep();

}
