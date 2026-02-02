/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.CBRNType;
import pl.edu.wat.wcy.cop.app.shared.domain.ADatP3ReportDto;

import java.util.Date;
// Defines the contract for a dat 3 report property access.


public interface ADatP3ReportPropertyAccess extends PropertyAccess<ADatP3ReportDto> {
    ADatP3ReportPropertyAccess INSTANCE = GWT.create(ADatP3ReportPropertyAccess.class);

    @Path("uuid")
    ModelKeyProvider<ADatP3ReportDto> key();

    @Path("content")
    ValueProvider<ADatP3ReportDto, String> name();

    ValueProvider<ADatP3ReportDto, CBRNType> cbrnType();

    ValueProvider<ADatP3ReportDto, String> lat();

    ValueProvider<ADatP3ReportDto, String> lon();

    ValueProvider<ADatP3ReportDto, Date> dateOfIncydent();

}
