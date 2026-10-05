/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;
// Represents a base event.


public class BaseEvent extends GenericEvent {

    private Object source;

    /**
     * @param source
     */
    public BaseEvent(Object source) {
        super();
        this.source = source;
    }

    /**
     * @return the source
     */
    public Object getSource() {
        return source;
    }

}
