package pl.edu.wat.wcy.cop.app.client.domain.spec;

import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.domain.MapSymbolClientModel;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.client.utils.resources.copResources;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.GpxTraceDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueCircleZoneDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueDto;

import java.util.Collection;
import java.util.List;
// Represents gpx trace client model.

public class GpxTraceClientModel extends MapSymbolClientModel<GpxTraceDto> {
    public GpxTraceClientModel(GpxTraceDto object,
                               Feature... zones) {
        super(object, zones);
    }

    public GpxTraceClientModel(GpxTraceDto object,
                               Collection<Feature> zones) {
        super(object, zones.toArray(new Feature[zones.size()]));
    }
}
