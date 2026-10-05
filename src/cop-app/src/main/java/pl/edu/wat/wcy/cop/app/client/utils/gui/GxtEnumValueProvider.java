/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.gui;

import com.sencha.gxt.core.client.ValueProvider;
// Supplies gxt enum value data.


public class GxtEnumValueProvider<T extends Enum> implements ValueProvider<T, String> {

    /*
     * (non-Javadoc)
     *
     * @see com.sencha.gxt.core.client.ValueProvider#getValue(java.lang.Object)
     */
    @Override
    public String getValue(T object) {
        // TODO Auto-generated method stub
        return object.toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sencha.gxt.core.client.ValueProvider#setValue(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public void setValue(T object, String value) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see com.sencha.gxt.core.client.ValueProvider#getPath()
     */
    @Override
    public String getPath() {
        // TODO Auto-generated method stub
        return null;
    }

}
