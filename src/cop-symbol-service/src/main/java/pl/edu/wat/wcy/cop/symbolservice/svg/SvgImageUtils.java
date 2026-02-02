/**
 *
 */
package pl.edu.wat.wcy.cop.symbolservice.svg;

import org.apache.batik.transcoder.TranscoderException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
// Provides svg image utilities.


public class SvgImageUtils {
    public static BufferedImage createBufferedImageFromSVGImage(SvgImage image)
            throws TranscoderException, IOException {
        SvgToBufferedImageTranscoder transcoder = new SvgToBufferedImageTranscoder();
        transcoder.getTranscodingHints().putAll(image.getHints());
        transcoder.transcode(image.getInput(), null);
        return transcoder.getImg();
    }

    public static BufferedImage createBufferedImageFromSVGImage(SvgImage image, Dimension d)
            throws TranscoderException, IOException {
        if (d != null) {
            image.setImageDimension(d);
        }
        SvgToBufferedImageTranscoder transcoder = new SvgToBufferedImageTranscoder();
        transcoder.setTranscodingHints(image.getHints());
        transcoder.transcode(image.getInput(), null);
        return transcoder.getImg();
    }
}
