package pl.edu.wat.wcy.cop.symbolservice;

import pl.edu.wat.wcy.cop.common.threads.SynchronizedBuffer;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// Supplies abstract symbol data.


public abstract class AbstractSymbolProvider {

    protected CodeToFileMapper codeToFileMapper = new CodeToFileMapper() {
    };

    private Map<String, SynchronizedBuffer<BufferedImage>> currentlyLoadingImages = new HashMap<String, SynchronizedBuffer<BufferedImage>>();

    private LinkedList<CachedSign> cachedSigns = new LinkedList<CachedSign>();
    private boolean saveInCache = true;
    private ExecutorService executor = Executors.newFixedThreadPool(5);

    private BufferedImage getImage(SymbolType symbolType, final String code) {

        SynchronizedBuffer<BufferedImage> buffer = null;
        synchronized (this) {

            if (!currentlyLoadingImages.containsKey(code)) {
                buffer = new SynchronizedBuffer<BufferedImage>();
                currentlyLoadingImages.put(code, buffer);
                final SynchronizedBuffer<BufferedImage> tmpBuffer = buffer;
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        BufferedImage img = getImageFromProvider(symbolType, code);
                        tmpBuffer.put(img);
                        if (!saveInCache)
                            currentlyLoadingImages.remove(code);
                        else
                            cachedSigns.add(new CachedSign(img, symbolType, code));

                    }
                });
            } else {
                buffer = currentlyLoadingImages.get(code);
            }
        }

        return buffer.take();

    }

    public BufferedImage getImageForCode(SymbolType symbolType, String code) {
        code = code.toLowerCase();
        code = updateCodeForMilitaryUnitOrInstallation(code);
        return getImage(symbolType, code);
    }

    protected abstract BufferedImage getImageFromProvider(SymbolType symbolType, String code);

    public synchronized void clearCache() {
        currentlyLoadingImages.forEach((key, buffer) -> buffer.put(null));
        currentlyLoadingImages.clear();
        cachedSigns.clear();
        System.gc();
    }

    protected boolean isSaveInCache() {
        return saveInCache;
    }

    protected void setSaveInCache(boolean saveInCache) {
        this.saveInCache = saveInCache;
    }

    /**
     * @param code
     * @return
     */
    private String updateCodeForMilitaryUnitOrInstallation(String code) {
        if (code.startsWith("u")) {
            code = "sfgp" + code + "-----";
        } else if (code.startsWith("i")) {
            code = "sfgp" + code + "----";
        }
        return code;
    }
}
