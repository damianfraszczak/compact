/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.mvp.impl;

import pl.edu.wat.wcy.cop.app.client.mvp.View;
import pl.edu.wat.wcy.cop.app.shared.domain.ScenarioDto;

import java.util.List;
// Defines the contract for configuration view.

public interface ConfigurationView extends View {

    void setPresenter(Presenter presenter);

    void setModel(ScenarioDto model);

    /**
     * @param content
     */
    void setAvailableScenariosIds(List<Long> content);

    interface Presenter {

        void goToVisualisation();

        void generateSituation();

        void generatePreparedSituation();

        /**
         * @param value
         */
        void loadSelectedScenarioId(Long value);
    }

}
