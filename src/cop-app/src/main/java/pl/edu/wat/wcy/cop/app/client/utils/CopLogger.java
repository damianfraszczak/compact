/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils;

import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/*
 * SEVERE (highest value) WARNING INFO CONFIG FINE FINER FINEST (lowest value)
 */

@Singleton
// Represents cop logger.
public class CopLogger {

    private static CopLogger instance;

    private Map<String, Logger> loggerMap = new HashMap<>();
    private Handler webConsoleHandler = new Handler() {

        @Override
        public void publish(LogRecord record) {
            // records.add(0, record);

        }

        @Override
        public void flush() {
            // records.commitChanges();
        }

        @Override
        public void close() throws SecurityException {
            // TODO Auto-generated method stub

        }
    };

    public synchronized static CopLogger getInstance() {
        if (instance == null) {
            instance = new CopLogger();
        }
        return instance;
    }

    /**
     *
     * @param source
     * @param text
     */
    public void error(Object source, String text) {
        log(source, Level.SEVERE, text);
    }

    /**
     *
     * @param source
     * @param text
     */
    public void config(Object source, String text) {
        log(source, Level.CONFIG, text);
    }

    /**
     *
     * @param source
     * @param text
     */
    public void debug(Object source, String text) {
        log(source, Level.FINE, text);
    }

    /**
     *
     * @param source
     * @param text
     */
    public void debug(Class source, String text) {
        log(source, Level.FINE, text);
    }

    /**
     *
     * @param source
     * @param text
     */
    public void info(Object source, String text) {
        log(source, Level.INFO, text);
    }

    /**
     * @param source
     * @param info
     * @param text
     */
    public void log(Object source, Level level, String text) {
        log(source.getClass(), level, text);

    }

    public void log(Class source, Level level, String text) {
        getLogger(source.getName()).log(level, text);
    }

    /**
     * @param name
     * @return
     */
    private Logger getLogger(String name) {
        Logger logger = loggerMap.get(name);
        if (logger == null) {
            logger = Logger.getLogger(name);
            logger.addHandler(webConsoleHandler);
        }
        return logger;
    }
}
