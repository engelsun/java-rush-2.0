package com.javarush.task.task33.task3310.strategy;

/**
 * Created by engelsun on 6/13/2017.
 */
public class FileStorageStrategy implements StorageStrategy {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long DEFAULT_BUCKET_SIZE_LIMIT = 1000L;
    private int size;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private long maxBucketSize;

    public FileStorageStrategy() {
        for (int i = 0; i < DEFAULT_INITIAL_CAPACITY; i++) {
            table[i] = new FileBucket();
        }
    }

    int hash(Long k) {
        return k.hashCode();
    }

    int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    Entry getEntry(Long key) {
        if (size == 0)
            return null;
        int hash = key != null ? hash(key) : 0;
        FileBucket fileBucket = table[indexFor(hash, table.length)];
        for (Entry entry = fileBucket.getEntry(); entry != null; entry = entry.next) {
            Object k;
            if (entry.hash == hash &&
                    ((k = entry.key) == key || (key != null && key.equals(k)))) {
                return entry;
            }
        }
        return null;
    }

    void resize(int newCapacity) {
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
    }

    void transfer(FileBucket[] newTable) {
        int newCapacity = newTable.length;
        for (FileBucket fb : table) {
            if (fb == null) continue;
            Entry entry = fb.getEntry();
            while (null != entry) {
                Entry next = entry.next;
                int i = indexFor(entry.hash, newCapacity);
                if (newTable[i] == null)
                    newTable[i] = new FileBucket();
                entry.next = newTable[i].getEntry();
                newTable[i] = new FileBucket();
                newTable[i].putEntry(entry);
                entry = next;
            }
            fb.remove();
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        if (table[bucketIndex].getFileSize() > bucketSizeLimit) {
            resize(2 * table.length);
            hash = hash(key);
            bucketIndex = indexFor(hash, table.length);
        }
        createEntry(hash, key, value, bucketIndex);
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry entry = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, entry));
        size++;
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        return getKey(value) != null;
    }

    @Override
    public void put(Long key, String value) {
        int hash = key != null ? hash(key) : 0;
        int bucketIndex = indexFor(hash, table.length);
        addEntry(hash, key, value, bucketIndex);
    }

    @Override
    public Long getKey(String value) {
        for (FileBucket fb : table) {
            if (fb != null) {
                for (Entry entry = fb.getEntry(); entry != null; entry = entry.next) {
                    if (entry.getValue().equals(value))
                        return entry.getKey();
                }
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        if (key == null)
            return null;
        Entry entry = getEntry(key);
        return entry != null ? entry.getValue() : null;
    }
}