/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import pl.edu.wat.wcy.cop.app.client.mvp.impl.VisualizationPlace;

@WithTokenizers({VisualizationPlace.Tokenizer.class})
// Defines the contract for cop place history mapper.
public interface CopPlaceHistoryMapper extends PlaceHistoryMapper {

}
