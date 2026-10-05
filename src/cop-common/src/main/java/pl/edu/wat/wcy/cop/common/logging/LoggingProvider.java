
package pl.edu.wat.wcy.cop.common.logging;


// Defines a logging provider contract.
public interface LoggingProvider {
    /**
     * @param callerClassName
     * @param msg
     */
    void trace(String callerClassName, String msg);

    /**
     * @param msg
     */
    void debug(String callerClassName, String msg);

    /**
     * @param callerClassName
     * @param message
     */
    void info(String callerClassName, String msg);

    /**
     * @param callerClassName
     * @param msg
     */
    void warn(String callerClassName, String msg);

    void error(String callerClassName, String msg);

    /**
     * @param callerClassName
     * @param msg
     * @param throwable
     */
    void error(String callerClassName, String msg, Throwable throwable);

}
