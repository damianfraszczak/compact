package pl.edu.wat.wcy.cop.app.shared.response;

import com.fasterxml.jackson.annotation.JsonValue;
// Enumerates error code.


public enum ErrorCode {
    GLOBAL(2), IMAGE_NOT_FOUND(3);

    private int errorCode;

    ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {
        return errorCode;
    }
}
