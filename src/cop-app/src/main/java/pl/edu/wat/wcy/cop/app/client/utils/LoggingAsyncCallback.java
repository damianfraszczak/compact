/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils;

import com.google.gwt.user.client.rpc.AsyncCallback;
import pl.edu.wat.wcy.cop.app.client.utils.gui.LoadingProgressBarManager;
// Represents logging async callback.


public abstract class LoggingAsyncCallback<T> extends AbstractLoggingCallback implements AsyncCallback<T> {

    public LoggingAsyncCallback() {
        super();
    }

    public LoggingAsyncCallback(ErrorFormatter errorFormatter, LoadingProgressBarManager loadingProgressBarManager) {
        super(errorFormatter, loadingProgressBarManager);
    }

    @Override
    public void onSuccess(T response) {
        success(response);
        hideProgressBar();

    }

    /**
     * @param method
     * @param response
     */
    protected abstract void success(T response);

    @Override
    public void onFailure(Throwable caught) {
        errorFormatter.showError(this, caught, "RPC");
        hideProgressBar();
    }

}
