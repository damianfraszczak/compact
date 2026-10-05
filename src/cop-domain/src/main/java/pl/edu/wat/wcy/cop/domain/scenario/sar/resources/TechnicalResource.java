package pl.edu.wat.wcy.cop.domain.scenario.sar.resources;

import pl.edu.wat.wcy.cop.domain.scenario.sar.enums.EquipmentCategory;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
// Stores data about a technical resource.
public class TechnicalResource extends SearchResource {
    @Enumerated(EnumType.STRING)
    private EquipmentCategory category;

    public EquipmentCategory getCategory() {
        return category;
    }

    public void setCategory(EquipmentCategory category) {
        this.category = category;
    }
}
