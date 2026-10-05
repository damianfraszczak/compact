/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.client.domain.spec.ADatP3ReportClientModel;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.CBRNType;

import java.util.Date;
// Defines the contract for a dat 3 report client model property access.


public interface ADatP3ReportClientModelPropertyAccess extends PropertyAccess<ADatP3ReportClientModel> {
    ADatP3ReportClientModelPropertyAccess INSTANCE = GWT.create(ADatP3ReportClientModelPropertyAccess.class);

    @Path("checked")
    ValueProvider<ADatP3ReportClientModel, Boolean> checked();

    @Path("object.uuid")
    ModelKeyProvider<ADatP3ReportClientModel> key();

    ValueProvider<ADatP3ReportClientModel, CBRNType> cbrnType();

    ValueProvider<ADatP3ReportClientModel, Date> dateOfIncydent();

}
