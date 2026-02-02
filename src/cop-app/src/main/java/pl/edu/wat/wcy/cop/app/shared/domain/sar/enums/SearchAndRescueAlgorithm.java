package pl.edu.wat.wcy.cop.app.shared.domain.sar.enums;
// Enumerates search and rescue algorithm.

public enum SearchAndRescueAlgorithm {
    SIMPLE("Ring model (podstawowy)"), GRID("Algorytm zalewowy w 8 kierunkach");

    private String name;

    SearchAndRescueAlgorithm(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
