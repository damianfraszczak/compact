/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain;

import java.io.Serializable;
import java.util.Objects;
// Represents default object.


public class DefaultObject implements UniqueObject<String>, Serializable {

    private long id;
    private String uuid;

    public DefaultObject() {
        this(GUID.get());
    }

    public DefaultObject(String uuid) {
        super();
        this.uuid = uuid;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject#getKey()
     */
    @Override
    public String getKey() {
        return uuid;
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
        return id + "";
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     *            the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getDescription();
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        DefaultObject that = (DefaultObject) object;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
