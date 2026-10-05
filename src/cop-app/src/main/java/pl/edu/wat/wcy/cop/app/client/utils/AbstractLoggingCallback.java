/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils;

import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.utils.gui.LoadingProgressBarManager;
// Represents abstract logging callback.


public abstract class AbstractLoggingCallback {

    protected ErrorFormatter errorFormatter;
    protected LoadingProgressBarManager loadingProgressBarManager;

    public AbstractLoggingCallback() {
        this(CopGinInjector.INSTANCE.getErrorFormatter(), CopGinInjector.INSTANCE.getLoadingProgressBarManager());
    }

    public AbstractLoggingCallback(ErrorFormatter errorFormatter, LoadingProgressBarManager loadingProgressBarManager) {
        super();
        this.errorFormatter = errorFormatter;
        this.loadingProgressBarManager = loadingProgressBarManager;
        showProgressBar();
    }

    protected void showProgressBar() {
        loadingProgressBarManager.showProgressBar();

    }

    protected void hideProgressBar() {
        loadingProgressBarManager.hideProgressBar();
    }

}
