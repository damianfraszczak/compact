/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.App6AMilitaryUnitTreeElement;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.AffiliationEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.BattleDimensionEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.UnitSizeEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.UnitTypeEnumDto;
// Defines the contract for app 6 a military unit tree element property access.


public interface App6AMilitaryUnitTreeElementPropertyAccess extends PropertyAccess<App6AMilitaryUnitTreeElement> {
    App6AMilitaryUnitTreeElementPropertyAccess INSTANCE = GWT
            .create(App6AMilitaryUnitTreeElementPropertyAccess.class);

    @Path("object.object.uuid")
    ModelKeyProvider<App6AMilitaryUnitTreeElement> key();

    @Path("object.object.description")
    ValueProvider<App6AMilitaryUnitTreeElement, String> description();

    @Path("object.object.nameAdditional")
    ValueProvider<App6AMilitaryUnitTreeElement, String> name();

    @Path("object.object.code")
    ValueProvider<App6AMilitaryUnitTreeElement, String> code();

    @Path("object.object.codingScheme")
    ValueProvider<App6AMilitaryUnitTreeElement, String> codingScheme();

    @Path("object.object.status")
    ValueProvider<App6AMilitaryUnitTreeElement, String> status();

    @Path("object.object.affiliation")
    ValueProvider<App6AMilitaryUnitTreeElement, AffiliationEnumDto> affiliation();

    @Path("object.object.battleDimension")
    ValueProvider<App6AMilitaryUnitTreeElement, BattleDimensionEnumDto> battleDimension();

    @Path("object.object.countryCode")
    ValueProvider<App6AMilitaryUnitTreeElement, String> countryCode();

    @Path("object.object.functionId")
    ValueProvider<App6AMilitaryUnitTreeElement, String> functionId();

    @Path("object.object.orderOfBattle")
    ValueProvider<App6AMilitaryUnitTreeElement, String> orderOfBattle();

    @Path("object.object.mobilitySize")
    ValueProvider<App6AMilitaryUnitTreeElement, UnitSizeEnumDto> mobilitySize();

    @Path("object.object.unitType")
    ValueProvider<App6AMilitaryUnitTreeElement, UnitTypeEnumDto> unitType();
}
