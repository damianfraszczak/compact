/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.domain;

import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.client.utils.images.IconsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.resources.copResources;
import pl.edu.wat.wcy.cop.app.shared.domain.ISymbolOnTheMap;
import pl.edu.wat.wcy.cop.app.shared.domain.MapSymbolDto;
// Represents map symbol with base 64 client model.

public class MapSymbolWithBase64ClientModel<T extends ISymbolOnTheMap<String>>
        extends MapSymbolWithImageSourceClientModel<T> {

    public MapSymbolWithBase64ClientModel(T object, Feature feature, String base64Image) {
        super(object, feature, base64Image);
    }

    public String getBase64Image() {
        return getImageSource();
    }

    public void setBase64Image(String base64Image) {
        setImageSource(base64Image);
    }

    @Override
    public String getTooltipText() {
        String tooltipTemplate = copResources.INSTANCE.tooltipTempalte().getText();
        MapSymbolDto mapSymbolDto = getObject().getMapSymbol();
        return StringUtils.format(tooltipTemplate, mapSymbolDto.getlNDescription(), mapSymbolDto.getcNDescription(),
                mapSymbolDto.getrNDescription(), mapSymbolDto.getlCDescription(),
                IconsUtils.getBase64ImgSrc(getImageSource()), mapSymbolDto.getrCDescription(),
                mapSymbolDto.getlSDescription(), mapSymbolDto.getcSDescription(), mapSymbolDto.getrSDescription(),
                getObject().getDescription());
    }

}
