/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.App6AMilitaryUnitDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.AffiliationEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.BattleDimensionEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.UnitSizeEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.UnitTypeEnumDto;
// Defines the contract for app 6 a military unit property access.


public interface App6AMilitaryUnitPropertyAccess extends PropertyAccess<App6AMilitaryUnitDto> {
    App6AMilitaryUnitPropertyAccess INSTANCE = GWT.create(App6AMilitaryUnitPropertyAccess.class);

    @Path("uuid")
    ModelKeyProvider<App6AMilitaryUnitDto> key();

    ValueProvider<App6AMilitaryUnitDto, String> description();

    @Path("nameAdditional")
    ValueProvider<App6AMilitaryUnitDto, String> name();

    ValueProvider<App6AMilitaryUnitDto, String> code();

    ValueProvider<App6AMilitaryUnitDto, String> codingScheme();

    ValueProvider<App6AMilitaryUnitDto, String> status();

    ValueProvider<App6AMilitaryUnitDto, AffiliationEnumDto> affiliation();

    ValueProvider<App6AMilitaryUnitDto, BattleDimensionEnumDto> battleDimension();

    ValueProvider<App6AMilitaryUnitDto, String> countryCode();

    ValueProvider<App6AMilitaryUnitDto, String> functionId();

    ValueProvider<App6AMilitaryUnitDto, String> orderOfBattle();

    ValueProvider<App6AMilitaryUnitDto, UnitSizeEnumDto> mobilitySize();

    ValueProvider<App6AMilitaryUnitDto, UnitTypeEnumDto> unitType();
}
