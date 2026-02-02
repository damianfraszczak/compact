package pl.edu.wat.wcy.cop.app.shared.domain.sar;

import pl.edu.wat.wcy.cop.app.shared.domain.EventCircleZoneDto;
// Carries search and rescue circle zone data.

public class SearchAndRescueCircleZoneDto extends EventCircleZoneDto {
    private int dispersion;

    public int getDispersion() {
        return dispersion;
    }

    public void setDispersion(int dispersion) {
        this.dispersion = dispersion;
    }
}
