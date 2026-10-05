/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol;

import com.google.gwt.dom.client.DivElement;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import ol.Overlay;
import ol.event.EventListener;
import ol.event.MeasureEvent;
import ol.interaction.Draw;
import ol.interaction.Draw.Event;
import ol.style.Style;
import pl.edu.wat.wcy.cop.app.client.ol.extra.OLMeasure;
import pl.edu.wat.wcy.cop.app.client.ol.extra.OLMeasure.DrawAndMeasureListener;

@Singleton
// Manages ol measure.
public class OLMeasureManager {
    @Inject
    private MapWidget mapWidget;
    private OLMeasure lastMeasure;
    private Overlay helpTooltipOverlay;
    private DivElement helpTooltipOverlayElement;

    /**
     * @param measureType
     * @param listener
     * @param style
     * @param measureMode
     */
    public void startMeasure(OLMeasureType measureType, DrawAndMeasureListener listener, Style style,
                             OLMeasureMode measureMode) {
        stopMeasure();
        lastMeasure = new OLMeasure(mapWidget.getMap(), mapWidget.getMeasurePersistOverlaySource());
        if (style != null) {
            lastMeasure.setStyle(style);
        }

        DrawAndMeasureListener defaultListener = new DrawAndMeasureListener() {

            @Override
            public void onMeasure(MeasureEvent evt) {
                if (listener != null) {
                    listener.onMeasure(evt);
                }

            }

            @Override
            public void onDrawStart(Draw.Event event) {
                if (listener != null) {
                    listener.onDrawStart(event);
                }
                event.getFeature().getGeometry().on("", new EventListener<Event>() {

                    @Override
                    public void onEvent(Event event) {

                    }
                });

            }
        };
        switch (measureType) {
            case CIRCLE:
                lastMeasure.startMeasureCircle(defaultListener, shouldImediately(measureMode), shouldPersist(measureMode));
                break;
            case DISTANCE:
                lastMeasure.startMeasureLength(defaultListener, shouldImediately(measureMode), shouldPersist(measureMode));
                break;
            case POLYGON:
                lastMeasure.startMeasureArea(defaultListener, shouldImediately(measureMode), shouldPersist(measureMode));
                break;
            default:
                break;
        }
    }

    /**
     * @param measureMode
     * @return
     */
    private boolean shouldPersist(OLMeasureMode measureMode) {
        return OLMeasureMode.PERSIST.equals(measureMode) || OLMeasureMode.PERSIST_AND_IMMEDIATELY.equals(measureMode);
    }

    /**
     * @param measureMode
     * @return
     */
    private boolean shouldImediately(OLMeasureMode measureMode) {
        return OLMeasureMode.IMMEDIATELY.equals(measureMode)
                || OLMeasureMode.PERSIST_AND_IMMEDIATELY.equals(measureMode);

    }

    /**
     *
     */
    public void stopMeasure() {
        if (lastMeasure != null) {
            lastMeasure.stop();
        }
    }

    public enum OLMeasureType {
        CIRCLE, DISTANCE, POLYGON
    }

    public enum OLMeasureMode {
        PERSIST_AND_IMMEDIATELY, PERSIST, IMMEDIATELY, NONE
    }

}
