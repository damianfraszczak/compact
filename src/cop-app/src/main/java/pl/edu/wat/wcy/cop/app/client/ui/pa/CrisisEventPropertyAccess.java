/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.CrisisEventDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.crisis.CrisisEventStatusDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.crisis.CrisisEventTypeDto;

import java.util.Date;
// Defines the contract for crisis event property access.


public interface CrisisEventPropertyAccess extends PropertyAccess<CrisisEventDto> {
    CrisisEventPropertyAccess INSTANCE = GWT.create(CrisisEventPropertyAccess.class);
    ValueProvider<CrisisEventDto, Date> timestampEnd = new ValueProvider<CrisisEventDto, Date>() {

        @Override
        public Date getValue(CrisisEventDto object) {
            return new Date(object.getTimestampEnd());
        }

        @Override
        public void setValue(CrisisEventDto object, Date value) {
            object.setTimestampEnd(value.getTime());
        }

        @Override
        public String getPath() {
            return "timestampEnd";
        }
    };
    ValueProvider<CrisisEventDto, Date> timestampStart = new ValueProvider<CrisisEventDto, Date>() {

        @Override
        public Date getValue(CrisisEventDto object) {
            return new Date(object.getTimestampStart());
        }

        @Override
        public void setValue(CrisisEventDto object, Date value) {
            object.setTimestampStart(value.getTime());
        }

        @Override
        public String getPath() {
            return "timestampStart";
        }
    };

    @Path("uuid")
    ModelKeyProvider<CrisisEventDto> key();

    ValueProvider<CrisisEventDto, String> htrcId();

    ValueProvider<CrisisEventDto, CrisisEventTypeDto> type();

    ValueProvider<CrisisEventDto, CrisisEventStatusDto> status();

    ValueProvider<CrisisEventDto, String> substanceName();

    ValueProvider<CrisisEventDto, String> threatLevel();

    ValueProvider<CrisisEventDto, String> province();

    ValueProvider<CrisisEventDto, String> city();

    ValueProvider<CrisisEventDto, String> source();

    ValueProvider<CrisisEventDto, String> description();

}
