/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.response;

import java.util.Date;
// Represents a abstract response.

public class AbstractResponse {
    private int status;
    private Date timestamp;
    private boolean ok;

    /**
     *
     */
    public AbstractResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     *
     * @param status
     */
    public AbstractResponse(int status, boolean ok) {
        super();
        this.status = status;
        this.ok = ok;
        this.timestamp = new Date();
    }

    public int getStatus() {
        return status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @return the ok
     */
    public boolean isOk() {
        return ok;
    }
}
