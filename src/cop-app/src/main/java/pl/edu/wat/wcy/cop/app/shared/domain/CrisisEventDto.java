/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain;

import pl.edu.wat.wcy.cop.app.shared.domain.enums.crisis.CrisisEventStatusDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.crisis.CrisisEventTypeDto;

import java.util.LinkedList;
import java.util.List;
// Carries crisis event data.


public class CrisisEventDto extends ScenarioPointObjectDto implements ISymbolOnTheMap<String> {

    private String htrcId;
    private CrisisEventTypeDto type;
    private CrisisEventStatusDto status;
    private String substanceName;
    private String threatLevel;
    private String province;
    private String city;

    private long timestampStart;
    private long timestampEnd;

    private String source;
    private String description;

    private List<CrisisEventCricleZoneDto> circleZones;

    private List<CrisisEventMultiPointZoneDto> areaZones;

    /**
     *
     */
    public CrisisEventDto() {
        super();
    }

    /**
     * @param point
     */
    public CrisisEventDto(GeoPointDto point) {
        super(point);
    }

    public CrisisEventDto(String htrcId, CrisisEventTypeDto type, CrisisEventStatusDto status, String substanceName,
                          String threatLevel, String province, String city, long timestampStart, long timestampEnd, String source,
                          String description, List<CrisisEventCricleZoneDto> circleZones,
                          List<CrisisEventMultiPointZoneDto> areaZones) {
        super();
        this.htrcId = htrcId;
        this.type = type;
        this.status = status;
        this.substanceName = substanceName;
        this.threatLevel = threatLevel;
        this.province = province;
        this.city = city;
        this.timestampStart = timestampStart;
        this.timestampEnd = timestampEnd;
        this.source = source;
        this.description = description;
        this.circleZones = circleZones;
        this.areaZones = areaZones;
    }

    /**
     * @return the htrcId
     */
    public String getHtrcId() {
        return htrcId;
    }

    /**
     * @param htrcId
     *            the htrcId to set
     */
    public void setHtrcId(String htrcId) {
        this.htrcId = htrcId;
    }

    /**
     * @return the type
     */
    public CrisisEventTypeDto getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(CrisisEventTypeDto type) {
        this.type = type;
    }

    /**
     * @return the status
     */
    public CrisisEventStatusDto getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(CrisisEventStatusDto status) {
        this.status = status;
    }

    /**
     * @return the substanceName
     */
    public String getSubstanceName() {
        return substanceName;
    }

    /**
     * @param substanceName
     *            the substanceName to set
     */
    public void setSubstanceName(String substanceName) {
        this.substanceName = substanceName;
    }

    /**
     * @return the threatLevel
     */
    public String getThreatLevel() {
        return threatLevel;
    }

    /**
     * @param threatLevel
     *            the threatLevel to set
     */
    public void setThreatLevel(String threatLevel) {
        this.threatLevel = threatLevel;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province
     *            the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     *            the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the timestampStart
     */
    public long getTimestampStart() {
        return timestampStart;
    }

    /**
     * @param timestampStart
     *            the timestampStart to set
     */
    public void setTimestampStart(long timestampStart) {
        this.timestampStart = timestampStart;
    }

    /**
     * @return the timestampEnd
     */
    public long getTimestampEnd() {
        return timestampEnd;
    }

    /**
     * @param timestampEnd
     *            the timestampEnd to set
     */
    public void setTimestampEnd(long timestampEnd) {
        this.timestampEnd = timestampEnd;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source
     *            the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the circleZones
     */
    public List<CrisisEventCricleZoneDto> getCircleZones() {
        if (circleZones == null) {
            circleZones = new LinkedList<CrisisEventCricleZoneDto>();
        }
        return circleZones;
    }

    /**
     * @param circleZones
     *            the circleZones to set
     */
    public void setCircleZones(List<CrisisEventCricleZoneDto> circleZones) {

        this.circleZones = circleZones;
    }

    /**
     * @return the areaZones
     */
    public List<CrisisEventMultiPointZoneDto> getAreaZones() {
        if (areaZones == null) {
            areaZones = new LinkedList<CrisisEventMultiPointZoneDto>();
        }
        return areaZones;
    }

    /**
     * @param areaZones
     *            the areaZones to set
     */
    public void setAreaZones(List<CrisisEventMultiPointZoneDto> areaZones) {
        this.areaZones = areaZones;
    }

    @Override
    public String toString() {
        return getDescription();
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.shared.domain.ISymbolOnTheMap#getName()
     */
    @Override
    public String getName() {
        return htrcId;
    }
}
