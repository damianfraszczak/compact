package pl.edu.wat.wcy.cop.domain.scenario.sar.enums;

// Lists possible missing-person behaviors.
public enum PersonBehaviour {
    ABANDONED_VEHICLE("Porzucony pojazd"),
    ABDUCTION("Uprowadzenie"),
    ANGLER("Wędkarz"),
    ATV(""),
    AUTISTIC("Autystyk"),
    CAMPER("Kemping"),
    CAVER("Grotołaz"),
    CHILD_1_3_YEARS("Dziecko 1-3 lata"),
    CHILD_4_6_YEARS("Dziecko 4-6 lat"),
    CHILD_7_9_YEARS("Dziecko 7-9 lat"),
    CHILD_10_12_YEARS("Dziecko 10-12 lat"),
    CHILD_13_15_YEARS("Dziecko 13-15 lata"),
    CLIMBER("Wspinacz"),
    DEMENTIA("Demencja"),
    DESPONDENT("Przygnębiony"),
    FOUR_WHEEL_DRIVE("Napęd na 4 koła"),
    GATHERER("Zbieracz"),
    HIKER("Turysta"),
    HORSEBACK_RIDER("Jeździec konny"),
    HUNTER("Myśliwy"),
    MENTAL_DISABILITY("Niepełonosprawny umysłowo"),
    MENTAL_ILLNESS("Chory umysłowo"),
    MOUNTAIN_BIKER("Rowerzysta górski"),
    RUNNER("Biegacz"),
    SKIER_ALPINE("Narciacz alpejski"),
    SKIER_NORDIC("Narciarcz"),
    SNOWBOARDER("Snowboardzista"),
    SNOWMOBILER("Skuter śnieżny"),
    SNOWSHOER("Buty śnieżne"),
    SUBSTANCE_ABUSE("Odurzony"),
    VEHICLE("Pojazd"),
    WORKER("Pracownik");

    private String name;

    public String getName() {
        return name;
    }

    PersonBehaviour(String name) {
        this.name = name;
    }
}
