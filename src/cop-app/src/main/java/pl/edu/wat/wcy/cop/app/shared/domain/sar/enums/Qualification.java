package pl.edu.wat.wcy.cop.app.shared.domain.sar.enums;
// Enumerates qualification.

public enum Qualification {
    LOW("Niedoświadczony"), HIGH("Doświadczony");

    private String name;

    Qualification(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
