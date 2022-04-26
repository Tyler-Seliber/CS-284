package cs284;

import java.util.*;

public class ChainedHashMap<K, V> implements Map<K, V> {
    private static final double MAX_LOAD_CAPACITY = 0.7;


    static class CHMEntry<K, V> {
        public K key;
        public V val;

        public String toString() {
            return "(K: " + key + ", V: " + val + ")";
        }
    }

    @SuppressWarnings("unchecked") // disable the warnings we'll get from leaving off <>

    private LinkedList<CHMEntry>[] table = new LinkedList[64]; // note that we leave off the <> in a few spots!
    private int size, rehashes;

    public ChainedHashMap() {
        table = new LinkedList[64];
        size = 0;
        rehashes = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return table.length;
    }

    @Override
    public int rehashes() {
        return rehashes;
    }

    @Override
    public int[] bucketSizes() {
        int[] sizes = new int[table.length];
        for (int i = 0; i < table.length; i++) {
            sizes[i] = table[i].size();
        }
        return sizes;
    }

    @Override
    public V get(K key) {
        int hash = hash(key);
        if (table[hash] == null) {
            return null;
        }
        for (CHMEntry<K, V> entry : table[hash]) {
            if (entry.key.equals(key)) {
                return entry.val;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V put(K key, V val) {
        // Check the current load factor and rehash the table if necessary
        rehashIfNecessary();

        int hash = hash(key);
        CHMEntry<K, V> entry = new CHMEntry<>();
        entry.key = key;
        entry.val = val;

        V old = null;

        // Initialize the bucket if it doesn't exist
        if (table[hash] == null) {
            table[hash] = new LinkedList<>();
        }
        // Check if the key already exists in the bucket
        if (containsKey(key)) {
            // Check if the key already exists in the bucket
            for (CHMEntry<K, V> e : table[hash]) {
                if (e.key.equals(key)) {
                    System.out.print("Replacing " + e.toString() + " with " + entry.toString() + "...");
                    old = e.val;
                    e.val = val;
                    e.key = key;
                    return old;
                }
            }
        } else {
            // Add the entry to the bucket
            table[hash].add(entry);
            size++;
        }
        return old;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    // TODO
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<Entry<K, V>>() {
            @Override
            // TODO
            public boolean hasNext() {
                return false;
            }

            @Override
            // TODO
            public Entry<K, V> next() {
                return null;
            }
        };
    }

    private int hash(K key) {
        int hash = Objects.hashCode(key);
        return Math.abs(hash) % table.length;
    }

    private void rehashIfNecessary() {
        double loadFactor = (double) size / table.length;
        if (loadFactor > MAX_LOAD_CAPACITY) {
            // Copy buckets from table into a new array with double the capacity
            this.table = Arrays.copyOf(table, table.length * 2);
            rehashes++;
        }
    }
}
