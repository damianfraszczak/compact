package pl.edu.wat.wcy.cop.app.client.utils;
// Provides cop app utilities.

public class CopAppUtils {

    private static boolean visualisationView;

    public static boolean isVisualisationView() {
        return visualisationView;
    }

    public static void setVisualisationView(boolean visualisationView) {
        CopAppUtils.visualisationView = visualisationView;
    }
}
