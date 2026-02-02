/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.PoiCircleZoneDto;
// Defines the contract for poi circle zone property access.


public interface PoiCircleZonePropertyAccess extends PropertyAccess<PoiCircleZoneDto> {

    PoiCircleZonePropertyAccess INSTANCE = GWT.create(PoiCircleZonePropertyAccess.class);

    @Path("uuid")
    ModelKeyProvider<PoiCircleZoneDto> key();

    ValueProvider<PoiCircleZoneDto, String> name();

    ValueProvider<PoiCircleZoneDto, String> description();

    ValueProvider<PoiCircleZoneDto, Double> radius();

}
