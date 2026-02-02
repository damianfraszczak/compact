/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.gui;

import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import pl.edu.wat.wcy.cop.app.client.utils.MessagesUtils;
// Supplies gxt enum model data.


public class GxtEnumModelProvider<T extends Enum> implements LabelProvider<T>, ModelKeyProvider<T> {

    public String getKey(T item) {
        return item.toString();
    }

    public String getLabel(T item) {
        return MessagesUtils.enumToString(item);
    }
}
