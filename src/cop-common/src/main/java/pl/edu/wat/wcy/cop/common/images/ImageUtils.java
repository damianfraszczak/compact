package pl.edu.wat.wcy.cop.common.images;

import pl.edu.wat.wcy.cop.common.logging.OMLogger;
import java.util.Base64;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;


// Provides image conversion and manipulation helpers.
public class ImageUtils {

    public static String IMAGE_TYPE_PNG = "png";

    public static String encodeToString(BufferedImage image, String imageType) {
        if (image == null) {
            return null;
        }

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ImageIO.write(image, imageType, bos);
            byte[] imageBytes = bos.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] readImageAsByteArray(Path filepath) {
        try {
            return Files.readAllBytes(filepath);
        } catch (IOException e) {
            OMLogger.getInstance().error("Nie udalo sie utworzyc tablicy bajtow z dostarczonej sciezki", e);
        }
        return null;
    }

    public static BufferedImage imageFromByteArray(byte[] bytes) {
        try {
            return ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            OMLogger.getInstance().error("Nie udalo sie utworzyc obrazka z tablicy bajtow", e);
        }
        return null;
    }

    public static byte[] imageToByteArray(Image image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(toBufferedImage(image), "png", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            OMLogger.getInstance().error("Nie udalo sie otrzymac talicy bajtow z obrazka", e);
        }
        return null;
    }

    public static BufferedImage overlayImages(BufferedImage bgImage, BufferedImage fgImage) {
        if (fgImage.getHeight() > bgImage.getHeight() || fgImage.getWidth() > fgImage.getWidth()) {
            System.err.println("Nakładany obraz jest zbyt duży");
            return null;
        }
        Graphics2D g = bgImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(bgImage, 0, 0, null);
        g.drawImage(fgImage, 0, 0, null);

        g.dispose();
        return bgImage;
    }

    public static BufferedImage createImage(int w, int h) {

        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        ((Graphics2D) bi.getGraphics()).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        return bi;
    }

    public static BufferedImage copyImage(BufferedImage source) {
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }

    public static BufferedImage resizeIfNeeded(BufferedImage image, int maxWidth, int maxHeight) {

        int newWidth = Math.min(maxWidth, image.getWidth());
        int newHeight = Math.min(maxHeight, image.getHeight());
        if (newWidth == maxWidth || newHeight == maxHeight) {
            return toBufferedImage(resize(image, newWidth, newHeight));
        } else
            return image;
    }

    public static ImageIcon resize(ImageIcon icon, int width, int height) {
        if (icon == null)
            return icon;
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    public static Image resize(BufferedImage img, int width, int height) {
        if (img != null)
            return img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return null;
    }

    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        if (img == null) {
            return null;
        }
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }

    public static Icon makeTranslucentIcon(Icon icon, float opacity) {

        if (icon != null) {
            BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = bi.createGraphics();
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
            g.setComposite(ac);
            icon.paintIcon(null, g, 0, 0);
            g.dispose();

            return new ImageIcon(bi);
        } else {
            return null;
        }
    }

    public static AffineTransform findTranslation(AffineTransform at, BufferedImage bi) {
        Point2D p2din, p2dout;

        p2din = new Point2D.Double(0.0, 0.0);
        p2dout = at.transform(p2din, null);
        double ytrans = p2dout.getY();

        p2din = new Point2D.Double(0, bi.getHeight());
        p2dout = at.transform(p2din, null);
        double xtrans = p2dout.getX();

        AffineTransform tat = new AffineTransform();
        tat.translate(-xtrans, -ytrans);
        return tat;
    }

    public static BufferedImage rotate(BufferedImage image, double angle) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }

    private static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }

    // metoda na potrzeby VEMLayer
    public static synchronized BufferedImage readImage(URL url) throws IOException {
        BufferedImage rasterImage = null;
        if (url != null && urlExists(url))
            rasterImage = ImageIO.read(url);
        return rasterImage;
    }

    /**
     * @param url
     * @return
     */
    private static boolean urlExists(URL url) {
        try (InputStream istream = url.openStream()) {
        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
