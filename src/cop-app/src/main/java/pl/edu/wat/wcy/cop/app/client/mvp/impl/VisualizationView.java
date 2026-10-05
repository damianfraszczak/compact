/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.mvp.impl;

import pl.edu.wat.wcy.cop.app.client.domain.spec.ScenarioClientModel;
import pl.edu.wat.wcy.cop.app.client.mvp.View;
// Defines the contract for visualization view.

public interface VisualizationView extends View {
    void setPresenter(Presenter presenter);

    void setScenario(ScenarioClientModel scenario);

    interface Presenter {

        /**
         *
         */
        void clearSituation();

    }
}
