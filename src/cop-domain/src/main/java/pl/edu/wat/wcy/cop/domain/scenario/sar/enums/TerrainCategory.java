package pl.edu.wat.wcy.cop.domain.scenario.sar.enums;

// Lists terrain categories.
public enum TerrainCategory {
    OPEN(0),
    WOODLAND(1),
    SWAMPY(2),
    BUSHY(3),
    GRASSY(4),
    WATERY(5),
    OPEN_WOODLAND(6),
    WOODLAND_SWAMPY(7),
    OPEN_BUSHY(8),
    GRASSY_SWAMPY(9),
    WATERY_WOODLAND(10),
    WATERY_BUSHY(11);

    public static TerrainCategory[] TerrainCategories = {OPEN,
            WOODLAND,
            SWAMPY,
            BUSHY,
            GRASSY,
            WATERY,
            OPEN_WOODLAND,
            WOODLAND_SWAMPY,
            OPEN_BUSHY,
            GRASSY_SWAMPY,
            WATERY_WOODLAND,
            WATERY_BUSHY
    };


    public final Integer id;

    public static final int NUM = 12;

    private TerrainCategory(Integer id) {
        this.id = id;
    }
}
