package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchUnitDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.PersonBehaviour;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.SearchUnitType;
// Defines the contract for search unit property access.


public interface SearchUnitPropertyAccess extends PropertyAccess<SearchUnitDto> {
    SearchUnitPropertyAccess INSTANCE = GWT.create(SearchUnitPropertyAccess.class);

    @Editor.Path("uuid")
    ModelKeyProvider<SearchUnitDto> key();

    ValueProvider<SearchUnitDto, String> name();


    ValueProvider<SearchUnitDto, Double> sweepWidth();

    ValueProvider<SearchUnitDto, Double> sweepSpeed();



}