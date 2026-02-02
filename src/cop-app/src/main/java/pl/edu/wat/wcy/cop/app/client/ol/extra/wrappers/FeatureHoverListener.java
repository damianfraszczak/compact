/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol.extra.wrappers;

import ol.Coordinate;
import ol.Feature;
// Defines the contract for feature hover listener.

public interface FeatureHoverListener {

    void onFeatureHovered(Coordinate coords, Feature feature);
}
