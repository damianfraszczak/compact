package pl.edu.wat.wcy.cop.symbolservice;

import pl.edu.wat.wcy.cop.common.logging.OMLogger;
import pl.edu.wat.wcy.cop.common.resources.ResourceUtils;

import java.net.URL;
// Defines the contract for code to file mapper.



public interface CodeToFileMapper {
    String IMAGE_FILE_EXTENSION = ".png";
    String SVG_IMAGE_FILE_EXTENSION = ".svg";
    String FILE_SEPARATOR = "/";
    String APP6A_PATH = "symbols/app6a";
    String BG_PATH = "symbols/bg";
    String POLICE_PATH = "symbols/police";
    String CUSTOM_PATH = "symbols/custom";
    String RANK_PATH = "ranks";
    String TSO_PATH = "tso";
    String CODE_SEPARATOR = ":";
    String PL_RANK_CODE = "PL";
    String UNIT_PATH = APP6A_PATH + FILE_SEPARATOR + "svg_units" + FILE_SEPARATOR;
    String UNIT_RANK_PATH = APP6A_PATH + FILE_SEPARATOR + "svg_rank" + FILE_SEPARATOR;
    String MOBILITY_PATH = APP6A_PATH + FILE_SEPARATOR + "svg_mobility" + FILE_SEPARATOR;
    String SD = APP6A_PATH + FILE_SEPARATOR + "svg_sd" + FILE_SEPARATOR;

    String BG_PATH_FORMAT = BG_PATH + FILE_SEPARATOR + "%s" + IMAGE_FILE_EXTENSION;
    String POLICE_PATH_FORMAT = POLICE_PATH + FILE_SEPARATOR + "%s" + IMAGE_FILE_EXTENSION;
    String CUSTOM_PATH_FORMAT = CUSTOM_PATH + FILE_SEPARATOR + "%s" + IMAGE_FILE_EXTENSION;

    default URL mapCodeToFileURL(SymbolType symbolType, String code) {
        URL result = null;
        switch (symbolType) {
            case BG:
                OMLogger.getInstance().debug("Mapuje zapytanie o symbol sg " + String.format(BG_PATH_FORMAT, code));
                result = ResourceUtils.getResourceOrFileOrURL(this, String.format(BG_PATH_FORMAT, code));
                break;
            case POLICE:
                OMLogger.getInstance()
                        .debug("Mapuje zapytanie o symbol policji " + String.format(POLICE_PATH_FORMAT, code));
                result = ResourceUtils.getResourceOrFileOrURL(this, String.format(POLICE_PATH_FORMAT, code));
                break;
            case CUSTOM:
                OMLogger.getInstance().debug("Mapuje zapytanie o symbol policji " + String.format(CUSTOM_PATH_FORMAT, code));
                result = ResourceUtils.getResourceOrFileOrURL(this, String.format(CUSTOM_PATH_FORMAT, code));
                break;
            default:
                break;
        }
        return result;
    }

}
