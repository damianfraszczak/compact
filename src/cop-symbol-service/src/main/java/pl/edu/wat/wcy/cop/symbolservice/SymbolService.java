package pl.edu.wat.wcy.cop.symbolservice;

import pl.edu.wat.wcy.cop.common.images.IconImageLoader;
import pl.edu.wat.wcy.cop.common.images.ImageUtils;
import pl.edu.wat.wcy.cop.common.logging.OMLogger;

import java.awt.*;
import java.awt.image.BufferedImage;
// Provides symbol operations.


public class SymbolService {

    private AbstractSymbolProvider provider = new DefaultSymbolProvider();

    private IconImageLoader images = new IconImageLoader();

    public void clearCache() {
        provider.clearCache();
    }

    public void setSaveInCache(boolean saveInCache) {
        provider.setSaveInCache(saveInCache);
    }

    /**
     * @param symbolType
     * @param code
     * @param width
     * @param height
     * @return
     * @throws ImageNotFoundForCodeException
     */
    public Image getImageForCode(SymbolType symbolType, String code, int width, int height)
            throws ImageNotFoundForCodeException {
        return getImageFromCacheOrProvider(symbolType, code, width, height);
    }

    /**
     * @param symbolType
     * @param code
     * @param width
     * @param height
     * @return
     * @throws ImageNotFoundForCodeException
     */
    private synchronized BufferedImage getImageFromCacheOrProvider(SymbolType symbolType, String code, int width,
                                                                   int height) throws ImageNotFoundForCodeException {
        if (code == null)
            code = "BRAK KODU";
        OMLogger.getInstance().debug("Zadanie obrazka dla kodu " + code + " typu " + symbolType);
        BufferedImage image = images.getImageByKey(code, width, height);
        if (image == null) {
            image = provider.getImageForCode(symbolType, code);
            if (image == null) {
                throw new ImageNotFoundForCodeException(symbolType, code);
            } else {
                image = ImageUtils.toBufferedImage(ImageUtils.resize(image, width, height));
                images.putImage(code, image, width, height);
            }
        }
        return image;
    }

}
