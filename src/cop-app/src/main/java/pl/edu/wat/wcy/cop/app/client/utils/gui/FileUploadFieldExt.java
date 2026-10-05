/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.gui;

import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.form.FileUploadField;
// Represents file upload field ext.

public class FileUploadFieldExt extends FileUploadField {
    public native XElement getFile() /*-{
        return this.@com.sencha.gxt.widget.core.client.form.FileUploadField::file;
    }-*/;
}
