/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.ol.sources;

import pl.edu.wat.wcy.cop.app.shared.AppConstants;
// Enumerates ol map box layer source.


public enum OLMapBoxLayerSource {

    // https://docs.mapbox.com/api/maps/#raster-tiles
    STREETS("https://api.mapbox.com/styles/v1/mapbox/streets-v11/{z}/{x}/{y}?access_token=" + AppConstants.MAPBOX_API_KEY),
    SATELITE("https://api.mapbox.com/v4/mapbox.satellite/{z}/{x}/{y}.png?access_token=" + AppConstants.MAPBOX_API_KEY),
    TERRAIN("https://api.mapbox.com/v4/mapbox.terrain-rgb/{z}/{x}/{y}.pngraw?access_token="+ AppConstants.MAPBOX_API_KEY),
    TRAFFIC("https://api.mapbox.com/v4/mapbox.mapbox-traffic-v1/9/87/204.vector.pbf?access_token="+ AppConstants.MAPBOX_API_KEY),
    OUTDOOR("https://api.mapbox.com/styles/v1/mapbox/outdoors-v11/{z}/{x}/{y}?access_token=" + AppConstants.MAPBOX_API_KEY),
    DARK("https://api.mapbox.com/styles/v1/mapbox/dark-v10/{z}/{x}/{y}?access_token=" + AppConstants.MAPBOX_API_KEY),
    LIGHT("https://api.mapbox.com/styles/v1/mapbox/light-v10/{z}/{x}/{y}?access_token=" + AppConstants.MAPBOX_API_KEY),
    NAVIGATION_PREVIEW_DAY("https://api.mapbox.com/styles/v1/mapbox/navigation-preview-day-v4/{z}/{x}/{y}?access_token=" + AppConstants.MAPBOX_API_KEY),
    NAVIGATION_PREVIEW_NIGHT("https://api.mapbox.com/styles/v1/mapbox/navigation-preview-night-v4/{z}/{x}/{y}?access_token=" + AppConstants.MAPBOX_API_KEY);
    private String url;

    OLMapBoxLayerSource(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
