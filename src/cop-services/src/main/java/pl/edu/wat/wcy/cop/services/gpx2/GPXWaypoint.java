package pl.edu.wat.wcy.cop.services.gpx2;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
// Represents gpx waypoint.

public class GPXWaypoint implements Comparable<GPXWaypoint> {

    private Double latitude;
    private Double longitude;
    private Double elevation;
    private Date date;
    private String name;


    public GPXWaypoint(Element wpt) {

        if (wpt == null) return;

        if (wpt.hasAttribute("lat")) {
            latitude = Double.valueOf(wpt.getAttribute("lat"));
        }
        if (wpt.hasAttribute("lon")) {
            longitude = Double.valueOf(wpt.getAttribute("lon"));
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
            } else if (tag.getNodeName().equals("ele")) {
                elevation = Double.valueOf(tag.getFirstChild().getNodeValue());
            } else if (tag.getNodeName().equals("time")) {
                date = DatatypeConverter.parseDateTime(tag.getFirstChild().getNodeValue()).getTime();
            }
        }


    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getElevation() {
        return elevation;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String toString() {
        return name;
    }

    @Override
    public int compareTo(GPXWaypoint o) {

        if (this.name == null) return 1;
        if (o.getName() == null) return 0;

        return this.toString().compareTo(o.toString());

    }
}