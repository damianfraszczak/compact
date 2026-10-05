/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
// Defines the contract for app images.


public interface AppImages extends ClientBundle {
    AppImages INSTANCE = GWT.create(AppImages.class);

    @Source("up.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource up();

    @Source("down.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource down();

    @Source("add.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource add();

    @Source("edit.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource edit();

    @Source("remove.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource remove();

    @Source("ajax-loader.gif")
    ImageResource loadingIcon();

    @Source("disabled.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource disabled();

    @Source("expand.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource expand();

    @Source("collapse.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource collapse();

    @Source("user.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource user();

    @Source("spy-mousemode.png")
    @ImageOptions(width = AppConstants.DEFAULT_ICON_WIDTH, height = AppConstants.DEFAULT_ICON_HEIGHT)
    ImageResource spyMousemode();
}
