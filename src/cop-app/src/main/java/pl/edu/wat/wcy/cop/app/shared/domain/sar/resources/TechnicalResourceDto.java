package pl.edu.wat.wcy.cop.app.shared.domain.sar.resources;


import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.EquipmentCategory;
// Carries technical resource data.

public class TechnicalResourceDto extends SearchResourceDto {

    private EquipmentCategory category = EquipmentCategory.BINOCULARS;

    public EquipmentCategory getCategory() {
        return category;
    }

    public void setCategory(EquipmentCategory category) {
        this.category = category;
    }
}
