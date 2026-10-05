package pl.edu.wat.wcy.cop.services;

import org.modelmapper.ModelMapper;
import pl.edu.wat.wcy.cop.domain.GpxGeoPoint;
import pl.edu.wat.wcy.cop.services.gpx2.GPXTrackPoint;
// Provides helper logic for mapping.

public class MappingHelper {
    public static <E, D> D map(E entity, Class<D> dtoClass) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, dtoClass);
    }

    public static GpxGeoPoint mapGpxPoint(GPXTrackPoint point, GpxGeoPoint gpxGeoPoint) {
//        gpxGeoPoint.setComment(point.getComment().orElse(null));
//        if (point.getCourse().isPresent()) {
//            gpxGeoPoint.setCourse(point.getCourse().get().doubleValue());
//        }
//        gpxGeoPoint.setDescription(point.getDescription().orElse(null));
//        gpxGeoPoint.setElevation(point.getElevation()
//                .orElse(Length.of(0, Length.Unit.FOOT)).doubleValue());
//        gpxGeoPoint.setGeoidHeight(point.getGeoidHeight()
//                .orElse(Length.of(0, Length.Unit.FOOT)).doubleValue());
//        gpxGeoPoint.setHdop(point.getHdop().orElse(0.0));
//        gpxGeoPoint.setMagneticVariation(point.getMagneticVariation()
//                .orElse(Degrees.ofDegrees(0)).doubleValue());
//        gpxGeoPoint.setName(point.getName().orElse(""));
//        gpxGeoPoint.setPdop(point.getPdop().orElse(0.0));
//        gpxGeoPoint.setSat(point.getSat().orElse(UInt.of(0)).intValue());
//        gpxGeoPoint.setSource(point.getSource().orElse(null));
//        gpxGeoPoint.setSpeed(point.getSpeed().orElse(Speed.of(0, Speed.Unit.KILOMETERS_PER_HOUR))
//                .doubleValue());
//        gpxGeoPoint.setTime(point.getTime().orElse(null));
//        gpxGeoPoint.setType(point.getType().orElse(null));
//        gpxGeoPoint.setVdop(point.getVdop().orElse(0.0));
        gpxGeoPoint.setLat(point.getLatitude().doubleValue());
        gpxGeoPoint.setLon(point.getLongitude().doubleValue());
        return gpxGeoPoint;
    }
}

