/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.ol.sources;

import pl.edu.wat.wcy.cop.app.shared.AppConstants;
// Enumerates ol weather layer sources.


public enum OLWeatherLayerSources {
    CLOUDS("http://{a-c}.tile.openweathermap.org/map/clouds/{z}/{x}/{y}.png?appid=" + AppConstants.WEATHER_MAPS_API_KEY),
    PRECIPITATIONS("http://{a-c}.tile.openweathermap.org/map/precipitation/{z}/{x}/{y}.png?appid=" + AppConstants.WEATHER_MAPS_API_KEY),
    SEA_LEVEL_PRESSURE("http://{a-c}.tile.openweathermap.org/map/pressure/{z}/{x}/{y}.png?appid=" + AppConstants.WEATHER_MAPS_API_KEY),
    WIND_SPEED("http://{a-c}.tile.openweathermap.org/map/wind/{z}/{x}/{y}.png?appid=" + AppConstants.WEATHER_MAPS_API_KEY),
    TEMPERATURE("http://{a-c}.tile.openweathermap.org/map/temperature/{z}/{x}/{y}.png?appid=" + AppConstants.WEATHER_MAPS_API_KEY);
    private String url;

    OLWeatherLayerSources(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
