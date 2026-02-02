package pl.edu.wat.wcy.cop.domain.scenario.sar.enums;

// Lists search plan states.
public enum SearchPlanState {
    PLANNED("Planowany"), STARTED("Rozpoczęty"),
    FINISHED("Zakończony");

    private String name;

    SearchPlanState(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
