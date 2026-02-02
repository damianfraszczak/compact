/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.events;
// Represents a object event.


public class ObjectEvent<T> extends BaseEvent {

    private T object;

    /**
     * @param source
     */
    public ObjectEvent(Object source, T object) {
        super(source);
        this.object = object;
    }

    /**
     * @return the object
     */
    public T getObject() {
        return object;
    }

    /**
     * @param object
     *            the object to set
     */
    public void setObject(T object) {
        this.object = object;
    }


}
