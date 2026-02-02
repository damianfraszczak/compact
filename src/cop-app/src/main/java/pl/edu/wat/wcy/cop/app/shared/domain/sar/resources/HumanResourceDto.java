package pl.edu.wat.wcy.cop.app.shared.domain.sar.resources;


import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.Qualification;
// Carries human resource data.

public class HumanResourceDto extends SearchResourceDto {

    private Qualification qualification = Qualification.HIGH;

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public HumanResourceDto() {
    }

    public HumanResourceDto(int number, Qualification qualification) {
        super(number);
        this.qualification = qualification;
    }
}
