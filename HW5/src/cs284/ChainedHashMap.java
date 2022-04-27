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
        for (int i = 0; i < table.length; i += 1) {
            if (table[i] != null) {
                sizes[i] = table[i].size();
            }
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
                    old = e.val;
                    e.val = val;
                    e.key = key;
                    return old;
                }
            }
        } else {
            // Check the current load factor and rehash the table if necessary
            rehashIfNecessary();

            // Add the entry to the bucket
            table[hash].add(entry);
            size += 1;
        }
        return old;
    }

    @Override
    public V remove(K key) {
        if (size == 0) {
            return null;
        }
        int hash = hash(key);
        if (table[hash] == null) {
            return null;
        }
        for (CHMEntry<K, V> entry : table[hash]) {
            if (entry.key.equals(key)) {
                V old = entry.val;
                table[hash].remove(entry);
                size -= 1;
                return old;
            }
        }
        return null;
    }

    @Override
    // TODO
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<Entry<K, V>>() {

            private int bucketSizes[] = bucketSizes();
            private int hash = 0;
            private int entryIndex = 0;
            private int count = 0;
            private CHMEntry<K, V> lastVisited = null;

            private void advance() {
                // Loop through each hash in the table (each index in the underlying array)
                for (int i = hash; i < table.length; i += 1) {
                    // Loop through each entry in the bucket in table[i]
                    for (int j = entryIndex; j < bucketSizes[i]; j += 1) {
                        // If current bucket is not empty
                        if (bucketSizes[i] > 0) {
                            hash = i;
                            entryIndex = j;
                            lastVisited = table[i].get(j);
                            count += 1;
                            return;
                        }
                    }
                }
            }
            @Override
            // TODO
            public boolean hasNext() {
                return count < size;
            }

            @Override
            // TODO
            public Entry<K, V> next() {
                advance();
                return new Entry<K, V>() {
                    @Override
                    public K key() {
                        return lastVisited.key;
                    }

                    @Override
                    public V value() {
                        return lastVisited.val;
                    }
                };
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
            rehashes += 1;
        }
    }
}
