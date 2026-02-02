package pl.edu.wat.wcy.cop.app.shared.domain.sar;

import pl.edu.wat.wcy.cop.app.shared.domain.GeoPointDto;

import java.util.Date;
// Carries gpx geo point data.

public class GpxGeoPointDto extends GeoPointDto {

    private Double elevation;
    private Double speed;
    private Date time;
    private Double magneticVariation;
    private Double geoidHeight;
    private String name;
    private String comment;
    private String description;
    private String source;
    private String symbol;
    private String type;
    private Integer sat;
    private Double hdop;
    private Double vdop;
    private Double pdop;
    private Double course;

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getMagneticVariation() {
        return magneticVariation;
    }

    public void setMagneticVariation(Double magneticVariation) {
        this.magneticVariation = magneticVariation;
    }

    public Double getGeoidHeight() {
        return geoidHeight;
    }

    public void setGeoidHeight(Double geoidHeight) {
        this.geoidHeight = geoidHeight;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSat() {
        return sat;
    }

    public void setSat(Integer sat) {
        this.sat = sat;
    }

    public Double getHdop() {
        return hdop;
    }

    public void setHdop(Double hdop) {
        this.hdop = hdop;
    }

    public Double getVdop() {
        return vdop;
    }

    public void setVdop(Double vdop) {
        this.vdop = vdop;
    }

    public Double getPdop() {
        return pdop;
    }

    public void setPdop(Double pdop) {
        this.pdop = pdop;
    }

    public Double getCourse() {
        return course;
    }

    public void setCourse(Double course) {
        this.course = course;
    }
}
