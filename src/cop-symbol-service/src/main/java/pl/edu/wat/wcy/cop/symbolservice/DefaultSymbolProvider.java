/**
 *
 */
package pl.edu.wat.wcy.cop.symbolservice;

import pl.edu.wat.wcy.cop.common.images.ImageUtils;
import pl.edu.wat.wcy.cop.common.logging.OMLogger;
import pl.edu.wat.wcy.cop.common.resources.ResourceUtils;
import pl.edu.wat.wcy.cop.symbolservice.svg.SvgImage;
import pl.edu.wat.wcy.cop.symbolservice.svg.SvgImageUtils;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Damian Frąszczak
 * @since 14 lut 2017
 */
// Supplies default symbol data.
public class DefaultSymbolProvider extends AbstractSymbolProvider {

    private CodeToFileMapper codeToFileMapper = new CodeToFileMapper() {
    };

    @Override
    protected BufferedImage getImageFromProvider(SymbolType symbolType, String code) {
        if (SymbolType.APP6A.equals(symbolType)) {
            return getApp6aSymbol(code);
        } else
            try {
                URL path = codeToFileMapper.mapCodeToFileURL(symbolType, code);
                OMLogger.getInstance().debug(String.format("Dla kodu %s wyznaczono sciezke %s", code, path));
                return ImageUtils.readImage(path);
            } catch (Exception e) {
                e.printStackTrace();
                OMLogger.getInstance().error("Blad ladowania obrazka dla kodu " + code, e);
                return null;
            }
    }

    /**
     * @param code
     * @return
     */
    private BufferedImage getApp6aSymbol(String code) {
        String tmpCode = code.toLowerCase();
        String unitCode = getCode(tmpCode, 0, 1, 2, 3, 4, 5, 6, 7, 8);

        String mobilityCode = getCode(tmpCode, 1, 10, 11);
        String rankCode = getCode(tmpCode, 1, 10, 11);
        String sdCode = getCode(tmpCode, 1, 10);

        return buildOverlayImages(CodeToFileMapper.UNIT_PATH + unitCode + CodeToFileMapper.SVG_IMAGE_FILE_EXTENSION,
                CodeToFileMapper.MOBILITY_PATH + mobilityCode + CodeToFileMapper.SVG_IMAGE_FILE_EXTENSION,
                CodeToFileMapper.UNIT_RANK_PATH + rankCode + CodeToFileMapper.SVG_IMAGE_FILE_EXTENSION,
                CodeToFileMapper.SD + sdCode + CodeToFileMapper.SVG_IMAGE_FILE_EXTENSION);
    }

    /**
     * @param unitCode
     * @param mobilityCode
     * @param rankCode
     * @param sdCode
     * @return
     */
    private BufferedImage buildOverlayImages(String unitCode, String mobilityCode, String rankCode, String sdCode) {
        BufferedImage result = null;
        result = overlay(result, unitCode);
        result = overlay(result, mobilityCode);
        result = overlay(result, rankCode);
        result = overlay(result, sdCode);
        return result;
    }

    /**
     * @param result
     * @paramcodePath
     * @return
     */
    private BufferedImage overlay(BufferedImage result, String codePath) {
        BufferedImage svgimage;
        try {
            OMLogger.getInstance().debug("Zadanie zaladowanie obrazka SVG " + codePath);
            SvgImage image = new SvgImage(ResourceUtils.getResourceOrFileOrURL(this, codePath));
            svgimage = SvgImageUtils.createBufferedImageFromSVGImage(image);
            if (result == null) {
                result = svgimage;
            } else {
                result = ImageUtils.overlayImages(result, svgimage);
            }
            OMLogger.getInstance().debug("Zaladowano obrazek SVG " + codePath);
        } catch (Exception e) {
        }

        return result;
    }

    /**
     * @param code
     *            indexes
     * @return
     */
    private String getCode(String code, Integer... indexes) {
        StringBuffer buffer = new StringBuffer();
        List<Integer> listIndexes = Arrays.asList(indexes);
        for (int i = 0; i < code.length(); i++) {
            if (listIndexes.contains(i)) {
                buffer.append(code.toCharArray()[i]);
            } else {
                buffer.append('-');
            }

        }
        return buffer.toString();
    }

}
