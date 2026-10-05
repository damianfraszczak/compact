/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.GwtEvent;
import elemental.client.Browser;
import elemental.events.Event;
import elemental.events.EventListener;
import elemental.html.File;
import elemental.html.FileReader;
import elemental.js.html.JsFileList;
import elemental.js.html.JsInputElement;

import java.util.logging.Logger;
// Provides gwt utilities.


public class GwtUtils {
    private static Logger logger = Logger.getLogger(GwtUtils.class.getName());

    public static void readFiles(FileUploadFieldExt fileUploadField, EventListener listener) {
        JsInputElement jsInputElement = fileUploadField.getFile().cast();
        JsFileList fileList = jsInputElement.getFiles();
        if (fileList == null || fileList.getLength() == 0) {
            logger.warning("Nie wybrano pliku");
            listener.handleEvent(null);
            return;
        }

        for (int i = 0; i < fileList.getLength(); i++) {
            readFile(fileList.item(i), listener);
        }
    }

    public static void readFile(File file, EventListener listener) {
        logger.fine("odczyt pliku = " + file.getName());
        FileReader fileReader = Browser.getWindow().newFileReader();
        fileReader.setOnload(listener);
        fileReader.readAsText(file);
    }

    public static void initGwtApp() {
        GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
            public void onUncaughtException(Throwable e) {
                logger.severe("UncaughtException" + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    public static void executeOnDocumentReady(Runnable run) {
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {

            @Override
            public void execute() {
                run.run();
            }
        });
    }

    /**
     * @param evt
     * @return
     */
    public static String getStringFromFileReadEvent(Event evt) {
        FileReader fileReader = (FileReader) evt.getTarget();
        String result = (String) fileReader.getResult();
        return result;
    }
}
