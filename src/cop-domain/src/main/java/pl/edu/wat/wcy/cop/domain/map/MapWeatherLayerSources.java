/**
 * 
 */
package pl.edu.wat.wcy.cop.domain.map;


// Lists weather layer sources.
public enum MapWeatherLayerSources {
	CLOUDS("http://{a-c}.tile.openweathermap.org/map/clouds/{z}/{x}/{y}.png"),
	PRECIPITATIONS("http://{a-c}.tile.openweathermap.org/map/precipitation/{z}/{x}/{y}.png"),
	SEA_LEVEL_PRESSURE("http://{a-c}.tile.openweathermap.org/map/pressure/{z}/{x}/{y}.png"),
	WIND_SPEED("http://{a-c}.tile.openweathermap.org/map/wind/{z}/{x}/{y}.png"),
	TEMPERATURE("http://{a-c}.tile.openweathermap.org/map/temperature/{z}/{x}/{y}.png");
	private String url;

	MapWeatherLayerSources(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

}
