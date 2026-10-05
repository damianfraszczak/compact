package pl.edu.wat.wcy.cop.app.shared.domain.sar.enums;
// Enumerates person behaviour.

public enum PersonBehaviour {
    ABANDONED_VEHICLE("Abandoned Vehicle"),
    ABDUCTION("Abduction"),
    ANGLER("Angler"),
    ATV("ATV"),
    AUTISTIC("Autistic"),
    CAMPER("Camper"),
    CAVER("Caver"),
    CHILD_1_3_YEARS("Child 1-3 Years"),
    CHILD_4_6_YEARS("Child 4-6 Years"),
    CHILD_7_9_YEARS("Child 7-9 Years"),
    CHILD_10_12_YEARS("Child 10-12 Years"),
    CHILD_13_15_YEARS("Child 13-15 Years"),
    CLIMBER("Climber"),
    DEMENTIA("Dementia"),
    DESPONDENT("Despondent"),
    FOUR_WHEEL_DRIVE("Four-Wheel Drive"),
    GATHERER("Gatherer"),
    HIKER("Hiker"),
    HORSEBACK_RIDER("Horseback Rider"),
    HUNTER("Hunter"),
    MENTAL_DISABILITY("Mentally Disabled"),
    MENTAL_ILLNESS("Mentally Ill"),
    MOUNTAIN_BIKER("Mountain Biker"),
    RUNNER("Runner"),
    SKIER_ALPINE("Alpine Skier"),
    SKIER_NORDIC("Nordic Skier"),
    SNOWBOARDER("Snowboarder"),
    SNOWMOBILER("Snowmobiler"),
    SNOWSHOER("Snowshoer"),
    SUBSTANCE_ABUSE("Substance Abuse"),
    VEHICLE("Vehicle"),
    WORKER("Worker");

    private String name;

    public String getName() {
        return name;
    }

    PersonBehaviour(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
