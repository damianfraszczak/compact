/**
 *
 */
package pl.edu.wat.wcy.cop.app.server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.wcy.cop.app.shared.GwtEndpoints;
import pl.edu.wat.wcy.cop.app.shared.response.ErrorCode;
import pl.edu.wat.wcy.cop.common.images.ImageUtils;
import pl.edu.wat.wcy.cop.symbolservice.ImageNotFoundForCodeException;
import pl.edu.wat.wcy.cop.symbolservice.SymbolService;
import pl.edu.wat.wcy.cop.symbolservice.SymbolType;

import javax.inject.Inject;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;


@RestController
// Handles symbol service requests.
public class SymbolServiceController {
    private static final int IMAGE_SIZE = 250;
    private static String DEFAULT_IMAGE_AS_BASE_64 = "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";

    @Inject
    private SymbolService symbolService;

    @RequestMapping(value = GwtEndpoints.GWT_SYMBOL_SERVICE, method = RequestMethod.GET)
    public ResponseEntity<?> getImagesForCodes(@RequestParam("codes") List<String> codes) {
        try {
            return WebUtil.createOkEntity(
                    codes.stream().map(x -> getImageForCode(x, IMAGE_SIZE, IMAGE_SIZE)).collect(Collectors.toList()));
        } catch (Exception ex) {
            return WebUtil.createErrorEntity(HttpStatus.NOT_FOUND, ErrorCode.IMAGE_NOT_FOUND, ex.getMessage());
        }

    }

    public String getImageForCode(String code, int width, int height) throws ImageNotFoundForCodeException {
        return convertImageToBase64String(
                symbolService.getImageForCode(getSymbolTypeForCode(code), code, width, height));
    }

    private String convertImageToBase64String(Image image) {
        if (image != null)
            return ImageUtils.encodeToString(ImageUtils.toBufferedImage(image), ImageUtils.IMAGE_TYPE_PNG);
        else
            return DEFAULT_IMAGE_AS_BASE_64;
    }

    /**
     * @param code
     * @return
     */
    private SymbolType getSymbolTypeForCode(String code) {
        if (code.startsWith("4"))
            return SymbolType.BG;
        else if (code.startsWith("3"))
            return SymbolType.POLICE;
        else if (code.toLowerCase().startsWith("custom"))
            return SymbolType.CUSTOM;
        else
            return SymbolType.APP6A;
    }
}
