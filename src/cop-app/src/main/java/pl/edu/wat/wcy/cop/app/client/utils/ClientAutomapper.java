/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils;

import pl.edu.wat.wcy.cop.app.shared.domain.CrisisEventDto;
import pl.edu.wat.wcy.cop.app.shared.domain.DefaultObject;
import pl.edu.wat.wcy.cop.app.shared.domain.PointOfInterestDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.GpxTraceDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchUnitDto;
// Represents client automapper.

public class ClientAutomapper {

    public static <T> void copyFromLeftToRight(T left, Object right) {
        // id ustaw zawsze
        if (left instanceof DefaultObject && right instanceof DefaultObject) {
            ((DefaultObject) right).setId(((DefaultObject) left).getId());
        }
        if (left instanceof PointOfInterestDto && right instanceof PointOfInterestDto) {
            PointOfInterestDto l = (PointOfInterestDto) left;
            PointOfInterestDto r = (PointOfInterestDto) right;
            // nalezy przekopiwac strefy, one nigdzie nie sa odwolywane
            r.setAreaZones(l.getAreaZones());
            r.setCircleZones(l.getCircleZones());
        } else if (left instanceof CrisisEventDto && right instanceof CrisisEventDto) {
            CrisisEventDto l = (CrisisEventDto) left;
            CrisisEventDto r = (CrisisEventDto) right;
            // nalezy przekopiwac strefy, one nigdzie nie sa odwolywane
            r.setAreaZones(l.getAreaZones());
            r.setCircleZones(l.getCircleZones());
        } else if (left instanceof SearchAndRescueDto && right instanceof SearchAndRescueDto) {
            SearchAndRescueDto l = (SearchAndRescueDto) left;
            SearchAndRescueDto r = (SearchAndRescueDto) right;
            // nalezy przekopiwac strefy, one nigdzie nie sa odwolywane
            r.setCircleZones(l.getCircleZones());
            r.setAreaZones(l.getAreaZones());
            r.setGridZones(l.getGridZones());
            r.setSearchUnits(l.getSearchUnits());
//            r.setTechnicalResources(l.getTechnicalResources());
//            r.setHumanResources(l.getHumanResources());
            r.setSearchAndRescueSearchPlans(l.getSearchAndRescueSearchPlans());
        } else if (left instanceof SearchUnitDto && right instanceof SearchUnitDto) {
            SearchUnitDto l = (SearchUnitDto) left;
            SearchUnitDto r = (SearchUnitDto) right;
            r.setTechnicalResources(l.getTechnicalResources());
            r.setHumanResources(l.getHumanResources());
        } else if (left instanceof GpxTraceDto && right instanceof GpxTraceDto) {
            GpxTraceDto l = (GpxTraceDto) left;
            GpxTraceDto r = (GpxTraceDto) right;
            r.setTracks(l.getTracks());
        
        }
    }
}
