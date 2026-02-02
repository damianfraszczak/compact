/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.client.domain.spec.MSWiAUnitClientModel;
// Defines the contract for ms wi a object client model property access.


public interface MSWiAObjectClientModelPropertyAccess extends PropertyAccess<MSWiAUnitClientModel> {
    MSWiAObjectClientModelPropertyAccess INSTANCE = GWT.create(MSWiAObjectClientModelPropertyAccess.class);

    @Path("checked")
    ValueProvider<MSWiAUnitClientModel, Boolean> checked();

    @Path("object.uuid")
    ModelKeyProvider<MSWiAUnitClientModel> key();

    @Path("object.name")
    ValueProvider<MSWiAUnitClientModel, String> name();

    @Path("object.code")
    ValueProvider<MSWiAUnitClientModel, String> code();

    @Path("object.description")
    ValueProvider<MSWiAUnitClientModel, String> description();

}
