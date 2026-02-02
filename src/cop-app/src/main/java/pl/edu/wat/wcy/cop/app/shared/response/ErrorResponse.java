package pl.edu.wat.wcy.cop.app.shared.response;
// Represents a error response.


public class ErrorResponse extends AbstractResponse {

    private String message;

    private ErrorCode errorCode;

    public ErrorResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ErrorResponse(final String message, final ErrorCode errorCode, int status) {
        super(status, false);
        this.message = message;
        this.errorCode = errorCode;

    }

    public static ErrorResponse of(final String message, final ErrorCode errorCode, int status) {
        return new ErrorResponse(message, errorCode, status);
    }

    public String getMessage() {
        return message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
