package com.company.dataStructures;

public class BaseHashTable<X, Y> {

    public BaseHashTable(final int capacity) {
        this.capacity = capacity;
        data = new HashEntry[capacity];
        size = 0;
    }

    private class HashEntry<X, Y> {
        private X key;
        private Y value;

        public HashEntry(final X key, final Y value) {
            this.key = key;
            this.value = value;
        }

        public X getKey() {
            return key;
        }

        public void setKey(final X key) {
            this.key = key;
        }

        public Y getValue() {
            return value;
        }

        public void setValue(final Y value) {
            this.value = value;
        }
    }

    private final HashEntry[] data;

    private final int capacity;

    private int size;

    public int size() {
        return size;
    }

    public Y get(final X key) {
        final int hash = calculateHash(key);

        if (data[hash] == null) {
            return null;
        } else {
            return (Y) data[hash].getValue();
        }
    }

    public void put(final X key, final Y value) {
        final int hash = calculateHash(key);
        data[hash] = new HashEntry(key, value);
        size++;
    }

    private int calculateHash(final X key) {
        int hash = key.hashCode() % capacity;
        while (data[hash] != null && !data[hash].getKey().equals(key)) {
            hash = (hash + 1) % capacity;
        }
        return hash;
    }

    public Y delete(final X key) {
        final Y value = get(key);
        if (value != null) {
            int hash = calculateHash(key);
            data[hash] = null;
            size--;
            hash = (hash + 1) % capacity;

            while (data[hash] != null) {
                final HashEntry he = data[hash];
                data[hash] = null;
                put((X) he.getKey(), (Y) he.getValue());
                size--;
                hash = (hash + 1) % capacity;
            }
        }
        return value;
    }

    public boolean hasKey(final X key) {
        final int hash = calculateHash(key);

        if (data[hash] == null) {
            return false;
        } else {
            return data[hash].getKey().equals(key);
        }
    }

    private boolean hasValue(final Y value) {
        for (final HashEntry entry : data) {
            if (entry != null && entry.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

}
