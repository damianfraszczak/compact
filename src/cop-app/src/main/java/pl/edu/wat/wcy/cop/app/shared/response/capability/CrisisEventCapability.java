/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.response.capability;

import pl.edu.wat.wcy.cop.app.shared.domain.enums.crisis.CrisisEventStatusDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.crisis.CrisisEventTypeDto;
// Represents crisis event capability.


public class CrisisEventCapability extends ScenarioPointObjectCapability {
    private String htrcId = ResponseUtils.STRING_NAME;
    private String type = ResponseUtils.oneOfOptions(CrisisEventTypeDto.values());
    private String status = ResponseUtils.oneOfOptions(CrisisEventStatusDto.values());
    private String substanceName = ResponseUtils.STRING_NAME;
    private String threatLevel = ResponseUtils.STRING_NAME;
    private String province = ResponseUtils.STRING_NAME;
    private String city = ResponseUtils.STRING_NAME;

    private String timestampStart = ResponseUtils.LONG_NAME;

    private String timestampEnd = ResponseUtils.LONG_NAME;

    private String source = ResponseUtils.STRING_NAME;
    private String description = ResponseUtils.STRING_NAME;

    /**
     * @return the htrcId
     */
    public String getHtrcId() {
        return htrcId;
    }

    /**
     * @param htrcId the htrcId to set
     */
    public void setHtrcId(String htrcId) {
        this.htrcId = htrcId;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the substanceName
     */
    public String getSubstanceName() {
        return substanceName;
    }

    /**
     * @param substanceName the substanceName to set
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
     * @param threatLevel the threatLevel to set
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
     * @param province the province to set
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
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the timestampStart
     */
    public String getTimestampStart() {
        return timestampStart;
    }

    /**
     * @param timestampStart the timestampStart to set
     */
    public void setTimestampStart(String timestampStart) {
        this.timestampStart = timestampStart;
    }

    /**
     * @return the timestampEnd
     */
    public String getTimestampEnd() {
        return timestampEnd;
    }

    /**
     * @param timestampEnd the timestampEnd to set
     */
    public void setTimestampEnd(String timestampEnd) {
        this.timestampEnd = timestampEnd;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
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
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }


}
