/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.grid;

import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import pl.edu.wat.wcy.cop.app.client.domain.spec.*;
import pl.edu.wat.wcy.cop.app.client.ui.forms.*;
// Provides visualisation form utilities.


public class VisualisationFormUtils {

    public static AbstractForm getFormForObject(Object object, SelectHandler handler) {
        if (object instanceof App6AMilitaryUnitClientModel) {
            return new APP6AMilitaryUnitForm(((App6AMilitaryUnitClientModel) object).getObject(), handler);
        } else if (object instanceof MSWiAUnitClientModel) {
            return new MSWiAUnitForm(((MSWiAUnitClientModel) object).getObject(), handler);
        } else if (object instanceof PointOfInterestClientModel) {
            return new PointOfInterestForm(((PointOfInterestClientModel) object).getObject(), handler);
        } else if (object instanceof CrisisEventClientModel) {
            return new CrisisEventForm(((CrisisEventClientModel) object).getObject(), handler);
        } else if (object instanceof ADatP3ReportClientModel) {
            return new ADatP3ReportForm(((ADatP3ReportClientModel) object).getObject(), handler);
        } else if (object instanceof SearchAndRescueClientModel) {
            return new SearchAndRescueForm(((SearchAndRescueClientModel) object).getObject(), handler);
        } else
            return null;
    }
}
