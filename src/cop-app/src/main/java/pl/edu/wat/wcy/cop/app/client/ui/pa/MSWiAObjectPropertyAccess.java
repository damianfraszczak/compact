/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.MSWiAUnitDto;
// Defines the contract for ms wi a object property access.


public interface MSWiAObjectPropertyAccess extends PropertyAccess<MSWiAUnitDto> {
    MSWiAObjectPropertyAccess INSTANCE = GWT.create(MSWiAObjectPropertyAccess.class);

    @Path("uuid")
    ModelKeyProvider<MSWiAUnitDto> key();

    ValueProvider<MSWiAUnitDto, String> name();

    ValueProvider<MSWiAUnitDto, String> code();

    ValueProvider<MSWiAUnitDto, String> description();

}
