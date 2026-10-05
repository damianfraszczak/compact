/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.mvp;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import java.util.logging.Logger;
// Represents base activity.


public abstract class BaseActivity<P extends BasePlace> extends AbstractActivity {

    protected static final Logger logger = Logger.getLogger(BaseActivity.class.getName());
    private ActivityState state = ActivityState.STOPPED;
    private P place;

    public P getPlace() {
        return place;
    }

    public void setPlace(P place) {
        this.place = place;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.google.gwt.activity.shared.Activity#start(com.google.gwt.user.client.
     * ui.AcceptsOneWidget, com.google.gwt.event.shared.EventBus)
     */
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        state = ActivityState.STARTED;

    }

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.activity.shared.AbstractActivity#onCancel()
     */
    @Override
    public void onCancel() {
        super.onCancel();
        state = ActivityState.CANCELLED;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.activity.shared.AbstractActivity#onStop()
     */
    @Override
    public void onStop() {
        super.onStop();
        state = ActivityState.STOPPED;
    }

    /**
     * @return the state
     */
    public ActivityState getState() {
        return state;
    }

    public enum ActivityState {
        STARTED, STOPPED, CANCELLED
    }

}
