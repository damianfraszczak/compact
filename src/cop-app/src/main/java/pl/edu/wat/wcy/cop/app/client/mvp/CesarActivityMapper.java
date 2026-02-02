/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;
import pl.edu.wat.wcy.cop.app.client.mvp.impl.ConfigurationActivity;
import pl.edu.wat.wcy.cop.app.client.mvp.impl.ConfigurationPlace;
import pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationActivity;
import pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationPlace;


@SuppressWarnings({"rawtypes", "unchecked"})
// Maps cesar activity data.
public class CesarActivityMapper implements ActivityMapper {
    @Inject
    private Provider<ConfigurationActivity> configurationActivityProvider;
    @Inject
    private Provider<VisualizationActivity> visualizationActivityProvider;

    private BasePlace currentPlace;
    private BaseActivity currentActivity;

    public BaseActivity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(BaseActivity currentActivity) {
        this.currentActivity = currentActivity;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.google.gwt.activity.shared.ActivityMapper#getActivity(com.google.gwt.
     * place.shared.Place)
     */
    @Override
    public Activity getActivity(Place place) {

        BaseActivity activity = null;
        if (currentActivity != null && currentPlace != null && place != null
                && currentPlace.getClass().equals(place.getClass())) {
            // we're not changing places. Place data may have been updated.
            // Update current activity with new place instance and return it
            currentPlace = (BasePlace) place;
            currentActivity.setPlace(currentPlace);
            return currentActivity;
        }
        if (place instanceof VisualizationPlace) {
            activity = visualizationActivityProvider.get();
        }
        if (place instanceof ConfigurationPlace) {
            activity = configurationActivityProvider.get();
        }
        if (activity == null) {
            return null;
        }
        currentPlace = (BasePlace) place;
        currentActivity = activity;
        activity.setPlace(currentPlace);
        return activity;
    }

}
