package pl.edu.wat.wcy.cop.services;


import com.goebl.simplify.PointExtractor;
import com.goebl.simplify.Simplify;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.edu.wat.wcy.cop.domain.GpxGeoPoint;
import pl.edu.wat.wcy.cop.domain.scenario.FeatureStyle;
import pl.edu.wat.wcy.cop.domain.scenario.sar.GpxTrace;
import pl.edu.wat.wcy.cop.domain.scenario.sar.GpxTraceTrack;
import pl.edu.wat.wcy.cop.services.gpx2.GPXParser;
import pl.edu.wat.wcy.cop.services.gpx2.GPXTrackPoint;
import pl.edu.wat.wcy.cop.services.repositories.impl.GpxTraceRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
// Provides gpx trace operations.
public class GpxTraceService extends AbstractServiceImpl<GpxTrace, GpxTraceRepository> {

    @Override
    public GpxTrace add(GpxTrace object) {
        updateTracks(object);
        return super.add(object);
    }


    @Override
    public GpxTrace update(GpxTrace object) {
        updateTracks(object);
        return super.update(object);
    }

    private void updateTracks(GpxTrace trace) {
        if (trace.getStyle() == null) {
            trace.setStyle(new FeatureStyle());
        }

        try {
            if (!StringUtils.isEmpty(trace.getContent())) {
                trace.getTracks().clear();
                File tmpFile = File.createTempFile(new Date().getTime() + "", ".gpx");
                FileWriter writer = new FileWriter(tmpFile);
                writer.write(trace.getContent().trim());
                writer.close();
                GPXParser gpx = new GPXParser(tmpFile.getAbsolutePath());

                gpx.getTracks().forEach(track -> {

                    GpxTraceTrack gpxTrack = MappingHelper.map(track, GpxTraceTrack.class);
                    track.getSegments().forEach(segment -> {
                        for (GPXTrackPoint point : segment.getPoints()) {
                            GpxGeoPoint mappedPoint = MappingHelper.mapGpxPoint(point, new GpxGeoPoint());
                            if (mappedPoint != null && mappedPoint.getLat() > 1 && mappedPoint.getLon() > 1) {
                                gpxTrack.getPoints().add(mappedPoint);
                            }

                        }
                    });
                    Simplify<GpxGeoPoint> simplify = new Simplify<GpxGeoPoint>(new GpxGeoPoint[0], new PointExtractor<GpxGeoPoint>() {

                        public double getX(GpxGeoPoint gpxGeoPoint) {
                            return gpxGeoPoint.getLat() * 1000000;
                        }


                        public double getY(GpxGeoPoint gpxGeoPoint) {
                            return gpxGeoPoint.getLon() * 1000000;
                        }
                    });
                    gpxTrack.setPoints(Arrays.asList(simplify.simplify(gpxTrack.getPoints().toArray(new GpxGeoPoint[gpxTrack.getPoints().size()]), 100f, false)));
                    trace.getTracks().add(gpxTrack);
                });


                trace.setContent("");
            }

        } catch (Exception ex) {
            System.err.println(ex);
        }


    }

    public static void main(String[] args) throws IOException {
        GpxTrace trace = new GpxTrace();

        GPXParser gpx = new GPXParser("F:\\Pobrane\\activity_5416303343.gpx");
        gpx.getTracks().forEach(track -> {

            GpxTraceTrack gpxTrack = MappingHelper.map(track, GpxTraceTrack.class);
            track.getSegments().forEach(segment -> {
                for (GPXTrackPoint point : segment.getPoints()) {
                    gpxTrack.getPoints().add(MappingHelper.mapGpxPoint(point, new GpxGeoPoint()));
                }
            });
            Simplify<GpxGeoPoint> simplify = new Simplify<GpxGeoPoint>(new GpxGeoPoint[0], new PointExtractor<GpxGeoPoint>() {

                public double getX(GpxGeoPoint gpxGeoPoint) {
                    return gpxGeoPoint.getLat() * 1000000;
                }


                public double getY(GpxGeoPoint gpxGeoPoint) {
                    return gpxGeoPoint.getLon() * 1000000;
                }
            });
            GpxGeoPoint[] simplified = simplify.simplify(gpxTrack.getPoints().toArray(new GpxGeoPoint[gpxTrack.getPoints().size()]), 150, false);
            System.out.println("BEFORE " + gpxTrack.getPoints().size());
            System.out.println("AFTER " + simplified.length);
            gpxTrack.setPoints(Arrays.asList(simplified));

        });
    }
}
