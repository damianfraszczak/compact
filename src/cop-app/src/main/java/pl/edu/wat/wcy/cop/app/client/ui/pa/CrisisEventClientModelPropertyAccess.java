/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.client.domain.spec.CrisisEventClientModel;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.crisis.CrisisEventStatusDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.crisis.CrisisEventTypeDto;

import java.util.Date;
// Defines the contract for crisis event client model property access.


public interface CrisisEventClientModelPropertyAccess extends PropertyAccess<CrisisEventClientModel> {
    CrisisEventClientModelPropertyAccess INSTANCE = GWT.create(CrisisEventClientModelPropertyAccess.class);
    ValueProvider<CrisisEventClientModel, Date> timestampEnd = new ValueProvider<CrisisEventClientModel, Date>() {

        @Override
        public Date getValue(CrisisEventClientModel object) {
            return new Date(object.getObject().getTimestampEnd());
        }

        @Override
        public void setValue(CrisisEventClientModel object, Date value) {
            object.getObject().setTimestampEnd(value.getTime());
        }

        @Override
        public String getPath() {
            return "object.timestampEnd";
        }
    };
    ValueProvider<CrisisEventClientModel, Date> timestampStart = new ValueProvider<CrisisEventClientModel, Date>() {

        @Override
        public Date getValue(CrisisEventClientModel object) {
            return new Date(object.getObject().getTimestampStart());
        }

        @Override
        public void setValue(CrisisEventClientModel object, Date value) {
            object.getObject().setTimestampStart(value.getTime());
        }

        @Override
        public String getPath() {
            return "object.timestampStart";
        }
    };

    @Path("checked")
    ValueProvider<CrisisEventClientModel, Boolean> checked();

    @Path("object.key")
    ModelKeyProvider<CrisisEventClientModel> key();

    @Path("object.htrcId")
    ValueProvider<CrisisEventClientModel, String> htrcId();

    @Path("object.type")
    ValueProvider<CrisisEventClientModel, CrisisEventTypeDto> type();

    @Path("object.status")
    ValueProvider<CrisisEventClientModel, CrisisEventStatusDto> status();

    @Path("object.substanceName")
    ValueProvider<CrisisEventClientModel, String> substanceName();

    @Path("object.threatLevel")
    ValueProvider<CrisisEventClientModel, String> threatLevel();

    @Path("object.province")
    ValueProvider<CrisisEventClientModel, String> province();

    @Path("object.city")
    ValueProvider<CrisisEventClientModel, String> city();

    @Path("object.source")
    ValueProvider<CrisisEventClientModel, String> source();

    @Path("object.description")
    ValueProvider<CrisisEventClientModel, String> description();

}
