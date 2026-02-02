package pl.edu.wat.wcy.cop.common.logging;


// Wraps logging calls with a shared logger instance.
public class OMLogger {

    private static OMLogger INSTANCE;
    private LoggingProvider loggingProvider = new LogbackSL4JLoggingProvider();

    private OMLogger() {

    }

    public synchronized static OMLogger getInstance() {
        if (INSTANCE == null)
            INSTANCE = new OMLogger();
        return INSTANCE;
    }

    public void log(LogLevel level, String msg) {
        switch (level) {
            case DEBUG:
                debug(msg);
                break;
            case TRACE:
                trace(msg);
                break;
            case INFO:
                info(msg);
                break;
            case WARN:
                warn(msg);
                break;
            case ERROR:
                error(msg);
                break;
        }
    }

    /**
     * @param msg
     */
    public void trace(String msg) {
        loggingProvider.trace(getCallerClassName(), String.format("%s, %s", getMethodName(), msg));
    }

    /**
     * @param msg
     */
    public void trace(Class cls, String msg) {
        loggingProvider.trace(cls.getSimpleName(), String.format("%s, %s", getMethodName(), msg));
    }

    /**
     * @param msg
     */
    public void debug(String msg) {
        loggingProvider.debug(getCallerClassName(), String.format("%s, %s", getMethodName(), msg));
    }

    /**
     * @param msg
     */
    public void debug(Class cls, String msg) {
        loggingProvider.debug(cls.getSimpleName(), String.format("%s, %s", getMethodName(), msg));
    }

    /**
     * @param msg
     */
    public void info(String msg) {
        loggingProvider.info(getCallerClassName(), String.format("%s, %s", getMethodName(), msg));
    }

    /**
     * @param cls
     * @param msg
     */
    public void info(Class cls, String msg) {
        loggingProvider.info(cls.getSimpleName(), String.format("%s, %s", getMethodName(), msg));

    }

    /**
     * @param msg
     */
    public void warn(String msg) {
        loggingProvider.warn(getCallerClassName(), String.format("%s, %s", getMethodName(), msg));
    }

    /**
     * @param cls
     * @param msg
     */
    public void warn(Class cls, String msg) {
        loggingProvider.warn(cls.getSimpleName(), String.format("%s, %s", getMethodName(), msg));

    }

    /**
     * @param msg
     */
    public void error(String msg) {
        loggingProvider.error(getCallerClassName(), String.format("%s, %s", getMethodName(), msg));
    }

    public void error(Class cls, String msg) {
        loggingProvider.error(cls.getSimpleName(), String.format("%s, %s", getMethodName(), msg));
    }

    public void error(String msg, Throwable throwable) {
        loggingProvider.error(getCallerClassName(), String.format("%s, %s", getMethodName(), msg), throwable);
    }

    public void error(Class cls, String msg, Throwable throwable) {
        loggingProvider.error(cls.getSimpleName(), String.format("%s, %s", getMethodName(), msg), throwable);
    }

    /**
     * @return
     */
    private String getMethodName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i = 1; i < stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(OMLogger.class.getName())
                    && ste.getClassName().indexOf("java.lang.Thread") != 0
                    && !ste.getMethodName().toLowerCase().contains("debug")) {
                return "metoda: " + ste.getMethodName() + " linia: " + ste.getLineNumber();
            }
        }
        return null;

    }

    private String getCallerClassName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i = 1; i < stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(OMLogger.class.getName())
                    && ste.getClassName().indexOf("java.lang.Thread") != 0 && !ste.getMethodName().contains("debug")) {
                return ste.getClassName();
            }
        }
        return null;
    }

    /**
     * @param level
     */
    public void setLevel(LogLevel level) {

    }

}
