package pl.edu.wat.wcy.cop.services.gpx2;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
// Represents gpx track.

public class GPXTrack implements Comparable<GPXTrack> {
    private String type;
    private String name;
    private ArrayList<GPXTrackSegment> segments = new ArrayList<>();

    public GPXTrack(Element wpt) {

        if (wpt == null) return;

        if (wpt.hasAttribute("type")) {
            type = wpt.getAttribute("type");
        }
        if (wpt.hasAttribute("name")) {
            name = wpt.getAttribute("name");
        }

        NodeList values = wpt.getChildNodes();

        for (int i = 0; i <= values.getLength(); i++) {
            Element tag;

            if (values.item(i) instanceof Element) {
                tag = (Element) values.item(i);
            } else {
                continue;
            }

            if (tag.getNodeName().equals("name")) {
                name = tag.getFirstChild().getNodeValue();
            } else if (tag.getNodeName().equals("type")) {
                type = tag.getFirstChild().getNodeValue();
            }
        }

        processSegments(wpt.getElementsByTagName("trkseg"));

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<GPXTrackSegment> getSegments() {
        return segments;
    }

    public void setSegments(ArrayList<GPXTrackSegment> segments) {
        this.segments = segments;
    }

    private void processSegments(NodeList values) {
        for (int i = 0; i <= values.getLength(); i++) {
            Element tag;

            if (values.item(i) instanceof Element) {
                tag = (Element) values.item(i);
            } else {
                continue;
            }

            GPXTrackSegment segment = new GPXTrackSegment(tag);
            segments.add(segment);


        }
    }

    @Override
    public int compareTo(GPXTrack o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Name " + name + " segments " + segments;
    }
}
