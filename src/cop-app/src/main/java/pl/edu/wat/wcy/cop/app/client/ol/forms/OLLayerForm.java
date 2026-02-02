/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol.forms;

import com.sencha.gxt.widget.core.client.Slider;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtUtils;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
// Defines ol layer form UI.


public abstract class OLLayerForm<T extends OLLayer, E extends OLLayerEditor<T>> extends AbstractForm<T, E> {

    /**
     * @param model
     * @param okHandler
     */
    public OLLayerForm(T model, SelectHandler okHandler) {
        super(model, okHandler);

    }

    /**
     * @param model
     * @param title
     * @param okHandler
     */
    public OLLayerForm(T model, String title, SelectHandler okHandler) {
        super(model, title, okHandler);

    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#beforeShow()
     */
    @Override
    protected void beforeShow() {
        super.beforeShow();
        Slider opacitySlider = this.editor.opacity;
        opacitySlider.setValue((int) (this.model.getOpacity() * 100));
        opacitySlider.setMinValue(1);
        opacitySlider.setMaxValue(100);
        // if (model.getName() != null && !model.getName().isEmpty()) {
        // editor.name.setEnabled(false);
        // }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm#afterOkClicked()
     */
    @Override
    protected void afterOkClicked() {
        super.afterOkClicked();
        model.setOpacity(this.editor.opacity.getValue() / 100.0);
    }

    @Override
    protected int getMaxRowsCount() {
        return editor.getRowsCount();
    }

    @Override
    protected String getDefaultTitle() {
        return "Warstwa GIS";
    }
}
