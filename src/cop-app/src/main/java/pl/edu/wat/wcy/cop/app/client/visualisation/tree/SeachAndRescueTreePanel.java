package pl.edu.wat.wcy.cop.app.client.visualisation.tree;

import com.sencha.gxt.widget.core.client.menu.Menu;
import pl.edu.wat.wcy.cop.app.client.domain.spec.SearchAndRescueClientModel;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElement;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElementObject;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement;
import pl.edu.wat.wcy.cop.app.shared.domain.ScenarioPointObjectDto;
import pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject;
// Renders seach and rescue tree panel UI.

public class SeachAndRescueTreePanel extends VisualisationTreePanel<SearchAndRescueClientModel>{
    @Override
    protected TreeElement<? extends UniqueObject<String>, String> getNode(SearchAndRescueClientModel object) {
        return new SearchAndRescueTreeElement(object);
    }

    @Override
    protected TreeElement<UniqueObject<String>, String> createRoot() {
        return new DefaultTreeElement<UniqueObject<String>, String>(
                new DefaultTreeElementObject("Obszary poszukiwań"),
                GIS_IMAGES.layers_operational());
    }
    @Override
    protected Menu getContextMenuForObject(TreeElement<UniqueObject<String>, String> selectedElement) {
        return VisualisationTreePanel.extendMenuForShowOnMapMenuItem(super.getContextMenuForObject(selectedElement),
                (ScenarioPointObjectDto) selectedElement);
    }
}
