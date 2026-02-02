/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol;

import ol.*;
import ol.control.Control;
import ol.event.EventListener;
import ol.format.JsonFeature;
import ol.layer.Base;
import ol.layer.Vector;
import ol.source.Source;
import pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationActivity;
import pl.edu.wat.wcy.cop.app.client.ol.extra.wrappers.FeatureHoverListener;
import pl.edu.wat.wcy.cop.app.client.utils.resources.copResources;

import java.lang.Object;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
// Represents ol native methods.


public class OLNativeMethods {

    public static void fitFeaturesOfLayerSourceOnMap(Map map, ol.source.Vector vectorSource) {
        fitToFeatureOnMap(map, vectorSource.getFeatures());
    }

    public static void fitToAllFeatures(Map map) {
        List<Feature> features = collectVectorFeatures(map);
        fitToFeatureOnMap(map, features.toArray(new Feature[0]));
    }

    public static native void fitToFeatureOnMap(Map map, ol.Feature... features) /*-{
        var shouldFit = false;
        if (features != null && features.length > 0) {
            var extent = features[0].getGeometry().getExtent().slice(0);
            features.forEach(function (feature) {
                if (feature != null && feature.getGeometry().getCoordinates()[0] != 0.0
                    && feature.getGeometry().getCoordinates()[1] != 0.0) {
                    $wnd.ol.extent
                        .extend(extent, feature.getGeometry().getExtent());
                    shouldFit = true;
                }

            });
            if (shouldFit) {
                map.getView().fit(extent, map.getSize(), {
                    constrainResolution: true,
                    nearest: true
                });
                $wnd.zoomOnFit(map);
            }
        }

    }-*/;

    public static native void initStaticFunctions()/*-{
        $wnd.zoomOnFit = function (map) {
            var zoomAfterFit = map.getView().getZoom() - 2;
            if (zoomAfterFit > 10)
                zoomAfterFit = 10;
            map.getView().setZoom(zoomAfterFit);
        };
        $wnd.elastic = function (t) {
            return Math.pow(2, -10 * t)
                * Math.sin((t - 0.075) * (2 * Math.PI) / 0.3) + 1;
        };
        $wnd.staticCenterOnObject = function (view, obj) {
            view.animate({
                duration: 700,
                easing: $wnd.elastic,
                center: obj.coordinate
            });
        };
        $wnd.staticExportMap = function staticExportMap(map) {
            var fileName = "mapa.png";
            map.once('postcompose', function (event) {
                var canvas = event.context.canvas;
                if ($wnd.navigator.msSaveBlob) {
                    $wnd.navigator.msSaveBlob(canvas.msToBlob(), fileName);
                } else {
                    canvas.toBlob(function (blob) {
                        $wnd.saveAs(blob, fileName);
                    });
                }
            });
            map.renderSync();
        }
    }-*/;

    public static int getLayerIndex(Map map, Base layer) {
        return findLayerIndex(map, layer);
    }

    private static int findLayerIndex(Map map, Base layer) {
        for (int i = 0; i < map.getLayers().getLength(); i++) {
            if (map.getLayers().getArray()[i] == layer) {
                return i;
            }
        }
        return -1;
    }

    public static boolean layerUp(Map map, Base layer) {
        int currentIndex = getLayerIndex(map, layer);
        return setLayerIndex(map, layer, currentIndex, currentIndex - 1);
    }

    public static boolean layerDown(Map map, Base layer) {
        int currentIndex = getLayerIndex(map, layer);
        return setLayerIndex(map, layer, currentIndex, currentIndex + 1);
    }

    public static boolean layerTop(Map map, Base layer) {
        int currentIndex = getLayerIndex(map, layer);
        return setLayerIndex(map, layer, currentIndex, map.getLayers().getLength() - 1);
    }

    public static boolean layerBottom(Map map, Base layer) {
        int currentIndex = getLayerIndex(map, layer);
        return setLayerIndex(map, layer, currentIndex, 0);
    }

    private static List<Feature> collectVectorFeatures(Map map) {
        List<Feature> features = new LinkedList<>();
        for (int i = 0; i < map.getLayers().getLength(); i++) {
            Base layer = map.getLayers().getArray()[i];
            if (layer instanceof Vector) {
                Vector vectorLayer = (Vector) layer;
                features.addAll(Arrays.asList(((ol.source.Vector) vectorLayer.getSource()).getFeatures()));
            }
        }
        return features;
    }

    public static native boolean setLayerIndex(Map map, Base layer, int currentIndex, int newIndex) /*-{
        var layersCount = map.getLayers().getLength();
        if (newIndex > -1 && newIndex < layersCount) {
            var layerToChange = map.getLayers().getArray()[newIndex];
            map.getLayers().getArray()[newIndex] = layer;
            map.getLayers().getArray()[currentIndex] = layerToChange;
            map.updateSize();
            return true;
        }
        return false;

    }-*/;

    /**
     * @param map
     * @param layer
     * @param index
     */
    public static native void setLayerIndex(Map map, Base layer, int index) /*-{
        map.getLayers().getArray().splice(index, 0, layer);
        map.updateSize();
    }-*/;

    public static native void addPointMoveListener(Map map, FeatureHoverListener listener) /*-{
        map
            .on(
                'pointermove',
                function (evt) {
                    var pixel = evt.pixel;
                    var feature = map.forEachFeatureAtPixel(pixel,
                        function (feature) {
                            return feature;
                        });
                    listener.@pl.edu.wat.wcy.cop.app.client.ol.extra.wrappers.FeatureHoverListener::onFeatureHovered(Lol/Coordinate;Lol/Feature;)(evt.coordinate, feature);
                });
    }-*/;

    public static native void addListener(Map map, String type, EventListener<MapBrowserEvent> listener) /*-{
        map
            .on(type, listener);
    }-*/;

    public static native void removeListener(Map map, String type, EventListener<MapBrowserEvent> listener) /*-{
        map
            .un(type, listener);
    }-*/;

    public static native ol.layer.Vector createVectorTileLayer(String url, JsonFeature format)
        /*-{
            var vector = new $wnd.ol.layer.Vector({
                source: new $wnd.ol.source.VectorTile({
                    url: url,
                    format: format
                })
            });
            return vector
        }-*/;

    public static native void setMapLegentText(String title, String desc)/*-{
        var container = $wnd.mapLegend;
        while (container.firstChild) {
            container.removeChild(container.lastChild);
        }
        if (title) {
            var description = $wnd.document
                .createElement('div');
            description.className = "description";
            description.innerHTML = desc;
            description.hidden = false;
            var name = $wnd.document
                .createElement('h1');
            var span = $wnd.document
                .createElement('span');
            span.className = "";
            name.appendChild(span);
            name.innerHTML += title;

            container.appendChild(name);
            container.appendChild(description);
        }


    }-*/;


    public static native ol.layer.Vector createVectorLayer(String url, Object format, boolean addToMapLegend)
        /*-{
            function isValidUrl(url) {
                try {
                    new $wnd.URL(url);
                    // no exception - url is valid
                    return true;
                } catch (e) {
                    //exception thrown - url invalid
                    return false;
                }
            }

            if (!isValidUrl(url)) {
                var blob = new $wnd.Blob([url], {
                    type: 'text/xml'
                });
                url = $wnd.URL.createObjectURL(blob);
            }
            var vector = new $wnd.ol.layer.Vector({
                source: new $wnd.ol.source.Vector({
                    url: url,
                    format: format
                })
            });
            if (addToMapLegend)
                vector
                    .getSource()
                    .on(
                        'change',
                        function (evt) {
                            var source = evt.target;
                            if (source.getState() === 'ready') {
                                //addFeaturesToLegend
                                var features = source.getFeatures();
                                var container = $wnd.mapLegend;
                                for (var key in features) {
                                    var feature = features[key];
                                    var properties = feature
                                        .getProperties();
                                    var color = feature.getStyleFunction()
                                        .bind(feature)()[0].getStroke()
                                        .getColor();
                                    var rgba = "rgba(" + color[0] + ", "
                                        + color[1] + ", " + color[2]
                                        + ", " + color[3] + ")";

                                    var description = $wnd.document
                                        .createElement('div');
                                    description.className = "description";
                                    description.innerHTML = properties.description;
                                    description.hidden = true;

                                    var name = $wnd.document
                                        .createElement('h1');
                                    var collapseButton = $wnd.document
                                        .createElement('button');
                                    collapseButton.innerHTML = "&#x25BC;";
                                    collapseButton
                                        .setAttribute(
                                            "onclick",
                                            "var element = this.parentElement.nextElementSibling;if(element.hidden){element.hidden=false;this.innerHTML=\"&#x25B2;\";}else{element.hidden=true;this.innerHTML=\"&#x25BC;\";}");
                                    name.appendChild(collapseButton);
                                    var span = $wnd.document
                                        .createElement('span');
                                    span.style.background = rgba;
                                    span.className = "color-square";
                                    name.appendChild(span);
                                    name.innerHTML += properties.name;

                                    container.appendChild(name);
                                    container.appendChild(description);
                                }
                            }
                        });
            return vector
        }-*/;

    /**
     * @param url
     * @return
     */
    public static native ol.layer.Vector createHeatmapLayer(String url)
    /*-{
        return new $wnd.ol.layer.Heatmap({
            source: new $wnd.ol.source.Vector({
                url: url,
                format: new $wnd.ol.format.KML({
                    extractStyles: false
                })
            }),
        });
    }-*/;

    public static native ol.Coordinate getFeatureCords(Feature feature) /*-{
        return feature.getGeometry().getCoordinates();
    }-*/;

    public static native ol.Coordinate[][] getFeatureCordsAsArray(Feature feature) /*-{
        return feature.getGeometry().getCoordinates();
    }-*/;

    public static native JsonFeature createGeoJsonFormat() /*-{
        return new $wnd.ol.format.GeoJSON({});
    }-*/;

    public static native JsonFeature createGpxFormat() /*-{
        return new $wnd.ol.format.GeoJSON({});
    }-*/;

    public static native JsonFeature createKMLFormat() /*-{
        return new $wnd.ol.format.GPX({});
    }-*/;

    public static native JsonFeature createMVTFormat() /*-{
        return new $wnd.ol.format.MVT({});
    }-*/;

    public static native JsonFeature createTopoJSONFormat() /*-{
        return new $wnd.ol.format.TopoJSON({});
    }-*/;

    public static ol.style.Text createTextStyle(String text, TextAlign textAlign, TextBaseline textBaseline) {
        return createNativeTextStyle(text, textAlign.getValue(), textBaseline.getValue());
    }

    /**
     * @param text
     * @param textAlign
     * @param textBaseline
     * @return
     */
    public static native ol.style.Text createNativeTextStyle(String text, String textAlign, String textBaseline)
    /*-{
        return new new $wnd.ol.style.Text({
            textAlign: textAlign,
            textBaseline: textBaseline,
            //font : font,
            text: text,
            //fill : new $wnd.ol.style.Fill({
            //	color : fillColor
            //}),
            //stroke : new ol.style.Stroke({
            //	color : outlineColor,
            //	width : outlineWidth
            //}),
            offsetX: 0,
            offsetY: 0,
            rotation: 0
        });
    }-*/;

    public static native Vector createClusterLayerFor(ol.source.Vector source, int distance)
        /*-{
            var clusterSource = new $wnd.ol.source.Cluster({
                distance: distance,
                source: source,
                geometryFunction: function (feature) {
                    var featureType = feature.getGeometry().getType();
                    if (featureType == 'Point')
                        return feature.getGeometry();
                    else
                        return null;
                },
            });
            var styleCache = {};
            var cluster = new $wnd.ol.layer.Vector({
                source: clusterSource,
                style: function (feature) {
                    var size = feature.get('features').length;
                    var style = styleCache[size];
                    if (!style) {
                        style = new $wnd.ol.style.Style({
                            text: new $wnd.ol.style.Text({
                                text: size > 1 ? size.toString() : "",
                                fill: new $wnd.ol.style.Fill({
                                    color: '#fff'
                                })
                            })
                        });
                        styleCache[size] = style;
                    }
                    return style;
                },

            });
            return cluster;
        }-*/;

    public static native void exportMap(ol.Map map)
        /*-{
            $wnd.staticExportMap(map);
            (map);
        }-*/;

    public static native void startSpyMouseMode(ol.Map map)
        /*-{

            $wnd.radius = 75;

            $wnd.mousePosition = null;

            if (!$wnd.spyMouseMoveListener) {
                $wnd.spyMouseMoveListener = function (event) {
                    $wnd.mousePosition = map.getEventPixel(event);
                    map.render();
                }
            }
            if (!$wnd.spyMouseOutListener) {
                $wnd.spyMouseOutListener = function () {
                    $wnd.mousePosition = null;
                    map.render();
                }
            }
            var container = $wnd.document.getElementById('cop-map');
            container.addEventListener('mousemove', $wnd.spyMouseMoveListener);
            container.addEventListener('mouseout', $wnd.spyMouseOutListener);

            if (!$wnd.precomposeListener) {
                $wnd.precomposeListener = function (event) {
                    var ctx = event.context;
                    var pixelRatio = event.frameState.pixelRatio;
                    ctx.save();
                    ctx.beginPath();
                    if ($wnd.mousePosition) {
                        // only show a circle around the mouse
                        ctx.arc($wnd.mousePosition[0] * pixelRatio,
                            $wnd.mousePosition[1] * pixelRatio, $wnd.radius
                            * pixelRatio, 0, 2 * Math.PI);
                        ctx.lineWidth = 5 * pixelRatio;
                        ctx.strokeStyle = 'rgba(0,0,0,0.5)';
                        ctx.stroke();
                    }
                    ctx.clip();
                }
            }

            if (!$wnd.postcomposeListener) {
                $wnd.postcomposeListener = function (event) {
                    var ctx = event.context;
                    ctx.restore();
                }
            }
            if (!$wnd.spySelectedLayer) {
                //set default layer
                var layers = map.getLayers();
                $wnd.spySelectedLayer = layers.item(layers.getLength() - 1);
            }
            if ($wnd.spySelectedLayer) {
                //start spy mouse mode
                $wnd.spySelectedLayer.on('precompose', $wnd.precomposeListener);
                $wnd.spySelectedLayer.on('postcompose', $wnd.postcomposeListener);

            }
            //listen on changes
            $wnd.changeSpySelectedLayer = function (layer) {

                if ($wnd.spySelectedLayer) {
                    $wnd.spySelectedLayer.un('precompose', $wnd.precomposeListener);
                    $wnd.spySelectedLayer.un('postcompose',
                        $wnd.postcomposeListener);
                }
                $wnd.spySelectedLayer = layer;
                if ($wnd.spySelectedLayer) {
                    //start spy mouse mode
                    $wnd.spySelectedLayer.on('precompose', $wnd.precomposeListener);
                    $wnd.spySelectedLayer.on('postcompose',
                        $wnd.postcomposeListener);
                }
            }

        }-*/;

    public static native void stopSpyMouseMode(ol.Map map)
        /*-{
            $wnd.mousePosition = null;

            var container = $wnd.document.getElementById('cop-map');
            container.removeEventListener('mousemove', $wnd.spyMouseMoveListener);
            container.removeEventListener('mouseout', $wnd.spyMouseOutListener);

            if ($wnd.spySelectedLayer) {
                //stop mouse mode
                $wnd.spySelectedLayer.un('precompose', $wnd.precomposeListener);
                $wnd.spySelectedLayer.un('postcompose', $wnd.postcomposeListener);
            }

            //listen on changes
            $wnd.changeSpySelectedLayer = function (layer) {
                $wnd.spySelectedLayer = layer;
            };
            map.render();
        }-*/;

    /**
     * @param source
     * @return
     */
    public static native String getLayerUrl(Source source)/*-{
        var urls = "";
        if (source.getUrls)
            return getUrls()[0];
        else
            return urls;
    }-*/;

    public static native GenericFunction<?, ?> getSelectInteractionStyle()/*-{
        var styles = {};
        styles['Polygon'] = [
            new $wnd.ol.style.Style({
                fill: new $wnd.ol.style.Fill({
                    color: [255, 255, 255, 0.5]
                })
            }),
            new $wnd.ol.style.Style({
                stroke: new $wnd.ol.style.Stroke({
                    color: [255, 255, 255, 1],
                    width: 5
                })
            }),
            new $wnd.ol.style.Style({
                stroke: new $wnd.ol.style.Stroke({
                    color: [0, 153, 255, 1],
                    width: 3
                })
            })
        ];
        styles['MultiPolygon'] = styles['Polygon'];

        styles['LineString'] = [
            new $wnd.ol.style.Style({
                stroke: new $wnd.ol.style.Stroke({
                    color: [255, 255, 255, 1],
                    width: 5
                })
            }),
            new $wnd.ol.style.Style({
                stroke: new $wnd.ol.style.Stroke({
                    color: [0, 153, 255, 1],
                    width: 3
                })
            })
        ];
        styles['MultiLineString'] = styles['LineString'];

        styles['Point'] = [
            new $wnd.ol.style.Style({
                image: new $wnd.ol.style.Circle({
                    radius: 7,
                    fill: new $wnd.ol.style.Fill({
                        color: [0, 153, 255, 1]
                    }),
                    stroke: new $wnd.ol.style.Stroke({
                        color: [255, 255, 255, 0.75],
                        width: 1.5
                    })
                }),
                zIndex: 100000
            })
        ];
        styles['MultiPoint'] = styles['Point'];

        styles['GeometryCollection'] = styles['Polygon'].concat(styles['Point']);

        return function (feature) {
            return styles[feature.getGeometry().getType()];
        };
    }-*/;


    public static native void initContextMenu(Map map, VisualizationActivity va)/*-{
        var view = map.getView();
        var selectedFeature = null;

        var mapMenuItems = [
            {
                text: 'Add object',
                icon: @pl.edu.wat.wcy.cop.app.client.utils.images.JSNIImages::addImageUri()(),
                items: [
                    {
                        text: 'Add APP6A',
                        callback: function (obj) {
                            va.@pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationActivity::addObjectFromContextMenu(Lol/Coordinate;Ljava/lang/String;)(obj.coordinate, "1");
                        }
                    },
                    {
                        text: 'Add Threat',
                        callback: function (obj) {
                            va.@pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationActivity::addObjectFromContextMenu(Lol/Coordinate;Ljava/lang/String;)(obj.coordinate, "4");
                        }
                    },
                    {
                        text: 'Add SAR',
                        callback: function (obj) {
                            va.@pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationActivity::addObjectFromContextMenu(Lol/Coordinate;Ljava/lang/String;)(obj.coordinate, "6");
                        }
                    },
                    {
                        text: 'Add POI',
                        callback: function (obj) {
                            va.@pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationActivity::addObjectFromContextMenu(Lol/Coordinate;Ljava/lang/String;)(obj.coordinate, "3");
                        }
                    },
                ]
            },
            {
                text: 'GIS',
                icon: @pl.edu.wat.wcy.cop.app.client.utils.images.JSNIImages::getCenterMapImageUri()(),
                items: [
                    {
                        text: 'Center map here',
//                        icon: @pl.edu.wat.wcy.cop.app.client.utils.images.JSNIImages::getCenterMapImageUri()(),
                        callback: function (obj) {
                            $wnd.staticCenterOnObject(view, obj);
                        }
                    },
                    {
                        text: 'Center map based on all objects',
//                        icon: @pl.edu.wat.wcy.cop.app.client.utils.images.JSNIImages::getCenterMapImageUri()(),
                        callback: function (obj) {
                            @pl.edu.wat.wcy.cop.app.client.ol.OLNativeMethods::fitToAllFeatures(Lol/Map;)(map);
                        }
                    },
                    {
                        text: 'Copy coordinates',
//                        icon: @pl.edu.wat.wcy.cop.app.client.utils.images.JSNIImages::getCenterMapImageUri()(),
                        items: [
                            {
                                text: 'Currently selected',
                                callback: function (obj) {
                                    var coordsFormater = @pl.edu.wat.wcy.cop.app.client.ol.OLMapUtils::COORDS_FORMATTER;
                                    var formatedCoords = coordsFormater.@pl.edu.wat.wcy.cop.app.client.ol.OLCoordsFormatter::format(Lol/Coordinate;)(obj.coordinate);
                                    @pl.edu.wat.wcy.cop.app.client.utils.NativeMethods::putToClipboard(Ljava/lang/String;)(formatedCoords);
                                    va.@pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationActivity::putCoordsToLocalCache(Lol/Coordinate;)(obj.coordinate);
                                }
                            },
                            {
                                text: 'eSPOZ',
                                callback: function (obj) {
                                    var transformedCoords = @pl.edu.wat.wcy.cop.app.client.ol.GeoUtils::transformToLatLon(Lol/Coordinate;)(obj.coordinate);
                                    @pl.edu.wat.wcy.cop.app.client.utils.NativeMethods::putToClipboard(Ljava/lang/String;)("eSPOZ#" + transformedCoords[1] + "#" + transformedCoords[0]);
                                    va.@pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationActivity::putCoordsToLocalCache(Lol/Coordinate;)(obj.coordinate);

                                }
                            },
                            {
                                text: 'Lat lon',
                                callback: function (obj) {
                                    var transformedCoords = @pl.edu.wat.wcy.cop.app.client.ol.GeoUtils::transformToLatLon(Lol/Coordinate;)(obj.coordinate);
                                    @pl.edu.wat.wcy.cop.app.client.utils.NativeMethods::putToClipboard(Ljava/lang/String;)(transformedCoords[1] + " " + transformedCoords[0]);
                                    va.@pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationActivity::putCoordsToLocalCache(Lol/Coordinate;)(obj.coordinate);
                                }
                            }],

                    },
                    {
                        text: 'Map export',
//                        icon: @pl.edu.wat.wcy.cop.app.client.utils.images.JSNIImages::getExportMapImageUri()(),
                        callback: function (obj) {
                            $wnd.staticExportMap(map);
                        }
                    },
                ]
            },
        ];

        var contextmenu = new $wnd.ContextMenu({
            width: 350,
            defaultItems: false, // defaultItems are (for now) Zoom In/Zoom Out
            items: []
        });
        map.addControl(contextmenu);

        contextmenu
            .on(
                'open',
                function (evt) {
                    selectedFeature = map.forEachFeatureAtPixel(
                        evt.pixel, function (ft, l) {
                            return ft;
                        });
                    contextmenu.clear();
                    if (selectedFeature) {
                        contextmenu.push({
                            text: 'Selected object',
                            icon: @pl.edu.wat.wcy.cop.app.client.utils.images.JSNIImages::editImageUri()(),
                            items: [
                                {
                                    text: 'Edit',
                                    callback: function (obj) {
                                        va.@pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationActivity::editObject(Lol/Feature;)(selectedFeature);
                                    }
                                },
                                {
                                    text: 'Start/Stop position edition',
                                    callback: function (obj) {
                                        va.@pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationActivity::modifyPosition(Lol/Feature;)(selectedFeature);
                                    }
                                },
                                {
                                    text: 'Delete',
                                    callback: function (obj) {
                                        va.@pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationActivity::deleteObject(Lol/Feature;)(selectedFeature);
                                    }
                                }
                            ]
                        })
                    }
                    contextmenu.extend(mapMenuItems);
                });
    }-*/;

    public static native Control createMapLegend() /*-{
        var legend = $wnd.document.createElement('div');
        $wnd.mapLegend = legend;
        legend.id = 'map-legend';
        legend.className = 'ol-unselectable ol-control';
        var control = new $wnd.ol.control.Control({
            element: legend
        });
        return control;
    }-*/;

    /**
     * @return
     */
    public static native Graticule createGraticule() /*-{
        var graticule = new $wnd.ol.Graticule({
            strokeStyle: new $wnd.ol.style.Stroke({
                width: 1,
                lineDash: [0.5, 2]
            }),
            showLabels: true
        });
        return graticule;
    }-*/;

    public static Control createMapCrosshairs(OLCoordsFormatter formatter) {
        return createMapCrosshairs(formatter, copResources.INSTANCE.mapCrosshairs().getText());
    }

    public static native Control createMapCrosshairs(OLCoordsFormatter formatter,
                                                     String html) /*-{

        function format(coords) {
            var type = formatter.@pl.edu.wat.wcy.cop.app.client.ol.OLCoordsFormatter::getType()();
            var formatted = formatter.@pl.edu.wat.wcy.cop.app.client.ol.OLCoordsFormatter::format(Lol/Coordinate;)(coords);
            if (type.toString() == 'DMS') {
                var indexOf = formatted.indexOf('N');
                if (indexOf < 0) {
                    indexOf = formatted.indexOf('S');
                }
                if (indexOf < 0) {
                    return formatted;
                }
                return 'lat: ' + formatted.substr(0, 1 + indexOf)
                    + '<br/>lon: ' + formatted.substr(2 + indexOf);
            }
            return formatted;
        }

        var template = $wnd.document.createElement('template');
        template.innerHTML = html;

        var control = new $wnd.ol.control.Control(
            {
                element: template.content.firstChild,
                render: function (mapEvent) {
                    if (control.getMap()) {
                        var map = control.getMap();
                        var width = map.getViewport().clientWidth;
                        var height = map.getViewport().clientHeight;
                        $wnd.document
                            .getElementById('crosshair-upper-left').innerHTML = format(map
                            .getCoordinateFromPixel([0, 0]));
                        $wnd.document
                            .getElementById('crosshair-upper-right').innerHTML = format(map
                            .getCoordinateFromPixel([width, 0]));
                        $wnd.document
                            .getElementById('crosshair-lower-right').innerHTML = format(map
                            .getCoordinateFromPixel([width, height]));
                        $wnd.document
                            .getElementById('crosshair-lower-left').innerHTML = format(map
                            .getCoordinateFromPixel([0, height]));
                        $wnd.document.getElementById('crosshair-center').innerHTML = format(map
                            .getCoordinateFromPixel([width / 2,
                                height / 2]));
                    }
                }
            });

        return control;
    }-*/;


    public enum TextAlign {
        CENTER("center"), END("end"), LEFT("left"), RIGHT("right"), START("start");
        private String value;

        /**
         * @param value
         */
        TextAlign(String value) {
            this.value = value;
        }

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }

    }

    public enum TextBaseline {
        ALPHABETIC("alphabetic"), BOTTOM("bottom"), HANGING("hanging"), IDEOGRAPHIC("ideographic"), MIDDLE(
                "Middle"), TOP("top");
        private String value;

        /**
         * @param value
         */
        TextBaseline(String value) {
            this.value = value;
        }

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }

    }

    public interface FeatureSelectionListener {
        default boolean isIntrestedIn(Feature feature) {
            return false;
        }

        void edit(Feature feature);

        void delete(Feature feature);
    }

}
