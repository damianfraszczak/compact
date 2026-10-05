package pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3;
// Enumerates cbrn type.

public enum CBRNType {
    RAD("RAD"),
    NUC("NUC"),
    BIO("BIO"),
    CHEM("CHEM");

    private final String name;

    CBRNType(String name) {
        this.name = name;
    }

    public static CBRNType findByValue(String value) {
        for (CBRNType typeOfBSpline : CBRNType.values()) {
            if (typeOfBSpline.name.equals(value)) {
                return typeOfBSpline;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
