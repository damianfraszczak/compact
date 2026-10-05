/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain;

import java.util.List;
// Carries poi multi point zone data.

public class PoiMultiPointZoneDto extends EventMultiPointZoneDto {

    /**
     *
     */
    public PoiMultiPointZoneDto() {
        super();
    }

    /**
     * @param name
     * @param points
     */
    public PoiMultiPointZoneDto(String name, List<GeoPointDto> points) {
        super(name, points);
    }

}
