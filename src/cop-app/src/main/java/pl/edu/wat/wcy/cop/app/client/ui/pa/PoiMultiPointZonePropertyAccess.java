/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.PoiMultiPointZoneDto;
// Defines the contract for poi multi point zone property access.


public interface PoiMultiPointZonePropertyAccess extends PropertyAccess<PoiMultiPointZoneDto> {
    PoiMultiPointZonePropertyAccess INSTANCE = GWT.create(PoiMultiPointZonePropertyAccess.class);

    @Path("uuid")
    ModelKeyProvider<PoiMultiPointZoneDto> key();

    ValueProvider<PoiMultiPointZoneDto, String> name();

    ValueProvider<PoiMultiPointZoneDto, String> description();
}
