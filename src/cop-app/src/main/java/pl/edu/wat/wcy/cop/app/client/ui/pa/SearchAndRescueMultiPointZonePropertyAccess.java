/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueSegmentDto;
// Defines the contract for search and rescue multi point zone property access.

public interface SearchAndRescueMultiPointZonePropertyAccess extends PropertyAccess<SearchAndRescueSegmentDto> {
    SearchAndRescueMultiPointZonePropertyAccess INSTANCE = GWT.create(SearchAndRescueMultiPointZonePropertyAccess.class);

    @Path("uuid")
    ModelKeyProvider<SearchAndRescueSegmentDto> key();

    ValueProvider<SearchAndRescueSegmentDto, String> name();

    ValueProvider<SearchAndRescueSegmentDto, String> description();
    ValueProvider<SearchAndRescueSegmentDto, Double> poc();
    ValueProvider<SearchAndRescueSegmentDto, Double> area();
}
