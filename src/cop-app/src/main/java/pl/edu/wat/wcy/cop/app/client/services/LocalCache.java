/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.services;

import com.google.gwt.storage.client.Storage;
import com.google.gwt.storage.client.StorageMap;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
// Represents local cache.


public class LocalCache {

    private static LocalCache INSTANCE;
    private Storage cacheStore = Storage.getLocalStorageIfSupported();

    private Map<String, String> cache = new HashMap<>();

    private LocalCache() {
        if (cacheStore != null) {
            cache = new StorageMap(cacheStore);
        } else {
            cache = new HashMap<>();
        }
    }

    public static LocalCache getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocalCache();
        }
        return INSTANCE;
    }

    public String get(String key) {
        return cache.get(key);
    }

    public List<String> get(List<String> keys) {
        return keys.stream().map(x -> get(x)).collect(Collectors.toList());

    }

    public void put(String key, Object value) {
        put(key, value != null ? value.toString() : "");
    }

    public void put(String key, String value) {
        cache.put(key, value);
    }

    public void put(List<String> keys, List<String> values) {
        for (int i = 0; i < keys.size(); i++) {
            put(keys.get(i), values.get(i));
        }

    }

    /**
     * @param code
     * @param result
     */
    public void putResized(String code, String result) {
        put(AppConstants.RESIZED_IMAGE + code, result);
    }

    /**
     *
     * @param code
     * @return
     */
    public String getResized(String code) {
        return get(AppConstants.RESIZED_IMAGE + code);
    }
}
