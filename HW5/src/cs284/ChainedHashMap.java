/*
    Name: Tyler Seliber
    Honor Pledge: I pledge my honor that I have abided by the Stevens Honor System.
 */

package cs284;

import java.util.*;

public class ChainedHashMap<K, V> implements Map<K, V> {

    private static final double MAX_LOAD_CAPACITY = 0.7;

    static class CHMEntry<K, V> {
        public K key;
        public V val;
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
        int[] sizes = new int[capacity()];
        for (int i = 0; i < capacity(); i += 1) {
            if (table[i] != null) {
                sizes[i] = table[i].size();
            }
        }
        return sizes;
    }

    @Override
    public V get(K key) {
        // Get the hash code of the key
        int hash = hash(key);
        // If there is no bucket at the hash code, return null
        if (table[hash] == null || table[hash].isEmpty()) {
            return null;
        }
        // Iterate through the bucket to find the key
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
        // Get the hash code of the key
        int hash = hash(key);

        // Initialize the bucket if it doesn't exist
        if (table[hash] == null || table[hash].isEmpty()) {
            table[hash] = new LinkedList<>();
        }

        // Check if the key already exists in the bucket
        if (containsKey(key)) {
            // Check if the key already exists in the bucket
            for (CHMEntry<K, V> e : table[hash]) {
                if (e.key.equals(key)) {
                    V oldVal = e.val;
                    e.val = val;
                    e.key = key;
                    // Return the old value
                    return oldVal;
                }
            }
        } else {
            // Check if load factor after addition of entry is too high, rehash the map if necessary
            if (rehashIfNecessary()) {
                // Recalculate the hash for key
                hash = hash(key);
                // Initialize the bucket if it doesn't exist after rehashing
                if (table[hash] == null || table[hash].isEmpty()) {
                    table[hash] = new LinkedList<>();
                }
            }

            // Create the new entry
            CHMEntry<K, V> entry = new CHMEntry<>();
            entry.key = key;
            entry.val = val;

            // Add the entry to the bucket
            table[hash].add(entry);
            size += 1;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int hash = hash(key);
        // Return null if the map or bucket is empty
        if (size == 0 || table[hash] == null || table[hash].isEmpty()) {
            return null;
        }
        // Iterate through the bucket at index hash to find the key
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
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {

            private final int[] bucketSizes = bucketSizes(); // Keep track of the bucket sizes
            private int hash = 0; // Keep track of current array index in table
            private int entryIndex = 0; // Keep track of the current entry's index in the bucket
            private int count = 0; // Keep track of the number of entries iterated through
            private CHMEntry<K, V> lastVisited = null; // Keep track of the last visited entry

            private void advance() {
                // Loop through each hash in the table (each index in the underlying array)
                while (hash < capacity()) {
                    // Loop through each entry in the bucket in table[i]
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
            public boolean hasNext() {
                return count < size;
            }

            @Override
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

    // Get the hash code for a given key
    private int hash(K key) {
        int hash = Objects.hashCode(key);
        return Math.abs(hash) % table.length;
    }

    // Check if load factor is greater than max load factor, if so rehash the
    // Returns true if rehashing occurred, false otherwise
    private boolean rehashIfNecessary() {
        // Calculate load factor before adding a new entry
        double loadFactor = (double) (size + 1) / capacity();

        // Check if we need to rehash
        if (loadFactor > MAX_LOAD_CAPACITY) {

            // Copy buckets from table into a new array with double the capacity
            LinkedList<CHMEntry>[] oldTable = Arrays.copyOf(table, capacity());

            // Reinitialize the table with the new capacity
            this.table = new LinkedList[capacity() * 2];
            this.size = 0;

            // Copy the entries from the old table into the new table
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
