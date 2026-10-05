package pl.edu.wat.wcy.cop.app.shared.domain.sar.enums;
// Enumerates search plan state.

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
