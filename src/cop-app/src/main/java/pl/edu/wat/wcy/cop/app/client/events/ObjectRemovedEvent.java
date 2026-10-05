/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.events;
// Represents a object removed event.


public class ObjectRemovedEvent<T> extends ObjectEvent<T> {

    /**
     * @param source
     * @param object
     */
    public ObjectRemovedEvent(Object source, T object) {
        super(source, object);
    }

}
