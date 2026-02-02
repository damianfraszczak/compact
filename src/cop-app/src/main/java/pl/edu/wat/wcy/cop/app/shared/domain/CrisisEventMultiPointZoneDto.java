/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain;

import java.util.List;
// Carries crisis event multi point zone data.



public class CrisisEventMultiPointZoneDto extends EventMultiPointZoneDto {

    /**
     *
     */
    public CrisisEventMultiPointZoneDto() {
        super();
    }

    /**
     * @param name
     * @param points
     */
    public CrisisEventMultiPointZoneDto(String name, List<GeoPointDto> points) {
        super(name, points);
    }

}
