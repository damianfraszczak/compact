package pl.edu.wat.wcy.cop.app.shared.domain.sar;

import pl.edu.wat.wcy.cop.app.shared.domain.EventZoneDto;
import pl.edu.wat.wcy.cop.app.shared.domain.FeatureStyleDto;
import pl.edu.wat.wcy.cop.app.shared.domain.ScenarioObject;

import java.util.LinkedList;
import java.util.List;
// Carries gpx trace data.

public class GpxTraceDto extends EventZoneDto implements ScenarioObject {
    private Long scenarioId;
    private String content;

    private List<GpxTraceTrackDto> tracks;

    /**
     *
     */
    public GpxTraceDto() {
        super();
        FeatureStyleDto style = new FeatureStyleDto();
        style.setFillAlpha(0);
        setStyle(style);
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<GpxTraceTrackDto> getTracks() {
        if (tracks == null) {
            tracks = new LinkedList<>();
        }
        return tracks;
    }

    public void setTracks(List<GpxTraceTrackDto> tracks) {
        this.tracks = tracks;
    }

    /**
     * @return the scenarioId
     */
    public Long getScenarioId() {
        return scenarioId;
    }

    /**
     * @param scenarioId the scenarioId to set
     */
    public void setScenarioId(Long scenarioId) {
        this.scenarioId = scenarioId;
    }
}
