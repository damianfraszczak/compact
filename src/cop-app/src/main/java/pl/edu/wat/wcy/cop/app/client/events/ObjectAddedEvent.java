/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.events;
// Represents a object added event.


public class ObjectAddedEvent<T> extends ObjectEvent<T> {

    /**
     * @param source
     * @param object
     */
    public ObjectAddedEvent(Object source, T object) {
        super(source, object);
    }


}
