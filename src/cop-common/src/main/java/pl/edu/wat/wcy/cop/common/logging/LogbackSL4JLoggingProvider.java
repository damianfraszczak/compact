
package pl.edu.wat.wcy.cop.common.logging;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;


// Configures Logback-backed SLF4J logging.
public class LogbackSL4JLoggingProvider extends SL4JLoggingProvider {

    public LogbackSL4JLoggingProvider() {
        super();
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
        setLogingPropertiesForHttpClient();
    }

    public static void main(String[] args) {
        OMLogger.getInstance().trace("TRACE");
        OMLogger.getInstance().debug("DEBUG");
        OMLogger.getInstance().info("INFO");
        OMLogger.getInstance().warn("WARN");
        OMLogger.getInstance().error("ERROR");
    }

    private void setLogingPropertiesForHttpClient() {
    }
}
