/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol.extra;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import ol.source.SourceOptions;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
// Represents bing maps options.
public class BingMapsOptions extends SourceOptions {
    @JsProperty
    public native String getKey();

    @JsProperty
    public native void setKey(String key);

    @JsProperty
    public native String getImagerySet();

    @JsProperty
    public native void setImagerySet(String imagerySet);
}
