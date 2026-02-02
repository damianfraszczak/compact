/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.response;
// Represents a ok response.



public class OkResponse<T> extends AbstractResponse {

    private T content;

    /**
     *
     */
    public OkResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param content
     */
    public OkResponse(T content) {
        super(200, true);
        this.content = content;
    }

    /**
     * @return the content
     */
    public T getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(T content) {
        this.content = content;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OkResponse [content=" + content + "]";
    }

}
