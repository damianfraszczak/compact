package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.Qualification;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.HumanResourceDto;

import java.util.Date;
// Defines the contract for human resources property access.


public interface HumanResourcesPropertyAccess extends PropertyAccess<HumanResourceDto> {
    HumanResourcesPropertyAccess INSTANCE = GWT.create(HumanResourcesPropertyAccess.class);

    @Editor.Path("uuid")
    ModelKeyProvider<HumanResourceDto> key();

    ValueProvider<HumanResourceDto, Qualification> qualification();

    ValueProvider<HumanResourceDto, Integer> number();


    ValueProvider<HumanResourceDto, Date> time = new ValueProvider<HumanResourceDto, Date>() {

        @Override
        public Date getValue(HumanResourceDto object) {
            return new Date(object.getTime());
        }

        @Override
        public void setValue(HumanResourceDto object, Date value) {
            object.setTime(value.getTime());
        }

        @Override
        public String getPath() {
            return "time";
        }
    };


}