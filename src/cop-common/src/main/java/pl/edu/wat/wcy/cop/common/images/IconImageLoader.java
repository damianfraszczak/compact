package pl.edu.wat.wcy.cop.common.images;

import pl.edu.wat.wcy.cop.common.logging.OMLogger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;


// Loads icon images from resources.
public class IconImageLoader {

    private OMLogger logger = OMLogger.getInstance();

    private Map<String, Map<Rectangle, BufferedImage>> imagesMap = new Hashtable<String, Map<Rectangle, BufferedImage>>();

    public synchronized BufferedImage getImage(URL url, int width, int height, boolean shouldCache) {
        if (url == null)
            return null;
        BufferedImage image = getImageByKey(getKey(url), width, height);
        if (image == null) {
            Map<Rectangle, BufferedImage> images = imagesMap.get(getKey(url));
            if (images == null) {
                images = new Hashtable<Rectangle, BufferedImage>();
                if (shouldCache)
                    imagesMap.put(getKey(url), images);
            }
            Rectangle size = new Rectangle(width, height);
            image = images.get(size);
            if (image == null) {
                image = loadImage(url);
                if (image != null) {
                    image = ImageUtils.toBufferedImage(ImageUtils.resize(image, width, height));
                    if (shouldCache)
                        images.put(size, image);
                }
            }
        }
        return image;
    }

    public BufferedImage getImageByKey(String key, int width, int height) {
        BufferedImage image = null;
        Map<Rectangle, BufferedImage> images = imagesMap.get(key);
        if (images != null) {
            image = images.get(new Rectangle(width, height));
        }
        return image;
    }

    public void putImage(String key, BufferedImage image, int width, int height) {
        Map<Rectangle, BufferedImage> images = imagesMap.get(key);
        if (images == null) {
            images = new Hashtable<Rectangle, BufferedImage>();
            imagesMap.put(key, images);
        }
        images.put(new Rectangle(width, height), image);
    }

    private BufferedImage loadImage(URL url) {
        try {
            return ImageIO.read(url);
        } catch (IOException e) {
            logger.warn("Brak obrazka " + url);
        }
        return null;
    }

    private String getKey(URL url) {
        // TODO Auto-generated method stub
        return url.getPath();
    }
}
