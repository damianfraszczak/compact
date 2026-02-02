/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol.extra;

import ol.Map;
// Represents geocoder.


public class Geocoder {

    public static native void initGeocoder(Map map) /*-{
        var geocoder = new $wnd.Geocoder('nominatim', {
            provider: 'photon',
            lang: 'pl',
            placeholder: 'Search ...',
            limit: 5,
            debug: true,
            autoComplete: true,
            keepOpen: true
        });
        map.addControl(geocoder);
        geocoder.on('addresschosen', function (evt) {
            geocoder.getSource().clear();
        });
    }-*/;
}
