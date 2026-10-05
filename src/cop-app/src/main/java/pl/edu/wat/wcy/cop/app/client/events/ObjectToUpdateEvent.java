/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.events;
// Represents a object to update event.

public class ObjectToUpdateEvent<T> extends ObjectEvent<T> {

    /**
     * @param source
     * @param object
     */
    public ObjectToUpdateEvent(Object source, T object) {
        super(source, object);
    }

}
