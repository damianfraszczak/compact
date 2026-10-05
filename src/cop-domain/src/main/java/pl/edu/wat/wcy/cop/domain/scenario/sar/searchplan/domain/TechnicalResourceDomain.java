package pl.edu.wat.wcy.cop.domain.scenario.sar.searchplan.domain;

import pl.edu.wat.wcy.cop.domain.scenario.sar.enums.EquipmentCategory;

import java.io.Serializable;

// Stores domain data for technical resources.
public class TechnicalResourceDomain extends SearchResourceDomain implements Serializable {
    private EquipmentCategory category;

    public TechnicalResourceDomain(int number, EquipmentCategory category) {
        super(number);
        this.category = category;
    }
}
