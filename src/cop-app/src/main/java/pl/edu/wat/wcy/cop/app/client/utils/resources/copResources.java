/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;
// Defines the contract for cop resources.


public interface copResources extends ClientBundle {
    copResources INSTANCE = GWT.create(copResources.class);

    @Source("object-tooltip-template.html")
    TextResource tooltipTempalte();
    @Source("sar-tooltip-template.html")
    TextResource sarTooltipTempalte();

    @Source("units.xml")
    TextResource units();

    @Source("SymbolsMetadata.xml")
    TextResource MswiaSymbols();

    @Source("SymbolsMetadata.json")
    TextResource MswiaSymbolsJson();

    @Source("map-crosshairs.html")
    TextResource mapCrosshairs();

    @Source("kml/czechowice-amoniak-Bleve.kml")
    TextResource czechowiceAmonikakBleveKML();
}
