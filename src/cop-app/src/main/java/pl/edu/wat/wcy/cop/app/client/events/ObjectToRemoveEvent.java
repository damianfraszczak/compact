/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.events;
// Represents a object to remove event.


public class ObjectToRemoveEvent<T> extends ObjectEvent<T> {

    /**
     * @param source
     * @param object
     */
    public ObjectToRemoveEvent(Object source, T object) {
        super(source, object);
    }

}
