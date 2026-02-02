/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.tree;

import com.google.gwt.resources.client.ImageResource;
import pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @param <K>
 *            object key type
 * @param <O>
 *            object type
 */
// Represents default tree element.
public class DefaultTreeElement<O extends UniqueObject<K>, K> implements TreeElement<O, K> {

    private O object;
    private boolean checked;
    private LinkedHashSet<TreeElement<O, K>> children;
    private ImageResource image;

    /**
     * @param object
     */
    public DefaultTreeElement(O object, ImageResource image) {
        super();
        this.checked = true;
        this.object = object;
        this.image = image;
        children = new LinkedHashSet<>();

    }

    /**
     * @return the object
     */
    @Override
    public O getObject() {
        return object;
    }

    /**
     * @param object
     *            the object to set
     */
    @Override
    public void setObject(O object) {
        this.object = object;
    }

    @Override
    public String getObjectDesct() {
        return object.getDescription();
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement#getKey()
     */
    @Override
    public K getKey() {
        return getObject().getKey();
    }

    @Override
    public ImageResource getImage() {
        return image;
    }

    /**
     * @return the checked
     */
    @Override
    public boolean isChecked() {
        return checked;
    }

    /**
     * @param checked
     *            the checked to set
     */
    @Override
    public void setChecked(boolean checked) {
        this.checked = checked;

    }

    /**
     *
     * @param child
     */
    @Override
    public void addChild(TreeElement<O, K> child) {
        this.children.add(child);

    }

    /**
     *
     * @param child
     */
    @Override
    public void removeChild(TreeElement<O, K> child) {
        this.children.remove(child);
    }

    /**
     * @return the children
     */
    @Override
    public Set<TreeElement<O, K>> getChildren() {
        return children;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((object == null) ? 0 : object.getKey().hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DefaultTreeElement other = (DefaultTreeElement) obj;
        if (object == null) {
            return other.object == null;
        } else return object.getKey().equals(other.object.getKey());
    }

}
