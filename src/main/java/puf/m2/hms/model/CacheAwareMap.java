package puf.m2.hms.model;

import java.util.HashMap;

public class CacheAwareMap<K, V> extends HashMap<K, V> {

    private static final long serialVersionUID = 2056301907098102257L;

    public V put(K key, V value) {
        if (HmsEntity.isCached()) {
            return super.put(key, value);
        }
        return null;
    }

}