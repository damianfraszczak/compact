/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.images;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.inject.Singleton;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;


@Singleton
// Defines the contract for gis images.
public interface GisImages extends ClientBundle {

    GisImages INSTANCE = GWT.create(GisImages.class);

    @Source("gis/center.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource center();

    @Source("gis/export_map.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource export_map();

    @Source("gis/layer_kml.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layer_kml();

    @Source("gis/controls.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource controls();

    @Source("gis/interactions.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource interactions();

    @Source("gis/coords.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource coords();

    @Source("gis/disabled.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource disabled();

    @Source("gis/layer_add.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layer_add();

    @Source("gis/layer_down.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layer_down();

    @Source("gis/layer_edit.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layer_edit();

    @Source("gis/layer_osm.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layer_osm();

    @Source("gis/layer_bing.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layer_bing();

    @Source("gis/layer_clouds.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layer_clouds();

    @Source("gis/layer_precipitations.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layer_precipitations();

    @Source("gis/layer_pressure.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layer_pressure();

    @Source("gis/layer_temperature.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layer_temperature();

    @Source("gis/layer_weather.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layer_weather();

    @Source("gis/layer_wind.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layer_wind();

    @Source("gis/layer_raster.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layer_raster();

    @Source("gis/layer_remove.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layer_remove();

    @Source("gis/layer_up.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layer_up();

    @Source("gis/layer_wfs.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layer_wfs();

    @Source("gis/layer_wms.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layer_wms();

    @Source("gis/layers_analytical.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layers_analytical();

    @Source("gis/layers_geo.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layers_geo();

    @Source("gis/layers_operational.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layers_operational();

    @Source("gis/layers_raster.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layers_raster();

    @Source("gis/layers_vector.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layers_vector();

    @Source("gis/layers.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource layers();

    @Source("gis/measure_area.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource measure_area();

    @Source("gis/measure_circle.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource measure_circle();

    @Source("gis/measure_distance.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource measure_distance();

    @Source("gis/measure.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource measure();

    @Source("gis/scale_line.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource scale_line();

    @Source("gis/scale_line_degrees.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource scale_line_degrees();

    @Source("gis/scale_line_metric.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource scale_line_metric();

    @Source("spy-mousemode.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource spyMouseMode();
}
