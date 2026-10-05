package pl.edu.wat.wcy.cop.domain.scenario.sar.searchplan;

// Lists qualification categories.
public enum Qualification {
    HIGH(0),
    LOW(1);

    public final int id;
    public static final int NUM = 2;

    private Qualification(int id) {
        this.id = id;
    }

}
