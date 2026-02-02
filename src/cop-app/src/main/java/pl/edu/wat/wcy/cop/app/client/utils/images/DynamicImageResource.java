/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.images;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.ui.Image;
// Represents dynamic image resource.


public class DynamicImageResource implements ImageResource {

    private Image image;

    public DynamicImageResource(Image image) {
        super();
        this.image = image;
    }

    public DynamicImageResource(String url) {
        this(new Image(url));
    }

    public String getName() {
        return image.getTitle();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public int getLeft() {
        return image.getAbsoluteLeft();
    }

    public SafeUri getSafeUri() {
        return UriUtils.fromSafeConstant(getURL());
    }

    public int getTop() {
        return image.getOriginTop();
    }

    public String getURL() {
        return image.getUrl();
    }

    public int getWidth() {
        return image.getWidth();
    }

    public boolean isAnimated() {
        return false;
    }

}
