package pl.edu.wat.wcy.cop.common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// Configures logging through SLF4J.
public class SL4JLoggingProvider implements LoggingProvider {

    public static void main(String[] args) {
        OMLogger logger = OMLogger.getInstance();
        logger.info("INFO");
    }

    public void info(String callerClassName, String msg) {
        getLogger(callerClassName).info(msg);
        System.out.println(callerClassName + " " + msg);

    }

    public void error(String callerClassName, String msg) {
        getLogger(callerClassName).error(msg);

    }

    public void error(String callerClassName, String msg, Throwable throwable) {
        getLogger(callerClassName).error(msg, throwable);

    }

    public void debug(String callerClassName, String msg) {
        // DEBUG USED AS INFO
        info(callerClassName,msg);

    }

    public void trace(String callerClassName, String msg) {
        getLogger(callerClassName).trace(msg);

    }

    public void warn(String callerClassName, String msg) {
        getLogger(callerClassName).warn(msg);

    }

    private Logger getLogger(String callerClassName) {
        return LoggerFactory.getLogger(callerClassName);
    }

}
