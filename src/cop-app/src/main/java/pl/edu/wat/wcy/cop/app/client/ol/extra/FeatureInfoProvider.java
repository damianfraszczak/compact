/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol.extra;
// Defines the contract for feature info provider.

public interface FeatureInfoProvider {

    String PROVIDER_KEY = "FEATURE_INFO_PROVIDER";

    String getTooltipText();
}
