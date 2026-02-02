/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol.extra;

import jsinterop.annotations.JsType;
import ol.source.TileImage;

@JsType(isNative = true, name = "BingMaps", namespace = "ol.source")
// Represents bing maps source.
public class BingMapsSource extends TileImage {

    public BingMapsSource() {
    }

    public BingMapsSource(BingMapsOptions bingMapsOptions) {
    }

}
