/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.ol.sources;
// Enumerates ol wms geoportal layer sources.

public enum OLWmsGeoportalLayerSources {

    ADMINISTRATIVE_POLAND("http://mapy.geoportal.gov.pl/wss/service/img/guest/Administracyjna/MapServer/WMSServer",
            "Administracyjna", "Administracyjna"),
    BUILDINGS_BDOT_2009_HEALTH(
            "http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_BDOT_BUD_2009/MapServer/WMSServer", "0",
            "Budynki ochrony zdrowia lub opieki socjalnej"),
    BUILDINGS_BDOT_2009_TRANSPORT(
            "http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_BDOT_BUD_2009/MapServer/WMSServer", "1",
            "Budynki transportu lub łączności"),
    BUILDINGS_BDOT_2009_MAGAZINES(
            "http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_BDOT_BUD_2009/MapServer/WMSServer", "2",
            "Budynki magazynowe lub zbiorniki silosy"),
    BUILDINGS_BDOT_2009_FLATS(
            "http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_BDOT_BUD_2009/MapServer/WMSServer", "3",
            "Budynki mieszkalne"),
    BUILDINGS_BDOT_2009_SCHOOLS(
            "http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_BDOT_BUD_2009/MapServer/WMSServer", "4",
            "Budynki oświaty nauki kultury lub sportu"),
    BUILDINGS_BDOT_2009_OTHERS(
            "http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_BDOT_BUD_2009/MapServer/WMSServer", "5",
            "Inne budynki niemieszkalne"),
    BUILDINGS_BDOT_2009_SERVICES(
            "http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_BDOT_BUD_2009/MapServer/WMSServer", "6",
            "Budynki handlowe lub usługowe"),
    BUILDINGS_BDOT_2009_OFFICES(
            "http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_BDOT_BUD_2009/MapServer/WMSServer", "7",
            "Budynki biurowe"),


    BUILDINGS_BDOT_2010_SACRAL(
            "http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_BDOT_BUD_2010/MapServer/WMSServer", "0",
            "Budynki_sakralne"),
    BUILDINGS_BDOT_2010_HEALTH(
            "http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_BDOT_BUD_2010/MapServer/WMSServer", "1",
            "Budynki ochrony zdrowia lub opieki socjalnej"),
    BUILDINGS_BDOT_2010_TRANSPORT(
            "http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_BDOT_BUD_2010/MapServer/WMSServer", "2",
            "Budynki transportu lub łączności"),
    BUILDINGS_BDOT_2010_MAGAZINES(
            "http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_BDOT_BUD_2010/MapServer/WMSServer", "3",
            "Budynki magazynowe lub zbiorniki silosy"),
    BUILDINGS_BDOT_2010_FLATS(
            "http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_BDOT_BUD_2010/MapServer/WMSServer", "4",
            "Budynki mieszkalne"),
    BUILDINGS_BDOT_2010_SCHOOLS(
            "http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_BDOT_BUD_2010/MapServer/WMSServer", "5",
            "Budynki oświaty nauki kultury lub sportu"),
    BUILDINGS_BDOT_2010_OTHERS(
            "http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_BDOT_BUD_2010/MapServer/WMSServer", "6",
            "Inne budynki niemieszkalne"),
    BUILDINGS_BDOT_2010_SERVICES(
            "http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_BDOT_BUD_2010/MapServer/WMSServer", "7",
            "Budynki handlowe lub usługowe"),
    BUILDINGS_BDOT_2010_OFFICES(
            "http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_BDOT_BUD_2010/MapServer/WMSServer", "8",
            "Budynki biurowe"),


    MEDICAL_DISPATCHING("http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_dyspozyt_med_WMS/MapServer/WMSServer", "0", "Obecne dyspozytornie medyczne"),

    ISOK_LIDAR2("http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_ISOK_WMS/MapServer/WMSServer", "0", "LIDAR Standard II - planowany zasięg opracowania"),
    ISOK_LIDAR2_BLOCKS("http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_ISOK_WMS/MapServer/WMSServer", "1", "LIDAR - bloki"),
    ISOK_PGSIK("http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_ISOK_WMS/MapServer/WMSServer", "2", "Ortofotomapa w PZGiK"),


    POLAND_LANDSCAPE("http://mapy.geoportal.gov.pl/wss/service/img/guest/Krajobrazowa/MapServer/WMSServer", "Krajobrazowa", "Krajobrazowa"),

    LOCALIZATION_ZSIN("http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_ZSIN_EUPOS_WMS/MapServer/WMSServer", "0", "Lokalizacje ZSIN"),
    LOCALIZATION_ASG_EUPOS("http://mapy.geoportal.gov.pl/wss/service/pub/guest/G2_ZSIN_EUPOS_WMS/MapServer/WMSServer", "1", "Lokalizacje ASG-EUPOS");


    private String url;

    private String layers;
    private String layerName;

    OLWmsGeoportalLayerSources(String url, String layers, String layerName) {
        this.url = url;
        this.layers = layers;
        this.layerName = layerName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLayers() {
        return layers;
    }

    public void setLayers(String layers) {
        this.layers = layers;
    }

    /**
     * @return the layerName
     */
    public String getLayerName() {
        return layerName;
    }


}
