/**
 *
 */
package pl.edu.wat.wcy.cop.app.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import java.util.logging.Level;
import java.util.logging.Logger;
// Represents cop.

public class Cop implements EntryPoint {
    private static final Logger logger = Logger.getLogger(Cop.class.getName());

    public void onModuleLoad() {

        GWT.runAsync(new RunAsyncCallback() {
            @Override
            public void onFailure(Throwable reason) {
                logger.log(Level.SEVERE, "Unable to start application", reason);
                Window.alert("Some error occurred while starting application");
            }


            @Override
            public void onSuccess() {
                GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
                    public void onUncaughtException(Throwable e) {
                        logger.log(Level.SEVERE, "Uncaught exception", e);
                        CopGinInjector.INSTANCE.getLoadingProgressBarManager().forceHideProgressBar();
                    }
                });
                CopGinInjector.INSTANCE.appController().start(RootLayoutPanel.get());
            }
        });

    }
}
