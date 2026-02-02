/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.ol.sources;
// Enumerates ol tile layer source.


public enum OLTileLayerSource {
    Wikimedia("https://maps.wikimedia.org/osm-intl/{z}/{x}/{y}.png"),
    CartoDB("http://{a-c}.basemaps.cartocdn.com/light_all/{z}/{x}/{y}.png"),
    CartoDB_Dark("http://{a-c}.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}.png"),
    Transport("http://tile2.opencyclemap.org/transport/{z}/{x}/{y}.png"),
    Thunderforest("http://{a-c}.tile.thunderforest.com/outdoors/{z}/{x}/{y}.png"),
    Stamen("http://{a-c}.tile.stamen.com/toner/{z}/{x}/{y}.png"),
    Stamen_Watercolor("http://{a-c}.tile.stamen.com/watercolor/{z}/{x}/{y}.png"),
    OpenStreetMap("http://{a-c}.tile.openstreetmap.org/{z}/{x}/{y}.png"),
    Thunderforest_Landscape("http://{a-c}.tile.thunderforest.com/landscape/{z}/{x}/{y}.png");
//	MapsForFree_Relief("https://maps-for-free.com/layer/relief/z{z}/row{y}/{z}_{x}-{y}.jpg"),
//	MapsForFree_Water("https://maps-for-free.com/layer/water/z{z}/row{y}/{z}_{x}-{y}.gif"),
//	MapsForFree_Street("https://maps-for-free.com/layer/streets/z{z}/row{y}/{z}_{x}-{y}.gif"),
//	MapsForFree_Country("https://maps-for-free.com/layer/country/z{z}/row{y}/{z}_{x}-{y}.png"),
//	MapsForFree_Crop("https://maps-for-free.com/layer/crop/z{z}/row{y}/{z}_{x}-{y}.gif"),
//	MapsForFree_Grass("https://maps-for-free.com/layer/grass/z{z}/row{y}/{z}_{x}-{y}.gif"),
//	MapsForFree_Forest("https://maps-for-free.com/layer/forest/z{z}/row{y}/{z}_{x}-{y}.gif"),
//	MapsForFree_Tundra("https://maps-for-free.com/layer/tundra/z{z}/row{y}/{z}_{x}-{y}.gif"),
//	MapsForFree_Sand("https://maps-for-free.com/layer/sand/z{z}/row{y}/{z}_{x}-{y}.gif"),
//	MapsForFree_Swamp("https://maps-for-free.com/layer/swamp/z{z}/row{y}/{z}_{x}-{y}.gif"),
//	MapsForFree_Ice("https://maps-for-free.com/layer/ice/z{z}/row{y}/{z}_{x}-{y}.gif");

    private String url;

    /**
     * @param url
     */
    OLTileLayerSource(String url) {
        this.url = url;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

}
