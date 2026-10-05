/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.PointOfInterestDto;
// Defines the contract for point of interest property access.


public interface PointOfInterestPropertyAccess extends PropertyAccess<PointOfInterestDto> {
    PointOfInterestPropertyAccess INSTANCE = GWT.create(PointOfInterestPropertyAccess.class);

    @Path("uuid")
    ModelKeyProvider<PointOfInterestDto> key();

    ValueProvider<PointOfInterestDto, String> name();

    ValueProvider<PointOfInterestDto, String> description();
}
