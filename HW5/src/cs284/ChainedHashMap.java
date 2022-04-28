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

    private LinkedList<CHMEntry>[] table;
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

        V oldVal = null;

        // Initialize the bucket if it doesn't exist
        if (table[hash] == null || table[hash].isEmpty()) {
            table[hash] = new LinkedList<>();
        }
        // Check if the key already exists in the bucket
        if (containsKey(key)) {
            // Check if the key already exists in the bucket
            for (CHMEntry<K, V> e : table[hash]) {
                if (e.key.equals(key)) {
                    oldVal = e.val;
                    e.val = val;
                    e.key = key;
                    return oldVal;
                }
            }
        } else {
            // Check the current load factor and rehash the table if necessary
            if (rehashIfNecessary()) {
                // Recalculate the hash
                hash = hash(key);

                if (table[hash] == null || table[hash].isEmpty()) {
                    table[hash] = new LinkedList<>();
                }
            }

            // Add the entry to the bucket
            table[hash].add(entry);
            size += 1;
        }
        return oldVal;
    }

    @Override
    public V remove(K key) {
        // Return null if map is empty
        if (size == 0) {
            return null;
        }
        int hash = hash(key);
        // Return null if the bucket is empty
        if (table[hash] == null) {
            return null;
        }
        for (CHMEntry<K, V> entry : table[hash]) {
            if (entry.key.equals(key)) {
                V old = entry.val;
                table[hash].remove(entry);
                size -= 1;

                // Check if bucket is now empty, if so make it null
                if (table[hash].size() == 0) {
                    table[hash] = null;
                }
                return old;
            }
        }
        return null;
    }

    @Override
    // TODO
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {

            private int bucketSizes[] = bucketSizes();
            private int hash = 0;
            private int entryIndex = 0;
            private int count = 0;
            private CHMEntry<K, V> lastVisited = null;

            // Might need to change so advance doesn't modify anything while we're at current entry. The next() method should update the lastVisited instead.
            // Make advance() idempotent: (idem = same, potent = power) -> running it 1 time or 100 times won't do anything different, it gets you to the right spot
            // Invariant would be whether you are at a valid spot or you have reached the end
            private void advance() {
                // Loop through each hash in the table (each index in the underlying array)
//                for (int i = hash; i < table.length; i += 1) {
                while (hash < table.length) {
                    // Loop through each entry in the bucket in table[i]
//                    for (int j = entryIndex; j < bucketSizes[i]; j += 1) {
                    while (entryIndex < bucketSizes[hash]) {
                        // If current bucket is not empty
                        if (bucketSizes[hash] > 0) {
                            lastVisited = table[hash].get(entryIndex);
                            count += 1;
                            entryIndex += 1;
                            return;
                        }
                        entryIndex += 1;
                    }
                    hash += 1;
                    entryIndex = 0;
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

                    public String toString() {
                        return "(K: " + key() + ", V: " + value() + ")";
                    }
                };
            }
        };
    }

    private int hash(K key) {
        int hash = Objects.hashCode(key);
        return Math.abs(hash) % table.length;
    }

    private boolean rehashIfNecessary() {
        double loadFactor = (double) (size + 1) / capacity();
        if (loadFactor > MAX_LOAD_CAPACITY) {
            // Copy buckets from table into a new array with double the capacity
            LinkedList<CHMEntry>[] oldTable = Arrays.copyOf(table, capacity());
            this.table = new LinkedList[capacity() * 2];
            this.size = 0;

            for (LinkedList<CHMEntry> bucket : oldTable) {
                if (bucket != null) {
                    for (CHMEntry<K, V> entry : bucket) {
                        put(entry.key, entry.val);
                    }
                }

            }
            this.rehashes += 1;
            return true;
        } else {
            return false;
        }
    }
}
