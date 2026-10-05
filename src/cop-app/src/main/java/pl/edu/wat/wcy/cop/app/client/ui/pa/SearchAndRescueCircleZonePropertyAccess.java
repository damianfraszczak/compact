/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueCircleZoneDto;
// Defines the contract for search and rescue circle zone property access.



public interface SearchAndRescueCircleZonePropertyAccess extends PropertyAccess<SearchAndRescueCircleZoneDto> {

    SearchAndRescueCircleZonePropertyAccess INSTANCE = GWT.create(SearchAndRescueCircleZonePropertyAccess.class);

    @Path("uuid")
    ModelKeyProvider<SearchAndRescueCircleZoneDto> key();

    ValueProvider<SearchAndRescueCircleZoneDto, String> name();

    ValueProvider<SearchAndRescueCircleZoneDto, String> description();

    ValueProvider<SearchAndRescueCircleZoneDto, Double> radius();
    ValueProvider<SearchAndRescueCircleZoneDto, Integer> dispersion();
}
