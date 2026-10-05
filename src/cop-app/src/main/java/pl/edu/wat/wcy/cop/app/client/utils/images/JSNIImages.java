/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.images;
// Represents jsni images.


public class JSNIImages {

    public static String getCenterMapImageUri() {
        return GisImages.INSTANCE.center().getSafeUri().asString();
    }

    public static String getExportMapImageUri() {
        return GisImages.INSTANCE.export_map().getSafeUri().asString();
    }

    public static String addImageUri() {
        return AppImages.INSTANCE.add().getSafeUri().asString();
    }

    public static String editImageUri() {
        return AppImages.INSTANCE.edit().getSafeUri().asString();
    }
}
