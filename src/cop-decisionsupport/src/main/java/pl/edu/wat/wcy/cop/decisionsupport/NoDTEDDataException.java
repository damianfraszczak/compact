package pl.edu.wat.wcy.cop.decisionsupport;
// Signals no dted data errors.

public class NoDTEDDataException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoDTEDDataException() {
        super();
    }

    public NoDTEDDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NoDTEDDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDTEDDataException(String message) {
        super(message);
    }

    public NoDTEDDataException(Throwable cause) {
        super(cause);
    }

}
