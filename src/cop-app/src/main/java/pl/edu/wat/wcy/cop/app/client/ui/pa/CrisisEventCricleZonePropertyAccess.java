/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.CrisisEventCricleZoneDto;
// Defines the contract for crisis event cricle zone property access.


public interface CrisisEventCricleZonePropertyAccess extends PropertyAccess<CrisisEventCricleZoneDto> {

    CrisisEventCricleZonePropertyAccess INSTANCE = GWT.create(CrisisEventCricleZonePropertyAccess.class);

    @Path("uuid")
    ModelKeyProvider<CrisisEventCricleZoneDto> key();

    ValueProvider<CrisisEventCricleZoneDto, String> name();

    ValueProvider<CrisisEventCricleZoneDto, String> description();

    ValueProvider<CrisisEventCricleZoneDto, Double> radius();

}
