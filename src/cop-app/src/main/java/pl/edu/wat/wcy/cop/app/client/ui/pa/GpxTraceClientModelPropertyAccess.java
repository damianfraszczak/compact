/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.client.domain.spec.GpxTraceClientModel;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.GpxTraceDto;
// Defines the contract for gpx trace client model property access.

public interface GpxTraceClientModelPropertyAccess extends PropertyAccess<GpxTraceClientModel> {
    GpxTraceClientModelPropertyAccess INSTANCE = GWT.create(GpxTraceClientModelPropertyAccess.class);
    @Path("checked")
    ValueProvider<GpxTraceClientModel, Boolean> checked();

    @Path("object.uuid")
    ModelKeyProvider<GpxTraceClientModel> key();
    @Path("object.name")
    ValueProvider<GpxTraceClientModel, String> name();
    @Path("object.description")
    ValueProvider<GpxTraceClientModel, String> description();
}
