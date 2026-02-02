/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import pl.edu.wat.wcy.cop.app.client.utils.gui.LoadingProgressBarManager;
// Represents logging method callback.


public abstract class LoggingMethodCallback<T> extends AbstractLoggingCallback implements MethodCallback<T> {

    public LoggingMethodCallback() {
        super();
    }

    public LoggingMethodCallback(ErrorFormatter errorFormatter, LoadingProgressBarManager loadingProgressBarManager) {
        super(errorFormatter, loadingProgressBarManager);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.fusesource.restygwt.client.MethodCallback#onFailure(org.fusesource.
     * restygwt.client.Method, java.lang.Throwable)
     */
    @Override
    public void onFailure(Method method, Throwable exception) {

        errorFormatter.showError(this, exception, method.getResponse().getText());
        hideProgressBar();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.fusesource.restygwt.client.MethodCallback#onSuccess(org.fusesource.
     * restygwt.client.Method, java.lang.Object)
     */
    @Override
    public void onSuccess(Method method, T response) {
        success(method, response);
        hideProgressBar();

    }

    /**
     * @param method
     * @param response
     */
    protected abstract void success(Method method, T response);

}
