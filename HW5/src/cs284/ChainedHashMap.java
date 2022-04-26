package cs284;

import java.util.Iterator;
import java.util.LinkedList;

public class ChainedHashMap<K, V> implements Map<K, V> {

    private LinkedList<Entry<K, V>>[] buckets;

    public ChainedHashMap() {
        buckets = new LinkedList[64];
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int capacity() {
        return buckets.length;
    }

    @Override
    public int rehashes() {
        return 0;
    }

    @Override
    public int[] bucketSizes() {
        int[] sizes = new int[buckets.length];
        for (int i = 0; i < buckets.length; i++) {
            sizes[i] = buckets[i].size();
        }
        return sizes;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public Object put(Object key, Object val) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
