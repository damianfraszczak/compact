/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain;

import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.*;

import java.util.Date;
// Carries a dat 3 report data.


public class ADatP3ReportDto extends ScenarioObjectDto {
    private String content;

    private CBRNType cbrnType;
    private String lat;
    private String lon;
    private Date dateOfIncydent;
    private Float releaseAresRadius;
    private Float hazardAreaDistance;
    private Float hazardAreaDistanceR1;
    private Float hazardAreaDistanceR2;
    private Float hazardAreaDistanceR3;

    /**
     *
     */
    public ADatP3ReportDto() {
        super();
    }

    /**
     * @param content
     */
    public ADatP3ReportDto(String content) {
        super();
        this.content = content;
        ADatP3 aDatP3 = new ADatP3Parser().parseADatP3Content(content);
        this.cbrnType = aDatP3.getCbrnType();
        this.lat = String.valueOf(aDatP3.getMapCenter().getY());
        this.lon = String.valueOf(aDatP3.getMapCenter().getX());
        this.dateOfIncydent = aDatP3.getDelta();
        if (this.cbrnType.equals(CBRNType.BIO) || this.cbrnType.equals(CBRNType.CHEM)) {
            this.releaseAresRadius = ((PapaA) aDatP3.getPapa()).getReleaseAresRadius();
            this.hazardAreaDistance = ((PapaA) aDatP3.getPapa()).getHazardAreaDistance();
        } else {
            this.releaseAresRadius = ((PapaR) aDatP3.getPapa()).getReleaseAresRadius();
            this.hazardAreaDistanceR1 = ((PapaR) aDatP3.getPapa()).getHazardAreaDistanceR1();
            this.hazardAreaDistanceR2 = ((PapaR) aDatP3.getPapa()).getHazardAreaDistanceR2();
            this.hazardAreaDistanceR3 = ((PapaR) aDatP3.getPapa()).getHazardAreaDistanceR3();
        }
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    public CBRNType getCbrnType() {
        return cbrnType;
    }

    public void setCbrnType(CBRNType cbrnType) {
        this.cbrnType = cbrnType;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Date getDateOfIncydent() {
        return dateOfIncydent;
    }

    public void setDateOfIncydent(Date dateOfIncydent) {
        this.dateOfIncydent = dateOfIncydent;
    }

    public Float getReleaseAresRadius() {
        return releaseAresRadius;
    }

    public void setReleaseAresRadius(Float releaseAresRadius) {
        this.releaseAresRadius = releaseAresRadius;
    }

    public Float getHazardAreaDistance() {
        return hazardAreaDistance;
    }

    public void setHazardAreaDistance(Float hazardAreaDistance) {
        this.hazardAreaDistance = hazardAreaDistance;
    }

    public Float getHazardAreaDistanceR1() {
        return hazardAreaDistanceR1;
    }

    public void setHazardAreaDistanceR1(Float hazardAreaDistanceR1) {
        this.hazardAreaDistanceR1 = hazardAreaDistanceR1;
    }

    public Float getHazardAreaDistanceR2() {
        return hazardAreaDistanceR2;
    }

    public void setHazardAreaDistanceR2(Float hazardAreaDistanceR2) {
        this.hazardAreaDistanceR2 = hazardAreaDistanceR2;
    }

    public Float getHazardAreaDistanceR3() {
        return hazardAreaDistanceR3;
    }

    public void setHazardAreaDistanceR3(Float hazardAreaDistanceR3) {
        this.hazardAreaDistanceR3 = hazardAreaDistanceR3;
    }
}
