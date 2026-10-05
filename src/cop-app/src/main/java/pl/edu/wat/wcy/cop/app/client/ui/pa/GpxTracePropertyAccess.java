/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.GpxTraceDto;
// Defines the contract for gpx trace property access.

public interface GpxTracePropertyAccess extends PropertyAccess<GpxTraceDto> {
    GpxTracePropertyAccess INSTANCE = GWT.create(GpxTracePropertyAccess.class);


    @Path("uuid")
    ModelKeyProvider<GpxTraceDto> key();

    ValueProvider<GpxTraceDto, String> name();

    ValueProvider<GpxTraceDto, String> description();
}
