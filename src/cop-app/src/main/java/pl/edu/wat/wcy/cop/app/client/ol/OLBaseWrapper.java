/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol;

import ol.OLUtil;
import ol.layer.Base;
import pl.edu.wat.wcy.cop.app.shared.domain.GUID;
import pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject;

import java.util.HashMap;
import java.util.Map;
// Represents ol base wrapper.


public class OLBaseWrapper implements UniqueObject<String> {

    private static Map<String, String> layersIds = new HashMap<>();
    private Base layer;
    private String id;

    /**
     * @param layer
     */
    public OLBaseWrapper(Base layer) {
        super();
        this.layer = layer;
        setId();
    }

    /**
     *
     */
    private void setId() {
        String layerKey = OLUtil.getName(layer);
        this.id = layersIds.get(layerKey);
        if (this.id == null) {
            this.id = GUID.get();
            layersIds.put(layerKey, this.id);
        }
    }

    /**
     * @return the layer
     */
    public Base getLayer() {
        return layer;
    }

    /**
     * @param layer
     *            the layer to set
     */
    public void setLayer(Base layer) {
        this.layer = layer;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject#getKey()
     */
    @Override
    public String getKey() {
        // TODO Auto-generated method stub
        return this.id;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject#getObjectDescription(
     * )
     */
    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return OLUtil.getName(layer);
    }

}
