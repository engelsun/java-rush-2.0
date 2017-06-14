package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

/**
 * Created by engelsun on 6/14/2017.
 */
public class CachingProxyRetriever implements Retriever {
    private Storage storage;
    private OriginalRetriever originalRetriever;
    private LRUCache<Long, Object> cache;

    public CachingProxyRetriever(Storage storage) {
        this.storage = storage;
        originalRetriever = new OriginalRetriever(storage);
        cache = new LRUCache<>(1);
    }

    @Override
    public Object retrieve(long id) {
        Object retrieve = cache.find(id);
        if (retrieve == null) {
            retrieve = originalRetriever.retrieve(id);
            cache.set(id, retrieve);
        }
        return retrieve;
    }
}
