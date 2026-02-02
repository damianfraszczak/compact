package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.client.domain.spec.SearchAndRescueClientModel;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.PersonBehaviour;
// Defines the contract for search and rescue client model property access.

public interface SearchAndRescueClientModelPropertyAccess extends PropertyAccess<SearchAndRescueClientModel> {
    SearchAndRescueClientModelPropertyAccess INSTANCE = GWT
            .create(SearchAndRescueClientModelPropertyAccess.class);

    @Editor.Path("checked")
    ValueProvider<SearchAndRescueClientModel, Boolean> checked();

    @Editor.Path("object.uuid")
    ModelKeyProvider<SearchAndRescueClientModel> key();

    @Editor.Path("object.name")
    ValueProvider<SearchAndRescueClientModel, String> name();

    @Editor.Path("object.description")
    ValueProvider<SearchAndRescueClientModel, String> description();
    @Editor.Path("object.personBehaviour")
    ValueProvider<SearchAndRescueClientModel, PersonBehaviour> personBehaviour();
}
