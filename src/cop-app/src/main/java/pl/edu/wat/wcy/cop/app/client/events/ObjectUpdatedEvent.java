/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.events;
// Represents a object updated event.


public class ObjectUpdatedEvent<T> extends ObjectEvent<T> {

    /**
     * @param source
     * @param object
     */
    public ObjectUpdatedEvent(Object source, T object) {
        super(source, object);
    }

}
