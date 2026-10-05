/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.mvp.impl;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import pl.edu.wat.wcy.cop.app.client.mvp.BasePlace;
// Represents visualization place.

public class VisualizationPlace extends BasePlace {

    private Long id;

    /**
     * @param content
     */
    public VisualizationPlace(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Prefix("visualization")
    public static class Tokenizer implements PlaceTokenizer<VisualizationPlace> {

        @Override
        public VisualizationPlace getPlace(String token) {

            return new VisualizationPlace(Long.parseLong(token));
        }

        /*
         * (non-Javadoc)
         *
         * @see
         * com.google.gwt.place.shared.PlaceTokenizer#getToken(com.google.gwt.
         * place.shared.Place)
         */
        @Override
        public String getToken(VisualizationPlace place) {
            // TODO Auto-generated method stub
            return place.getId() + "";
        }
    }
}
