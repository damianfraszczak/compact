/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.client.domain.spec.App6AMilitaryUnitClientModel;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.AffiliationEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.BattleDimensionEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.UnitSizeEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.UnitTypeEnumDto;
// Defines the contract for app 6 a military unit client model property access.


public interface App6AMilitaryUnitClientModelPropertyAccess extends PropertyAccess<App6AMilitaryUnitClientModel> {
    App6AMilitaryUnitClientModelPropertyAccess INSTANCE = GWT
            .create(App6AMilitaryUnitClientModelPropertyAccess.class);

    @Path("object.uuid")
    ModelKeyProvider<App6AMilitaryUnitClientModel> key();


    @Path("base64Image")
    ValueProvider<App6AMilitaryUnitClientModel, String> base64Image();

    @Path("checked")
    ValueProvider<App6AMilitaryUnitClientModel, Boolean> checked();

    @Path("object.description")
    ValueProvider<App6AMilitaryUnitClientModel, String> description();

    @Path("object.nameAdditional")
    ValueProvider<App6AMilitaryUnitClientModel, String> name();

    @Path("object.code")
    ValueProvider<App6AMilitaryUnitClientModel, String> code();

    @Path("object.codingScheme")
    ValueProvider<App6AMilitaryUnitClientModel, String> codingScheme();

    @Path("object.status")
    ValueProvider<App6AMilitaryUnitClientModel, String> status();

    @Path("object.affiliation")
    ValueProvider<App6AMilitaryUnitClientModel, AffiliationEnumDto> affiliation();

    @Path("object.battleDimension")
    ValueProvider<App6AMilitaryUnitClientModel, BattleDimensionEnumDto> battleDimension();

    @Path("object.countryCode")
    ValueProvider<App6AMilitaryUnitClientModel, String> countryCode();

    @Path("object.functionId")
    ValueProvider<App6AMilitaryUnitClientModel, String> functionId();

    @Path("object.orderOfBattle")
    ValueProvider<App6AMilitaryUnitClientModel, String> orderOfBattle();

    @Path("object.mobilitySize")
    ValueProvider<App6AMilitaryUnitClientModel, UnitSizeEnumDto> mobilitySize();

    @Path("object.unitType")
    ValueProvider<App6AMilitaryUnitClientModel, UnitTypeEnumDto> unitType();

}
