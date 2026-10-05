/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.client.domain.spec.PointOfInterestClientModel;
// Defines the contract for point of interest client model property access.


public interface PointOfInterestClientModelPropertyAccess extends PropertyAccess<PointOfInterestClientModel> {
    PointOfInterestClientModelPropertyAccess INSTANCE = GWT
            .create(PointOfInterestClientModelPropertyAccess.class);

    @Path("checked")
    ValueProvider<PointOfInterestClientModel, Boolean> checked();

    @Path("object.uuid")
    ModelKeyProvider<PointOfInterestClientModel> key();

    @Path("object.name")
    ValueProvider<PointOfInterestClientModel, String> name();

    @Path("object.description")
    ValueProvider<PointOfInterestClientModel, String> description();
}
