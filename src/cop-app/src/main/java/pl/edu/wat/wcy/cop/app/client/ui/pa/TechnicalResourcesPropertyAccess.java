package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.EquipmentCategory;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.TechnicalResourceDto;

import java.util.Date;
// Defines the contract for technical resources property access.


public interface TechnicalResourcesPropertyAccess extends PropertyAccess<TechnicalResourceDto> {
    TechnicalResourcesPropertyAccess INSTANCE = GWT.create(TechnicalResourcesPropertyAccess.class);

    @Editor.Path("uuid")
    ModelKeyProvider<TechnicalResourceDto> key();

    ValueProvider<TechnicalResourceDto, EquipmentCategory> category();

    ValueProvider<TechnicalResourceDto, Integer> number();

    ValueProvider<TechnicalResourceDto, Date> time = new ValueProvider<TechnicalResourceDto, Date>() {

        @Override
        public Date getValue(TechnicalResourceDto object) {
            return new Date(object.getTime());
        }

        @Override
        public void setValue(TechnicalResourceDto object, Date value) {
            object.setTime(value.getTime());
        }

        @Override
        public String getPath() {
            return "time";
        }
    };


}