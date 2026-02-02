package pl.edu.wat.wcy.cop.domain.scenario.sar.searchplan;

/**
 * Klasa definiująca możliwe typy jednostek poszukiwawczych.
 */
// Lists search unit types.
public enum SearchUnitType {
    SZYBKA_TROJKA(0, 3, 3),
    TYRALIERA(1, 4, 100);

    public final int id;
    public final int lowLimit;
    public final int highLimit;
    public static final int NUM = 2;

    private SearchUnitType(int id, int lowLimit, int highLimit) {
        this.id = id;
        this.lowLimit = lowLimit;
        this.highLimit = highLimit;
    }

    public static SearchUnitType get(int id) {
        if (id == 0) return SZYBKA_TROJKA;
        return TYRALIERA;
    }
}
