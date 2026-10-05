package pl.edu.wat.wcy.cop.symbolservice;

// Defines the contract for code to file mapper.



public interface CodeToFileMapper {
    String SVG_IMAGE_FILE_EXTENSION = ".svg";
    String FILE_SEPARATOR = "/";
    String APP6A_PATH = "symbols/app6a";
    String UNIT_PATH = APP6A_PATH + FILE_SEPARATOR + "svg_units" + FILE_SEPARATOR;
    String UNIT_RANK_PATH = APP6A_PATH + FILE_SEPARATOR + "svg_rank" + FILE_SEPARATOR;
    String MOBILITY_PATH = APP6A_PATH + FILE_SEPARATOR + "svg_mobility" + FILE_SEPARATOR;
    String SD = APP6A_PATH + FILE_SEPARATOR + "svg_sd" + FILE_SEPARATOR;
}
