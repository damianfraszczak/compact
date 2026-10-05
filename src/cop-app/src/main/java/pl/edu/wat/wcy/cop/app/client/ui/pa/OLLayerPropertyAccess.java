/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayerType;
// Defines the contract for ol layer property access.


public interface OLLayerPropertyAccess extends PropertyAccess<OLLayer> {
    OLLayerPropertyAccess INSTANCE = GWT.create(OLLayerPropertyAccess.class);

    @Path("name")
    ModelKeyProvider<OLLayer> key();

    @Path("name")
    ValueProvider<OLLayer, String> name();

    ValueProvider<OLLayer, OLLayerType> layerType();
}
