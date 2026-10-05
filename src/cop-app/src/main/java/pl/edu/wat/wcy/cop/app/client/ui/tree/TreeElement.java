/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.tree;

import com.google.gwt.resources.client.ImageResource;

import java.util.Set;
// Defines the contract for tree element.

public interface TreeElement<O, K> {
    O getObject();

    void setObject(O object);

    K getKey();

    String getObjectDesct();

    boolean isChecked();

    void setChecked(boolean checked);

    void addChild(TreeElement<O, K> child);

    void removeChild(TreeElement<O, K> child);

    Set<TreeElement<O, K>> getChildren();

    ImageResource getImage();

}
