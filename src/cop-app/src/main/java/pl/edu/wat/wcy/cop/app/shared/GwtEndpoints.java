/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared;
// Defines the contract for gwt endpoints.


public interface GwtEndpoints {
    String DEV_PREFIX = "";
    String GWT_REMOTE_LOGGING = DEV_PREFIX + "/cop/remote_logging";
    String GWT_SYMBOL_SERVICE = DEV_PREFIX + "/cop/symbolservice";
    String GWT_VISUALZIATION_SERVICE = DEV_PREFIX + "/cop/visualization";
    String GWT_USER_SERVICE = DEV_PREFIX + "/cop/visualization/user";
    String GWT_FLOOD_AREA = DEV_PREFIX + "/cop/floodarea";
    String GWT_LOS_AREA = DEV_PREFIX + "/cop/losarea";
    String GWT_TERRAIN_PROFILE = DEV_PREFIX + "/cop/terrainprofile";
    String GWT_POI_REST_SERVICE = DEV_PREFIX + "/cop/api/poi";
    String GWT_APP6A_REST_SERVICE = DEV_PREFIX + "/cop/api/app6a";
    String GWT_MSWIA_REST_SERVICE = DEV_PREFIX + "/cop/api/mswia";
    String GWT_CRISIS_EVENT_REST_SERVICE = DEV_PREFIX + "/cop/api/crisisevent";
    String GWT_ADATP3_REST_SERVICE = DEV_PREFIX + "/cop/api/adatp3";
    String GWT_GPX_REST_SERVICE = DEV_PREFIX + "/cop/api/gpx";
    String GWT_VISUALZIATION_SERVICE_GET_LIST = GWT_VISUALZIATION_SERVICE + "/list";
    String GWT_SEARCH_AND_RESCUE_SERVICE = DEV_PREFIX + "/cop/api/sar";
}
