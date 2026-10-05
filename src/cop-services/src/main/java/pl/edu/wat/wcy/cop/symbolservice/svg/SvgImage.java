package pl.edu.wat.wcy.cop.symbolservice.svg;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.ImageTranscoder;
import pl.edu.wat.wcy.cop.common.logging.OMLogger;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
// Represents svg image.


public class SvgImage {
    private TranscoderInput input;
    private TranscodingHints hints = new TranscodingHints();
    private URL url;

    public SvgImage(URL url) {
        try {
            this.url = url;
            this.input = new TranscoderInput(url.openStream());
        } catch (IOException e) {
            OMLogger.getInstance().error("Brak pliku dla znaku : " + url);
        }
    }

    public SvgImage(String filename) {
        File f = new File(filename);
        try {
            this.input = new TranscoderInput(f.toURI().toURL().toString());
        } catch (Exception e) {
            OMLogger.getInstance().error(e.getMessage(), e);
        }
    }

    public URL getUrl() {
        return url;
    }

    public void setImageWidth(float width) {
        hints.put(ImageTranscoder.KEY_WIDTH, new Float(width));

    }

    public void setImageHeight(float height) {
        hints.put(ImageTranscoder.KEY_HEIGHT, new Float(height));
    }

    public void setImageDimension(Dimension d) {
        hints.put(ImageTranscoder.KEY_WIDTH, new Float(d.width));
        hints.put(ImageTranscoder.KEY_HEIGHT, new Float(d.height));
    }

    public void setArea(Rectangle2D area) {
        hints.put(ImageTranscoder.KEY_AOI, area);
    }

    public void setBackgroundColor(Paint p) {
        hints.put(ImageTranscoder.KEY_BACKGROUND_COLOR, p);
    }

    public TranscodingHints getHints() {
        return hints;
    }

    public TranscoderInput getInput() {
        return input;
    }

}
