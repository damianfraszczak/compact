/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.tree;

import pl.edu.wat.wcy.cop.app.shared.domain.GUID;
import pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject;
// Represents default tree element object.


public class DefaultTreeElementObject implements UniqueObject<String> {

    private String name;
    private String id;

    /**
     * @param name
     */
    public DefaultTreeElementObject(String name) {
        super();
        this.name = name;
        this.id = GUID.get();
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject#getKey()
     */
    @Override
    public String getKey() {
        // TODO Auto-generated method stub
        return this.id;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject#getObjectDescription(
     * )
     */
    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return name;
    }

}
