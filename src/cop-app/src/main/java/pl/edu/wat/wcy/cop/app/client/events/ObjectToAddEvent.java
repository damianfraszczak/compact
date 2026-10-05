/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.events;
// Represents a object to add event.


public class ObjectToAddEvent<T> extends ObjectEvent<T> {

    /**
     * @param source
     * @param object
     */
    public ObjectToAddEvent(Object source, T object) {
        super(source, object);
        // TODO Auto-generated constructor stub
    }

}
