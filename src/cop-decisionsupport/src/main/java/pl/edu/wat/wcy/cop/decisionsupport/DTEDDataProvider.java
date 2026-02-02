package pl.edu.wat.wcy.cop.decisionsupport;

import com.bbn.openmap.dataAccess.dted.DTEDDirectoryHandler;
import com.bbn.openmap.dataAccess.dted.DTEDFrameCache;
import com.bbn.openmap.proj.coords.LatLonPoint;
import com.bbn.openmap.tools.terrain.LOSGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.cop.common.logging.OMLogger;

@Service
// Supplies dted data data.
public class DTEDDataProvider {
    // 3 seconds => 3/60/60/180*PI
    // wartosci brane z application.properties
    @Value("${dtedDataprovider.dtedSpacing}")
    private double dtedSpacing = 0.0000145444104333;
    @Value("${dtedDataprovider.dtedDirectoryPath}")
    private String dtedDirectoryPath = "../../konfiguracja/dane/DTED";
    private DTEDFrameCache dtedFrameCache;
    private boolean initialized;
    private LOSGenerator losGenerator;

    public DTEDDataProvider() {
    }

    public int getElevation(LatLonPoint point) throws NoDTEDDataException {
        init();

        int elevation = dtedFrameCache.getElevation(point.getLatitude(), point.getLongitude());
        OMLogger.getInstance().debug("Zapytanie o punkty " + point.getLatitude() + ", " + point.getLongitude()
                + " elevation puste ? " + (elevation == DTEDFrameCache.NO_DATA ? "tak" : "nie"));
        if (elevation == DTEDFrameCache.NO_DATA) {
            throw new NoDTEDDataException("No DTED data for point: " + point);
        }
        return elevation;
    }

    private synchronized void init() {
        if (!initialized) {
            OMLogger.getInstance()
                    .debug("Inicjalizacja z parametrami spacing = " + dtedSpacing + " sciezka " + dtedDirectoryPath);
            DTEDDirectoryHandler dtedDirectoryHandler = new DTEDDirectoryHandler(dtedDirectoryPath);
            dtedFrameCache = new DTEDFrameCache();
            dtedFrameCache.addDTEDDirectoryHandler(dtedDirectoryHandler);
            losGenerator = new LOSGenerator(dtedFrameCache);
            initialized = true;
        }
    }

    public double getDtedSpacing() {
        return dtedSpacing;
    }

    public boolean isLos(LatLonPoint observerPoint, int observerHeight, LatLonPoint targetPoint) {
        init();

        double distance = observerPoint.distance(targetPoint);
        int pointsAmount = Math.max(1, (int) Math.ceil(distance / dtedSpacing));
        boolean addStartElevation = true;
        int endObjHeight = 0;
        return losGenerator.isLOS(observerPoint, observerHeight, addStartElevation, targetPoint, endObjHeight, pointsAmount);
    }

}
