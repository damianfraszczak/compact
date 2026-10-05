package pl.edu.wat.wcy.cop.services.gpx2;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
// Represents gpx track segment.

public class GPXTrackSegment {

    private ArrayList<GPXTrackPoint> points = new ArrayList<GPXTrackPoint>();

    public GPXTrackSegment(Element wpt) {

        processPoints(wpt.getElementsByTagName("trkpt"));

    }

    public ArrayList<GPXTrackPoint> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<GPXTrackPoint> points) {
        this.points = points;
    }

    private void processPoints(NodeList values) {
        for (int i = 0; i <= values.getLength(); i++) {
            Element tag;

            if (values.item(i) instanceof Element) {
                tag = (Element) values.item(i);
            } else {
                continue;
            }

            GPXTrackPoint point = new GPXTrackPoint(tag);
            points.add(point);


        }
    }

    @Override
    public String toString() {
        return "GPXTrackSegment - " + points;
    }
}
