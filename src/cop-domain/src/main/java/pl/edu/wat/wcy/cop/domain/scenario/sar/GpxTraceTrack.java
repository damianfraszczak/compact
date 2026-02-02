package pl.edu.wat.wcy.cop.domain.scenario.sar;

import pl.edu.wat.wcy.cop.domain.BaseEntity;
import pl.edu.wat.wcy.cop.domain.GpxGeoPoint;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.LinkedList;
import java.util.List;

@Entity
// Stores a GPX track segment.
public class GpxTraceTrack extends BaseEntity {
    private String name;
    private String comment;
    private String description;
    private String source;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<GpxGeoPoint> points;

    /**
     * @return the points
     */
    public List<GpxGeoPoint> getPoints() {
        if (points == null) {
            points = new LinkedList<>();
        }

        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(List<GpxGeoPoint> points) {
        this.points = points;
    }

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
}
