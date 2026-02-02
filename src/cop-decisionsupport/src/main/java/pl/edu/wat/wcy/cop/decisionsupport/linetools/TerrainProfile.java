package pl.edu.wat.wcy.cop.decisionsupport.linetools;

import com.bbn.openmap.proj.Length;
import com.bbn.openmap.proj.coords.LatLonPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.cop.decisionsupport.DTEDDataProvider;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
// Represents terrain profile.
public class TerrainProfile extends AbstractGeoLineTool {
    @Autowired
    private DTEDDataProvider dtedDataProvider;
    private double step;
    private List<TerrainProfilePoint> terrainProfilePoints = new ArrayList<>();
    private double accuracy;

    public TerrainProfile() {
        super();
    }

    public void init(List<Double> points, double accuracy) {
        // TODO prawidlowa obsluga accuracy
        if (accuracy == 0) {
            accuracy = 0.000001;
        }
        this.accuracy = accuracy;
        step = dtedDataProvider.getDtedSpacing() / accuracy;
        terrainProfilePoints.clear();
        super.init(points);
    }

    public Result getResult() {
        return new Result(terrainProfilePoints, accuracy);
    }

    protected void process(LatLonPoint point, double distance) {
        TerrainProfilePoint terrainProfilePoint = new TerrainProfilePoint();
        terrainProfilePoint.setLat(point.getLatitude());
        terrainProfilePoint.setLon(point.getLongitude());
        terrainProfilePoint.setDistance(Length.METER.fromRadians(distance));
        terrainProfilePoint.setElevation(dtedDataProvider.getElevation(point));
        terrainProfilePoints.add(terrainProfilePoint);
    }

    protected double getStep() {
        return step;
    }

    public class Result {
        private List<TerrainProfilePoint> terrainProfilePoints;
        private Double accuracy;

        public Result(List<TerrainProfilePoint> terrainProfilePoints, Double accuracy) {
            super();
            this.terrainProfilePoints = terrainProfilePoints;
            this.accuracy = accuracy;
        }

        public List<TerrainProfilePoint> getTerrainProfilePoints() {
            return terrainProfilePoints;
        }

        public void setTerrainProfilePoints(List<TerrainProfilePoint> terrainProfilePoints) {
            this.terrainProfilePoints = terrainProfilePoints;
        }

        public Double getAccuracy() {
            return accuracy;
        }

        public void setAccuracy(Double accuracy) {
            this.accuracy = accuracy;
        }

    }

}
