/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.fusesource.restygwt.client.MethodCallback;
import pl.edu.wat.wcy.cop.app.client.services.server.*;
import pl.edu.wat.wcy.cop.app.client.utils.LoggingMethodCallback;
import pl.edu.wat.wcy.cop.app.shared.domain.*;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.GpxTraceDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueSegmentDto;
import pl.edu.wat.wcy.cop.app.shared.response.OkResponse;

import java.util.Date;
import java.util.List;


@Singleton
// Provides crud adapter operations.
public class CrudAdapterService {

    @Inject
    private ADatP3ServiceProvider adatP3ServiceProvider;
    @Inject
    private App6AServiceProvider app6a6AServiceProvider;
    @Inject
    private CrisisEventServiceProvider crisisEventServiceProvider;
    @Inject
    private MSWiAUnitServiceProvider mswiaUnitServiceProvider;
    @Inject
    private PointOfInterestServiceProvider poiServiceProviderentServiceProvider;
    @Inject
    private SearchAndRescueServiceProvider sarServiceProviderentServiceProvider;

    @Inject
    private GpxTraceServiceProvider gpxProviderentServiceProvider;


    public void getSegmentsForScenarionAndModifiedDate(long scenarioId,
                                                       Date modifiedDate,
                                                       MethodCallback<OkResponse<List<SearchAndRescueSegmentDto>>> callback) {
        sarServiceProviderentServiceProvider.getSegmentsForScenarioIdAndModifiedDate(scenarioId, modifiedDate.getTime(), callback);
    }

    /**
     *
     * @param object
     * @param callback
     */
    public <T> void add(Object object, LoggingMethodCallback callback) {
        if (object instanceof ADatP3ReportDto) {
            addADatP3Report((ADatP3ReportDto) object, callback);
        } else if (object instanceof App6AMilitaryUnitDto) {
            addApp6AMilitaryUnit((App6AMilitaryUnitDto) object, callback);
        } else if (object instanceof CrisisEventDto) {
            addCrisisEvent((CrisisEventDto) object, callback);
        } else if (object instanceof MSWiAUnitDto) {
            addMSWiAUnit((MSWiAUnitDto) object, callback);
        } else if (object instanceof PointOfInterestDto) {
            addPointOfInterest((PointOfInterestDto) object, callback);
        } else if (object instanceof SearchAndRescueDto) {
            addSearchAndRescue((SearchAndRescueDto) object, callback);
        } else if (object instanceof GpxTraceDto) {
            addGpxTrace((GpxTraceDto) object, callback);
        }

    }


    /**
     * @param object
     * @param callback
     */
    public void addPointOfInterest(PointOfInterestDto object,
                                   LoggingMethodCallback<OkResponse<PointOfInterestDto>> callback) {
        poiServiceProviderentServiceProvider.create(object, callback);

    }

    private void addSearchAndRescue(SearchAndRescueDto object, LoggingMethodCallback callback) {
        sarServiceProviderentServiceProvider.create(object, callback);
    }

    private void addGpxTrace(GpxTraceDto object, LoggingMethodCallback callback) {
        gpxProviderentServiceProvider.create(object, callback);
    }

    /**
     * @param object
     * @param callback
     */
    public void addMSWiAUnit(MSWiAUnitDto object, LoggingMethodCallback<OkResponse<MSWiAUnitDto>> callback) {
        mswiaUnitServiceProvider.create(object, callback);

    }

    /**
     * @param object
     * @param callback
     */
    public void addCrisisEvent(CrisisEventDto object, LoggingMethodCallback<OkResponse<CrisisEventDto>> callback) {
        crisisEventServiceProvider.create(object, callback);

    }

    /**
     * @param object
     * @param callback
     */
    public void addApp6AMilitaryUnit(App6AMilitaryUnitDto object,
                                     LoggingMethodCallback<OkResponse<App6AMilitaryUnitDto>> callback) {
        app6a6AServiceProvider.create(object, callback);

    }

    /**
     * @param object
     * @param callback
     */
    public void addADatP3Report(ADatP3ReportDto object, LoggingMethodCallback<OkResponse<ADatP3ReportDto>> callback) {
        adatP3ServiceProvider.create(object, callback);

    }

    /**
     *
     * @param object
     * @param callback
     */
    public void update(Object object, LoggingMethodCallback callback) {
        if (object instanceof ADatP3ReportDto) {
            updateADatP3Report((ADatP3ReportDto) object, callback);
        } else if (object instanceof App6AMilitaryUnitDto) {
            updateApp6AMilitaryUnit((App6AMilitaryUnitDto) object, callback);
        } else if (object instanceof CrisisEventDto) {
            updateCrisisEvent((CrisisEventDto) object, callback);
        } else if (object instanceof MSWiAUnitDto) {
            updateMSWiAUnit((MSWiAUnitDto) object, callback);
        } else if (object instanceof PointOfInterestDto) {
            updatePointOfInterest((PointOfInterestDto) object, callback);
        } else if (object instanceof SearchAndRescueDto) {
            updateSearchAndRescue((SearchAndRescueDto) object, callback);
        } else if (object instanceof GpxTraceDto) {
            updateGpxTrace((GpxTraceDto) object, callback);
        }
    }


    /**
     * @param object
     * @param callback
     */
    public void updateMSWiAUnit(MSWiAUnitDto object, LoggingMethodCallback<OkResponse<MSWiAUnitDto>> callback) {
        mswiaUnitServiceProvider.update(object, callback);

    }

    /**
     * @param object
     * @param callback
     */
    public void updateCrisisEvent(CrisisEventDto object, LoggingMethodCallback<OkResponse<CrisisEventDto>> callback) {
        crisisEventServiceProvider.update(object, callback);

    }

    /**
     * @param object
     * @param callback
     */
    public void updateApp6AMilitaryUnit(App6AMilitaryUnitDto object,
                                        LoggingMethodCallback<OkResponse<App6AMilitaryUnitDto>> callback) {
        app6a6AServiceProvider.update(object, callback);

    }

    /**
     * @param object
     * @param callback
     */
    public void updateADatP3Report(ADatP3ReportDto object,
                                   LoggingMethodCallback<OkResponse<ADatP3ReportDto>> callback) {
        adatP3ServiceProvider.update(object, callback);

    }

    /**
     * @param object
     * @param callback
     */
    public void updatePointOfInterest(PointOfInterestDto object,
                                      LoggingMethodCallback<OkResponse<PointOfInterestDto>> callback) {
        poiServiceProviderentServiceProvider.update(object, callback);
    }

    public void updateSearchAndRescue(SearchAndRescueDto object,
                                      LoggingMethodCallback<OkResponse<SearchAndRescueDto>> callback) {
        sarServiceProviderentServiceProvider.update(object, callback);
    }

    private void updateGpxTrace(GpxTraceDto object, LoggingMethodCallback callback) {
        gpxProviderentServiceProvider.update(object, callback);
    }

    /**
     *
     * @param object
     * @param callback
     */
    public void delete(Object object, LoggingMethodCallback<OkResponse<String>> callback) {
        if (object instanceof ADatP3ReportDto) {
            deleteADatP3Report((ADatP3ReportDto) object, callback);
        } else if (object instanceof App6AMilitaryUnitDto) {
            deleteApp6AMilitaryUnit((App6AMilitaryUnitDto) object, callback);
        } else if (object instanceof CrisisEventDto) {
            deleteCrisisEvent((CrisisEventDto) object, callback);
        } else if (object instanceof MSWiAUnitDto) {
            deleteMSWiAUnit((MSWiAUnitDto) object, callback);
        } else if (object instanceof PointOfInterestDto) {
            deletePointOfInterest((PointOfInterestDto) object, callback);
        } else if (object instanceof SearchAndRescueDto) {
            deleteSearchAndRescue((SearchAndRescueDto) object, callback);
        } else if (object instanceof GpxTraceDto) {
            deleteGpxTrace((GpxTraceDto) object, callback);
        }

    }


    /**
     * @param object
     * @param callback
     */
    public void deleteMSWiAUnit(MSWiAUnitDto object, LoggingMethodCallback<OkResponse<String>> callback) {
        mswiaUnitServiceProvider.delete(object.getId(), callback);

    }

    /**
     * @param object
     * @param callback
     */
    public void deleteCrisisEvent(CrisisEventDto object, LoggingMethodCallback<OkResponse<String>> callback) {
        crisisEventServiceProvider.delete(object.getId(), callback);

    }

    /**
     * @param object
     * @param callback
     */
    public void deleteApp6AMilitaryUnit(App6AMilitaryUnitDto object,
                                        LoggingMethodCallback<OkResponse<String>> callback) {
        app6a6AServiceProvider.delete(object.getId(), callback);

    }

    /**
     * @param object
     * @param callback
     */
    public void deleteADatP3Report(ADatP3ReportDto object, LoggingMethodCallback<OkResponse<String>> callback) {
        adatP3ServiceProvider.delete(object.getId(), callback);

    }

    /**
     * @param object
     * @param callback
     */
    public void deletePointOfInterest(PointOfInterestDto object, LoggingMethodCallback<OkResponse<String>> callback) {
        poiServiceProviderentServiceProvider.delete(object.getId(), callback);

    }

    private void deleteSearchAndRescue(SearchAndRescueDto object, LoggingMethodCallback<OkResponse<String>> callback) {
        sarServiceProviderentServiceProvider.delete(object.getId(), callback);
    }

    private void deleteGpxTrace(GpxTraceDto object, LoggingMethodCallback<OkResponse<String>> callback) {
        gpxProviderentServiceProvider.delete(object.getId(), callback);
    }
}
