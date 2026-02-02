package pl.edu.wat.wcy.cop.app.client.ol.curve;
// Enumerates type of b spline.

public enum TypeOfBSpline {
    NO_B_SPLINE("No B-Spline"), BEZIER("B-Spline based on Bezier"), PERIODIC_B_SPLINE(
            "B-Spline"), INTERPOLATED_B_SPLINE("Interpolated B-Spline");

    private final String name;

    TypeOfBSpline(String name) {
        this.name = name;
    }

    public static TypeOfBSpline findByValue(String value) {
        for (TypeOfBSpline typeOfBSpline : TypeOfBSpline.values()) {
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
