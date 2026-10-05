package pl.edu.wat.wcy.cop.app.shared.domain.sar;

import pl.edu.wat.wcy.cop.app.shared.domain.DefaultObject;

import java.util.List;
// Carries gpx trace track data.

public class GpxTraceTrackDto extends DefaultObject {
    private  String name;
    private  String comment;
    private  String description;
    private  String source;

    private List<GpxGeoPointDto> points;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<GpxGeoPointDto> getPoints() {
        return points;
    }

    public void setPoints(List<GpxGeoPointDto> points) {
        this.points = points;
    }
}
