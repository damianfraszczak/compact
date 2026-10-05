/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.images;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Image;
// Represents scalable image.


public class ScalableImage extends Image {
    private int width;
    private int height;
    private ImageResource res;

    public ScalableImage(String base64Image, int width, int height) {
        this(new DynamicImageResource(base64Image), width, height);
    }

    public ScalableImage(ImageResource res, int width, int height) {
        this.setUrl(res.getSafeUri());
        this.res = res;
        this.width = width;
        this.height = height;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onLoad() {
        int widthOfBigImage = this.getOffsetWidth();
        int heightOfBigImage = this.getOffsetHeight();
        double scaleX = width / res.getWidth();
        double scaleY = height / res.getHeight();
        this.setResource(res);
        DOM.setStyleAttribute(getElement(), "backgroundPosition", (int) (res.getLeft() * -1 * scaleX)
                + "px " + (int) (res.getTop() * -1 * scaleY) + "px ");
        DOM.setStyleAttribute(getElement(), "backgroundSize", (int) (widthOfBigImage * scaleX) + "px "
                + (int) (heightOfBigImage * scaleY) + "px ");
        this.setPixelSize((int) (res.getWidth() * scaleX), (int) (res.getHeight() * scaleY));
    }
}
