package pl.edu.wat.wcy.cop.domain.scenario.sar;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.edu.wat.wcy.cop.domain.scenario.Zone;

import javax.persistence.*;
import java.util.List;

@Entity
// Stores a GPX trace.
public class GpxTrace extends Zone {
    @Column(name = "scenario_id")
    private Long scenarioId;

    @Lob
    private String content;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<GpxTraceTrack> tracks;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<GpxTraceTrack> getTracks() {
        return tracks;
    }

    public void setTracks(List<GpxTraceTrack> tracks) {
        this.tracks = tracks;
    }

    public Long getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(Long scenarioId) {
        this.scenarioId = scenarioId;
    }
}
