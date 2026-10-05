/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.response.capability;

import pl.edu.wat.wcy.cop.app.shared.GwtEndpoints;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
// Represents a get capability response.
public class GetCapabilityResponse {

    private String service_path = GwtEndpoints.GWT_VISUALZIATION_SERVICE;
    private String service_method = "POST";

    private VisualisationRequest requestBody = new VisualisationRequest();

    /**
     * @return the service_path
     */
    public String getService_path() {
        return service_path;
    }

    /**
     * @param service_path
     *            the service_path to set
     */
    public void setService_path(String service_path) {
        this.service_path = service_path;
    }

    /**
     * @return the service_method
     */
    public String getService_method() {
        return service_method;
    }

    /**
     * @param service_method
     *            the service_method to set
     */
    public void setService_method(String service_method) {
        this.service_method = service_method;
    }

    /**
     * @return the requestBody
     */
    public VisualisationRequest getRequestBody() {
        return requestBody;
    }

    /**
     * @param requestBody
     *            the requestBody to set
     */
    public void setRequestBody(VisualisationRequest requestBody) {
        this.requestBody = requestBody;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "GetCapabilityResponse [service_path=" + service_path + ", service_method=" + service_method
                + ", requestBody=" + requestBody + "]";
    }

}
