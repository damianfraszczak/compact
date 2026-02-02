package pl.edu.wat.wcy.cop.symbolservice.svg;

import org.apache.batik.transcoder.ErrorHandler;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;
import pl.edu.wat.wcy.cop.common.logging.OMLogger;

import java.awt.*;
import java.awt.image.BufferedImage;
// Represents svg to buffered image transcoder.


public class SvgToBufferedImageTranscoder extends ImageTranscoder {

    BufferedImage img;

    public SvgToBufferedImageTranscoder() {
        super();
        // TODO Auto-generated constructor stub
        setErrorHandler(new ErrorHandler() {

            @Override
            public void warning(TranscoderException arg0) throws TranscoderException {
                OMLogger.getInstance().error("Problem z transkoderem", arg0);

            }

            @Override
            public void fatalError(TranscoderException arg0) throws TranscoderException {

                OMLogger.getInstance().error("Problem z transkoderem", arg0);
            }

            @Override
            public void error(TranscoderException arg0) throws TranscoderException {

                OMLogger.getInstance().error("Problem z transkoderem", arg0);
            }
        });
    }

    public BufferedImage createImage(int w, int h) {

        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        ((Graphics2D) bi.getGraphics()).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        return bi;
    }

    public void writeImage(BufferedImage img, TranscoderOutput output) throws TranscoderException {

        this.img = img;
    }

    public BufferedImage getImg() {
        return img;
    }

}
