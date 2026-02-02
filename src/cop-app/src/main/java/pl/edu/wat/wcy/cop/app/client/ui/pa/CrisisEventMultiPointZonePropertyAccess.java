/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.CrisisEventMultiPointZoneDto;
// Defines the contract for crisis event multi point zone property access.


public interface CrisisEventMultiPointZonePropertyAccess extends PropertyAccess<CrisisEventMultiPointZoneDto> {
    CrisisEventMultiPointZonePropertyAccess INSTANCE = GWT
            .create(CrisisEventMultiPointZonePropertyAccess.class);

    @Path("uuid")
    ModelKeyProvider<CrisisEventMultiPointZoneDto> key();

    ValueProvider<CrisisEventMultiPointZoneDto, String> name();

    ValueProvider<CrisisEventMultiPointZoneDto, String> description();
}
