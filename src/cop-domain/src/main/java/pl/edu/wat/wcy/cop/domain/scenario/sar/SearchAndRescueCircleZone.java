package pl.edu.wat.wcy.cop.domain.scenario.sar;

import pl.edu.wat.wcy.cop.domain.scenario.CircleZone;

import javax.persistence.Entity;

@Entity
// Defines a circular search-and-rescue zone.
public class SearchAndRescueCircleZone extends CircleZone {
    private Integer dispersion;

    public Integer getDispersion() {
        return dispersion;
    }

    public void setDispersion(Integer dispersion) {
        this.dispersion = dispersion;
    }
}
