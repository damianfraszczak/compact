/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.domain;

import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.client.utils.resources.copResources;
import pl.edu.wat.wcy.cop.app.shared.domain.ISymbolOnTheMap;
import pl.edu.wat.wcy.cop.app.shared.domain.MapSymbolDto;
// Represents map symbol with image source client model.

public class MapSymbolWithImageSourceClientModel<T extends ISymbolOnTheMap<String>> extends MapSymbolClientModel<T> {

    private String imageSource;

    public MapSymbolWithImageSourceClientModel(T object, Feature feature, String imageSource) {
        super(object, feature);
        this.imageSource = imageSource;
    }

    public MapSymbolWithImageSourceClientModel(T object, Feature[] features, String imageSource) {
        super(object, features);
        this.imageSource = imageSource;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }/*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.domain.MapSymbolClientModel#
     * getTooltipText()
     */

    @Override
    public String getTooltipText() {
        String tooltipTemplate = copResources.INSTANCE.tooltipTempalte().getText();
        MapSymbolDto mapSymbolDto = getObject().getMapSymbol();
        return StringUtils.format(tooltipTemplate, mapSymbolDto.getlNDescription(), mapSymbolDto.getcNDescription(),
                mapSymbolDto.getrNDescription(), mapSymbolDto.getlCDescription(), getImageSource(),
                mapSymbolDto.getrCDescription(), mapSymbolDto.getlSDescription(), mapSymbolDto.getcSDescription(),
                mapSymbolDto.getrSDescription(), getObject().getDescription());
    }

}
