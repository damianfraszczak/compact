/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.GeoPointDto;
// Defines the contract for geo point property access.


public interface GeoPointPropertyAccess extends PropertyAccess<GeoPointDto> {
    GeoPointPropertyAccess INSTANCE = GWT.create(GeoPointPropertyAccess.class);
    ModelKeyProvider<GeoPointDto> key = new ModelKeyProvider<GeoPointDto>() {

        @Override
        public String getKey(GeoPointDto item) {
            return item.getId();
        }
    };

    ValueProvider<GeoPointDto, Double> lat();

    ValueProvider<GeoPointDto, Double> lon();
}
