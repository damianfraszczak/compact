package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.PersonBehaviour;
// Defines the contract for search and rescue property access.


public interface SearchAndRescuePropertyAccess extends PropertyAccess<SearchAndRescueDto> {
    SearchAndRescuePropertyAccess INSTANCE = GWT.create(SearchAndRescuePropertyAccess.class);

    @Editor.Path("uuid")
    ModelKeyProvider<SearchAndRescueDto> key();

    ValueProvider<SearchAndRescueDto, String> name();

    ValueProvider<SearchAndRescueDto, String> description();


    ValueProvider<SearchAndRescueDto, PersonBehaviour> personBehaviour();
}